package com.yaorange.Controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @description: SpringSecurity认证配置
 * @company: yaorange
 */
@Configuration
@EnableWebSecurity//开启认证
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;//使用自定义方法判断用户
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Bean
    //配置为bean给授权服务器使用
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    由自定义的MyUserDetailsService替换
//    @Override
//    @Bean
//    //配置为bean给授权服务器使用
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }

    @Override
    //配置认证路径，通过认证路径获取token
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**").authorizeRequests()//授权请求
                //配置不拦截的请求，让该请求去获取token
                .antMatchers("/oauth/**").permitAll()//许可
                .and().csrf().disable();//关闭csrf包含，因为java默认开启了csrf保护
    }

    @Override
    //配置用于认证的用户名和密码
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("zhangsan")
//                .password("$2a$10$55bzZYdJuBjZ5XOLrK.G.O3.CIMa2LSDTAGSpaSEOxwPz2cgNw4lK")
//                .roles("admin")
//                .and()
//                .withUser("lisi")
//                .password("$2a$10$55bzZYdJuBjZ5XOLrK.G.O3.CIMa2LSDTAGSpaSEOxwPz2cgNw4lK")
//                .roles("user");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println(encode);
    }
}
