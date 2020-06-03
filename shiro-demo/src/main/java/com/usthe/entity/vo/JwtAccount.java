package com.usthe.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class JwtAccount  implements Serializable {

    /**
     * 令牌id
     */
    private String tokenId;
    /**
     * 客户标识（用户名、账号）
     */
    private String appId;
    /**
     * 签发者(JWT令牌此项有值)
     */
    private String issuer;
    /**
     * 签发时间
     */
    private Date issuedAt;
    /**
     * 接收方(JWT令牌此项有值)
     */
    private String audience;
    /**
     * 访问主张-角色(JWT令牌此项有值)
     */
    private String roles;
    /**
     * 访问主张-资源(JWT令牌此项有值)
     */
    private String perms;
    /**
     * 客户地址
     */
    private String host;

    public JwtAccount() {

    }

    public JwtAccount(String tokenId, String appId, String issuer, Date issuedAt, String audience, String roles, String perms, String host) {
        this.tokenId = tokenId;
        this.appId = appId;
        this.issuer = issuer;
        this.issuedAt = issuedAt;
        this.audience = audience;
        this.roles = roles;
        this.perms = perms;
        this.host = host;
    }
}
