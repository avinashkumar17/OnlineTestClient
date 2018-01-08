package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Levels;
import com.example.demo.entity.Category;

import java.util.List;
import java.lang.String;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(nativeQuery = true, value = "select * from category where id=?1")
	public List<Category> findData(int id);
	
}
