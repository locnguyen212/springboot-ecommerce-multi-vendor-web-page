package com.dodo.web.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dodo.web.Interceptors.ShopownerInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	@Autowired
	private ShopownerInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(interceptor)
			.addPathPatterns("/shop-owners/**");
	}
}
