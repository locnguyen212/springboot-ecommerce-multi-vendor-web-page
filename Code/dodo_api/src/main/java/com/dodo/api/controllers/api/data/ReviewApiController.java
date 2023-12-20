package com.dodo.api.controllers.api.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IReviewService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.ReviewDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/review")
@Tag(name = "Data Review")
public class ReviewApiController {
	@Autowired
	IReviewService reviewService;

	@Autowired
	IUserService userService;

	//allow all
	@GetMapping(value = { "find-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReviewDto> findById(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<ReviewDto>(reviewService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ReviewDto>(HttpStatus.BAD_REQUEST);
		}

	}

	//allow all
	@GetMapping(value = { "find-by-product-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReviewDto>> findByProductProductId(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<List<ReviewDto>>(reviewService.findByProductProductId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ReviewDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-by-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReviewDto>> findByUserUserId(Authentication auth) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<ReviewDto>>(reviewService.findByUserUserId(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ReviewDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-by-shop-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReviewDto>> findByShopId(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<List<ReviewDto>>(reviewService.findByShopId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ReviewDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
