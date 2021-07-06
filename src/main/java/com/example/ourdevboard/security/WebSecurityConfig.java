package com.example.ourdevboard.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
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


    /*
     * 웹 사이트의 페이지가 많아질수록 아래와 같은 설정을 통해 권한 설정 및 접근 페이지 설정 가능
     * 로그인 페이지, 로그인 성공, 로그인 실패 등 설정 가능
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/detail*").hasAnyRole()
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll();

        http.cors().and();
        http.csrf().disable();
    }

}