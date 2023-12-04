package com.dodo.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.web.IServices.IParentCategoryService;
import com.dodo.web.models.Parentcategory;

@Component
public class ParentCategoryUniqueValidator implements Validator {
	@Autowired
	private IParentCategoryService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Parentcategory.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Parentcategory parentcategory = (Parentcategory) target;

		if (!parentcategory.getParentCategoryName().isBlank()) {
			var unique = service.findByName(parentcategory.getParentCategoryName());

			if(unique!=null) {
				//Check if this is a create command
				if(parentcategory.getParentCategoryId()==null) {
					errors.rejectValue("parentCategoryName", "NotUnique", null, null);
				}else {
					//else phase is an update command
					if(parentcategory.getParentCategoryId()!=unique.getParentCategoryId()) {
						errors.rejectValue("parentCategoryName", "NotUnique", null, null);
					}
				}
			}
		}

	}

}
