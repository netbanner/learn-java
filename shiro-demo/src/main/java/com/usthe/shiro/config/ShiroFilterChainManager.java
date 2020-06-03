package com.usthe.shiro.config;

import com.usthe.service.AccountService;
import com.usthe.shiro.filter.BonJwtFilter;
import com.usthe.shiro.filter.PasswordFilter;
import com.usthe.shiro.provider.ShiroFilterRulesProvider;
import com.usthe.shiro.rule.RolePermRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ShiroFilterChainManager  {

    private final ShiroFilterRulesProvider shiroFilterRulesProvider;
    private final StringRedisTemplate redisTemplate;
    private final AccountService accountService;

    @Value("${bootshiro.enableEncryptPassword}")
    private boolean isEncryptPassword;

    @Autowired
    public ShiroFilterChainManager(ShiroFilterRulesProvider shiroFilterRulesProvider,StringRedisTemplate redisTemplate,AccountService accountService){
        this.shiroFilterRulesProvider = shiroFilterRulesProvider;
        this.redisTemplate = redisTemplate;
        this.accountService = accountService;
    }

    public Map<String,Filter> initGetFilters() {
        Map<String,Filter> filters = new LinkedHashMap<>();
        PasswordFilter passwordFilter = new PasswordFilter();
        passwordFilter.setRedisTemplate(redisTemplate);
        passwordFilter.setEncryptPassword(isEncryptPassword);
        filters.put("auth",passwordFilter);
        BonJwtFilter jwtFilter = new BonJwtFilter();
        jwtFilter.setRedisTemplate(redisTemplate);
        jwtFilter.setAccountService(accountService);
        filters.put("jwt",jwtFilter);
        return filters;
    }

    /**
     * description 初始化获取过滤链规则
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public Map<String,String> initGetFilterChain() {
        Map<String,String> filterChain = new LinkedHashMap<>();
        // -------------anon 默认过滤器忽略的URL
        List<String> defalutAnon = Arrays.asList("/css/**","/js/**");
        defalutAnon.forEach(ignored -> filterChain.put(ignored,"anon"));
        // -------------auth 默认需要认证过滤器的URL 走auth--PasswordFilter
        List<String> defalutAuth = Arrays.asList("/account/**");
        defalutAuth.forEach(auth -> filterChain.put(auth,"auth"));
        // -------------dynamic 动态URL
        if (shiroFilterRulesProvider != null) {
            List<RolePermRule> rolePermRules = this.shiroFilterRulesProvider.loadRolePermRules();
            if (null != rolePermRules) {
                rolePermRules.forEach(rule -> {
                    StringBuilder chain = rule.toFilterChain();
                    if (null != chain) {
                        filterChain.putIfAbsent(rule.getUrl(),chain.toString());
                    }
                });
            }
        }
        return filterChain;
    }


}
