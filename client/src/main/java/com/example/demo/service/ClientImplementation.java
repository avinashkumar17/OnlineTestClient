package com.example.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Category;

@Service("dao")
public class ClientImplementation implements ClientInterface {

	@Override
	public String signIn(String username, String password) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/login";
		String authorization;  
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setRequestProperty("Accept", "text/plain");
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = "username=" + username + "&password=" + password;
			is.write(st.getBytes());
			is.close();
			http.connect();
			authorization = http.getHeaderField("Authorization");
			/*BufferedInputStream buf=new BufferedInputStream(is);
		    int i=0;
		    while((i=buf.read())!= -1)
		    {
		    	System.out.println("the data "+(char)i);
		    	
		    }
		    buf.close();*/
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			authorization = e.toString();
			
		} finally {
			http.disconnect();
		}
		return authorization;

	}
	
	
	@Override
	public String removecat(int id) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/removeCategory";
		String authorization;  
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = "category=" + id;
			is.write(st.getBytes());
			is.close();
			http.connect();
			//authorization = http.getHeaderField("Authorization");
			/*BufferedInputStream buf=new BufferedInputStream(is);
		    int i=0;
		    while((i=buf.read())!= -1)
		    {
		    	System.out.println("the data "+(char)i);
		    	
		    }
		    buf.close();*/
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			authorization = "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			authorization = e.toString();
			
		} finally {
			http.disconnect();
		}
		return authorization;

	}
	
	@Override
	public String editCategory(int id, String category) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/editCategory";
		String authorization;  
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = "id=" + id+"&category="+category;
			System.out.println("EditCategory : " +st);
			is.write(st.getBytes());
			is.close();
			http.connect();
			//authorization = http.getHeaderField("Authorization");
			/*BufferedInputStream buf=new BufferedInputStream(is);
		    int i=0;
		    while((i=buf.read())!= -1)
		    {
		    	System.out.println("the data "+(char)i);
		    	
		    }
		    buf.close();*/
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			authorization = "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			authorization = e.toString();
			
		} finally {
			http.disconnect();
		}
		return authorization;

	}


	@Override
	public String getCategory() {
		 final String uri = "http://localhost:8080/online/getCategory";
		 	System.out.println("URL "+uri);
		    RestTemplate restTemplate = new RestTemplate();
		    HttpHeaders headers = new HttpHeaders();
		    headers.add("Accept","application/json");
		    //headers.add("", "");
		    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		    
		    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		    
		    System.out.println("Result "+result.getBody());
		    
		    return result.getBody();
	}

}
