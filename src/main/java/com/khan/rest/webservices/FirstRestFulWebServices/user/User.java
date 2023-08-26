package com.khan.rest.webservices.FirstRestFulWebServices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity(name="user_details")
public class User {
	@Id
	@GeneratedValue
	@Column(name= "id")
	@JsonProperty("id")
	private Integer id;
	@Size(min = 2, message = "Name min size is 2 atleast")
	@Column(name= "user_name")
	@JsonProperty("user_name")
	private String name;
	@Past(message = "birthDate should be lessthan from todays date")
	@Column(name= "birth_date")
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "email in not in proepr format")
	@Column(name= "email_id")
	@JsonProperty("email_id")
	private String emailID;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
	
	

	public List<Post> getPosts() {
		return posts;
	}
	public void setPost(List<Post> posts) {
		this.posts = posts;
	}
	protected User() {
		super();
	}
	public User(Integer id, String name, LocalDate birthDate, String emailId) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.emailID = emailId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", emailID=" + emailID + "]";
	}
}
