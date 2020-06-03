package com.usthe.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account  implements Serializable {

    private String appId;
    private String password;
    private String salt;
}
