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

import com.dodo.api.IServices.IOrderCancellationService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.OrdercancellationDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/order-cancellation")
public class OrderCancellationCrudApiController {
	@Autowired
	IOrderCancellationService cancellationService;

	@Autowired
	IUserService userService;
	
	// user, shop
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestBody @Valid OrdercancellationDto dto, 
			BindingResult bindingResult
			) {
		try {	
			var userId = userService.findByUsername(auth.getName()).getUserId();
			
			// create 
			dto.setCreatedAt(new Date());
			dto.setCancellationDate(new Date());
			dto.setUserUserId(userId);
			// create 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = cancellationService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	// user, shop
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			@RequestBody @Valid OrdercancellationDto dto, 
			BindingResult bindingResult
			) {
		try {
			//validate
			if(dto.getCancellationId() == null || cancellationService.findById(dto.getCancellationId())==null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			//validate
			return new ResponseEntity<Object>(new Object() {
				public boolean status = cancellationService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
