package com.example.ourdevboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 가능하게 함
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * 접근정보를 확인할 필요없는 경로 및 파일 설정
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    // 패스워드 인코딩
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 웹 사이트의 페이지가 많아질수록 아래와 같은 설정을 통해 권한 설정 및 접근 페이지 설정 가능
     * 로그인 페이지, 로그인 성공, 로그인 실패 등 설정 가능
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/detail*").hasAnyRole()
                .antMatchers("/", "/api/posts", "/api/posts/*", "/posts/detail*", "/user/login/forbidden",
                        "/user/signup", "/user/login").permitAll()
                // 그 외 모든 요청은 인증과정 필요, 로그인 페이지로 리다이렉트된다
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/user/login/forbidden")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/")
                .failureUrl("/user/login/error")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true) // 로그 아웃시 인증정보를 지우하고 세션을 무효화
                .permitAll();

        http.cors().and();
        http.csrf().disable();
    }

}