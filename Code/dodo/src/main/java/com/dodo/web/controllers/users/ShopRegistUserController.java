package com.dodo.web.controllers.users;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.helpers.FileHelper;
import com.dodo.web.models.Shopowner;
import com.dodo.web.validators.ShopownerUniqueValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = { "user/become-a-vendor" })
public class ShopRegistUserController {
	@Autowired
	IUserService userService;

	@Autowired
	IShopOwnerService shopOwnerService;
	
	@Autowired
	ShopownerUniqueValidator shopownerUniqueValidator;
	
	@GetMapping({ "index", "", "/" })
	public String index(ModelMap modelMap, Authentication authentication) {
		var loggedInUser = userService.findByUsername(authentication.getName());
		if(loggedInUser.getShopowners().size()!=0) {
			return "redirect:/error/404";
		}
		modelMap.put("loggedInUser", loggedInUser);
		return "users/becomeAVendor/index";
	}
	
	@GetMapping({ "register" })
	public String register(ModelMap modelMap, Authentication authentication) {
		var loggedInUser = userService.findByUsername(authentication.getName());
		if(loggedInUser.getShopowners().size()!=0) {
			return "redirect:/error/404";
		}
		
		var shopowner = new Shopowner();
		shopowner.setUser(userService.findByUsername(authentication.getName()));
		modelMap.put("shopowner", shopowner);
		return "users/becomeAVendor/register";
	}
	
	@GetMapping({ "success" })
	public String success() {
	
		return "users/becomeAVendor/success";
	}
	
	@PostMapping({"register"})
	public String register(
			ModelMap modelMap, 
			RedirectAttributes redirectAttributes, 
			@RequestParam("image") MultipartFile file, 
			@ModelAttribute("shopowner") @Valid Shopowner shopowner, 
			BindingResult bindingResult, HttpSession session, 
			Authentication authentication
			) {		
		try {
			//validate
			shopownerUniqueValidator.validate(shopowner, bindingResult);
			if (file.getSize() == 0) {
				bindingResult.rejectValue("shopLogoPath", "Upload");
			}
			if(bindingResult.hasErrors()) {				
				return "users/becomeAVendor/register";
			}	
			//validate
			  
			//file handling
			var fileName = FileHelper.saveImageFile(file);
			//file handling
			  
			//create shopowner
			shopowner.setShopLogoPath(fileName);
			shopowner.setCreatedAt(new Date());
			 
			if(shopOwnerService.save(shopowner)) {			
				redirectAttributes.addFlashAttribute("successRegister", true);
			}else {
				redirectAttributes.addFlashAttribute("alert", true);
			}			
			//create shopowner
			
			//set session
			session.setAttribute("isHasShop", userService.findByUsername(authentication.getName()).getShopowners().size() != 0);
			//set session
			
			return "redirect:/user/become-a-vendor/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}

	}
}
