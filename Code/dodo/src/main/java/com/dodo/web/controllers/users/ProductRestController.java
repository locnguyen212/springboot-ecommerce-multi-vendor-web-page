package com.dodo.web.controllers.users;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.web.IServices.IProductService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.IServices.IWishlistService;
import com.dodo.web.models.Product;
import com.dodo.web.models.User;
import com.dodo.web.models.Wishlist;
import com.dodo.web.modelview.ProductView;

@RestController
@RequestMapping("/user/api/products")
public class ProductRestController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IWishlistService wishlistService;

	@GetMapping("/searchHeader")
	public List<ProductView> searchHeader(@RequestParam("productName") String productName,
			@RequestParam("categoryName") String categoryName) {
		try {
			List<ProductView> listSearchProducts = productService.findProductViewByProductName(productName,
					categoryName);
			return listSearchProducts;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@PostMapping("/addWishList")
	public ResponseEntity<String> AddWishList(@RequestParam("productId") Integer pId,
			@RequestParam("userId") Integer uId) {
		try {
			Product p = productService.findById(pId);
			User u = userService.findById(uId);
			if (p == null || u == null) {
				return new ResponseEntity<>("Error", HttpStatus.OK);
			}
			List<Wishlist> check = wishlistService.findByUserUserId(uId);
			boolean productExists = check.stream()
					.anyMatch(wishlist -> wishlist.getProduct().equals(p) && wishlist.getUser().equals(u));
			if (!productExists) {
				Wishlist wl = new Wishlist();
				wl.setProduct(p);
				wl.setUser(u);
				wl.setCreatedAt(new Date());
				wishlistService.save(wl);
				return new ResponseEntity<>("Add Success", HttpStatus.OK);
			}else{
				return new ResponseEntity<>("Product has been added Wish List", HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}

}
