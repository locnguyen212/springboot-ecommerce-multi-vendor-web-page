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

import com.dodo.api.IServices.IPromotionService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.dtos.CategoryDto;
import com.dodo.api.dtos.PromotionDto;
import com.dodo.api.helpers.ValidateHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/promotion")
public class PromotionCrudApiController {
	@Autowired
	IPromotionService promotionService;
	
	@Autowired
	IShopOwnerService ownerService;

	//shop
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestBody @Valid PromotionDto dto, 
			BindingResult bindingResult
			) {
		try {
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			// validate
			if(dto.getStartDate()!=null && dto.getEndDate()!=null) {
				if(dto.getStartDate().compareTo(dto.getEndDate())>0) {
					bindingResult.rejectValue("startDate", "StartDate");
					bindingResult.rejectValue("endDate", "EndDate");
				}
			}
			
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
	
			// create 
			dto.setShopownerOwnerId(shopId);
			dto.setCreatedAt(new Date());
			// create 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = promotionService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			Authentication auth, 
			@RequestBody @Valid PromotionDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			if(dto.getPromotionId() == null || promotionService.findById(dto.getPromotionId())==null || promotionService.findById(dto.getPromotionId()).getShopownerOwnerId() != shopId) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}	
			
			if(dto.getStartDate()!=null && dto.getEndDate()!=null) {
				if(dto.getStartDate().compareTo(dto.getEndDate())>0) {
					bindingResult.rejectValue("startDate", "StartDate");
					bindingResult.rejectValue("endDate", "EndDate");
				}
			}
			
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
	
			// edit 
			dto.setUpdatedAt(new Date());
			// edit 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = promotionService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(
			Authentication auth, 
			@RequestParam(value = "id", required = true) int id
			) {
		try {
			// validate
			var promotion = promotionService.findById(id);
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			if (promotion == null || promotion.getShopownerOwnerId() != shopId) {
				return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
			}
			// validate
			return new ResponseEntity<Object>(new Object() {
				public boolean status = promotionService.delete(id);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
