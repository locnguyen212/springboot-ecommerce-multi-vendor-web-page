package com.dodo.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.web.IServices.ICategoryService;
import com.dodo.web.IServices.IParentCategoryService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.models.Category;
import com.dodo.web.models.Parentcategory;
import com.dodo.web.models.User;

@Component
public class UserEmailUniqueValidator implements Validator {
	@Autowired
	private IUserService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;

		if (!user.getEmail().isBlank()) {
			var unique = service.findByEmail(user.getEmail());

			if(unique!=null) {
				//Check if this is a create command
				if(user.getUserId()==null) {
					errors.rejectValue("email", "NotUnique", null, null);
				}else {
					//else phase is an update command
					if(user.getUserId()!=unique.getUserId()) {
						errors.rejectValue("email", "NotUnique", null, null);
					}
				}
			}
		}

	}

}
