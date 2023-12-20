package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IItemService;
import com.dodo.api.IServices.IProductService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.ItemDto;
import com.dodo.api.dtos.ProductDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/crud/item")
@Tag(name = "Crud Item")
public class ItemCrudApiController {
	@Autowired
	IItemService itemService;

	@Autowired
	IProductService productService;

	@Autowired
	IUserService userService;

	// user, shop
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestParam(value = "productId", required = true) int productId, 
			@RequestParam(value = "quantity", required = true) Integer quantity
			) {
		try {
			var user = userService.findByUsername(auth.getName());

			// Check the quantity of products left in stock
			if (!isValidQuantity(quantity, productId)) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			} 
			
			// Check if the product exists in the user's Item list
			ItemDto existingItem = itemService.findByProductIdAndUserId(productId, user.getUserId());

			if (existingItem != null) {
				// Product already exists, update quantity
				int newQuantity = existingItem.getQuantity() + quantity;
				
				if (!isValidQuantity(newQuantity, productId)) {
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				} 
				
				existingItem.setQuantity(newQuantity);
			} else {
				// The product does not exist yet, create a new item
				ProductDto product = productService.findByProductIdCustom(productId, true, null, true);
				if (product == null) {
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				} else {
					ItemDto newItem = new ItemDto(quantity, new Date(), productId, user.getUserId());
					
					return new ResponseEntity<Object>(new Object() {
						public boolean status = itemService.save(newItem);
					}, HttpStatus.OK);
				}
			}
	
			return new ResponseEntity<Object>(new Object() {
				public boolean status = itemService.save(existingItem);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

	// user, shop
	@PutMapping(value = {"edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			Authentication auth, 
			@RequestParam(value = "productId", required = true) int productId, 
			@RequestParam(value = "quantity", required = true) Integer quantity
			) {
		try {
			var user = userService.findByUsername(auth.getName());

			// Find the product in the user's Item list
			ItemDto existingItem = itemService.findByProductIdAndUserId(productId, user.getUserId());

			//Check for existing products
			if (existingItem != null) {
				// Check the quantity of products left in stock
				if (isValidQuantity(quantity, productId)) {
					existingItem.setQuantity(quantity);	
				} else {
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				}
				
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Object>(new Object() {
				public boolean status = itemService.save(existingItem);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

	// user, shop
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(
			Authentication auth, 
			@RequestParam(value = "id", required = true) int id) {
		try {
			//validate
			var userId = userService.findByUsername(auth.getName()).getUserId();
			var item = itemService.findById(id);
			if (userId != item.getUserUserId()) {
				return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
			}
			//validate

			return new ResponseEntity<Object>(new Object() {
				public boolean status = itemService.delete(id);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

	private boolean isValidQuantity(int quantity, int productId) {
		ProductDto product = productService.findByProductIdCustom(productId, null, true, true);
		if (product == null) {
			return false;
		}
		int availableQuantity = product.getStockQuantity();

		return quantity >= 0 && quantity <= availableQuantity;
	}
}
