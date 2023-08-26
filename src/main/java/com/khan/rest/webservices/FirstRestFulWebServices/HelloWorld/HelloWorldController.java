package com.khan.rest.webservices.FirstRestFulWebServices.HelloWorld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@RequestMapping(method = RequestMethod.GET, path= "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path= "/hello-world2")
	public String helloWorld2() {
		return "Hello World2";
	}
	
	@GetMapping(path= "/hello-world4")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path= "/hello-world-i18n")
	public String helloWorldi18n() {
		//this will provide which language is to pick as its pick from request header
		Locale locale = LocaleContextHolder.getLocale();
		String message = messageSource.getMessage("good.morning.message", null, "Defalt Message", locale);
		return message;
	}
}
