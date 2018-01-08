package com.example.demo.controller;

import java.util.ArrayList;

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
	@RequestMapping("index")
	public String index1(Model mode) {
		System.out.println("Test ok 2");
		ArrayList<String> s=new ArrayList<String>();
		s.add("data");
		mode.addAttribute("dat","hello");	
		int i =10;
		float b=(float)i;
			
		System.out.println("the "+b);
		return "index";
	}	
	

}
