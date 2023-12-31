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

import com.dodo.api.IServices.IPromotionService;
import com.dodo.api.dtos.PromotionDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/promotion")
@Tag(name = "Data Promotion")
public class PromotionApiController {
	@Autowired
	IPromotionService promotionService;

	//allow all
	@Operation(summary = "Role: allow anonymous")
	@GetMapping(value = { "find-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PromotionDto> findById(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<PromotionDto>(promotionService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PromotionDto>(HttpStatus.BAD_REQUEST);
		}

	}

	//allow all
	@Operation(summary = "Role: allow anonymous")
	@GetMapping(value = { "find-by-product-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PromotionDto>> findByProductProductId(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<List<PromotionDto>>(promotionService.findByProductProductId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PromotionDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@Operation(summary = "Role: allow anonymous")
	@GetMapping(value = { "find-by-current-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PromotionDto>> findByShopownerOwnerId(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<List<PromotionDto>>(promotionService.findByShopownerOwnerId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PromotionDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
