package com.example.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

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

}
