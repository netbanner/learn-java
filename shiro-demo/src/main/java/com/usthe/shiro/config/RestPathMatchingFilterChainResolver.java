package com.usthe.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Iterator;

@Slf4j
public class RestPathMatchingFilterChainResolver  extends PathMatchingFilterChainResolver {

    private static final int NUM_2 = 2;
    private static final String DEFAULT_PATH_SEPARATOR = "/";




    public RestPathMatchingFilterChainResolver() {
        super();
    }

    public RestPathMatchingFilterChainResolver(FilterConfig filterConfig) {
        super(filterConfig);
    }

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = this.getFilterChainManager();
        if(!filterChainManager.hasChains()){
            return null;
        }else {
            String requestURI = this.getPathWithinApplication(request);
            if(requestURI!=null&&requestURI.endsWith(DEFAULT_PATH_SEPARATOR)){
                requestURI = requestURI.substring(0,requestURI.length()-1);
            }
            Iterator var6 = filterChainManager.getChainNames().iterator();

            String pathPattern;
            boolean flag = true;
            String[] strings = null;
            do {
                if (!var6.hasNext()) {
                    return null;
                }
                pathPattern = (String)var6.next();

                strings = pathPattern.split("==");
                if (strings.length == NUM_2) {
                    // 分割出url+httpMethod,判断httpMethod和request请求的method是否一致,不一致直接false
                    if (WebUtils.toHttp(request).getMethod().toUpperCase().equals(strings[1].toUpperCase())) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                } else {
                    flag = false;
                }
                pathPattern = strings[0];
                if (pathPattern != null && pathPattern.endsWith(DEFAULT_PATH_SEPARATOR)) {
                    pathPattern = pathPattern.substring(0, pathPattern.length() -1);
                }
            } while(!this.pathMatches(pathPattern, requestURI) || flag);

            if(log.isTraceEnabled()){
                log.trace("Matched path pattern [" + pathPattern + "] for requestURI [" + requestURI + "].  Utilizing corresponding filter chain...");
            }
            if (strings.length == NUM_2) {
                pathPattern = pathPattern.concat("==").concat(WebUtils.toHttp(request).getMethod().toUpperCase());
            }

            return  filterChainManager.proxy(originalChain,pathPattern);
        }


    }
}
