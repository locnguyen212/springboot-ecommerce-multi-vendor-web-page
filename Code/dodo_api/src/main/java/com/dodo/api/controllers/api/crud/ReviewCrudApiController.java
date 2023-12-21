package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.api.IServices.IReviewService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.ReviewDto;
import com.dodo.api.helpers.FileHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/review")
@Tag(name = "Crud Review")
public class ReviewCrudApiController {
	@Autowired
	IReviewService reviewService;
	
	@Autowired
	IUserService userService;

	//shop, user
	@Operation(description = "Form Data Only, Cannot Send JSON; Role: user, shop")
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestParam(value = "image", required = false) MultipartFile file, 
			@ModelAttribute("review") @Valid ReviewDto dto, 
			BindingResult bindingResult
			) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			
			//file handling
			if (file != null && file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				dto.setReviewImage(fileName);
			}
			//file handling
	
			// create 
			dto.setUserUserId(userId);;
			dto.setCreatedAt(new Date());
			// create 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = reviewService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
