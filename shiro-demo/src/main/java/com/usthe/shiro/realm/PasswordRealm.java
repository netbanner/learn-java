package com.usthe.shiro.realm;

import com.usthe.entity.vo.Account;
import com.usthe.shiro.provider.AccountProvider;
import com.usthe.shiro.token.PasswordToken;
import com.usthe.util.Md5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class PasswordRealm extends AuthorizingRealm {


    private AccountProvider accountProvider;

    /**
     * description 这里只需要认证登录，成功之后派发 json web token 授权在那里进行
     *
     * @param principalCollection 1
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    public void setAccountProvider(AccountProvider accountProvider) {
        this.accountProvider = accountProvider;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof PasswordToken)) {
            return null;
        }

        if(null==authenticationToken.getPrincipal()||null==authenticationToken.getCredentials()){
            throw new UnknownAccountException();
        }
        String appId = (String)authenticationToken.getPrincipal();
        Account account = accountProvider.loadAccount(appId);
        if (account != null) {
            // 用盐对密码进行MD5加密
            ((PasswordToken) authenticationToken).setPassword(Md5Util.md5(((PasswordToken) authenticationToken).getPassword()+account.getSalt()));
            return new SimpleAuthenticationInfo(appId,account.getPassword(),getName());
        } else {
            return new SimpleAuthenticationInfo(appId,"",getName());
        }

    }


}
