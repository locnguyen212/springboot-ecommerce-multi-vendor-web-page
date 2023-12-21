package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.ShopownerDto;
import com.dodo.api.helpers.FileHelper;
import com.dodo.api.helpers.ValidateHelper;
import com.dodo.api.validators.ShopownerUniqueValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/shop")
@Tag(name = "Crud Shop")
public class ShopownerCrudApiController {
	@Autowired
	IShopOwnerService ownerService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ShopownerUniqueValidator shopownerUniqueValidator;

	//user
	@Operation(summary = "Form Data Only, Cannot Send JSON; Role: user")
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestParam(value = "image", required = true) MultipartFile file, 
			@ModelAttribute("shopowner") @Valid ShopownerDto dto, 
			BindingResult bindingResult
			) {
		try {
			var userId = userService.findByUsername(auth.getName()).getUserId();
			
			//validate
			shopownerUniqueValidator.validate(dto, bindingResult);
			
			if (file == null || file.getSize() == 0) {
				bindingResult.rejectValue("shopLogoPath", "Upload", null, "Image is required");
			}
			
			if(bindingResult.hasErrors()) {				
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}	
			//validate
			
			//file handling
			var fileName = FileHelper.saveImageFile(file);
			//file handling
	
			// create 
			dto.setShopLogoPath(fileName);
			dto.setUserUserId(userId);;
			dto.setCreatedAt(new Date());
			dto.setStatus(null);
			// create 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = ownerService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//admin, super admin
	@Operation(summary = "Role: super admin, admin")
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			Authentication auth, 
			@RequestParam @Valid ShopownerDto dto, 
			BindingResult bindingResult
			) {
		try {
			//validate
			shopownerUniqueValidator.validate(dto, bindingResult);

			if(bindingResult.hasErrors()) {				
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}	
			//validate
	
			// edit 
			dto.setUpdatedAt(new Date());
			// edit 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = ownerService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//admin, super admin
	@Operation(summary = "Role: super admin, admin")
	@PutMapping(value = { "approval" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> approval(
			Authentication auth, 
			@RequestParam(value = "shopId", required = true) int id,
			@RequestParam(value = "status", required = true) boolean status,
			BindingResult bindingResult
			) {
		try {
			var shop = ownerService.findById(id);
			
			if(shop == null || shop.getStatus()!=null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			
			if(status) {
				// edit 
				shop.setUpdatedAt(new Date());
				shop.setStatus(status);
				// edit 
				
				return new ResponseEntity<Object>(new Object() {
					public boolean status = ownerService.save(shop);
				}, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(new Object() {
					public boolean status = ownerService.delete(id);
				}, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@Operation(summary = "Role: shop")
	@PutMapping(value = { "edit-profile" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> editProfile(
			Authentication auth, 
			@RequestParam(value = "image", required = false) MultipartFile file, 
			@RequestParam(value = "description", required = false) String description,
			BindingResult bindingResult
			) {
		try {
			var myShop = ownerService.findByUserUsername(auth.getName());
			
			//file handling
			if (file != null && file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				FileHelper.deleteImageFile(myShop.getShopLogoPath());
				myShop.setShopLogoPath(fileName);
			}
			//file handling
	
			// edit 
			myShop.setUpdatedAt(new Date());
			// edit 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = ownerService.save(myShop);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
