package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IProductService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.IServices.IWishlistService;
import com.dodo.api.dtos.WishlistDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/crud/wishlist")
@Tag(name = "Crud Wishlist")
public class WishlistCrudApiController {
	@Autowired
	IWishlistService wishlistService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IProductService productService;

	//shop, user
	@Operation(summary = "Role: user, shop")
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestParam(value = "productId", required = true) int id
			) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			
			//validate
			var product = productService.findById(id);			
			if(product == null || !product.getStatus()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			var existedWishlist = wishlistService.findByProductProductIdAndUserUserId(id, userId);
			if(existedWishlist != null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			//validate
	
			// create 
			var wishlist = new WishlistDto().builder()
					.productProductId(id)
					.userUserId(userId)
					.createdAt(new Date())
					.build();
			// create 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = wishlistService.save(wishlist);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop, user
	@Operation(summary = "Role: user, shop")
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(
			Authentication auth, 
			@RequestParam(value = "id", required = true) int id
			) {
		try {
			// validate
			var wishlist = wishlistService.findById(id);
			var userId = userService.findByUsername(auth.getName()).getUserId();
			if (wishlist == null || wishlist.getUserUserId() != userId) {
				return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
			}
			// validate
			return new ResponseEntity<Object>(new Object() {
				public boolean status = wishlistService.delete(id);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
