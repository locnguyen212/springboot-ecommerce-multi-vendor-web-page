package com.dodo.api.controllers.account;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.IServices.IMailService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.CategoryDto;
import com.dodo.api.dtos.UserDto;
import com.dodo.api.helpers.FileHelper;
import com.dodo.api.helpers.MailHelper;
import com.dodo.api.helpers.ValidateHelper;
import com.dodo.api.models.Category;
import com.dodo.api.models.Role;
import com.dodo.api.models.User;
import com.dodo.api.validators.UserEmailUniqueValidator;
import com.dodo.api.validators.UserPasswordValidator;
import com.dodo.api.validators.UserUsernameUniqueValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("account/register")
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
			if (file.getSize() == 0) {
				bindingResult.rejectValue("avatar", "Upload");
			}
			if (bindingResult.hasErrors()) {
				throw new MethodArgumentNotValidException(null, bindingResult);
//				return new ResponseEntity<Object>(ValidateHelper.getErrors(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate

			// file handling
//			var fileName = FileHelper.saveImageFile(file);
			System.out.println(file.getSize());
			System.out.println(user);
			// file handling

			// create user
//			var role = new Role();
//			role.setRoleId(4);
//			user.setRoleRoleId(4);
//			user.setRole(role);
//			user.setStatus(false);
//			user.setCreatedAt(new Date());
//			user.setAvatar(fileName);
//			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//			 
//			return new ResponseEntity<Object>(new Object() {
//				public boolean status = userService.save(user);
//			}, HttpStatus.OK);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = {"test" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<CategoryDto>> test() {
		try {
			var page = 1;
			var size = 8;
			var pageable = PageRequest.of(page - 1, size);
			return new ResponseEntity<Page<CategoryDto>>(categoryService.test(pageable) ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Page<CategoryDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
