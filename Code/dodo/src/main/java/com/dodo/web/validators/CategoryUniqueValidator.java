package com.dodo.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.web.IServices.ICategoryService;
import com.dodo.web.IServices.IParentCategoryService;
import com.dodo.web.models.Category;
import com.dodo.web.models.Parentcategory;

@Component
public class CategoryUniqueValidator implements Validator {
	@Autowired
	private ICategoryService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Category.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Category category = (Category) target;

		if (!category.getCategoryName().isBlank()) {
			var unique = service.findByCategoryName(category.getCategoryName());

			if(unique!=null) {
				//Check if this is a create command
				if(category.getCategoryId()==null) {
					errors.rejectValue("categoryName", "NotUnique", null, null);
				}else {
					//else phase is an update command
					if(category.getCategoryId()!=unique.getCategoryId()) {
						errors.rejectValue("categoryName", "NotUnique", null, null);
					}
				}
			}
		}

	}

}
