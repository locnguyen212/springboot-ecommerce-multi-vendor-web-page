package com.dodo.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dodo.web.IServices.ICategoryService;
import com.dodo.web.IServices.IParentCategoryService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.models.Category;
import com.dodo.web.models.Shopowner;

@Component
public class ShopownerUniqueValidator implements Validator {
	@Autowired
	private IShopOwnerService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Shopowner.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Shopowner shopowner = (Shopowner) target;

		if (!shopowner.getShopName().isBlank()) {
			var unique = service.findByShopName(shopowner.getShopName());

			if(unique!=null) {
				//Check if this is a create command
				if(shopowner.getOwnerId()==null) {
					errors.rejectValue("shopName", "NotUnique", null, null);
				}else {
					//else phase is an update command
					if(shopowner.getOwnerId()!=unique.getOwnerId()) {
						errors.rejectValue("shopName", "NotUnique", null, null);
					}
				}
			}
		}

	}

}
