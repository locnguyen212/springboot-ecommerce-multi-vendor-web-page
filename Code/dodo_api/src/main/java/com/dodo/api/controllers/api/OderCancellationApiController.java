package com.dodo.api.controllers.api;

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

import com.dodo.api.IServices.IOrderCancellationService;
import com.dodo.api.IServices.IOrderService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.OrdercancellationDto;

@RestController
@RequestMapping("api/data/order-cancellation")
public class OderCancellationApiController {
	@Autowired
	IOrderCancellationService cancellationService;
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IShopOwnerService shopOwnerService;

	@Autowired
	IUserService userService;

	@GetMapping(value = { "find-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrdercancellationDto> findById(@RequestParam(value = "id", required = true) Integer id) {
		try {
			return new ResponseEntity<OrdercancellationDto>(cancellationService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<OrdercancellationDto>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = { "find-by-order-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrdercancellationDto> findByOrderOrderId(
			Authentication auth,
			@RequestParam(value = "id", required = true) Integer id
			) {
		try {
			//validate
			var user = userService.findByUsername(auth.getName());
			var order = orderService.findById(id);
			if(order == null || (user.getUserId() != order.getUserUserId())) {
				return new ResponseEntity<OrdercancellationDto>(HttpStatus.FORBIDDEN);
			}
			//validate
			return new ResponseEntity<OrdercancellationDto>(cancellationService.findByOrderOrderId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<OrdercancellationDto>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = { "find-by-logged-in-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrdercancellationDto>> findByShopownerOwnerId(Authentication auth) {
		try {
			var shop = shopOwnerService.findByUserUsername(auth.getName());
			
			//validate
			if(shop==null || !shop.getStatus()) {
				return new ResponseEntity<List<OrdercancellationDto>>(HttpStatus.BAD_REQUEST);
			}
			//validate
			
			return new ResponseEntity<List<OrdercancellationDto>>(cancellationService.findByShopownerOwnerId(shop.getOwnerId()), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrdercancellationDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-by-logged-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrdercancellationDto>> findByUserUsername(Authentication auth) {
		try {
			return new ResponseEntity<List<OrdercancellationDto>>(cancellationService.findByUserUsername(auth.getName()), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrdercancellationDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}