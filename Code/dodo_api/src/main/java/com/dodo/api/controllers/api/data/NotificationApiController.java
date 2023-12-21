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

import com.dodo.api.IServices.INotificationService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.NotificationDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/notification")
@Tag(name = "Data Notification")
public class NotificationApiController {
	@Autowired
	INotificationService notificationService;

	@Autowired
	IUserService userService;

	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "count-by-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> countByUserId(Authentication auth) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<Integer>(notificationService.countByUserId(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}

	}

	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "find-by-logged-in-user" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NotificationDto>> findByUserId(
			Authentication auth, 
			@RequestParam(value = "skip", required = true) int skip,
			@RequestParam(value = "take", required = true) int take
			) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<NotificationDto>>(notificationService.findByUserId(userId, skip, take),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<NotificationDto>>(HttpStatus.BAD_REQUEST);
		}

	}

	@Operation(summary = "Role: user, shop")
	@GetMapping(value = { "find-by-logged-in-user-custom" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NotificationDto>> findByUserIdAndTypeAndIsRead(
			Authentication auth, 
			@RequestParam(value = "skip", required = true) int skip,
			@RequestParam(value = "take", required = true) int take,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "isRead", required = false) Boolean isRead) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			return new ResponseEntity<List<NotificationDto>>(
					notificationService.findByUserIdAndTypeAndIsRead(userId, type, isRead, skip, take), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<NotificationDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
