package com.dodo.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	//====LOC====
	public List<Category> findByParentcategoryParentCategoryName(String name);
	public List<Category> findByParentcategoryParentCategoryId(int id);
	public List<Category> findByStatus(Boolean status);
	
	public Category findByCategoryName(String name);
	
	@Query("from Category where status is not null")
	public List<Category> findByStatusNotNull();
	
	public List<Category> findByUserUserId(int id);
	//====LOC====
	
}
