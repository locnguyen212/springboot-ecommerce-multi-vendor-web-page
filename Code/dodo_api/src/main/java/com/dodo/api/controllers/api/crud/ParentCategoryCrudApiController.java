package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.api.IServices.IParentCategoryService;
import com.dodo.api.dtos.ParentcategoryDto;
import com.dodo.api.helpers.FileHelper;
import com.dodo.api.helpers.ValidateHelper;
import com.dodo.api.validators.ParentCategoryUniqueValidator;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/parent-category")
@Tag(name = "Crud Parent Category")
public class ParentCategoryCrudApiController {
	@Autowired
	IParentCategoryService parentCategoryService;

	@Autowired
	ParentCategoryUniqueValidator uniqueValidator;

	//super admin, admin
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestParam(value = "image", required = true) MultipartFile file, 
			@ModelAttribute("parentCategory") @Valid ParentcategoryDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			uniqueValidator.validate(dto, bindingResult);
			if (file == null || file.getSize() == 0) {
				bindingResult.rejectValue("parentCategoryImagePath", "Upload", null, "Image is required");
			}
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
			
			//file handling
			var fileName = FileHelper.saveImageFile(file);
			//file handling
	
			// create 
			dto.setCreatedAt(new Date());
			dto.setParentCategoryImagePath(fileName);
			// create
			return new ResponseEntity<Object>(new Object() {
				public boolean status = parentCategoryService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin, admin
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			Authentication auth, 
			@RequestParam(value = "image", required = false) MultipartFile file, 
			@ModelAttribute("parentCategory") @Valid ParentcategoryDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			if(dto.getParentCategoryId() == null || parentCategoryService.findById(dto.getParentCategoryId())==null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			} 
			
			uniqueValidator.validate(dto, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
			
			//file handling
			if (file != null || file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				FileHelper.deleteImageFile(dto.getParentCategoryImagePath());
				dto.setParentCategoryImagePath(fileName);
			}
			//file handling
	
			// edit 
			dto.setUpdatedAt(new Date());
			// edit 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = parentCategoryService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(
			Authentication auth, 
			@RequestParam(value = "id", required = true) int id
			) {
		try {
			//validate
			var parentCategory = parentCategoryService.findById(id);
			if(parentCategory == null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			//validate
			
			var stt = parentCategoryService.delete(id);
			if(stt) {
				// delete file
				FileHelper.deleteImageFile(parentCategory.getParentCategoryImagePath());
				// delete file
			}
			
			return new ResponseEntity<Object>(new Object() {
				public boolean status = stt;
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
