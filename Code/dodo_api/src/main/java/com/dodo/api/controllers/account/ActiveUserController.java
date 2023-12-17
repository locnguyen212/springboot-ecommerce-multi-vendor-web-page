package com.dodo.api.controllers.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IUserService;

@RestController
@RequestMapping("account/active")
public class ActiveUserController {
	@Autowired
	IUserService userService;
	 
	@GetMapping(value = {"activate/{id}"}, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> index(@PathVariable("id") int id) {	
		var user = userService.findById(id);
		if(user.getStatus()) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
		user.setStatus(true);
		return new ResponseEntity<Object>(new Object() {
			public boolean status = userService.save(user);
		}, HttpStatus.OK);
	}	
}

