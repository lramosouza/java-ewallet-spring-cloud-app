package com.lsouza.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.lsouza.user.security.UserAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private UserAuthenticationEntryPoint authenticationEntryPoint;
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          	 .withUser("admin")
          	 .password("admin")
          	 .roles("ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
			        .antMatchers("/swagger-ui.html","/v1/authenticate")
			        .permitAll()
			        .antMatchers("/v1/**")
			        .authenticated()
			        .and()
			        .httpBasic()
			        .authenticationEntryPoint(authenticationEntryPoint)
			        .and()
			        .sessionManagement()
			        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			        .and()
			        .csrf()
			        .disable();
    }
}