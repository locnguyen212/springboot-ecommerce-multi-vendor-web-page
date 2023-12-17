package com.dodo.api.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IParentCategoryService;
import com.dodo.api.dtos.ParentcategoryDto;
import com.dodo.api.models.Parentcategory;
import com.dodo.api.repositories.ParentCategoryRepository;

@Service
public class ParentCategoryServiceImpl implements IParentCategoryService {

	@Autowired
	private ParentCategoryRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//====LOC====
	@Override
	public List<ParentcategoryDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<ParentcategoryDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ParentcategoryDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), ParentcategoryDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(ParentcategoryDto dto) {
		try {
			Parentcategory model = modelMapper.map(dto, Parentcategory.class);
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
	public ParentcategoryDto findByName(String name) {
		try {
			return modelMapper.map(repository.findByParentCategoryName(name), ParentcategoryDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	//====LOC====
	
}
