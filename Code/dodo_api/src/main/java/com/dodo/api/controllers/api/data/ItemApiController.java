package com.dodo.api.controllers.api.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IItemService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.ItemDto;
import com.dodo.api.modelview.dtos.CartViewDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/item")
@Tag(name = "Data Item")
public class ItemApiController {
	@Autowired
	IUserService userService;

	@Autowired
	IItemService itemService;

	//user, shop
	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "find-by-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemDto>> getAllByUser(Authentication auth) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<ItemDto>>(itemService.getAllByUser(userId) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ItemDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//user, shop
	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "count-by-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> countItemsByUserId(Authentication auth) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<Integer>(itemService.countItemsByUserId(userId) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//user, shop
	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "find-by-product-id-and-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemDto> findByProductIdAndUserId(
			Authentication auth, 
			@RequestParam(value = "productId", required = true) int productId
			) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<ItemDto>(itemService.findByProductIdAndUserId(productId, userId) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//user, shop
	@Operation(summary = "Role: user, shop")
	@PostMapping(value = { "get-cart-view-from-items" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CartViewDto>> getCartViewFromItems(Authentication auth, @RequestParam("itemIds") List<Integer> itemIds) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<CartViewDto>>(itemService.getCartViewFromItems(userId, itemIds) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CartViewDto>>(HttpStatus.BAD_REQUEST);
		}
	}
}
