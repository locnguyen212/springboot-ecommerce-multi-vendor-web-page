package com.dodo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Parentcategory;

@Repository
public interface ParentCategoryRepository extends JpaRepository<Parentcategory, Integer> {
	//====LOC====
	public Parentcategory findByParentCategoryName(String name);
	//====LOC====
}
