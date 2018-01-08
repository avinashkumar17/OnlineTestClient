package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AdminLogin;
import com.example.demo.entity.Category;
import com.example.demo.entity.Levels;
import com.example.demo.entity.Questions;
import com.example.demo.exception.UserAlreadyExist;

public interface Inter {

	public List<AdminLogin> getAdminDetails(String val);

	public List<Category> saveData(int id);
	
	public List<Category> findCategory();
	
	public List<Questions> findLevel(Levels level);
	
	public List<Levels> showLevel(int id);
	
	public String doSignUp(AdminLogin adminLogin) throws UserAlreadyExist;

}
