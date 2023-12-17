package com.dodo.api.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.dtos.ShopownerDto;

@Component
public class ShopownerUniqueValidator implements Validator {
	@Autowired
	private IShopOwnerService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ShopownerDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ShopownerDto shopowner = (ShopownerDto) target;

		if (!shopowner.getShopName().isBlank()) {
			var unique = service.findByShopName(shopowner.getShopName());

			if(unique!=null) {
				//Check if this is a create command
				if(shopowner.getOwnerId()==null) {
					errors.rejectValue("shopName", "NotUnique", null, "The value of this field has already been taken.");
				}else {
					//else phase is an update command
					if(shopowner.getOwnerId()!=unique.getOwnerId()) {
						errors.rejectValue("shopName", "NotUnique", null, "The value of this field has already been taken.");
					}
				}
			}
		}

	}

}
