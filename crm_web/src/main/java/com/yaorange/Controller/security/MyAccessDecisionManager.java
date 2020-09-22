package com.yaorange.Controller.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("accessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication：用户的权限
     * @param object               :就是一个资源地址
     * @param collection           请求的资源地址需要的权限集合
     * @return void
     * @throws
     * @description: 判断是否有权限访问该资源
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //通过判断请求的地址和当前用户拥有的模块匹配，来明确是否有权限
        //获取请求地址-> /system/show
        String requestURI = ((FilterInvocation) object).getRequest().getRequestURI();
        System.out.println("用户请求地址：" + requestURI);
        //获取用户的权限模块集合（就是每个模块的url）
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //遍历判断当前请求地址是否存在于用户权限集合中
        for (GrantedAuthority authority : authorities) {
            if (requestURI.startsWith(authority.getAuthority())) {
                System.out.println("匹配成功的权限模块URL：" + authority.getAuthority());
                return;//表示有权限访问
            }
        }
        //没有权限
        throw new AccessDeniedException("没有权限访问");

    }

    public static void main(String[] args) {
        int i = "/sss/system/dept/show".indexOf("/system/dept/");
        boolean b = "/sss/system/dept/show".startsWith("/system/dept/");
        System.out.println(i);
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
