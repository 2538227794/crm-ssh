package com.yaorange.Controller.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaorange.Controller.security.MyAccessDecisionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 资源服务器配置
 * @company: yaorange
 */
@Configuration
@EnableResourceServer//开启服务
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource(name = "accessDecisionManager")
    private MyAccessDecisionManager accessDecisionManager;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //指定资源id，需要一致，用户拿到token需要在这那资源
        resources.resourceId("rid").stateless(true);//自定义权限不足响应数据
        resources.accessDeniedHandler((req, resp, e) -> {
            resp.setContentType("text/html;charset=UTF-8");
            Map<String, Object> respMap = new HashMap<String, Object>();
            respMap.put("status", "1101");
            respMap.put("successMsg", null);
            respMap.put("errorMsg", "权限不足");
            ObjectMapper mapper = new ObjectMapper();
            resp.getWriter().write(mapper.writeValueAsString(respMap));
        });
        //自定义token错误响应
//        resources.authenticationEntryPoint((req,resp,e)->{
//            resp.getWriter().write("token错误");
//        });
//       //自定义token错误响应
//        resources.authenticationEntryPoint(new AuthenticationEntryPoint(){
//            @Override
//            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//            }
//        });
    }

    @Override
    //配置资源和角色
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()// 授权通过以后
                //配置访问指定路径必须拥有的角色权限
//                <security:intercept-url pattern="/secure/super/**" access="ROLE_WE_DONT_HAVE"/>
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").hasRole("user")
                //使用自定义的权限处理器覆盖默认处理器
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//装配自定义的权限处理器
                        return o;
                    }
                })
                //其他请求必须登录后
                .anyRequest().authenticated();
        //authenticated()要求在执行该请求时，必须已经登录了应用(认证的用户)
        //permitAll() 无条件允许访问
        //rememberMe() 如果用户是通过Remember-me功能认证的，就允许访问
    }
}
