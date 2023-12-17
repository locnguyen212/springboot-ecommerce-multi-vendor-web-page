package com.dodo.api.controllers.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IUserService;
import com.dodo.api.helpers.JwtHelper;
import com.dodo.api.modelview.TokenAuth;

@RestController
@RequestMapping("refresh")
public class RefreshController {
	@Autowired
	IUserService userService;
    
    @Autowired
	private JwtHelper jwtHelper;
	 
	@PostMapping(value = { "/" , "" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> refresh(@RequestBody TokenAuth tokenAuth) {
		try {
			if(tokenAuth == null || tokenAuth.getRefreshToken() == null || tokenAuth.getToken() == null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			
			var oldToken = tokenAuth.getToken();
			var oldRefreshToken = tokenAuth.getRefreshToken();
			
			var username = jwtHelper.getUsernameFromExpiredToken(oldToken);

			if(username == null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			
			UserDetails userDetails = userService.loadUserByUsername(username);
			var user = userService.findByUsernameModel(username);
			
			if(user == null || !user.getRefreshToken().equals(oldRefreshToken) || !jwtHelper.isTokenValid(oldRefreshToken, userDetails)) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			
			var newToken = jwtHelper.generateToken(user);
			var newRefreshToken = jwtHelper.generateRefreshToken(user);
			user.setToken(newToken);
			user.setRefreshToken(newRefreshToken);

			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.saveModel(user);
				public String token = newToken;
				public String refreshToken = newRefreshToken;
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}

