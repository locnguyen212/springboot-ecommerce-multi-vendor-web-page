package com.dodo.api.controllers.shop_owners;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.IServices.IOrderCancellationService;
import com.dodo.api.IServices.IParentCategoryService;
import com.dodo.api.IServices.IShopOwnerCouponService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.models.Category;
import com.dodo.api.validators.CategoryUniqueValidator;

import jakarta.validation.Valid;
 
@Controller
@RequestMapping("shop-owners/category")
public class CategoryShopownerController {
//	@Autowired
//	IUserService userService;
//	
//	@Autowired
//	IShopOwnerService shopOwnerService;
//	
//	@Autowired
//	IShopOwnerCouponService couponService;
//	
//	@Autowired
//	IOrderCancellationService cancellationService;
//
//	@Autowired
//	ICategoryService categoryService;
//
//	@Autowired
//	IParentCategoryService parentCategoryService;
//	
//	@Autowired
//	CategoryUniqueValidator uniqueValidator;
//
//	@GetMapping({ "index", "", "/" })
//	public String index(ModelMap modelMap) {
//		modelMap.put("categories", categoryService.findByStatusNotNull());
//
//		return "shopOwners/category/index";
//	}
//	
//	@GetMapping({ "from-shop" })
//	public String fromShop(ModelMap modelMap, Authentication authentication) {
//		var loggedInUserId = userService.findByUsername(authentication.getName()).getUserId();
//		modelMap.put("categories", categoryService.findByUserUserId(loggedInUserId));
// 
//		return "shopOwners/category/fromShop";
//	}
//  
//	@GetMapping({ "create" })
//	public String createCoupon(ModelMap modelMap, Authentication authentication) {
//		var category = new Category();
//		category.setUser(userService.findByUsername(authentication.getName()));
//		
//		modelMap.put("category", category);
//		modelMap.put("parentCategories", parentCategoryService.findAll());
//
//		return "shopOwners/category/create";
//	}
//
//	@PostMapping({ "create" })
//	public String create(ModelMap modelMap, RedirectAttributes redirectAttributes,
//			@ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
//		try {
//			//validate
//			uniqueValidator.validate(category, bindingResult);
//			if (bindingResult.hasErrors()) {
//				modelMap.put("parentCategories", parentCategoryService.findAll());
//				return "shopOwners/category/create";
//			}
//			//validate			  
//			
//			//create category
//			category.setCreatedAt(new Date());
//
//			if(categoryService.save(category)) {
//				redirectAttributes.addFlashAttribute("successCreated", true);
//			}else {
//				redirectAttributes.addFlashAttribute("alert", true);
//			}
//			//create category
//			
//			return "redirect:/shop-owners/category/from-shop";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}
//		
//	} 
//
////	@GetMapping({ "edit/{id}" })
////	public String edit(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id, Authentication authentication) {
////		try {
////			//validate
////			var loggedInUserId = userService.findByUsername(authentication.getName()).getUserId();
////			var category = categoryService.findById(id);
////			if(category == null || category.getUser().getUserId() != loggedInUserId) {
////				return "redirect:/error/404";
////			}					
////			//validate
////			
////			modelMap.put("category", category);
////			modelMap.put("parentCategories", parentCategoryService.findAll());
////			return "shopOwners/category/edit";
////		} catch (Exception e) {
////			e.printStackTrace();
////			return "redirect:/error/500";
////		}
////	}
////
////	@PostMapping({ "edit" })
////	public String edit(ModelMap modelMap, RedirectAttributes redirectAttributes, @ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
////		try {
////			//validate
////			uniqueValidator.validate(category, bindingResult);
////			if (bindingResult.hasErrors()) {
////				modelMap.put("parentCategories", parentCategoryService.findAll());
////				return "shopOwners/category/edit";
////			}
////			//validate
////
////			//edit category
////			category.setUpdatedAt(new Date());
////			if(categoryService.save(category)) {
////				redirectAttributes.addFlashAttribute("successUpdated", true);
////			}else {
////				redirectAttributes.addFlashAttribute("alert", true);
////			}
////			//edit category
////			return "redirect:/shop-owners/category/from-shop";
////		} catch (Exception e) {
////			e.printStackTrace();
////			return "redirect:/error/500";
////		}
////
////	}
////	 
////	@GetMapping({ "delete/{id}" })
////	public String delete(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id, Authentication authentication) {
////		try {
////			//validate
////			var loggedInUserId = userService.findByUsername(authentication.getName()).getUserId();
////			var category = categoryService.findById(id);
////			if(category == null || category.getUser().getUserId() != loggedInUserId) {
////				return "redirect:/error/404";
////			}					
////			//validate
////			
////			//actual delete
////			if(categoryService.delete(id)) {		
////				redirectAttributes.addFlashAttribute("successDelete", true);
////			}else {
////				redirectAttributes.addFlashAttribute("alertDelete", true);
////			}
////			//actual delete
////			return "redirect:/shop-owners/category/from-shop";
////		} catch (Exception e) {
////			e.printStackTrace();
////			redirectAttributes.addFlashAttribute("alertDelete", true);
////			return "redirect:/error/500";
////		}
////	}

}
