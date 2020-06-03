package com.usthe.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.usthe.entity.vo.Message;
import com.usthe.util.IpUtil;
import com.usthe.util.RequestResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Slf4j
public class PasswordFilter extends AccessControlFilter {

    private StringRedisTemplate redisTemplate;

    private boolean isEncryptPassword;
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)  {

        if(isPaswordTokenGet(request)){
            String tokenKey = UUID.randomUUID().toString();
            String userKey = "";
            try {
                redisTemplate.opsForValue().set("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + userKey.toUpperCase(), tokenKey, 5, TimeUnit.SECONDS);
                // 动态秘钥response返回给前端
                Message message = new Message();
                message.ok(1000, "issued tokenKey success")
                        .addData("tokenKey", tokenKey).addData("userKey", userKey.toUpperCase());
                RequestResponseUtil.responseWrite(JSON.toJSONString(message), response);
            }catch (Exception e){
                log.warn("签发动态秘钥失败"+e.getMessage(),e);
                Message message = new Message();
                message.ok(1000,"issued tokenKey fail");
                RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
            }
            return  false;
        }

        if(isPasswordLoginPost(request)){
            AuthenticationToken authenticationToken;
            try {
                authenticationToken = createPasswordToken(request);
            }catch (Exception e) {
                // response 告知无效请求
                Message message = new Message().error(1111,"error request");
                RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
                return false;
            }

            Subject subject = getSubject(request,response);
            try {
                subject.login(authenticationToken);
                //登录认证成功,进入请求派发json web token url资源内
                return true;
            }catch (AuthenticationException e) {
                log.warn(authenticationToken.getPrincipal()+"::"+e.getMessage());
                // 返回response告诉客户端认证失败
                Message message = new Message().error(1002,"login fail");
                RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
                return false;
            }catch (Exception e) {
                log.error(authenticationToken.getPrincipal()+"::认证异常::"+e.getMessage(),e);
                // 返回response告诉客户端认证失败
                Message message = new Message().error(1002,"login fail");
                RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
                return false;
            }

        }

        // 判断是否为注册请求,若是通过过滤链进入controller注册
        if (isAccountRegisterPost(request)) {
            return true;
        }
        // 之后添加对账户的找回等
        // response 告知无效请求
        Message message = new Message().error(1111,"error request");
        RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);

        return false;
    }

    private boolean isAccountRegisterPost(ServletRequest request){
        return false;
    }

    private AuthenticationToken createPasswordToken(ServletRequest request){
        return null;
    }

    private boolean isPasswordLoginPost(ServletRequest request){
        return false;
    }

    private boolean isPaswordTokenGet(ServletRequest request){
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {

        Subject subject = getSubject(request,response);
        // 如果其已经登录，再此发送登录请求
        //  拒绝，统一交给 onAccessDenied 处理
        return null != subject && subject.isAuthenticated();
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setEncryptPassword(boolean encryptPassword) {
        isEncryptPassword = encryptPassword;
    }
}
