package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping("getCategory")
	public ResponseEntity<String> getCategory(HttpServletRequest request) {
		/*session= request.getSession();
		 Object sessionValue =session.getAttribute(mAuthorization);
		
		System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}*/
		
		String mCategory = cli.getCategory();
		
		return new ResponseEntity<String>(mCategory, null, HttpStatus.OK);
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
	
	@RequestMapping("remove")
	public String removeCategory(int id, HttpServletRequest request) {
		/*session= request.getSession();
		 Object sessionValue =session.getAttribute(mAuthorization);
		
		System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}*/
		String response = cli.removecat(id);
		return response;
	}
	
	@RequestMapping("editCategory")
	public String editCategory(int id, String category, HttpServletRequest request) {
		/*session= request.getSession();
		 Object sessionValue =session.getAttribute(mAuthorization);
		
		System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}*/
		System.out.println(id+" : "+category);
		String response = cli.editCategory(id, category);
		return response;
	}

}
