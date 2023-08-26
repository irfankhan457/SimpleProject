package com.khan.rest.webservices.FirstRestFulWebServices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.rest.webservices.FirstRestFulWebServices.user.Post;

public interface PostRespository extends JpaRepository<Post, Integer> {
	
}
