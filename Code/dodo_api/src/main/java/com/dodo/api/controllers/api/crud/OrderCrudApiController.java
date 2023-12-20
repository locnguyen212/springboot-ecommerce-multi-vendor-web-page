package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IOrderService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.OrderDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/order")
public class OrderCrudApiController {
	@Autowired
	IOrderService orderService;

	@Autowired
	IUserService userService;
	
	// user, shop
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestBody @Valid OrderDto dto, 
			BindingResult bindingResult
			) {
		try {	
			var userId = userService.findByUsername(auth.getName()).getUserId();
			
			// create 
			dto.setOrderDate(new Date());
			dto.setUserUserId(userId);
			// create 
			return new ResponseEntity<Object>(new Object() {
				public OrderDto result = orderService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	// user, shop
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			@RequestBody @Valid OrderDto dto, 
			BindingResult bindingResult
			) {
		try {
			//validate
			if(dto.getOrderId() == null || orderService.findById(dto.getOrderId())==null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			//validate
			
			
			// edit order
			dto.setUpdatedAt(new Date());
			// edit order
			return new ResponseEntity<Object>(new Object() {
				public boolean status = orderService.saveBool(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
