package com.example.demo.entity;

public class Questions {

	private int id;
	private String question;
	private String answer;
	private String choice_a;
	private String choice_b;
	private String choice_c;
	private String choice_d;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getChoice_a() {
		return choice_a;
	}

	public void setChoice_a(String choice_a) {
		this.choice_a = choice_a;
	}

	public String getChoice_b() {
		return choice_b;
	}

	public void setChoice_b(String choice_b) {
		this.choice_b = choice_b;
	}

	public String getChoice_c() {
		return choice_c;
	}

	public void setChoice_c(String choice_c) {
		this.choice_c = choice_c;
	}

	public String getChoice_d() {
		return choice_d;
	}

	public void setChoice_d(String choice_d) {
		this.choice_d = choice_d;
	}

}
