package com.learn.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	
	/*@GetMapping("/hello-world-i18n/{name}") 
	// need to pass "Accept-Language" value as request header
	// Repeated code to get header in every method 
	public String getHello(@RequestHeader(name="Accept-Language", required=false) Locale locale, @PathVariable String name) {
		return messageSource.getMessage("hello.world.message", null, locale).concat(" "+name);
	}*/
	
	@GetMapping("/hello-world-i18n/{name}") 
	public String getHello(@PathVariable String name) {
		return messageSource.getMessage("hello.world.message", null,
							LocaleContextHolder.getLocale()).concat(" "+name);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
