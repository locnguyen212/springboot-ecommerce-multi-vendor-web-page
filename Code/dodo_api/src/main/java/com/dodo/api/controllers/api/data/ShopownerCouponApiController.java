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

import com.dodo.api.IServices.IShopOwnerCouponService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.dtos.ShopownercouponDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/shop-coupon")
@Tag(name = "Data Shop Coupon")
public class ShopownerCouponApiController {
	@Autowired
	IShopOwnerCouponService couponService;
	
	@Autowired
	IShopOwnerService ownerService;

	@GetMapping(value = { "find-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShopownercouponDto> findById(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<ShopownercouponDto>(couponService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ShopownercouponDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-by-current-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShopownercouponDto>> findByShopownerOwnerId(Authentication auth) {
		try {
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			return new ResponseEntity<List<ShopownercouponDto>>(couponService.findByShopownerOwnerId(shopId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ShopownercouponDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
