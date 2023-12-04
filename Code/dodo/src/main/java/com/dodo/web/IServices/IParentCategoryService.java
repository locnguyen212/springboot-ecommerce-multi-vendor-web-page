package com.dodo.web.IServices;

import java.util.List;

import com.dodo.web.models.Category;
import com.dodo.web.models.Parentcategory;

public interface IParentCategoryService {
	//====LOC====
	public List<Parentcategory> findAll();
	public Parentcategory findById(int id);
	public boolean save(Parentcategory parentcategory);
	public boolean delete(int id);
	
	public Parentcategory findByName(String name);
	//====LOC====
}
