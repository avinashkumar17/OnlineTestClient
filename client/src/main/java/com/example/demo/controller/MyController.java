package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import com.example.demo.service.ClientInterface;

@Controller
public class MyController {

	@Autowired
	ClientInterface dao;
	
	String mAuthorization = "mAuthorization";

	@RequestMapping(value = "login")
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	@ResponseBody
	public String signIn(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request) {
		/*RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		httpHeaders.add("Accept","text/plain");
		MultiValueMap<String, String> multiVal = new LinkedMultiValueMap<String, String>();
		multiVal.set("username", username);
		multiVal.set("password", password);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(
				httpHeaders, multiVal);
		ResponseEntity<String> jsonObj = restTemplate.exchange("http://localhost:8080/online/login",HttpMethod.POST,httpEntity,String.class);
		System.out.println("Authorization value: "+jsonObj.getHeaders().get("Authorization"));
	*/
		String response = dao.signIn(username, password);
		if (!response.startsWith("Bearer")) {
			return "Login Failed.";
		}
		HttpSession session = request.getSession();
		session.setAttribute(mAuthorization, response);
		System.out.println("Autokey : " + decode(response.replaceAll("Bearer ", "")));
		return "successfully loged in";
	}
	
	@RequestMapping(value = "dashboard")
	public String dashBoard(HttpServletRequest request, HttpSession session) {
		System.out.println("dashboard");
		session = request.getSession();
		String dAuthor = (String)session.getAttribute(mAuthorization);
		if(dAuthor.equals(null)) {
			return "UnAuthorized Sign In";	
		}
		
		return "dashboard";	
	}
	
	public String decode(String s) {
	    return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}
	public String encode(String s) {
	    return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
	}

}
