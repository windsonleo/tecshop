package com.tecsoluction.tecshop;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
public class TecshopApplication {
	
	
	
	@Bean
	 public MultipartConfigElement multipartConfigElement() {
	    
		 MultipartConfigFactory factory = new MultipartConfigFactory();
	        factory.setMaxFileSize("5120MB");
	        factory.setMaxRequestSize("5120MB");
	        return factory.createMultipartConfig();
		
		
	 }
	
	 @Bean
	 public StandardServletMultipartResolver multipartResolverServelet() {
	     return new StandardServletMultipartResolver();
	 }
	
	 @Bean
	 public CommonsMultipartResolver multipartResolver() {
		 CommonsMultipartResolver multipart = new CommonsMultipartResolver();
		 multipart.setMaxUploadSize(5 * 1024 * 1024);
		 return multipart;
		 }
	 
	 @Bean 
	 @Order(0) 
	 public MultipartFilter multipartFilter() {
		 MultipartFilter multipartFilter = new MultipartFilter();
		 multipartFilter.setMultipartResolverBeanName("multipartReso‌‌​​lver");
		 return multipartFilter;
		 
	 }

	public static void main(String[] args) {
		SpringApplication.run(TecshopApplication.class, args);
	}
}
