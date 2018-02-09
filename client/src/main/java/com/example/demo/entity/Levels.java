package com.example.demo.entity;

public class Levels {


	private int Id;

	private String levelCategory;


	private Category category;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getLevelCategory() {
		return levelCategory;
	}

	public void setLevelCategory(String levelCategory) {
		this.levelCategory = levelCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
