package com.dodo.api.controllers.api.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IUserService;
import com.dodo.api.IServices.IWishlistService;
import com.dodo.api.dtos.WishlistDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/wishlist")
@Tag(name = "Data Wishlist")
public class WishlistApiController {
	@Autowired
	IWishlistService wishlistService;
	
	@Autowired
	IUserService userService;

	//user, shop
	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "get-my-wishlist" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WishlistDto>> getMyWishList(Authentication auth) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<WishlistDto>>(wishlistService.findByUserUserId(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<WishlistDto>>(HttpStatus.BAD_REQUEST);
		}

	}


}
