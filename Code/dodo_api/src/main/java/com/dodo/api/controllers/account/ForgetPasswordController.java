package com.dodo.api.controllers.account;

import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.api.IServices.IMailService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.helpers.MailHelper;
import com.dodo.api.models.Mail;
import com.dodo.api.modelview.ResetPassword;

import jakarta.validation.Valid;

@RestController
@RequestMapping("account/forget-password")
public class ForgetPasswordController {
	@Autowired
	IUserService userService;
	
	@Autowired
	private IMailService mailService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping(value = {"reset"}, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> reset(@RequestBody @Valid Mail mail) {	
		try {
			var user = userService.findByEmail(mail.getEmail());
				
			//generate token and save
			var token = generateToken();
//			user.setPasswordToken(token);
			
			//Send mail
//			var fromEmail = environment.getProperty("spring.mail.username");
//			mailService.send(fromEmail, mail.getEmail(), "Reset password mail", MailHelper.getMailResetPassword(user.getUserId(), token));
			//Send mail
			
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.save(user);
			}, HttpStatus.OK);										
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = {"change-password"}, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> changePassword(@RequestBody @Valid ResetPassword resetPassword) {	
		try {
			//validate
			var user = userService.findByIdModel(resetPassword.getId());
			
			if(user==null || user.getToken() == null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			//validate
				
			//change password and save
			var newPassword = bCryptPasswordEncoder.encode(resetPassword.getPassword());
			
			user.setPassword(newPassword);
			user.setToken(null);
			
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.saveModel(user);
			}, HttpStatus.OK);	
			//change password and save	
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	private String generateToken() {
		String token = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
		if(userService.isTokenExist(token)) {
			generateToken();
		}
		return token;
	}
} 

