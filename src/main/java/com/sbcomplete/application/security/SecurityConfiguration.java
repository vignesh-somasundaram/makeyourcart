package com.sbcomplete.application.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers(new String[]{"/"}).permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/welcomepage")
//                .hasAnyRole("USER").anyRequest().authenticated().and().formLogin()
//                .permitAll().and().logout().permitAll();
////.antMatchers("/products").hasAnyRole("USER")
////                .antMatchers("/findprodbyId").hasAnyRole("USER")
//        http.csrf().disable();
//        http.headers().frameOptions().disable(); //To enable h2 console
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//        authenticationMgr.inMemoryAuthentication().withUser("vignesh").password("{noop}hello")
//                .authorities("ROLE_USER").and().withUser("john").password("{noop}hello")
//                .authorities("ROLE_USER");
//    }
//}

