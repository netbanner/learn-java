package com.usthe.shiro.token;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;

@Slf4j
@Data
public class JwtToken  implements AuthenticationToken {

    /**
     * 用户的标识
     */
    private String appId;
    /**
     * 用户的IP
     */
    private String ipHost;
    /**
     * 设备信息
     */
    private String deviceInfo;
    /**
     * json web token值
     */
    private String jwt;

    public JwtToken(String appId, String ipHost, String deviceInfo, String jwt) {
        this.appId = appId;
        this.ipHost = ipHost;
        this.deviceInfo = deviceInfo;
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return this.appId;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }
}
