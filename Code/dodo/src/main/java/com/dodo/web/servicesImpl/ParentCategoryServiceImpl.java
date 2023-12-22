package com.dodo.web.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IParentCategoryService;
import com.dodo.web.models.Parentcategory;
import com.dodo.web.repositories.ParentCategoryRepository;

@Service
public class ParentCategoryServiceImpl implements IParentCategoryService {

	@Autowired
	private ParentCategoryRepository repository;
	
	//====LOC====
	@Override
	public List<Parentcategory> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Parentcategory findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Parentcategory parentcategory) {
		try {
			repository.save(parentcategory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Parentcategory findByName(String name) {
		try {
			return repository.findByParentCategoryName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	//====LOC====
	
}
