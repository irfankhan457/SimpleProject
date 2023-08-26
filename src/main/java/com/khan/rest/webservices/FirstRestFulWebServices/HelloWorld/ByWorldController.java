package com.khan.rest.webservices.FirstRestFulWebServices.HelloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/by")
public class ByWorldController {

	@RequestMapping(method = RequestMethod.GET, path= "/hello-world")
	public String helloWorld() {
		return "By World";
	}
	
	@GetMapping(path= "/hello-world2")
	public String helloWorld2() {
		return "By World2";
	}
	
	@GetMapping(path= "/hello-world4")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("By World");
	}
	
	// path parameter
	// /users/{id}/todos/{id}  =>  /users/1/totos/101 
	// /hello-world/path-variable/{name}
	@GetMapping(path = "/hello-world/path-variable/{name}/{gender}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name, @PathVariable String gender) {
		if(gender.equalsIgnoreCase("m")) {
			return new HelloWorldBean(String.format("Hello Mr %s",name));
		} else if(gender.equalsIgnoreCase("f")) {
			return new HelloWorldBean(String.format("Hello Mrs %s",name));
		}
		return new HelloWorldBean(String.format("Hello %s",name));
		
	}
}
