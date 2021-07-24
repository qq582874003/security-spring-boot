package com.michael.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * desc
 *
 * @author wangce 2021-05-20
 * @since 1.0.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置用户信息服务
//    @Override
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return manager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //配置安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //屏蔽CSRF控制，即spring security不再限制CSRF
        http.authorizeRequests()
//                .antMatchers("/r/r1").hasAuthority("p1")
//                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
                .anyRequest().permitAll()//除了/r/**，其他的请求可以访问
                .and()
                .formLogin() //允许表单登录
                .loginPage("/login-view") //指定我们自己的登录页,spring security以重定向方式跳转到/login-view
                .loginProcessingUrl("/login") //指定登录处理的URL，也就是用户名、密码表单提交的目的路径
                .successForwardUrl("/login‐success") //指定登录成功后的跳转URL
                //我们必须允许所有用户访问我们的登录页（例如为验证的用户）
                //这个 formLogin().permitAll() 方法允许任意用户访问基于表单登录的所有的URL。
                .permitAll();
    }
}
