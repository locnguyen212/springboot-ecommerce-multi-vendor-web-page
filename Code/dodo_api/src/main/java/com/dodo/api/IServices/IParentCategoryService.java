package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.ParentcategoryDto;

public interface IParentCategoryService {
	//====LOC====
	public List<ParentcategoryDto> findAll();
	public ParentcategoryDto findById(int id);
	public boolean save(ParentcategoryDto parentcategory);
	public boolean delete(int id);
	
	public ParentcategoryDto findByName(String name);
	//====LOC====
}
