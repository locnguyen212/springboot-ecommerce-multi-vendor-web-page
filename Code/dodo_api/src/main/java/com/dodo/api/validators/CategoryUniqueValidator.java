package com.dodo.api.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.dtos.CategoryDto;

@Component
public class CategoryUniqueValidator implements Validator {
	@Autowired
	private ICategoryService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CategoryDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CategoryDto category = (CategoryDto) target;

		if (!category.getCategoryName().isBlank()) {
			var unique = service.findByCategoryName(category.getCategoryName());

			if(unique!=null) {
				//Check if this is a create command
				if(category.getCategoryId()==null) {
					errors.rejectValue("categoryName", "NotUnique", null, "The value of this field has already been taken.");
				}else {
					//else phase is an update command
					if(category.getCategoryId()!=unique.getCategoryId()) {
						errors.rejectValue("categoryName", "NotUnique", null, "The value of this field has already been taken.");
					}
				}
			}
		}

	}

}
