package com.dodo.api.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.api.dtos.UserDto;

@Component
public class UserPasswordValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDto user = (UserDto) target;
		if(user.getPassword().isBlank()) {
			errors.rejectValue("password", "NotBlank",  new Object[]{"password"}, null);
		}

	}

}
