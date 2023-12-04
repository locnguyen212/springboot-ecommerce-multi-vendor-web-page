package com.dodo.web.controllers.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.IMailService;
import com.dodo.web.IServices.IProductService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.helpers.FileHelper;
import com.dodo.web.helpers.MailHelper;
import com.dodo.web.models.Role;
import com.dodo.web.models.Shopowner;

@Controller
@RequestMapping("admin/shop-owner")
public class ShopOwnerAdminController {
	@Autowired
	IShopOwnerService shopOwnerService;

	@Autowired
	IProductService productService;

	@Autowired
	IUserService userService;

	@Autowired
	private IMailService mailService;

	@Autowired
	private Environment environment;

	@GetMapping({ "active-shops", "", "/" })
	public String activeShops(ModelMap modelMap) {
		modelMap.put("shops", shopOwnerService.findByStatus(true));
		modelMap.put("subtitle", "Active");
		return "admins/shopOwner/index";
	}

	@GetMapping({ "deactive-shops" })
	public String deactiveShops(ModelMap modelMap) {
		modelMap.put("shops", shopOwnerService.findByStatus(false));
		modelMap.put("subtitle", "Deactive");
		return "admins/shopOwner/index";
	}

	@GetMapping({ "waiting-for-approval-shopowners" })
	public String waitingForApproval(ModelMap modelMap) {
		modelMap.put("shops", shopOwnerService.findByStatus(null));
		modelMap.put("subtitle", "Waiting For Approval");
		return "admins/shopOwner/index";
	}

	@GetMapping({ "active/{id}" })
	public String active(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id,
			Authentication authentication) {
		try {
			var shop = shopOwnerService.findById(id);

			// validate
			if (shop == null || shop.getStatus()==true) {
				return "redirect:/error/404";
			}
			// validate
			var user = shop.getUser();
			user.setRole(new Role(3));
			user.setUpdatedAt(new Date());
			userService.save(user);
			
			shop.setStatus(true);
			shop.setUpdatedAt(new Date());

			if (shopOwnerService.save(shop)) {
				redirectAttributes.addFlashAttribute("successUpdated", true);
			} else {
				redirectAttributes.addFlashAttribute("alertUpdated", true);
			}

			return "redirect:/admin/shop-owner/deactive-shops";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
	 
	@GetMapping({ "deactive/{id}" })
	public String deactive(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id,
			Authentication authentication) {
		try {
			var shop = shopOwnerService.findById(id);

			// validate
			if (shop == null || shop.getStatus()==false) {
				return "redirect:/error/404";
			}
			// validate
			var user = shop.getUser();
			user.setRole(new Role(4));
			user.setUpdatedAt(new Date());
			userService.save(user);
			
			shop.setStatus(false);
			shop.setUpdatedAt(new Date());

			if (shopOwnerService.save(shop)) {
				redirectAttributes.addFlashAttribute("successUpdated", true);
			} else {
				redirectAttributes.addFlashAttribute("alertUpdated", true);
			}

			return "redirect:/admin/shop-owner/active-shops";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}

	@GetMapping({ "edit/{id}" })
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		try {
			var shop = shopOwnerService.findById(id);

			// validate
			if (shop == null) {
				return "redirect:/error/404";
			}
			// validate

			if (shop.getStatus() == null) {
				modelMap.put("subtitle", "Waiting For Approval");
			} else {
				if (shop.getStatus()) {
					modelMap.put("status", 1);
				} else {
					modelMap.put("status", 2);
				}
			}
			modelMap.put("shopowner", shop);

			return "admins/shopOwner/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}

	@PostMapping({ "edit" })
	public String edit(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@ModelAttribute("shopowner") Shopowner shopowner, @RequestParam("statusField") int status) {
		try {
			// status == 1 : active
			// status == 2 : deactive
			// status == 3 : refuse to active shop and delete the shop
			// status == 4 : approve
			var fromEmail = environment.getProperty("spring.mail.username");
			var toEmail = userService.findById(shopowner.getUser().getUserId()).getEmail();
			if (status == 1) {
				shopowner.setStatus(true);

				// Send mail
				String content = MailHelper.getEmailShopActiveNoti(shopowner.getShopName());
//				mailService.send(fromEmail, toEmail, "Active shop noti", content);
				// Send mail
			} else if (status == 2) {
				shopowner.setStatus(false);

				var products = productService.findByShopownerOwnerId(shopowner.getOwnerId());

				// set status of all products of that shop to false
				for (var e : products) {
					if (e.getStatus()) {
						e.setStatus(false);
						productService.save(e);
					}
				}
				// set status of all products of that shop to false

				// Send mail
//				mailService.send(fromEmail, toEmail, "Deactive shop noti", MailHelper.getEmailShopDeactiveNoti(shopowner.getShopName()));
				// Send mail
			} else if (status == 4) {
				shopowner.setStatus(true);
				var user = userService.findById(shopowner.getUser().getUserId());
				user.setRole(new Role(3));
				userService.save(user);
				// Send mail
//				mailService.send(fromEmail, toEmail, "Approve shop noti", MailHelper.getEmailShopApproveNoti(shopowner.getShopName()));
				// Send mail
			} else if (status == 3) {
				// actual delete
				if (shopOwnerService.delete(shopowner.getOwnerId())) {
					// actual delete

					// delete file
					FileHelper.deleteImageFile(shopowner.getShopLogoPath());
					// delete file

					// Send mail
//					mailService.send(fromEmail, toEmail, "Refuse shop noti", MailHelper.getEmailShopRefuseNoti(shopowner.getShopName()));
					// Send mail

					redirectAttributes.addFlashAttribute("successUpdated", true);
				} else {
					redirectAttributes.addFlashAttribute("alertDeleted", true);
				}
				return "redirect:/admin/shop-owner/waiting-for-approval--shopowners";
			}

			// edit shopowner
			shopowner.setUpdatedAt(new Date());
			if (shopOwnerService.save(shopowner)) {
				redirectAttributes.addFlashAttribute("successUpdated", true);
			} else {
				redirectAttributes.addFlashAttribute("alertUpdated", true);
			}
			// edit shopowner
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertUpdated", true);
		}
		return "redirect:/admin/shop-owner";
	}

	@GetMapping({ "delete/{id}" })
	public String delete(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id) {
		try {
			var baseShop = shopOwnerService.findById(id);
			var user = baseShop.getUser();
			user.setRole(new Role(4));
			userService.save(user);

			// actual delete
			if (shopOwnerService.delete(id)) {
				// delete file
				FileHelper.deleteImageFile(baseShop.getShopLogoPath());
				// delete file

				redirectAttributes.addFlashAttribute("successDeleted", true);
			} else {
				redirectAttributes.addFlashAttribute("alertDeleted", true);
			}
			// actual delete
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertDeleted", true);
		}
		return "redirect:/admin/shop-owner/deactive-shops";
	}
}
