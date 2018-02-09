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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
			/*
			 * BufferedInputStream buf=new BufferedInputStream(is); int i=0;
			 * while((i=buf.read())!= -1) { System.out.println("the data "+(char)i);
			 * 
			 * } buf.close();
			 */
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
	public String removecat(int id, String authorization) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/removeCategory";
		String response;
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setRequestProperty("Authorization", authorization);
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = "category=" + id;
			is.write(st.getBytes());
			is.close();
			http.connect();
			// authorization = http.getHeaderField("Authorization");
			/*
			 * BufferedInputStream buf=new BufferedInputStream(is); int i=0;
			 * while((i=buf.read())!= -1) { System.out.println("the data "+(char)i);
			 * 
			 * } buf.close();
			 */
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			response = "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = e.toString();

		} finally {
			http.disconnect();
		}
		return response;

	}

	@Override
	public String editCategory(int id, String category, String authorization) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/editCategory";
		String response;
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setRequestProperty("Authorization", authorization);
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = "id=" + id + "&category=" + category;
			System.out.println("EditCategory : " + st);
			is.write(st.getBytes());
			is.close();
			http.connect();
			// authorization = http.getHeaderField("Authorization");
			/*
			 * BufferedInputStream buf=new BufferedInputStream(is); int i=0;
			 * while((i=buf.read())!= -1) { System.out.println("the data "+(char)i);
			 * 
			 * } buf.close();
			 */
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			response = "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = e.toString();

		} finally {
			http.disconnect();
		}
		return response;
	}

	@Override
	public String getCategory(String authorization) {
		final String uri = "http://localhost:8080/online/getCategory";
		System.out.println("URL " + uri);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Authorization", authorization);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		// System.out.println("Result " + result.getBody());
		return result.getBody();
	}

	@Override
	public String addCategory(String category, String authorization) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/addCategory";
		String response;
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setRequestProperty("Authorization", authorization);
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = "category=" + category;
			System.out.println("EditCategory : " + st);
			is.write(st.getBytes());
			is.close();
			http.connect();
			// authorization = http.getHeaderField("Authorization");
			/*
			 * BufferedInputStream buf=new BufferedInputStream(is); int i=0;
			 * while((i=buf.read())!= -1) { System.out.println("the data "+(char)i);
			 * 
			 * } buf.close();
			 */
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			response = "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = e.toString();

		} finally {
			http.disconnect();
		}
		return response;
	}

	@Override
	public String getLevel(String authorization, int id) {
		final String uri = "http://localhost:8080/online/getLevels/" + id;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Authorization", authorization);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		// System.out.println("Result "+result.getBody());
		return result.getBody();
	}

	@Override
	public String addQuestion(String authorization, String values) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/addQuestion";
		String response;
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("Authorization", authorization);
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = values;
			System.out.println("Add Questions : " + st);
			is.write(st.getBytes());
			is.close();
			http.connect();
			// authorization = http.getHeaderField("Authorization");
			/*
			 * BufferedInputStream buf=new BufferedInputStream(is); int i=0;
			 * while((i=buf.read())!= -1) { System.out.println("the data "+(char)i);
			 * 
			 * } buf.close();
			 */
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			response = "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = e.toString();

		} finally {
			http.disconnect();
		}
		return response;
	}

	@Override
	public String addLevels(String authorization, String values) {
		try {
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", authorization);

			HttpEntity<String> entity = new HttpEntity<String>(values, headers);
			System.out.println("Entity " + entity);
			ResponseEntity<String> result = rest.exchange("http://localhost:8080/online/addLevels", HttpMethod.POST,
					entity, String.class);
			System.out.println("the success  :" + result.getBody());
			return "success";
		} catch (Exception e) {
			return "Error " + e;
		}
	}

	@Override
	public String getAllLevel(String authorization) {
		final String uri = "http://localhost:8080/online/getAllLevels";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Authorization", authorization);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		// System.out.println("Result "+result.getBody());
		return result.getBody();
	}

	@Override
	public String editLevel(int id, String level, String authorization) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/editLevel";
		String response;
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setRequestProperty("Authorization", authorization);
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = "id=" + id + "&levelCategory=" + level;
			System.out.println("EditCategory : " + st);
			is.write(st.getBytes());
			is.close();
			http.connect();
			// authorization = http.getHeaderField("Authorization");
			/*
			 * BufferedInputStream buf=new BufferedInputStream(is); int i=0;
			 * while((i=buf.read())!= -1) { System.out.println("the data "+(char)i);
			 * 
			 * } buf.close();
			 */
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			response = "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = e.toString();

		} finally {
			http.disconnect();
		}
		return response;
	}

	@Override
	public String removeLevel(int id, String authorization) {
		HttpURLConnection http = null;
		String url = "http://localhost:8080/online/removeLevel";
		String response;
		try {
			URL u = new URL(url);
			http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setRequestProperty("Authorization", authorization);
			http.setDoInput(true);
			http.setDoOutput(true);
			OutputStream is = http.getOutputStream();
			String st = "level=" + id;
			is.write(st.getBytes());
			is.close();
			http.connect();
			// authorization = http.getHeaderField("Authorization");
			/*
			 * BufferedInputStream buf=new BufferedInputStream(is); int i=0;
			 * while((i=buf.read())!= -1) { System.out.println("the data "+(char)i);
			 * 
			 * } buf.close();
			 */
			BufferedReader read = new BufferedReader(new InputStreamReader(http.getInputStream()));
			System.out.println("the response " + read.readLine());
			read.close();
			response = "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = e.toString();

		} finally {
			http.disconnect();
		}
		return response;
	}

}
