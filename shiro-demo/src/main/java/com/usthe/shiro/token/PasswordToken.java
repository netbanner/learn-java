package com.usthe.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
public class PasswordToken  implements AuthenticationToken {

    private String appId;
    private String password;
    private String timestamp;
    private String host;

    public PasswordToken(String appId, String password, String timestamp, String host) {
        this.appId = appId;
        this.password = password;
        this.timestamp = timestamp;
        this.host = host;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.appId;
    }
}
