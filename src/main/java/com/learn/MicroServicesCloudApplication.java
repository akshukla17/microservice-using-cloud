package com.learn;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class MicroServicesCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesCloudApplication.class, args);
	}

	@Bean
	public LocaleResolver getLocale() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// we'd use because we are passing language as header
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	//we can set this in application file
//	@Bean
//	public ResourceBundleMessageSource bundleMessageSource() {
//		ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();
//		msgSource.setBasename("messages");
//		return msgSource;
//	}
}
