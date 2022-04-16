package com.kirylshreyter.templatenotesapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/api/v1/errors/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(customOidcUserService)
                        )
                )
                .oauth2Login()
                .loginPage("/api/v1/errors/unauthorized");
    }
}
