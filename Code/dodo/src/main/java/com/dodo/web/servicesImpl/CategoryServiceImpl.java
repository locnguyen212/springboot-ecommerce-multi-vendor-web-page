package com.dodo.web.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.ICategoryService;
import com.dodo.web.models.Category;
import com.dodo.web.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository repository;

	//====LOC====
	@Override
	public List<Category> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Category findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Category category) {
		try {
			repository.save(category);
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
	public List<Category> findByParentCategoryName(String name) {
		try {
			return repository.findByParentcategoryParentCategoryName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Category> findByParentCategoryId(int id) {
		try {
			return repository.findByParentcategoryParentCategoryId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Category findByCategoryName(String name) {
		try {
			return repository.findByCategoryName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public List<Category> findByStatus(Boolean status) {
		try {
			return repository.findByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public List<Category> findByStatusNotNull() {
		try {
			return repository.findByStatusNotNull();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public Page<Category> findPaginated(Pageable pageable, List<Category> categories) {
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<Category> list;

        if (categories.size() < startItem) {
        	list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, categories.size());
            list = categories.subList(startItem, toIndex);
        }

        Page<Category> page
          = new PageImpl<Category>(list, PageRequest.of(currentPage, pageSize), categories.size());

        return page;
	}
	
	@Override
	public List<Category> findByUserUserId(int id) {
		try {
			return repository.findByUserUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	
	//====LOC====

	@Override
	public List<Category> getAllAndStatus() {	
		try {
			return repository.getAllAndStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}





}
