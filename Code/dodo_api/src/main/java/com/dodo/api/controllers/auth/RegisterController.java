package com.dodo.api.controllers.auth;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.IServices.IMailService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.UserDto;
import com.dodo.api.helpers.FileHelper;
import com.dodo.api.helpers.ValidateHelper;
import com.dodo.api.validators.UserEmailUniqueValidator;
import com.dodo.api.validators.UserPasswordValidator;
import com.dodo.api.validators.UserUsernameUniqueValidator;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/register")
@Tag(name = "Register User")
public class RegisterController {
	@Autowired
	IUserService userService;

	@Autowired
	IMailService mailService;
	
	@Autowired
	ICategoryService categoryService;

	@Autowired
	Environment environment;

	@Autowired
	UserEmailUniqueValidator emailUniqueValidator;

	@Autowired
	UserUsernameUniqueValidator usernameUniqueValidator;

	@Autowired
	UserPasswordValidator userPasswordValidator;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping(value = {"sign-up" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> register(@RequestParam(value = "image", required = true) MultipartFile file, @ModelAttribute("user") @Valid UserDto user, BindingResult bindingResult) {
		try {
			// validate
			emailUniqueValidator.validate(user, bindingResult);
			usernameUniqueValidator.validate(user, bindingResult);
			userPasswordValidator.validate(user, bindingResult);
			if (file.getSize() == 0 || file == null) {
				bindingResult.rejectValue("avatar", "Upload", null, "Avatar is required");
			}
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate

			// file handling
			var fileName = FileHelper.saveImageFile(file);
			// file handling

			// create user
			user.setRoleRoleId(4);
			user.setStatus(false);
			user.setCreatedAt(new Date());
			user.setAvatar(fileName);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.save(user);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = {"test" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> test(Authentication auth) {
		try {
			System.out.println("TEST TEST TEST");
			System.out.println(userService.findByUsername(auth.getName()));
			var page = 1;
			var size = 8;
			var pageable = PageRequest.of(page - 1, size);
//			return new ResponseEntity<Page<CategoryDto>>(categoryService.test(pageable) ,HttpStatus.OK);
			return new ResponseEntity<List<UserDto>>(userService.findAll() ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<UserDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
