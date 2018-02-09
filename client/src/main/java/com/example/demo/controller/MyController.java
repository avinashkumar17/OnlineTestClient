package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.InsertQuestions;
import com.example.demo.entity.Questions;
import com.example.demo.service.ClientInterface;
import com.google.gson.Gson;

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
		session = request.getSession();
		session.setAttribute(mAuthorization, response);
		System.out.println("Auto " + response);
		if (!response.startsWith("Bearer")) {
			return "Login failed";
		}
		return "success";
	}

	@RequestMapping("dashboard")
	public String dashboard(HttpServletRequest request) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		// System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}
		return "dashboard";
	}

	@RequestMapping("getCategory")
	public ResponseEntity<String> getCategory(HttpServletRequest request) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		// System.out.println("GET CATEGORY SessionValue" + sessionValue);
		/*
		 * if (sessionValue == null) { System.out.println("Null SessionValue");
		 * HttpHeaders headers = new HttpHeaders(); headers.add("Location",
		 * "redirect:login"); return new
		 * ResponseEntity<String>(headers,HttpStatus.FOUND); }
		 */

		String mCategory = cli.getCategory(sessionValue.toString());

		return new ResponseEntity<String>(mCategory, null, HttpStatus.OK);
	}

	@RequestMapping("addCategory")
	public String addCategory(HttpServletRequest request, @RequestParam("category") String addCategory) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		System.out.println("Add Category: " + addCategory);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}

		String response = cli.addCategory(addCategory, sessionValue.toString());

		return response;
	}

	@RequestMapping("remove")
	public String removeCategory(int id, HttpServletRequest request) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}
		String response = cli.removecat(id, sessionValue.toString());
		return response;
	}

	@RequestMapping("editCategory")
	public String editCategory(int id, String category, HttpServletRequest request) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}
		System.out.println(id + " : " + category);
		String response = cli.editCategory(id, category, sessionValue.toString());
		return response;
	}

	@RequestMapping("getLevel")
	public ResponseEntity<String> getlevel(HttpServletRequest request, int id) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "redirect:login");
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		}

		String mLevel = cli.getLevel(sessionValue.toString(), id);
		System.out.println("ID LEVEL: " + mLevel);
		return new ResponseEntity<String>(mLevel, null, HttpStatus.OK);
	}

	@RequestMapping(value = "addQuestion", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String addQuestion(@RequestBody String str, HttpServletRequest request) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		System.out.println("SessionValue" + sessionValue);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}
		System.out.println("Response: " + str);
		String response = cli.addQuestion(sessionValue.toString(), str);
		return "success";
	}

	@RequestMapping(value = "submitfile", method = RequestMethod.POST)
	@ResponseBody
	public String submitfile(@RequestParam("id") String id, @RequestParam("levelid") String levelid,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		String resp = "";
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);
		if (sessionValue == null) {
			return "redirect:login";
		}
		if (!file.isEmpty()) {
			// List<InsertQuestions> as = new ArrayList<>();
			List<Questions> lq = new ArrayList<>();
			InsertQuestions ioo = new InsertQuestions();
			try {
				ByteArrayInputStream byt = new ByteArrayInputStream(file.getBytes());
				Workbook workbook = new XSSFWorkbook(byt);
				Sheet datatypeSheet = workbook.getSheetAt(0);
				// Iterator<Row> iterator = datatypeSheet.iterator();
				int noOfColumns = datatypeSheet.getRow(0).getPhysicalNumberOfCells();
				System.out.println("NOOF COLUMNS : " + noOfColumns);
				for (int i = 1; i <= datatypeSheet.getLastRowNum(); i++) {
					if ((datatypeSheet.getRow(i)) != null) {
						Questions q = new Questions();
						for (int j = 0; j < noOfColumns; j++) {

							q.setQuestion(datatypeSheet.getRow(i).getCell(1).toString());
							q.setAnswer(datatypeSheet.getRow(i).getCell(2).toString());
							q.setChoice_a(datatypeSheet.getRow(i).getCell(3).toString());
							q.setChoice_b(datatypeSheet.getRow(i).getCell(4).toString());
							q.setChoice_c(datatypeSheet.getRow(i).getCell(5).toString());
							q.setChoice_d(datatypeSheet.getRow(i).getCell(6).toString());

						}
						lq.add(q);
					}
				}
				System.out.println("FILE ID" + id + " LEVELID" + levelid);
				ioo.setCategoryId(Integer.parseInt(id));
				ioo.setLevelId(Integer.parseInt(levelid));
				ioo.setItems(lq);
				Gson gg = new Gson();
				String json = gg.toJson(ioo);
				System.out.println(json);
				String response = cli.addQuestion(sessionValue.toString(), json);
				resp = response;
			} catch (Exception e) {
				System.out.println("Exception : " + e);
			}
		} else {
			System.out.println("File empty");
			resp = "fails";
		}

		return resp;
	}

	@RequestMapping("addNewLevels")
	public String newLevels(HttpServletRequest request, @RequestParam("prof1") String[] arg) throws JSONException {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);
		if (sessionValue != null) {
			String data = request.getParameter("level");
			JSONObject obj = new JSONObject();
			obj.put("categoryID", data);
			obj.put("levels", arg);
			String resp = cli.addLevels(sessionValue.toString(), obj.toString());
			return "redirect:dashboard?IID=Paris";
		} else {
			return "redirect:login";
		}
	}

	@RequestMapping(value = "getAllLevels", method = RequestMethod.GET)
	public ResponseEntity<String> getAllLevels(HttpServletRequest request) throws JSONException {

		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "redirect:login");
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		}

		String mLevel = cli.getAllLevel(sessionValue.toString());
		return new ResponseEntity<String>(mLevel, null, HttpStatus.OK);

	}

	@RequestMapping("editLevel")
	public String editLevel(@RequestParam("id") int id, @RequestParam("levelCategory") String levelCategory,
			HttpServletRequest request) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		System.out.println("SessionValueEDIT LEVEL " + levelCategory + " " + id);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}
		System.out.println(id + " : " + levelCategory);
		String response = cli.editLevel(id, levelCategory, sessionValue.toString());
		return response;
	}

	@RequestMapping("removeLevel")
	public String removeLevel(@RequestParam("id") int id, HttpServletRequest request) {
		session = request.getSession();
		Object sessionValue = session.getAttribute(mAuthorization);

		System.out.println("Remove LEVEL " + id);
		if (sessionValue == null) {
			System.out.println("Null SessionValue");
			return "redirect:login";
		}

		String response = cli.removeLevel(id, sessionValue.toString());
		return response;
	}
	
	@RequestMapping("logout")
	public String Logout(HttpServletRequest request) {
		 session = request.getSession();
		 session.invalidate();
		return "redirect:login";
	}
}
