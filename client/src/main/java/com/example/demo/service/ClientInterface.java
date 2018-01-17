package com.example.demo.service;

public interface ClientInterface {
	String signIn(String username, String password); 
	
	String removecat(int id);
	
	String editCategory(int id, String category);

	String getCategory();
	
	
}
