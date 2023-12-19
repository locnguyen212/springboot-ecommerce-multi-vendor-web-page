package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.IServices.IParentCategoryService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.CategoryDto;
import com.dodo.api.helpers.ValidateHelper;
import com.dodo.api.validators.CategoryUniqueValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/category")
public class CrudApiController {
	@Autowired
	ICategoryService categoryService;

	@Autowired
	IUserService userService;

	@Autowired
	IParentCategoryService parentCategoryService;

	@Autowired
	CategoryUniqueValidator uniqueValidator;

	//super admin, admin, shop
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> categoryCreate(Authentication auth, @RequestBody @Valid CategoryDto category, BindingResult bindingResult) {
		try {
			// validate
			uniqueValidator.validate(category, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
	
			// create category
			var user = userService.findByUsername(auth.getName());
			category.setStatus(true);
			category.setUserUserId(user.getUserId());
			category.setCreatedAt(new Date());
			// create category
			return new ResponseEntity<Object>(new Object() {
				public boolean status = categoryService.save(category);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin, admin
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> categoryEdit(@RequestBody @Valid CategoryDto category, BindingResult bindingResult) {
		try {
			// validate
			uniqueValidator.validate(category, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
	
			// edit category
			category.setUpdatedAt(new Date());
			// edit category
			return new ResponseEntity<Object>(new Object() {
				public boolean status = categoryService.save(category);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> categoryDelete(Authentication auth, @RequestParam(value = "id", required = true) int id) {
		try {
//			//validate
			String userRole = auth.getAuthorities().iterator().next().toString();
			if (!userRole.contains("SUPER")) {
				return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);  
			}
//			//validate
	
			return new ResponseEntity<Object>(new Object() {
				public boolean status = categoryService.delete(id);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
