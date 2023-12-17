package com.dodo.api.controllers.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IUserService;
import com.dodo.api.helpers.JwtHelper;
import com.dodo.api.modelview.LoginInfo;

@RestController
@RequestMapping("login")
public class LoginController {
	@Autowired
	IUserService userService;
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
	@Autowired
	private JwtHelper jwtHelper;
	 
	@PostMapping(value = { "/" , "" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody LoginInfo login) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getpassword()));
			var user = userService.findByUsernameModel(login.getUsername());
			var jwt = jwtHelper.generateToken(user);
			var newRefreshToken = jwtHelper.generateRefreshToken(user);
			user.setRefreshToken(newRefreshToken);
			user.setToken(jwt);
			userService.saveModel(user);
			return new ResponseEntity<Object>(new Object() {
				public boolean status = true;
				public String token = jwt;
				public String refreshToken = newRefreshToken;
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}

