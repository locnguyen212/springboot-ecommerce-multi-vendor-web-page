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

import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.UserDto;

@RestController
@RequestMapping("api/data/user")
public class UserApiController {
	@Autowired
	IUserService userService;

	//allow all
	@GetMapping(value = { "find-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> findById(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<UserDto>(userService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = { "find-all" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> findAll() {
		try {
			return new ResponseEntity<List<UserDto>>(userService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<UserDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-by-email" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> findByEmail(@RequestParam(value = "email", required = true) String email) {
		try {
			return new ResponseEntity<UserDto>(userService.findByEmail(email), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-by-username" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> findByUsername(@RequestParam(value = "name", required = true) String name) {
		try {
			return new ResponseEntity<UserDto>(userService.findByUsername(name), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}

	}
	

	@GetMapping(value = { "find-by-role-user-and-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> findByRoleUserAndShopowner() {
		try {
			return new ResponseEntity<List<UserDto>>(userService.findByRoleUserAndShopowner(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<UserDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
