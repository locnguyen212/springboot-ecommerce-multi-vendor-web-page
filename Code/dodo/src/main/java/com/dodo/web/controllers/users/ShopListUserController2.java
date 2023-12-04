package com.dodo.web.controllers.users;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodo.web.IServices.IOrderDetailService;
import com.dodo.web.IServices.IProductService;
import com.dodo.web.IServices.IReviewService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.models.Orderdetail;
import com.dodo.web.models.Product;
import com.dodo.web.models.Review;
import com.dodo.web.models.User;
import com.dodo.web.modelview.ProductView;
import com.dodo.web.validators.ShopownerUniqueValidator;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = { "user/shop-list" })
public class ShopListUserController2 {
	@Autowired
	private IUserService userService;

	@Autowired
	private IShopOwnerService shopOwnerService;

	@Autowired
	private ShopownerUniqueValidator shopownerUniqueValidator;

	@Autowired
	private IProductService iProductService;

	@Autowired
	private IOrderDetailService orderDetailService;

	@Autowired
	private IReviewService reviewService;

	@GetMapping("/listProductsByShopName")
	public String listProductsByShopName(@RequestParam("ownerId") String ownerId,
			@RequestParam("shopName") String shopName,
			@RequestParam("shopLogoPath") String shopLogoPath,
			ModelMap modelMap) {
		// Truy vấn danh sách sản phẩm dựa trên ownerId

		List<Product> shopProducts = iProductService.getProductsByOwnerId2(Integer.parseInt(ownerId));

		if (shopProducts != null) {
			modelMap.addAttribute("shopOwnerCoupon", shopProducts);
			modelMap.addAttribute("shopName", shopName);
			modelMap.addAttribute("shopLogoPath", shopLogoPath); // Thêm shopName vào modelMap
		}

		return "users/shoplist/list";
	}

	@GetMapping("/v_shop")
	public String viewShop(@RequestParam("shopId") String id, ModelMap modelMap,
			HttpSession session, @RequestParam(defaultValue = "1") String page) {
		Integer idShop = Integer.parseInt(id);
		var shopInfo = shopOwnerService.findById(idShop);
		int pageSize = 4;
		int pageNo = Integer.parseInt(page);
		User checkUser = (User) session.getAttribute("user");
		Page<ProductView> shopProducts = iProductService.getListProductByShop(true, null, true, idShop, pageNo,
				pageSize);
		if (checkUser != null) {
			if (checkUser.getUserId() == idShop) {
				shopProducts = iProductService.getListProductByShop(null, null, null, idShop, pageNo, pageSize);
			}
		}
		if (shopProducts != null && shopInfo != null) {
			var listProduct = shopProducts.getContent();
			modelMap.addAttribute("listProductForShop", listProduct);
			modelMap.addAttribute("shopName", listProduct.get(0).getSh_shopName());
			modelMap.addAttribute("shopLogoPath", listProduct.get(0).getSh_shopImage());
			// get shopDescription
			modelMap.addAttribute("shopDescription", shopInfo.getShopDescription());

			modelMap.addAttribute("currentPage", pageNo);
			modelMap.addAttribute("totalPages", shopProducts.getTotalPages());
			modelMap.addAttribute("totalItems", shopProducts.getTotalElements());
			modelMap.addAttribute("s_shopId", idShop);

		} else {
			return "redirect:/error/404";
		}
		Integer totalComments = 0;
		Double averageRating = 0.0;
		List<Review> listrv = reviewService.getReviewsByShopId(idShop);
		if (listrv != null) {
			totalComments = listrv.size();
			List<Integer> ratings = listrv.stream()
					.map(Review::getRating)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
					averageRating = ratings.isEmpty() ? 0
					: Math.round(ratings.stream()
							.mapToInt(Integer::intValue)
							.average()
							.orElse(0) * 100.0) / 100.0;

		}
		List<Orderdetail> allDetailProduct = orderDetailService.findAll();
		modelMap.addAttribute("allDetailProduct", allDetailProduct);
		modelMap.addAttribute("sh_totalComments", totalComments);
		modelMap.addAttribute("sh_averageRating", averageRating);
		return "users/shoplist/list";
	}

}
