package com.dodo.api.controllers.api.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.dtos.CategoryDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/category")
@Tag(name = "Data Category")
public class CategoryApiController {
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping(value = { "find-all-paginate" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<CategoryDto>> findAllPaginate(
			@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "size", defaultValue = "10") int size
			) {
		try {
			return new ResponseEntity<Page<CategoryDto>>(categoryService.findAll(PageRequest.of(page - 1, size)) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Page<CategoryDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-all" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDto>> findAll() {
		try {
			return new ResponseEntity<List<CategoryDto>>(categoryService.findAll() ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CategoryDto>>(HttpStatus.BAD_REQUEST);
		}

	}
   
	@GetMapping(value = { "find-by-status" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDto>> findByStatus(@RequestParam(value = "status", required = true) Boolean status) {
		try {
			return new ResponseEntity<List<CategoryDto>>(categoryService.findByStatus(status) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CategoryDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-by-status-not-null" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDto>> findByStatusNotNull() {
		try {
			return new ResponseEntity<List<CategoryDto>>(categoryService.findByStatusNotNull() ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CategoryDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> findById(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<CategoryDto>(categoryService.findById(id) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CategoryDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-by-name" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> findByCategoryName(@RequestParam(value = "name", required = true) String name) {
		try {
			return new ResponseEntity<CategoryDto>(categoryService.findByCategoryName(name) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CategoryDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-by-user-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDto>> findByUserUserId(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<List<CategoryDto>>(categoryService.findByUserUserId(id) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CategoryDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
