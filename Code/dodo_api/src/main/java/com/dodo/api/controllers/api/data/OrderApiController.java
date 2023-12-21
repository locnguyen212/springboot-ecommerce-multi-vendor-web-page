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

import com.dodo.api.IServices.IOrderService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.OrderDto;
import com.dodo.api.modelview.dtos.OrderViewDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/order")
@Tag(name = "Data Order")
public class OrderApiController {
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IShopOwnerService ownerService;

	@Autowired
	IUserService userService;
	
	//user, shop
	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "find-by-order-status-and-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> findByUserUsernameAndOrderStatus(
			Authentication auth, 
			@RequestParam(value = "orderStatus", required = true) String status
			) {
		try {	
			return new ResponseEntity<List<OrderDto>>(orderService.findByUserUsernameAndOrderStatus(auth.getName(), status), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin
	@Operation(summary = "Role:super admin")
	@GetMapping(value = { "find-sales-data-by-year" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderViewDto>> findSalesdataByYear(
			@RequestParam(value = "year", required = true) int year
			) {
		try {	
			return new ResponseEntity<List<OrderViewDto>>(orderService.findSalesdataByYear(year), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderViewDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin
	@Operation(summary = "Role: super admin")
	@GetMapping(value = { "find-sales-data-by-year-and-month" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderViewDto>> findSalesdataByYearAndMonth(
			@RequestParam(value = "year", required = true) int year,
			@RequestParam(value = "month", required = true) int month
			) {
		try {	
			return new ResponseEntity<List<OrderViewDto>>(orderService.findSalesdataByYearAndMonth(year, month), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderViewDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin
	@Operation(summary = "Role: super admin")
	@GetMapping(value = { "find-sales-data-years" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Integer>> findSalesdataYear() {
		try {	
			return new ResponseEntity<List<Integer>>(orderService.findSalesdataYear(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Integer>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@Operation(summary = "Role: shop")
	@GetMapping(value = { "find-by-order-status-and-current-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> findByShopownerOwnerIdAndOrderStatus(
			Authentication auth, 
			@RequestParam(value = "orderStatus", required = true) String status
			) {
		try {	
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			return new ResponseEntity<List<OrderDto>>(orderService.findByShopownerOwnerIdAndOrderStatus(shopId, status), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@Operation(summary = "Role: shop")
	@GetMapping(value = { "find-by-order-status-not-in-and-current-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> findByShopownerIdAndStatusOther(
			Authentication auth, 
			@RequestParam(value = "statusList", required = true) List<String> statusList
			) {
		try {	
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			return new ResponseEntity<List<OrderDto>>(orderService.findByShopownerIdAndStatusOther(shopId, statusList), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//user, shop
	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "find-by-payment-status-and-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> findByPaymentStatusAndUserUserId(
			Authentication auth, 
			@RequestParam(value = "status", required = true) String status
			) {
		try {	
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<OrderDto>>(orderService.findByPaymentStatusAndUserUserId(status, userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//user, shop
	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "find-by-order-status-not-in-and-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> findByUserIdAndStatusNotIn(
			Authentication auth, 
			@RequestParam(value = "statusList", required = true) List<String> statusList
			) {
		try {	
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<OrderDto>>(orderService.findByUserIdAndStatusNotIn(userId, statusList), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
