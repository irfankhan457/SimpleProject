package com.khan.rest.webservices.FirstRestFulWebServices.user.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.khan.rest.webservices.FirstRestFulWebServices.user.User;
import com.khan.rest.webservices.FirstRestFulWebServices.user.UserDaoService;
import com.khan.rest.webservices.FirstRestFulWebServices.user.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserResourceController {
	
	private UserDaoService userDaoService;
	
	//user constructor injection
	public UserResourceController(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUser() {
		return userDaoService.findAll();
	}
	
	//http://localhost:8080/users
	
	//EntityModel
	//WebMvcLinkBuilder
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id)  {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userDaoService.deleteById(id);
	}
	
}
