package com.khan.rest.webservices.FirstRestFulWebServices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	@GetMapping("/v1/person")
	public Personv1 getFirstVersionOfPerson() {
		return new Personv1("Irfan Khan");
	}
	
	@GetMapping("/v2/person")
	public Personv2 getSecondVersionOfPerson() {
		return new Personv2(new Name("Irfan", "Khan"));
	}
	
	@GetMapping(path="/person", params="version=1")
	public Personv1 getFirstVersionOfPersonRequestParameter() {
		return new Personv1("Irfan pathan");
	}
	
	@GetMapping(path="/person", params="version=2")
	public Personv2 getSecondVersionOfPersonRequestParameter() {
		return new Personv2(new Name("Irfan", "Khan"));
	}

	@GetMapping(path="/person/header", headers="X-API-VERSION=1")
	public Personv1 getFirstVersionOfPersonHeader() {
		return new Personv1("salman Khan");
	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=2")
	public Personv2 getSecondVersionOfPersonHeader() {
		return new Personv2(new Name("salman", "Khan"));
	}
	
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
	public Personv1 getFirstVersionOfAcceptHeader() {
		return new Personv1("sharuk Khan");
	}
	
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v2+json")
	public Personv2 getSecondVersionOfAcceptHeader() {
		return new Personv2(new Name("sharuk", "Khan"));
	}
	
	
}


