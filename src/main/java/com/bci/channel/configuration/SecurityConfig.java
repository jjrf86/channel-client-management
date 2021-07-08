package com.bci.channel.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/channel/v1/client-management/client").permitAll()
                .antMatchers(HttpMethod.GET, "/channel/v1/client-management/**").permitAll()
                .antMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                .antMatchers("/console/**").permitAll()
                .anyRequest().authenticated();
    }
}