package com.dodo.web.IServices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dodo.web.models.Category;
import com.dodo.web.models.Shopowner;
import com.dodo.web.models.User;

public interface ICategoryService {
	//====LOC====
	public List<Category> findAll();
	public List<Category> findByStatus(Boolean status);
	public List<Category> findByStatusNotNull();
	public Category findById(int id);
	public boolean save(Category category);
	public boolean delete(int id);
	
	public Category findByCategoryName(String name);
	
	public List<Category> findByUserUserId(int id);
	
	public Page<Category> findPaginated(Pageable pageable, List<Category> categories);
	//====LOC====
}
