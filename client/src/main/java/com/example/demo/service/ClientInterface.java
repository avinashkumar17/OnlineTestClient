package com.example.demo.service;

public interface ClientInterface {
	String signIn(String username, String password); 
	
	String removecat(int id, String authorization);
	
	String editCategory(int id, String category, String authorization);
	
	String editLevel(int id, String level, String authorization);
	
	String removeLevel(int id,  String authorization);

	String getCategory(String authorization);
	
	String addCategory(String addCategory, String authorization);
	
	String getLevel(String authorization, int id);
	
	String getAllLevel(String authorization);
	
	String addQuestion(String authorization,String values);
	
	String addLevels(String authorization,String values);
	
}
