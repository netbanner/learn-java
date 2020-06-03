package com.usthe.shiro.realm;

import com.usthe.shiro.token.JwtToken;
import com.usthe.util.JsonWebTokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;

public class JwtRealm  extends AuthorizingRealm {

    private static final String JWT = "jwt:";
    private static final int NUM_4 = 4;
    private static final char LEFT = '{';
    private static final char RIGHT = '}';


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(!(authenticationToken instanceof JwtToken)) {
            return null;
        }


        return null;
    }


    /**
     *  获取权限 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       String payload = (String)principalCollection.getPrimaryPrincipal();
       if(payload.startsWith(JWT)&&payload.charAt(NUM_4)==LEFT&&payload.charAt(payload.length()-1)==RIGHT){

           Map<String, Object> payloadMap = JsonWebTokenUtil.readValue(payload.substring(4));
           Set<String> roles = JsonWebTokenUtil.split((String)payloadMap.get("roles"));
           Set<String> permissions = JsonWebTokenUtil.split((String)payloadMap.get("perms"));

           SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
           if(!CollectionUtils.isEmpty(roles)){
               info.setRoles(roles);
           }
           if(!CollectionUtils.isEmpty(permissions)){
               info.setStringPermissions(permissions);
           }
           return info;
       }
       return null;
    }


}
