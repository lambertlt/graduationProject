package com.lambert.fun.new_app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.serviceImpl.UserServiceImpl;
import com.lambert.fun.new_app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 开启注解控制权限
/**
 * @PreAuthorize：方法执行前进行权限检查
 * @PostAuthorize：方法执行后进行权限检查
 * @Secured：类似于 @PreAuthorize
 * */

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceImpl userServiceImpl;

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
        hierarchy.setHierarchy("ROLE_admin > ROLE_assessor > ROLE_user");
        return hierarchy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
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
//                .antMatchers("/identity/delete", "/classify/create", "/classify/delete", "/classify/update").hasRole("admin")
//                .antMatchers().hasRole("assessor")
//                .antMatchers("/personalColumn/update", "/personalColumn/delete", "/personalColumn/create", "/identity/detail", "/identity/update", "/file/upload", "/file/download", "/file/update", "/file/deleteById", "/file/updateMedia", "/file/findAllMediaByUserId", "/personalColumn/create", "/personalColumn/delete", "/personalColumn/update", "/file/uploadImg").hasRole("user")
//                .antMatchers("/personalColumn/findAll", "/personalColumn/findAllByUserId", "/personalColumn/findById", "/identity/create", "/classify/findAll", "/file/video/player", "/file/findMediaById", "/personalColumn/findAll", "/personalColumn/findAllByUserId", "/personalColumn/findById", "/personalColumn/findAllByClassifyId", "/media/getMediaByArray").permitAll()
//                .antMatchers("/tourist/**").hasRole("tourist")
//                .anyRequest().authenticated() // 任何接口都需要拦截验证权限
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll() // 配置允许通过
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
                    User nowUser = (User) principal;
                    nowUser.setPassword("********");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(Result.ok(nowUser)));
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
//                    out.write("注销成功");
                    out.write(new ObjectMapper().writeValueAsString(Result.ok("注销成功")));
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
//                            out.write("尚未登录，请先登录");
                            out.write(new ObjectMapper().writeValueAsString(Result.ok("尚未登录，请先登录")));
                            out.flush();
                            out.close();
                        }
                );
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // 实现跨域
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(Duration.ofHours(1));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
