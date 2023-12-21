package com.dodo.api.controllers.api.crud;

import java.security.SecureRandom;
import java.util.Base64;
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

import com.dodo.api.IServices.IShopOwnerCouponService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.dtos.ShopownercouponDto;
import com.dodo.api.helpers.ValidateHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/shop-coupon")
@Tag(name = "Crud Shop Coupon")
public class ShopownerCouponCrudApiController {
	@Autowired
	IShopOwnerCouponService couponService;
	
	@Autowired
	IShopOwnerService ownerService;

	//shop
	@Operation(summary = "Role: shop")
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestBody @Valid ShopownercouponDto dto, 
			BindingResult bindingResult
			) {
		try {
			// create 
			dto.setCouponCode(generateCouponCode(8));
			dto.setCreatedAt(new Date());
			// create 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = couponService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@Operation(summary = "Role: shop")
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			Authentication auth, 
			@RequestBody @Valid ShopownercouponDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			if(dto.getShopOwnerCouponId() == null || couponService.findById(dto.getShopOwnerCouponId())==null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			
			if (dto.getCouponCode() == null || dto.getCouponCode().isBlank()) {
				bindingResult.rejectValue("couponCode", "CouponCode", null, "this field is required!");
			}

			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
	
			// edit 
			dto.setUpdatedAt(new Date());
			// edit 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = couponService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@Operation(summary = "Role: shop")
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(
			Authentication auth, 
			@RequestParam(value = "id", required = true) int id
			) {
		try {
			// validate
			var coupon = couponService.findById(id);
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			if (coupon == null || coupon.getShopownerOwnerId() != shopId) {
				return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
			}
			// validate
			return new ResponseEntity<Object>(new Object() {
				public boolean status = couponService.delete(id);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	private String generateCouponCode(int length) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] randomBytes = new byte[length];
		secureRandom.nextBytes(randomBytes);
		var code = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes).substring(0, length) + new Date().getTime();
		if (couponService.isCouponCodeExist(code)) {
			return generateCouponCode(length);
		}
		return code;
	}
}
