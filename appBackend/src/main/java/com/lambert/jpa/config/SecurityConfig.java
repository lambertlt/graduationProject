package com.lambert.jpa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
//        this.setPassword(new BCryptPasswordEncoder().encode("abc"));
//        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    RoleHierarchy roleHierarchy() {
        // 角色继承
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user > ROLE_tourist");
        return hierarchy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").hasRole("user")
                .antMatchers("/identity/delete", "/classify/create", "/classify/delete", "/classify/update").hasRole("admin")
                .antMatchers("/identity/detail", "/identity/update").hasRole("user")
                .antMatchers("/identity/create", "/classify/findAll").permitAll()
//                .antMatchers("/tourist/**").hasRole("tourist")
                .anyRequest().authenticated() // 任何接口都需要拦截验证权限
                .and()
                .rememberMe()
                // 记住我
                .tokenValiditySeconds(Math.round(60 * 60 * 5)) // 5min内记住密码
                .key("lambert")
                // 密匙
                .and()
                .cors()
                // 做跨域处理
                .and()
                .formLogin()
                .loginPage("/login") // 这个路径默认从 resources/static/ 这个文件下去找
//                .loginProcessingUrl("/doLogin") // 配置之后 /doLogin为默认登陆接口
//                .defaultSuccessUrl("/hello") // 配置之后会重定向到这个接口，并会记录登陆前的url，登陆成功后会跳回之前的url，设置boolean值为true，将会不记录url
                .successHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=UTF-8");
                    Object principal = authentication.getPrincipal();
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(principal));
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = resp.getWriter();
                    out.write(e.getMessage());
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                // 访问 logout 退出登陆状态
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = resp.getWriter();
                    out.write("注销成功");
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=UTF-8");
                            PrintWriter out = resp.getWriter();
                            out.write("尚未登录，请先登录");
                            out.flush();
                            out.close();
                        }
                );
    }
}
