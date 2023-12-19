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

import com.dodo.api.IServices.IWishlistService;
import com.dodo.api.dtos.WishlistDto;

@RestController
@RequestMapping("api/data/wishlist")
public class WishlistApiController {
	@Autowired
	IWishlistService wishlistService;

	@GetMapping(value = { "find-by-user-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WishlistDto>> findByUserUserId(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<List<WishlistDto>>(wishlistService.findByUserUserId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<WishlistDto>>(HttpStatus.BAD_REQUEST);
		}

	}


}
