package com.yaorange.Controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @description: 授权服务器配置
 * @company: yaorange
 */
@Configuration
@EnableAuthorizationServer//启动授权服务
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    //通过其他类提供
    //用于支持password授权模式：适用于前后端分离
    private AuthenticationManager authenticationManager;

    @Autowired
    //刷新token会使用到对象
    private UserDetailsService userDetailsService;

    @Autowired
    //配置了redis就存在
            RedisConnectionFactory redisConnectionFactory;//通过redis存储token，redis有失效时间

    @Bean
        //用于密码加盐
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//通过该对象的方法进行加密
    }

    @Override
    //设置授权模式
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()//配置在内存中
                .withClient("yaorange")//设置clientId,自定义
                //授权模式有两种：refresh_token（Spring提供，标准的Oauth2中没有该模式）
                .authorizedGrantTypes("password", "refresh_token")//设置授权模式
                .accessTokenValiditySeconds(1800)//设置token的失效时间
                .resourceIds("rid")//资源id
                .scopes("all")//配置作用域
                .secret("$2a$10$55bzZYdJuBjZ5XOLrK.G.O3.CIMa2LSDTAGSpaSEOxwPz2cgNw4lK");//设置密码，需要加密的密码
    }

    @Override
    //配置token存储
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    //配置支持clientId和secret做登录认证
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}
