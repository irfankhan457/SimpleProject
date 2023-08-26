package com.khan.rest.webservices.FirstRestFulWebServices.user.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.khan.rest.webservices.FirstRestFulWebServices.jpa.PostRespository;
import com.khan.rest.webservices.FirstRestFulWebServices.jpa.UserRespository;
import com.khan.rest.webservices.FirstRestFulWebServices.user.Post;
import com.khan.rest.webservices.FirstRestFulWebServices.user.User;
import com.khan.rest.webservices.FirstRestFulWebServices.user.UserDaoService;
import com.khan.rest.webservices.FirstRestFulWebServices.user.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserJPAResourceController {
	
	private UserDaoService userDaoService;
	
	private UserRespository userRespository;
	
	private PostRespository postRespository;
	
	//user constructor injection
	public UserJPAResourceController(UserDaoService userDaoService, UserRespository userRespository, PostRespository postRepository) {
		this.userDaoService = userDaoService;
		this.userRespository = userRespository;
		this.postRespository = postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUser() {
		return userRespository.findAll();
	}
	
	//http://localhost:8080/users
	
	//EntityModel
	//WebMvcLinkBuilder
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id)  {
		Optional<User> user = userRespository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User savedUser = userRespository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Optional<User> user = userRespository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		userRespository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable int id) {
		Optional<User> user = userRespository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		return user.get().getPosts();
	}
	
	/******************************** POST API's****************************************/
	//by given user ID will post the posts
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRespository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		post.setUser(user.get());

		Post savedPost = postRespository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
}
