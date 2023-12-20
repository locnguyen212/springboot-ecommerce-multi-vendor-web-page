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

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/category")
@Tag(name = "Crud Category")
public class CategoryCrudApiController {
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
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestBody @Valid CategoryDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			uniqueValidator.validate(dto, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
	
			// create 
			var userId = userService.findByUsername(auth.getName()).getUserId();
			dto.setStatus(true);
			dto.setUserUserId(userId);
			dto.setCreatedAt(new Date());
			// create 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = categoryService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin, admin
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			@RequestBody @Valid CategoryDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			if(dto.getCategoryId() == null || categoryService.findById(dto.getCategoryId())==null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}		
			
			uniqueValidator.validate(dto, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
	
			// edit 
			dto.setUpdatedAt(new Date());
			// edit 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = categoryService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(
			Authentication auth, 
			@RequestParam(value = "id", required = true) int id
			) {
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = categoryService.delete(id);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
