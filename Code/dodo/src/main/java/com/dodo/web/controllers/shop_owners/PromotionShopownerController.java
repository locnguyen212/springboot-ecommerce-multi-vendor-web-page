package com.dodo.web.controllers.shop_owners;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.ICategoryService;
import com.dodo.web.IServices.IOrderCancellationService;
import com.dodo.web.IServices.IParentCategoryService;
import com.dodo.web.IServices.IProductService;
import com.dodo.web.IServices.IPromotionService;
import com.dodo.web.IServices.IShopOwnerCouponService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.models.Product;
import com.dodo.web.models.Promotion;
import com.dodo.web.validators.CategoryUniqueValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("shop-owners/promotion")
public class PromotionShopownerController {
	@Autowired
	IUserService userService;
	
	@Autowired
	IShopOwnerService shopOwnerService;
	
	@Autowired
	IShopOwnerCouponService couponService;
	
	@Autowired
	IOrderCancellationService cancellationService;

	@Autowired
	ICategoryService categoryService;

	@Autowired
	IParentCategoryService parentCategoryService;
	
	@Autowired
	CategoryUniqueValidator uniqueValidator;
	
	@Autowired
	IPromotionService promotionService;
	
	@Autowired
	IProductService productService;

	@GetMapping({ "index", "", "/" })
	public String index(ModelMap modelMap, Authentication authentication) {
		var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
		modelMap.put("promotions", promotionService.findByShopownerOwnerId(shopId));
		return "shopOwners/promotion/index";
	}
	  
	@GetMapping({ "create" })
	public String create(ModelMap modelMap, Authentication authentication) {		
		modelMap.put("products", getProducts(authentication));
		
		modelMap.put("promotion", new Promotion());
		return "shopOwners/promotion/create";
	}
      	  
	@PostMapping({ "create" })
	public String create(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes, @ModelAttribute("promotion") @Valid Promotion promotion, BindingResult bindingResult) {
		try {
			//validate
			if(promotion.getStartDate()!=null && promotion.getEndDate()!=null) {
				if(promotion.getStartDate().compareTo(promotion.getEndDate())>0) {
					bindingResult.rejectValue("startDate", "StartDate");
					bindingResult.rejectValue("endDate", "EndDate");
				}
			}
			
			if (bindingResult.hasErrors()) {
				modelMap.put("products", getProducts(authentication));
				return "shopOwners/promotion/create";
			}
			//validate			  
			
			//create promotion	
			var shop = shopOwnerService.findByUserUsername(authentication.getName());
			promotion.setShopowner(shop);
			promotion.setCreatedAt(new Date());
			if(promotionService.save(promotion)) {
				redirectAttributes.addFlashAttribute("successCreated", true);
			}else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			//create promotion
			
			return "redirect:/shop-owners/promotion";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
	 
	@GetMapping({ "edit/{id}" })
	public String edit(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id, Authentication authentication) {
		try {
			//validate
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
			var promotion = promotionService.findById(id);
			if(promotion == null || promotion.getShopowner().getOwnerId() != shopId) {
				return "redirect:/error/404";
			}					
			//validate
			
			modelMap.put("promotion", promotion);
			return "shopOwners/promotion/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
	
	@PostMapping({ "edit" })
	public String edit(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes, @ModelAttribute("promotion") @Valid Promotion promotion, BindingResult bindingResult) {
		try {
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
			//validate
			if(promotion.getPromotionId() == null || promotionService.findById(promotion.getPromotionId())==null || promotionService.findById(promotion.getPromotionId()).getShopowner().getOwnerId() != shopId) {
				return "redirect:/error/400";
			}	
			
			if(promotion.getStartDate()!=null && promotion.getEndDate()!=null) {
				if(promotion.getStartDate().compareTo(promotion.getEndDate())>0) {
					bindingResult.rejectValue("startDate", "StartDate");
					bindingResult.rejectValue("endDate", "EndDate");
				}
			}
			
			if (bindingResult.hasErrors()) {
				modelMap.put("products", getProducts(authentication));
				return "shopOwners/promotion/edit";
			}
			//validate			  
			
			//create promotion	
			var oldPromotion = promotionService.findById(promotion.getPromotionId());
			promotion.setProduct(oldPromotion.getProduct());
			promotion.setShopowner(oldPromotion.getShopowner());
			promotion.setCreatedAt(oldPromotion.getCreatedAt());
			promotion.setUpdatedAt(new Date());
			if(promotionService.save(promotion)) {
				redirectAttributes.addFlashAttribute("successUpdated", true);
			}else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			//create promotion
			
			return "redirect:/shop-owners/promotion";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
	
	@GetMapping({ "delete/{id}" })
	public String delete(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes,
			@PathVariable("id") int id) {
		try {
			// validate
			var promotion = promotionService.findById(id);
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
			if (promotion == null || promotion.getShopowner().getOwnerId() != shopId) {
				return "redirect:/error/404";
			}
			// validate

			// actual delete
			if (promotionService.delete(id)) {
				redirectAttributes.addFlashAttribute("successDelete", true);
			} else {
				redirectAttributes.addFlashAttribute("alertDelete", true);
			}
			// actual delete
			return "redirect:/shop-owners/promotion";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
	
	private List<Product> getProducts(Authentication authentication) {
		var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
		var productWithPromotionIds = promotionService.findProductIdGotPromotionByShopownerId(shopId);
		if(productWithPromotionIds.size()==0) {
			return productService.findByShopownerOwnerIdAndStatusActive(shopId);
		}else {
			return productService.findProductWithoutPromotionByShopownerId(shopId);
		}
	}
 

}
