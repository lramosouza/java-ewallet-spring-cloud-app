package com.lsouza.wallet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lsouza.wallet.config.interceptor.AuthenticationInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor).addPathPatterns("/v1/**");
	}
}