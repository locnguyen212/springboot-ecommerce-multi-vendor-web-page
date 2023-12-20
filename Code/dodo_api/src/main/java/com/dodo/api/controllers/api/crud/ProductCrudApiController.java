package com.dodo.api.controllers.api.crud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import com.dodo.api.IServices.IProductService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.dtos.ParentcategoryDto;
import com.dodo.api.dtos.ProductDto;
import com.dodo.api.helpers.FileHelper;
import com.dodo.api.helpers.ValidateHelper;
import com.dodo.api.validators.ParentCategoryUniqueValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/crud/product")
public class ProductCrudApiController {
	@Autowired
	IProductService productService;
	
	@Autowired
	IShopOwnerService ownerService;

	//shop
	@PostMapping(value = { "create" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(
			Authentication auth, 
			@RequestParam(value = "image", required = true) MultipartFile file, 
			@ModelAttribute("product") @Valid ProductDto dto, 
			BindingResult bindingResult
			) {
		try {
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			// validate
			var pro = productService.findByProductNameAndShopownerOwnerId(dto.getProductName(), shopId);	
			
			if(pro!=null){
				bindingResult.rejectValue("productName", "product.uniqueName", null, "The name has already exist in your products!.");
			}
			
			if (file == null || file.getSize() == 0) {
				bindingResult.rejectValue("productImage", "Upload", null, "Image is required");
			}
			
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
			
			//file handling
			var fileName = FileHelper.saveImageFile(file);
			//file handling
	
			// create 
			dto.setShopownerOwnerId(shopId);
			dto.setCreatedAt(new Date());
			dto.setProductImage(fileName);
			// create
			return new ResponseEntity<Object>(new Object() {
				public boolean status = productService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@PutMapping(value = { "edit" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(
			Authentication auth, 
			@RequestParam(value = "image", required = false) MultipartFile file, 
			@ModelAttribute("product") @Valid ProductDto dto, 
			BindingResult bindingResult
			) {
		try {
			// validate
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			var baseProduct = productService.findById(dto.getProductId());
			if(baseProduct==null || baseProduct.getShopownerOwnerId() != shopId) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			
			if(!baseProduct.getProductName().equalsIgnoreCase(dto.getProductName())){
				var pro = productService.findByProductNameAndShopownerOwnerId(dto.getProductName(), shopId);	
				if(pro!=null){
					bindingResult.rejectValue("productName", "product.uniqueName", null, "The name has already exist in your products!.");
				}
			}
			
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<Object>(ValidateHelper.getErrorResponseBody(bindingResult), HttpStatus.BAD_REQUEST);
			}
			// validate
			
			//file handling
			if (file != null || file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				FileHelper.deleteImageFile(dto.getProductImage());
				dto.setProductImage(fileName);
			}
			//file handling
	
			// edit 
			dto.setUpdatedAt(new Date());
			// edit 
			return new ResponseEntity<Object>(new Object() {
				public boolean status = productService.save(dto);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//shop
	@DeleteMapping(value = { "delete" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(
			Authentication auth, 
			@RequestParam(value = "id", required = true) int id
			) {
		try {	
			// validate
			var product = productService.findById(id);
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			if (product == null || product.getShopownerOwnerId() != shopId) {
				return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
			}
			// validate
			
			var stt = productService.delete(id);
			if(stt) {
				// delete file
				FileHelper.deleteImageFile(product.getProductImage());
				// delete file
			}
	
			return new ResponseEntity<Object>(new Object() {
				public boolean status = stt;
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}
