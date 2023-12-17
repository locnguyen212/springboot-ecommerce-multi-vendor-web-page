package com.dodo.api.controllers.users;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.IServices.IItemService;
import com.dodo.api.IServices.IOrderDetailService;
import com.dodo.api.IServices.IOrderService;
import com.dodo.api.IServices.IProductService;
import com.dodo.api.IServices.IShopOwnerCouponService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.IServices.IWishlistService;
import com.dodo.api.models.Category;
import com.dodo.api.models.Orderdetail;
import com.dodo.api.models.Product;
import com.dodo.api.models.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = { "", "user/home" })
public class HomeUsersController {
//
//	@Autowired
//	private IProductService productService;
//
//	@Autowired
//	private ICategoryService categoryService;
//
//	@Autowired
//	private IItemService itemService;
//
//	@Autowired
//	IWishlistService wishlistService;
//
//	@Autowired
//	IUserService userService;
//
//	@Autowired
//	private IOrderService orderService;
//
//	@Autowired
//	private IOrderDetailService orderDetailService;
//
//	@Autowired
//	IShopOwnerService shopownerService;
//	
//	@Autowired
//	IShopOwnerCouponService couponService;
//
//	@GetMapping({ "index", "", "/" })
//	public String index(ModelMap modelMap, Authentication authentication,
//			HttpSession session, HttpServletRequest request) {
//		
//		//session = request.getSession();
//   		//LOC
//		// check if logged in, if so, set avatar, if not, do nothing
//		if(authentication!=null && !(authentication instanceof AnonymousAuthenticationToken)) {
//			//THIEN set session User start
//			User user = userService.findByUsername(authentication.getName());
//			if (user != null) {
//				var totalItem = itemService.countItemsByUserId(user.getUserId());
//				int totalOrderUnPaid = orderService.getMyOrder(user.getUserId()).size();
//				session.setAttribute("totalItem", totalItem);
//				session.setAttribute("totalOrderUnPaid", totalOrderUnPaid);
//				session.setAttribute("user", user);
//			}
//			//THIEN set session User end
//			
//			//LOC
//			session.setAttribute("avatar", userService.findByUsername(authentication.getName()).getAvatar());
//			session.setAttribute("isHasShop", userService.findByUsername(authentication.getName()).getShopowners().size() != 0);
//			session.setAttribute("userProfile", userService.findByUsername(authentication.getName()));
//			var shop = shopownerService.findByUserUsername(user.getUsername());
//			if (shop != null) {
//				if (shop.getStatus()) {
//					session.setAttribute("shopStatus", true);
//				} else {
//					session.setAttribute("shopStatus", false);
//				}
//				if (shop != null && shop.getStatus()) {
//					session.setAttribute("shopName", shop.getShopName());
//					session.setAttribute("shopLogoPath", shop.getShopLogoPath());
//					session.setAttribute("shopId", shop.getOwnerId());
//				}
//			}
//			
//			//LOC
//		}	
//		// check if logged in, if so, set avatar, if not, do nothing
//		//LOC
//		
//		List<Product> allProducts = productService.getAllAndStatus();
//		List<Category> allCategory = categoryService.getAllAndStatus();
//		List<Orderdetail>  allDetailProduct = orderDetailService.findAll();
//		Map<Category, List<Product>> productsByCategory = allProducts.stream().collect(Collectors.groupingBy(Product::getCategory));
//		productsByCategory.forEach((category, productList) -> {
//			productList.sort(Comparator.comparing(
//					(Product p) -> p.getUpdatedAt() != null ? p.getUpdatedAt() : p.getCreatedAt(),
//					Comparator.reverseOrder()
//			));
//		});
//		
//
//		//modelMap.addAttribute("allProducts", allProducts);
//		modelMap.addAttribute("allDetailProduct", allDetailProduct);
//		session.setAttribute("allCategory", allCategory);
//		modelMap.addAttribute("productsByCategory", productsByCategory);
//		return "users/home/all_product";
//	}

}
