package com.afir.salhy.LO54.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("USER", "ADMIN");

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user")
                .roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(
                        "/index/",
                        "/save", "/edit", "/delete")
                .hasRole("ADMIN");

        http.authorizeRequests()
                .antMatchers("/index/", "/form",
                        "/save", "/add")
                .hasRole("USER");

        //En fonction des rôles (admin / user), user n'a pas accès
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
