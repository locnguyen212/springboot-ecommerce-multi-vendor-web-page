package com.dodo.api.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.dtos.CategoryDto;
import com.dodo.api.models.Category;
import com.dodo.api.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	//====LOC====
	@Override
	public List<CategoryDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<CategoryDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CategoryDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), CategoryDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(CategoryDto dto) {
		try {
			Category model = modelMapper.map(dto, Category.class);
			repository.save(model);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(repository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CategoryDto findByCategoryName(String name) {
		try {
			return modelMapper.map(findByCategoryName(name), CategoryDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public List<CategoryDto> findByStatus(Boolean status) {
		try {
			return modelMapper.map(repository.findByStatus(status), new TypeToken<List<CategoryDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public List<CategoryDto> findByStatusNotNull() {
		try {
			return modelMapper.map(repository.findByStatusNotNull(), new TypeToken<List<CategoryDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public Page<CategoryDto> findPaginated(Pageable pageable, List<CategoryDto> categories) {
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<CategoryDto> list;

        if (categories.size() < startItem) {
        	list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, categories.size());
            list = categories.subList(startItem, toIndex);
        }

        Page<CategoryDto> page
          = new PageImpl<CategoryDto>(list, PageRequest.of(currentPage, pageSize), categories.size());

        return page;
	}
	
	@Override
	public List<CategoryDto> findByUserUserId(int id) {
		try {
			return modelMapper.map(repository.findByUserUserId(id), new TypeToken<List<CategoryDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Page<CategoryDto> test(Pageable pageable) {
		try {
			Page<CategoryDto> dtoPage = repository.test(pageable).map(entity -> modelMapper.map(entity, CategoryDto.class));
			return dtoPage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//====LOC====

}
