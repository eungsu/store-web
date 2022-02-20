package com.example.storeweb.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecuritySonfig extends WebSecurityConfigurerAdapter {

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
        .authorizeRequests()
        .antMatchers("/", "/register", "/completed").permitAll()
        .anyRequest().authenticated();
   }

   @Override
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/resources/**", "/h2-console/**");
   }
}
