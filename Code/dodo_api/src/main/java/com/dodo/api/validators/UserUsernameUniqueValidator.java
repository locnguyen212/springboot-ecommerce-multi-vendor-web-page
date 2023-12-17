package com.dodo.api.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.UserDto;

@Component
public class UserUsernameUniqueValidator implements Validator {
	@Autowired
	private IUserService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserDto.class);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDto user = (UserDto) target;
		if (!user.getUsername().isBlank()) {
			var unique = service.findByUsername(user.getUsername());

			if(unique!=null) {
				//Check if this is a create command
				if(user.getUserId()==null) {
					errors.rejectValue("username", "NotUnique", null, "The value of this field has already been taken.");
				}else {
					//else phase is an update command
					if(user.getUserId()!=unique.getUserId()) {
						errors.rejectValue("username", "NotUnique", null, "The value of this field has already been taken.");
					}
				}
			}
		}

	}

}
