package com.example.demo.entity;

import java.util.List;

public class InsertQuestions {
	
	private int id;
	private int levelId;
	private List<Questions> items;
	
	public InsertQuestions(int id, int levelId, List<Questions> items) {
		super();
		this.id = id;
		this.levelId = levelId;
		this.items = items;
	}
	public InsertQuestions() {
		// TODO Auto-generated constructor stub
	}
	public int getCategoryId() {
		return id;
	}
	public void setCategoryId(int id) {
		this.id = id;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public List<Questions> getItems() {
		return items;
	}
	public void setItems(List<Questions> items) {
		this.items = items;
	}
	
	

}
