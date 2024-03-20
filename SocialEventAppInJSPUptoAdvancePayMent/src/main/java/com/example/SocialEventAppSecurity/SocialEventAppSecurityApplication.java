package com.example.SocialEventAppSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class SocialEventAppSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialEventAppSecurityApplication.class, args);
	}
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource resourceBundleMessageSource(){
		ResourceBundleMessageSource resourceBundleMessage=new ResourceBundleMessageSource();
		resourceBundleMessage.setBasename("message");
		return resourceBundleMessage;
	}
}
