package com.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.blog.dto.User;

import com.blog.dto.Admin;
import com.blog.dto.Post;

@RestController
public class BlogController {

	@Autowired
	private RestTemplate restTemplate;

	private final String USER_API = "https://jsonplaceholder.typicode.com/users";
	private final String POST_API = "http://jsonplaceholder.typicode.com/posts";


	@GetMapping("/users/{id}")
	public User consumeUser(@PathVariable Integer id) {
		return restTemplate.getForObject(USER_API + "/{id}", User.class, id);
	}

	@GetMapping("/users")
	public User[] consumeAllUser() {
		return restTemplate.getForObject(USER_API, User[].class);

	}
	
	@GetMapping("/posts/{id}")
	public Post consumePost(@PathVariable Integer id) {
		return restTemplate.getForObject(POST_API + "/{id}", Post.class, id);
	}
	
	@GetMapping("/posts")
	public Post[] consumeAllPost() {
		return restTemplate.getForObject(POST_API, Post[].class);

	}
	
	@GetMapping("/admin")
	public List<Admin> consumeAllAdmin() {
		List<Admin> list = new ArrayList<>();
		User[] users = restTemplate.getForObject(USER_API, User[].class);
		for(User user : users) {
			Integer userId = user.getId();
			Post[] posts = restTemplate.getForObject(POST_API+"?userId="+userId, Post[].class);
			Admin admin = new Admin(user, posts);	
			list.add(admin);
		}
		return list;	
	}
	
	@GetMapping("/admin/{userId}")
	public Admin consumeAdmin(@PathVariable Integer userId) {
		if(userId > 0 && userId < 11) {
			User user = restTemplate.getForObject(USER_API + "/{userId}", User.class, userId);
			Post[] posts = restTemplate.getForObject(POST_API+"?userId="+userId, Post[].class);
			Admin admin = new Admin(user, posts);	
			return admin;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
		   
		
	}		

}

