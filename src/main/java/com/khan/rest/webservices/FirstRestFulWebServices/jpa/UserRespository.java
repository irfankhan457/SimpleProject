package com.khan.rest.webservices.FirstRestFulWebServices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.rest.webservices.FirstRestFulWebServices.user.User;

public interface UserRespository extends JpaRepository<User, Integer> {
	
}
