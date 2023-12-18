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

import com.dodo.api.IServices.IOrderDetailService;
import com.dodo.api.IServices.IOrderService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.OrderdetailDto;
import com.dodo.api.modelview.ReviewModelViewDto;

@RestController
@RequestMapping("api/data/order-details")
public class OrderDetailsApiController {
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IOrderDetailService orderDetailService;

	@Autowired
	IUserService userService;

	@GetMapping(value = { "find-by-order-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderdetailDto>> findByOrderOrderId(
			Authentication auth, 
			@RequestParam(value = "orderId", required = true) Integer id
			) {
		try {
			//validate
			var userId = userService.findByUsername(auth.getName()).getUserId();
			var order = orderService.findById(id);
			if(order == null || order.getUserUserId() != userId) {
				return new ResponseEntity<List<OrderdetailDto>>(HttpStatus.FORBIDDEN);
			}
			//validate
			
			return new ResponseEntity<List<OrderdetailDto>>(orderDetailService.findByOrderOrderId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderdetailDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "get-review-model-view" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReviewModelViewDto>> getReviewModelView(Authentication auth) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<ReviewModelViewDto>>(orderDetailService.getReviewModelByUserId(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ReviewModelViewDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
