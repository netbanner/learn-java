package com.usthe.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.usthe.entity.vo.Message;
import com.usthe.service.AccountService;
import com.usthe.shiro.token.JwtToken;
import com.usthe.util.IpUtil;
import com.usthe.util.JsonWebTokenUtil;
import com.usthe.util.RequestResponseUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.data.redis.core.StringRedisTemplate;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Slf4j
public class BonJwtFilter extends AbstractPathMatchingFilter {

    private static final String STR_EXPIRED = "expiredJwt";


    private StringRedisTemplate redisTemplate;
    private AccountService accountService;
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object obj) throws Exception {
        Subject subject = getSubject(request,response);

        //记录调用api日志到数据库
 /*       LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog(WebUtils.toHttp(servletRequest).getHeader("appId"),
                WebUtils.toHttp(servletRequest).getRequestURI(),WebUtils.toHttp(servletRequest).getMethod(),(short)1,null));*/

        boolean isJwtPost = (null != subject && !subject.isAuthenticated()) && isJwtSubmission(request);
        // 判断是否为JWT认证请求
        if (isJwtPost) {
            AuthenticationToken token = createJwtToken(request);
            try {
                subject.login(token);
                return this.checkRoles(subject,obj);
            }catch (AuthenticationException e) {

                // 如果是JWT过期
                if (STR_EXPIRED.equals(e.getMessage())) {
                    // 这里初始方案先抛出令牌过期，之后设计为在Redis中查询当前appId对应令牌，其设置的过期时间是JWT的两倍，此作为JWT的refresh时间
                    // 当JWT的有效时间过期后，查询其refresh时间，refresh时间有效即重新派发新的JWT给客户端，
                    // refresh也过期则告知客户端JWT时间过期重新认证

                    // 当存储在redis的JWT没有过期，即refresh time 没有过期
                    String appId = WebUtils.toHttp(request).getHeader("appId");
                    String jwt = WebUtils.toHttp(request).getHeader("authorization");
                    String refreshJwt = redisTemplate.opsForValue().get("JWT-SESSION-"+appId);
                    if (null != refreshJwt && refreshJwt.equals(jwt)) {
                        // 重新申请新的JWT
                        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
                        String roles = accountService.loadAccountRole(appId);
                        //seconds为单位,10 hours
                        long refreshPeriodTime = 36000L;
                        String newJwt = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(),appId,
                                "token-server",refreshPeriodTime >> 1,roles,null, SignatureAlgorithm.HS512);
                        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
                        redisTemplate.opsForValue().set("JWT-SESSION-"+appId,newJwt,refreshPeriodTime, TimeUnit.SECONDS);
                        Message message = new Message().ok(1005,"new jwt").addData("jwt",newJwt);
                        RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
                        return false;
                    }else {
                        // jwt时间失效过期,jwt refresh time失效 返回jwt过期客户端重新登录
                        Message message = new Message().error(1006,"expired jwt");
                        RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
                        return false;
                    }

                }
                // 其他的判断为JWT错误无效
                Message message = new Message().error(1007,"error Jwt");
                RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
                return false;

            }catch (Exception e) {
                // 其他错误
                log.error(IpUtil.getIpFromRequest(WebUtils.toHttp(request))+"--JWT认证失败"+e.getMessage(),e);
                // 告知客户端JWT错误1005,需重新登录申请jwt
                Message message = new Message().error(1007,"error jwt");
                RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
                return false;
            }
        }else {
            // 请求未携带jwt 判断为无效请求
            Message message = new Message().error(1111,"error request");
            RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
            return false;
        }
}

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request,response);
        // 未认证的情况上面已经处理  这里处理未授权
        if (subject != null && subject.isAuthenticated()){
            //  已经认证但未授权的情况
            // 告知客户端JWT没有权限访问此资源
            Message message = new Message().error(1008,"no permission");
            RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
        }
        return false;
    }

    private boolean isJwtSubmission(ServletRequest request) {

        String jwt = RequestResponseUtil.getHeader(request,"authorization");
        String appId = RequestResponseUtil.getHeader(request,"appId");
        return (request instanceof HttpServletRequest)
                && !StringUtils.isEmpty(jwt)
                && !StringUtils.isEmpty(appId);
    }

    private AuthenticationToken createJwtToken(ServletRequest request) {

        Map<String,String> maps = RequestResponseUtil.getRequestHeaders(request);
        String appId = maps.get("appId");
        String ipHost = request.getRemoteAddr();
        String jwt = maps.get("authorization");
        String deviceInfo = maps.get("deviceInfo");

        return new JwtToken(ipHost,deviceInfo,jwt,appId);
    }

    /**
     * description 验证当前用户是否属于mappedValue任意一个角色
     *
     * @param subject 1
     * @param mappedValue 2
     * @return boolean
     */
    private boolean checkRoles(Subject subject, Object mappedValue){
        String[] rolesArray = (String[]) mappedValue;
        return rolesArray == null || rolesArray.length == 0 || Stream.of(rolesArray).anyMatch(role -> subject.hasRole(role.trim()));
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
