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

//  @GetMapping("/index")
//  /**
//   * The Foo class is a silly example to illustrate documentation 
//   * comments.
//   */
//  public String Index(Model model, @RequestParam(required = false) String keyword,
//      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
//    try {
//    	model.addAttribute("title", "Shop-owners title");
//    	return "users/home/index";
//    } catch (Exception e) {
//      model.addAttribute("message", e.getMessage());
//    }
//
//    return "shop-owners/home/index";
//  }

//	@GetMapping({"index", "", "/"})
//	public String index(ModelMap modelMap, HttpSession session) {
//		session.setAttribute("shopname", userService.findByShopName(AuthHelper.getLoggedInUsername()).getShopName());
//
//	    return "shopOwners/home/index";
//	}

//	@GetMapping({"index", "", "/"})
//	public String index(ModelMap modelMap, HttpSession session, Authentication authentication) {
//	    if (authentication != null) {
//	        String loggedInUsername = authentication.getName();
//	        User shopOwner = userService.findByUsername(loggedInUsername);
//
//	        if (shopOwner != null) {
//	            Integer userId = shopOwner.getUserId();
//	            session.setAttribute("userId", userId); // Store userId as an Integer
//	        } else {
//	            // Handle the case when shopOwner is null
//	        }
//	    }
//
//	    return "shopOwners/home/index";
//	} 

//	@GetMapping({ "index" })
//	public String index(ModelMap modelMap, Model model, HttpSession session, Authentication authentication,
//			RedirectAttributes redirectAttributes) {
//		try {
//			// Lấy ownerId và userId từ session
//			Integer ownerId = (Integer) session.getAttribute("ownerId");
//			Integer userId = (Integer) session.getAttribute("userId");
//
//			// Kiểm tra xem ownerId và userId có tồn tại trong session hay không
//			// Kiểm tra xem ownerId và userId có tồn tại trong session hay không
//			if (ownerId != null && userId != null) {
//				// Lấy category từ cơ sở dữ liệu bằng ID
//				Shopowner shopowner = shopOwnerService.findById(ownerId);
//
//				if (shopowner != null) {
//					// Kiểm tra xem userId có phù hợp với ownerId (cùng một shop) hay không
//					if (userId.equals(ownerId)) {
//						modelMap.put("shopowner", shopowner);
//
//						modelMap.put("ownerId", ownerId);
//						modelMap.put("userId", userId);
//
//						return "shopOwners/shopsetting/index";
//					} else {
//						// Xử lý trường hợp khi userId không thuộc cùng một shop
//						return "redirect:/error/404"; // Chuyển hướng đến trang lỗi
//					}
//				} else {
//					// Xử lý trường hợp khi không tìm thấy category
//					return "redirect:/error/404"; // Chuyển hướng đến trang lỗi
//				}
//			} else {
//				// Xử lý trường hợp khi ownerId hoặc userId không tồn tại trong session
//				return "redirect:/login"; // Điều hướng đến trang đăng nhập
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("alertDeleted", true);
//			return "redirect:/shop-owners/shopowner-setting/index";
//		}
//
//	}
//
//	@GetMapping({ "edit/{ownerId}" })
//	public String edit(ModelMap modelMap, Model model, HttpSession session, Authentication authentication,
//			RedirectAttributes redirectAttributes) {
//		try {
//			// Lấy ownerId và userId từ session
//			Integer ownerId = (Integer) session.getAttribute("ownerId");
//			Integer userId = (Integer) session.getAttribute("userId");
//
//			// Kiểm tra xem ownerId và userId có tồn tại trong session hay không
//			// Kiểm tra xem ownerId và userId có tồn tại trong session hay không
//			if (ownerId != null && userId != null) {
//				// Lấy category từ cơ sở dữ liệu bằng ID
//				Shopowner shopowner = shopOwnerService.findById(ownerId);
//
//				if (shopowner != null) {
//					// Kiểm tra xem userId có phù hợp với ownerId (cùng một shop) hay không
//					if (userId.equals(ownerId)) {
//						modelMap.put("shopowner", shopowner);
//
//						modelMap.put("ownerId", ownerId);
//						modelMap.put("userId", userId);
//
//						return "shopOwners/shopsetting/edit";
//					} else {
//						// Xử lý trường hợp khi userId không thuộc cùng một shop
//						return "redirect:/error/404"; // Chuyển hướng đến trang lỗi
//					}
//				} else {
//					// Xử lý trường hợp khi không tìm thấy category
//					return "redirect:/error/404"; // Chuyển hướng đến trang lỗi
//				}
//			} else {
//				// Xử lý trường hợp khi ownerId hoặc userId không tồn tại trong session
//				return "redirect:/login"; // Điều hướng đến trang đăng nhập
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("alertDeleted", true);
//			return "redirect:/shop-owners/shopowner-setting/index";
//		}
//
//	}
//
//	@PostMapping("/edit/{ownerId}")
//	public String edit(HttpServletRequest request, ModelMap modelMap, HttpSession session,
//			RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile file,
//			@ModelAttribute("shopowner") @Valid Shopowner shopowner, @PathVariable("ownerId") int id,
//			BindingResult bindingResult) {
//		try {
//			Integer ownerId = Integer.parseInt(request.getParameter("ownerId"));
//			Integer userId = Integer.parseInt(request.getParameter("userId"));
//			if (bindingResult.hasErrors()) {
//				modelMap.put("ownerId", ownerId);
//				modelMap.put("userId", userId);
//				return "shopOwners/shopsetting/edit";
//			}
//
//			if (file.getSize() != 0) {
//				var fileName = FileHelper.saveImageFile(file);
//				shopowner.setShopLogoPath(fileName);
//			}
//
//			shopowner.setUpdatedAt(new Date());
//			User shopowner2 = userService.findById(userId);
//			shopowner.setUser(shopowner2);
//
//			if (shopOwnerService.save(shopowner)) {
//				session.setAttribute("shopLogoPath", shopowner.getShopLogoPath());
//				session.setAttribute("shopName", shopowner.getShopName());
//
//				redirectAttributes.addFlashAttribute("successMessage", "Shopowner update successfully");
//			} else {
//				redirectAttributes.addFlashAttribute("errorMessage", "Shopowner update failed");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}
//
//		return "redirect:/shop-owners/shopowner-setting/index";
//	}

}
