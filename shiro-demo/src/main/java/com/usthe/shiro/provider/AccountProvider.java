package com.usthe.shiro.provider;

import com.usthe.entity.vo.Account;

public interface AccountProvider {

    Account loadAccount(String appId);
}
