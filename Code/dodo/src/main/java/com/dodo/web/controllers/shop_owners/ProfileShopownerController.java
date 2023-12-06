package com.dodo.web.controllers.shop_owners;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.IShopOwnerCouponService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.helpers.FileHelper;

@Controller
@RequestMapping("shop-owners/profile")
public class ProfileShopownerController {
	@Autowired
	IUserService userService;

	@Autowired
	IShopOwnerService shopownerService;

	@Autowired
	IShopOwnerCouponService couponService;

	@GetMapping({ "index", "", "/" })
	public String index(ModelMap modelMap, Authentication authentication) {
		var shop = shopownerService.findByUserUsername(authentication.getName());
		modelMap.put("shopowner", shop);
		return "shopOwners/profile/index";
	}

	@PostMapping({ "edit" })
	public String edit(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes, @RequestParam(value = "image", required = false) MultipartFile file, @RequestParam("description") String description) {
		try {
			var shop = shopownerService.findByUserUsername(authentication.getName());
			// file handling
			if (file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				FileHelper.deleteImageFile(shop.getShopLogoPath());
				shop.setShopLogoPath(fileName);
			}
			// file handling

			// edit shop
			shop.setUpdatedAt(new Date());
			shop.setShopDescription(description);
			if (shopownerService.save(shop)) {
				redirectAttributes.addFlashAttribute("successUpdated", true);
			} else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			// edit shop

			return "redirect:/shop-owners/profile";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}

}
