package com.dodo.web.controllers.shop_owners;

import java.util.Date;
import java.util.List;

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
			//validate
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
 
//	@GetMapping({ "create" })
//	public String createCoupon(ModelMap modelMap, HttpSession session) {
//		Integer ownerId = (Integer) session.getAttribute("ownerId");
//		Integer userId = (Integer) session.getAttribute("userId");
//
//		if (ownerId != null) {
//			modelMap.put("ownerId", ownerId);
//			modelMap.put("userId", userId);// Lưu ownerId vào model để sử dụng trong biểu mẫu
//		} else {
//			// Xử lý trường hợp khi ownerId không tồn tại trong phiên
//			// Ví dụ: Chuyển hướng đến trang đăng nhập hoặc xử lý lỗi
//			return "redirect:/login"; // Điều hướng đến trang đăng nhập
//		}
//
//		modelMap.put("promotion", new Promotion());
//		List<Product> products = productService.getProductsByOwnerId2(ownerId);
//		modelMap.put("products", products);
//
//		return "shopOwners/promotionshop/create";
//	}
//
//	@PostMapping({ "create" })
//	public String create(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirectAttributes,
//			@ModelAttribute("promotion") @Valid Promotion category, BindingResult result, BindingResult bindingResult,
//			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//		try {
//			Integer ownerId = Integer.parseInt(request.getParameter("ownerId"));
//			Shopowner shopowner = shopOwnerService.findById(ownerId);
//			category.setCreatedAt(new Date());
//			category.setShopowner(shopowner);
//			String discountAmountParamStr = request.getParameter("discountAmount");
//			if (bindingResult.hasErrors()) {
//				modelMap.put("ownerId", ownerId);
//				List<Product> products = productService.getProductsByOwnerId2(ownerId);
//				modelMap.put("products", products);
//				return "shopOwners/promotionshop/create";
//			}
//
//			double discountAmount = 0.0; // Default value or handle it as appropriate
//
//			if (discountAmountParamStr != null && !discountAmountParamStr.trim().isEmpty()) {
//				try {
//					discountAmount = Double.parseDouble(discountAmountParamStr);
//
//					// Kiểm tra xem giá trị có là số âm không
//					if (discountAmount < 0) {
//						// Set the error message
//						redirectAttributes.addFlashAttribute("errorMessage", "Discount amount cannot be negative");
//						return "redirect:/shop-owners/promotion-shop/create";
//					}
//
//				} catch (NumberFormatException e) {
//					// Handle the parsing error
//					e.printStackTrace();
//					// You might want to add a flash attribute with an error message and redirect
//					redirectAttributes.addFlashAttribute("errorMessage", "Error parsing discount amount");
//					return "redirect:/shop-owners/promotion-shop/create";
//				}
//			}
//			category.setStartDate(java.sql.Date.valueOf(startDate));
//			category.setEndDate(java.sql.Date.valueOf(endDate));
//
//			if (startDate == null || endDate == null) {
//				redirectAttributes.addFlashAttribute("errorMessage",
//						"Please enter complete information for both start and end dates");
//				return "redirect:/shop-owners/promotion-shop/create";
//			}
//
//			// Lấy ngày hiện tại
//			LocalDate currentDate = LocalDate.now();
//
//			// Kiểm tra ngày bắt đầu
//			if (startDate.isBefore(currentDate)) {
//				redirectAttributes.addFlashAttribute("errorMessage",
//						"Past start date, please select present or future date");
//				return "redirect:/shop-owners/promotion-shop/create";
//			}
//
//			// Kiểm tra ngày kết thúc
//			if (endDate.isBefore(currentDate)) {
//				redirectAttributes.addFlashAttribute("errorMessage",
//						"Past end date, please select present or future date");
//				return "redirect:/shop-owners/promotion-shop/create";
//			}
//
//			// Kiểm tra xem ngày kết thúc có trước ngày bắt đầu không
//			if (endDate.isBefore(startDate)) {
//				redirectAttributes.addFlashAttribute("errorMessage", "The end date must be after the start date");
//				return "redirect:/shop-owners/promotion-shop/create";
//			}
//
//			// Tiếp tục lưu Promotion nếu kiểm tra qua
//			if (promotionService.save(category)) {
//				redirectAttributes.addFlashAttribute("successMessage", "Promotion created successfully");
//				request.setAttribute("successMessage", "Promotion created successfully");
//			} else {
//				redirectAttributes.addFlashAttribute("errorMessage", "Coupon creation failed");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("alertCreated", true);
//		}
//		return "redirect:/shop-owners/promotion-shop";
//	}
//
//	@GetMapping({ "delete/{promotionId}" })
//	public String delete(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirectAttributes,
//			@PathVariable("promotionId") int promotionId, HttpSession session) {
//		try {
//			// Lấy ownerId và userId từ session
//			Integer ownerId = (Integer) session.getAttribute("ownerId");
//			Integer userId = (Integer) session.getAttribute("userId");
//
//			// Kiểm tra xem ownerId và userId có tồn tại trong session hay không
//			if (ownerId != null && userId != null) {
//				// Kiểm tra xem userId có phù hợp với ownerId (cùng một shop) hay không
//				if (userId.equals(ownerId)) {
//					// Thực hiện xóa ở đây
//					if (promotionService.delete(promotionId)) {
//						redirectAttributes.addFlashAttribute("successMessage", "Promotion created successfully");
//						request.setAttribute("successMessage", "Promotion delete successfully");
//					} else {
//						redirectAttributes.addFlashAttribute("errorMessage", "Promotion deteled failed");
//					}
//				} else {
//					// Xử lý trường hợp khi userId không thuộc cùng một shop
//					return "redirect:/error/404"; // Chuyển hướng đến trang lỗi
//				}
//			} else {
//				// Xử lý trường hợp khi ownerId hoặc userId không tồn tại trong session
//				return "redirect:/error/404"; // Chuyển hướng đến trang lỗi
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("alertDeleted", true);
//		}
//		return "redirect:/shop-owners/promotion-shop";
//	}
//
//	@GetMapping({ "edit/{promotionId}" })
//	public String edit(ModelMap modelMap, RedirectAttributes redirectAttributes, HttpServletRequest request,
//			@PathVariable("promotionId") int promotionId, HttpSession session) {
//		try {
//			// Lấy ownerId và userId từ session
//			Integer ownerId = (Integer) session.getAttribute("ownerId");
//			Integer userId = (Integer) session.getAttribute("userId");
//
//			// Kiểm tra xem ownerId và userId có tồn tại trong session hay không
//			if (ownerId != null && userId != null) {
//				// Lấy category từ cơ sở dữ liệu bằng ID
//				Promotion promotion = promotionService.findById(promotionId);
//
//				if (promotion != null) {
//					// Kiểm tra xem userId có phù hợp với ownerId (cùng một shop) hay không
//					if (userId.equals(ownerId)) {
//						modelMap.put("promotion", promotion);
//						List<Product> products = productService.getProductsByOwnerId2(ownerId);
//						modelMap.put("products", products);
//						modelMap.put("ownerId", ownerId);
//						modelMap.put("userId", userId);
//
//						return "shopOwners/promotionshop/edit";
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
//			return "redirect:/shop-owners/promotion-shop";
//		}
//	}
//
//	@PostMapping("/edit/{promotionId}")
//	public String edit(HttpServletRequest request, ModelMap modelMap, HttpSession session,
//			RedirectAttributes redirectAttributes, @ModelAttribute("promotion") @Valid Promotion promotion,
//			BindingResult bindingResult, @PathVariable("promotionId") int promotionId,
//			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//
//		Integer ownerId = Integer.parseInt(request.getParameter("ownerId"));
//		if (bindingResult.hasErrors()) {
//			modelMap.put("ownerId", ownerId);
//			List<Product> products = productService.getProductsByOwnerId2(ownerId);
//			modelMap.put("products", products);
//			return "shopOwners/promotionshop/edit";
//		}
//
//		Shopowner shopowner = shopOwnerService.findById(ownerId);
//		promotion.setUpdatedAt(new Date());
//		promotion.setShopowner(shopowner);
//
//		String discountAmountParamStr = request.getParameter("discountAmount");
//
//		double discountAmount = 0.0; // Default value or handle it as appropriate
//
//		if (discountAmountParamStr != null && !discountAmountParamStr.trim().isEmpty()) {
//			try {
//				discountAmount = Double.parseDouble(discountAmountParamStr);
//
//				// Kiểm tra xem giá trị có là số âm không
//				if (discountAmount < 0) {
//					// Set the error message
//					redirectAttributes.addFlashAttribute("errorMessage", "Discount amount cannot be negative");
//					return "redirect:/shop-owners/promotion-shop/edit/" + promotionId;
//				}
//
//			} catch (NumberFormatException e) {
//				// Handle the parsing error
//				e.printStackTrace();
//				// You might want to add a flash attribute with an error message and redirect
//				redirectAttributes.addFlashAttribute("errorMessage", "Error parsing discount amount");
//				return "redirect:/shop-owners/promotion-shop/edit/" + promotionId;
//			}
//		}
//		promotion.setStartDate(java.sql.Date.valueOf(startDate));
//		promotion.setEndDate(java.sql.Date.valueOf(endDate));
//
//		if (startDate == null || endDate == null) {
//			redirectAttributes.addFlashAttribute("errorMessage",
//					"Please enter complete information for both start and end dates");
//			return "redirect:/shop-owners/promotion-shop/edit/" + promotionId;
//		}
//
//		// Lấy ngày hiện tại
//		LocalDate currentDate = LocalDate.now();
//
//		// Kiểm tra ngày bắt đầu
//		if (startDate.isBefore(currentDate)) {
//			redirectAttributes.addFlashAttribute("errorMessage",
//					"Past start date, please select present or future date");
//			return "redirect:/shop-owners/promotion-shop/edit/" + promotionId;
//		}
//
//		// Kiểm tra ngày kết thúc
//		if (endDate.isBefore(currentDate)) {
//			redirectAttributes.addFlashAttribute("errorMessage", "Past end date, please select present or future date");
//			return "redirect:/shop-owners/promotion-shop/edit";
//		}
//
//		// Kiểm tra xem ngày kết thúc có trước ngày bắt đầu không
//		if (endDate.isBefore(startDate)) {
//			redirectAttributes.addFlashAttribute("errorMessage", "The end date must be after the start date");
//			return "redirect:/shop-owners/promotion-shop/edit/" + promotionId;
//		}
//
//		// Save the updated coupon to the database or process it as needed using
//		// couponService
//		if (promotionService.save(promotion)) {
//			redirectAttributes.addFlashAttribute("successMessage", "Promotion update successfully");
//			request.setAttribute("successMessage", "Promotion update successfully");
//		} else {
//			redirectAttributes.addFlashAttribute("errorMessage", "Coupon update failed");
//		}
//
//		return "redirect:/shop-owners/promotion-shop";
//	}

}
