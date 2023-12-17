package com.dodo.api.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.api.IServices.IParentCategoryService;
import com.dodo.api.dtos.ParentcategoryDto;

@Component
public class ParentCategoryUniqueValidator implements Validator {
	@Autowired
	private IParentCategoryService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ParentcategoryDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ParentcategoryDto parentcategory = (ParentcategoryDto) target;

		if (!parentcategory.getParentCategoryName().isBlank()) {
			var unique = service.findByName(parentcategory.getParentCategoryName());

			if(unique!=null) {
				//Check if this is a create command
				if(parentcategory.getParentCategoryId()==null) {
					errors.rejectValue("parentCategoryName", "NotUnique", null, "The value of this field has already been taken.");
				}else {
					//else phase is an update command
					if(parentcategory.getParentCategoryId()!=unique.getParentCategoryId()) {
						errors.rejectValue("parentCategoryName", "NotUnique", null, "The value of this field has already been taken.");
					}
				}
			}
		}

	}

}
