package com.tsri.stock;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BootConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000")
			//.allowedOriginPatterns("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowCredentials(true);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) { 
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.addViewController("/login").setViewName("forward:/index.html");
	}	

}
