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
public class UserPasswordValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		if(user.getPassword().isBlank()) {
			errors.rejectValue("password", "NotBlank",  new Object[]{"password"}, null);
		}

	}

}
