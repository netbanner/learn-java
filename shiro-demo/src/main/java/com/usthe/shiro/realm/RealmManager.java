package com.usthe.shiro.realm;

import com.usthe.shiro.matcher.JwtMatcher;
import com.usthe.shiro.token.JwtToken;
import com.usthe.shiro.token.PasswordToken;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import com.usthe.shiro.provider.AccountProvider;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RealmManager {

    private PasswordMatcher passwordMatcher;
    private JwtMatcher jwtMatcher;
    private AccountProvider accountProvider;


    @Autowired
    public RealmManager(AccountProvider accountProvider,PasswordMatcher passwordMatcher,JwtMatcher jwtMatcher) {
        this.accountProvider = accountProvider;
        this.passwordMatcher = passwordMatcher;
        this.jwtMatcher = jwtMatcher;
    }

    public List<Realm>  getInitRealms(){
        List<Realm> realmList = new LinkedList<>();
        // ----- password
        PasswordRealm passwordRealm = new PasswordRealm();
        passwordRealm.setAccountProvider(accountProvider);
        passwordRealm.setCredentialsMatcher(passwordMatcher);
        passwordRealm.setAuthenticationTokenClass(PasswordToken.class);
        realmList.add(passwordRealm);
        // ----- jwt
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCredentialsMatcher(jwtMatcher);
        jwtRealm.setAuthenticationTokenClass(JwtToken.class);
        realmList.add(jwtRealm);
        return Collections.unmodifiableList(realmList);
    }
}
