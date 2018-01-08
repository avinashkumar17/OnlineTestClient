package com.example.demo.controller;

import java.util.ArrayList;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

=======
>>>>>>> 1c82d3efe3f147d2dfeb79f43684926af1804883
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

	@RequestMapping("login")
	public String index() {
		System.out.println("Test ok ");
		return "login";
	}
	
	

}
