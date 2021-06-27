package com.blog.dto;

public class Admin {

	private User user;
	
	private Post[] posts;	
	
	public Admin(User user, Post[] posts) {
		this.posts = posts;
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Post[] getPosts() {
		return posts;
	}

	public void setPosts(Post[] posts) {
		this.posts = posts;
	}
	
}
