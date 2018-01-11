package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ClientInterface;

@Controller
public class MyController {

	@Autowired
	ClientInterface cli;
	
	HttpSession session;

	String mAuthorization = "Auth";

	@RequestMapping("login")
	public String index() {
		return "login";
	}

	@RequestMapping("signIn")
	public @ResponseBody String signIn(String username, String password, HttpServletRequest request) {
		String response = cli.signIn(username, password);
		session= request.getSession();
		session.setAttribute(mAuthorization, response);
		System.out.println("Auto " + response);
		if (!response.startsWith("Bearer")) {
			return "Login failed";
		}
		return "success";
	}

	@RequestMapping("dashboard")
	public String dashboard(HttpServletRequest request) {
		/*session= request.getSession();
		 Object sessionValue =session.getAttribute(mAuthorization);
		
		System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}*/
		return "dashboard";
	}
	
	
	@RequestMapping("addCategory")
	public String addCategory(String addCategory, HttpServletRequest request) {
		/*session= request.getSession();
		 Object sessionValue =session.getAttribute(mAuthorization);
		
		System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}*/
		
		
		
		return "";
	}

}
