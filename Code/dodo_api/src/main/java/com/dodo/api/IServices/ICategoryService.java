package com.dodo.api.IServices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dodo.api.dtos.CategoryDto;

public interface ICategoryService {
	//====LOC====
	public List<CategoryDto> findAll();
	public List<CategoryDto> findByStatus(Boolean status);
	public List<CategoryDto> findByStatusNotNull();
	public CategoryDto findById(int id);
	public boolean save(CategoryDto category);
	public boolean delete(int id);

	public CategoryDto findByCategoryName(String name);
	
	public List<CategoryDto> findByUserUserId(int id);
	
	public Page<CategoryDto> findPaginated(Pageable pageable, List<CategoryDto> categories);
	
	public Page<CategoryDto> test(Pageable pageable);
	//====LOC====
}
