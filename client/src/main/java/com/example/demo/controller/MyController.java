package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

	@RequestMapping("login")
	public String index() {
		System.out.println("Test ok ");
		return "login";
	}

}
