package com.osl.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.osl.interceptor.AuthorCtrlInterceptor;

@Configuration
public class AuthorCtrlConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//这里可以添加多个拦截器
		registry.addInterceptor(new AuthorCtrlInterceptor()).addPathPatterns("/**").excludePathPatterns("/error");
	}
}
