package com.dodo.api.controllers.api.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IParentCategoryService;
import com.dodo.api.dtos.ParentcategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/parent-category")
@Tag(name = "Data Parent Category")
public class ParentCategoryApiController {
	@Autowired
	IParentCategoryService parentCategoryService;

	//admin, super admin
	@Operation(summary = "Role: super admin, admin")
	@GetMapping(value = { "find-by-name" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParentcategoryDto> findByName(
			@RequestParam(value = "name", required = true) String name
			) {
		try {
			return new ResponseEntity<ParentcategoryDto>(parentCategoryService.findByName(name), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ParentcategoryDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//admin, super admin
	@Operation(summary = "Role: super admin, admin")
	@GetMapping(value = { "find-all" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ParentcategoryDto>> findByName() {
		try {
			return new ResponseEntity<List<ParentcategoryDto>>(parentCategoryService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ParentcategoryDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
