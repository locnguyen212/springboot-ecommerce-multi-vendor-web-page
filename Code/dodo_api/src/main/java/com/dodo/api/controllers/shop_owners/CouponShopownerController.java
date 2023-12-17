package com.dodo.api.controllers.shop_owners;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.dodo.api.IServices.IShopOwnerCouponService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.models.Shopownercoupon;

import jakarta.validation.Valid;

@Controller
@RequestMapping("shop-owners/coupon")
public class CouponShopownerController {
//
//	@Autowired
//	IShopOwnerCouponService couponService;
//
//	@Autowired
//	IShopOwnerService shopOwnerService;
//
//	@GetMapping({ "index", "", "/" })
//	public String index(ModelMap modelMap, Authentication authentication) {
//		try {
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			modelMap.put("coupons", couponService.findByShopownerOwnerId(shopId));
//			return "shopOwners/coupon/index";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}
//	}
//
//	@GetMapping({ "create" })
//	public String create(ModelMap modelMap, Authentication authentication) {
//		var shop = shopOwnerService.findByUserUsername(authentication.getName());
//		var coupon = new Shopownercoupon();
//		coupon.setShopowner(shop);
//		modelMap.put("coupon", coupon);
//		return "shopOwners/coupon/create";
//	}
//
//	@PostMapping({ "create" })
//	public String create(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes,
//			@ModelAttribute("coupon") @Valid Shopownercoupon coupon, BindingResult bindingResult) {
//		try {
//			// validate
//			if (bindingResult.hasErrors()) {
//				return "shopOwners/coupon/create";
//			}
//			// validate
//
//			// create coupon
//			coupon.setCouponCode(generateCouponCode(8));
//			coupon.setCreatedAt(new Date());
//			if (couponService.save(coupon)) {
//				redirectAttributes.addFlashAttribute("successCreated", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alert", true);
//			}
//			// create coupon
//			return "redirect:/shop-owners/coupon";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}
//	}
//
//	@GetMapping({ "edit/{id}" })
//	public String edit(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes,
//			@PathVariable("id") int id) {
//		try {
//			// validate
//			var coupon = couponService.findById(id);
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			if (coupon == null || coupon.getShopowner().getOwnerId() != shopId) {
//				return "redirect:/error/404";
//			}
//			// validate
//
//			modelMap.put("coupon", coupon);
//			return "shopOwners/coupon/edit";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}
//	}
// 
//	@PostMapping({ "edit" })
//	public String editCoupon(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes,
//			@ModelAttribute("coupon") @Valid Shopownercoupon coupon, BindingResult bindingResult) {
//		try {
//			// validate
//			if (coupon.getCouponCode() == null || coupon.getCouponCode().isBlank()) {
//				bindingResult.rejectValue("couponCode", "CouponCode");
//			}
//
//			if (bindingResult.hasErrors()) {
//				return "shopOwners/coupon/edit";
//			}
//			// validate
//
//			// update coupon
//			coupon.setUpdatedAt(new Date());
//			if (couponService.save(coupon)) {
//				redirectAttributes.addFlashAttribute("successUpdated", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alert", true);
//			}
//			// update coupon
//			return "redirect:/shop-owners/coupon";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}
//	}
//
//	@GetMapping({ "delete/{id}" })
//	public String deleteCoupon(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes,
//			@PathVariable("id") int id) {
//		try {
//			// validate
//			var coupon = couponService.findById(id);
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			if (coupon == null || coupon.getShopowner().getOwnerId() != shopId) {
//				return "redirect:/error/404";
//			}
//			// validate
//			
//			//actual delete
//			if (couponService.delete(id)) {
//				redirectAttributes.addFlashAttribute("successDelete", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alertDelete", true);
//			}
//			//actual delete
//			
//			return "redirect:/shop-owners/coupon";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}
//	}
//
//	@GetMapping({ "switch/{id}" })
//	public String done(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes,
//			@PathVariable("id") int id) {
//		try {
//			// validate
//			var coupon = couponService.findById(id);
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			if (coupon == null || coupon.getShopowner().getOwnerId() != shopId) {
//				return "redirect:/error/404";
//			}
//			// validate
//			
//			if(coupon.getIsActive()==true) {
//				coupon.setIsActive(false);
//			}else if(coupon.getIsActive()==false) {
//				coupon.setIsActive(true);
//			}
//			coupon.setUpdatedAt(new Date());
//			
//			//actual delete
//			if (couponService.save(coupon)) {
//				redirectAttributes.addFlashAttribute("successUpdated", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alert", true);
//			}
//			//actual delete
//			
//			return "redirect:/shop-owners/coupon";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}
//	}
//
//	private String generateCouponCode(int length) {
//		SecureRandom secureRandom = new SecureRandom();
//		byte[] randomBytes = new byte[length];
//		secureRandom.nextBytes(randomBytes);
//		var code = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes).substring(0, length);
//		if (couponService.isCouponCodeExist(code)) {
//			return generateCouponCode(length);
//		}
//		return code;
//	}
}
