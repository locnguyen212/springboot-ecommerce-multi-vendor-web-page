package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.UserDto;
import com.dodo.api.helpers.FileHelper;
import com.dodo.api.helpers.ValidateHelper;
import com.dodo.api.validators.UserEmailUniqueValidator;
import com.dodo.api.validators.UserPasswordValidator;
import com.dodo.api.validators.UserUsernameUniqueValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/user")
@Tag(name = "Crud User")
public class UserCrudApiController {
	@Autowired
	IUserService userService;
	
	@Autowired
	UserEmailUniqueValidator emailUniqueValidator;

	@Autowired
	UserUsernameUniqueValidator usernameUniqueValidator;

	@Autowired
	UserPasswordValidator userPasswordValidator;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	//admin, super admin
	@Operation(summary = "Form Data Only, Cannot Send JSON; Role: super admin, admin")
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			@RequestParam(value = "image", required = true) MultipartFile file, 
			@ModelAttribute("user") @Valid UserDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			emailUniqueValidator.validate(dto, bindingResult);
			usernameUniqueValidator.validate(dto, bindingResult);
			userPasswordValidator.validate(dto, bindingResult);
			if (file == null || file.getSize() == 0) {
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
			dto.setRoleRoleId(4);
			dto.setStatus(true);
			dto.setCreatedAt(new Date());
			dto.setAvatar(fileName);
			dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//admin, super admin
	@Operation(summary = "Form Data Only, Cannot Send JSON; Role: super admin, admin")
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			@RequestParam(value = "image", required = false) MultipartFile file, 
			@ModelAttribute("user") @Valid UserDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			var baseUser = userService.findById(dto.getUserId());
			if(baseUser == null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			
			if (!dto.getPassword().isBlank()) {
				userPasswordValidator.validate(dto, bindingResult);
				dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
			} else {
				dto.setPassword(baseUser.getPassword());
			}

			emailUniqueValidator.validate(dto, bindingResult);
			usernameUniqueValidator.validate(dto, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate

			// file handling
			if (file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				FileHelper.deleteImageFile(dto.getAvatar());
				dto.setAvatar(fileName);
			}
			// file handling

			// update user
			dto.setUpdatedAt(new Date());
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//admin, super admin, shop, user
	@Operation(summary = "Form Data Only, Cannot Send JSON; Role: super admin, admin, user, shop")
	@PutMapping(value = { "edit-profile" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> editProfile(
			Authentication auth,
			@RequestParam(value = "image", required = false) MultipartFile file, 
			@ModelAttribute("user") @Valid UserDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			var baseUser = userService.findByUsername(auth.getName());
			
			if (!dto.getPassword().isBlank()) {
				userPasswordValidator.validate(dto, bindingResult);
				dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
			} else {
				dto.setPassword(baseUser.getPassword());
			}

			emailUniqueValidator.validate(dto, bindingResult);
			usernameUniqueValidator.validate(dto, bindingResult);
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate

			// file handling
			if (file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				FileHelper.deleteImageFile(dto.getAvatar());
				dto.setAvatar(fileName);
			}
			// file handling

			// update user
			dto.setUserId(baseUser.getUserId());
			dto.setUpdatedAt(new Date());
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//super admin
	@Operation(summary = "Role: super admin")
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(
			Authentication auth, 
			@RequestParam(value = "id", required = true) int id
			) {
		try {
			//validate
			var loggedInUserId = userService.findByUsername(auth.getName()).getUserId();
			var deletedUser = userService.findById(id);
			
			if(deletedUser == null || loggedInUserId == id) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			//validate
			
			//delete
			var stt = userService.delete(id);
			if(stt) {
				FileHelper.deleteImageFile(deletedUser.getAvatar());
			}
			//delete
			
			return new ResponseEntity<Object>(new Object() {
				public boolean status = stt;
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
