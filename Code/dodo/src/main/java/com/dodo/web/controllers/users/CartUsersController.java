package com.dodo.web.controllers.users;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodo.web.IServices.IItemService;
import com.dodo.web.IServices.IProductService;
import com.dodo.web.models.Item;
import com.dodo.web.models.Product;
import com.dodo.web.models.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = { "user/cart" })
public class CartUsersController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IItemService itemService;

	@GetMapping("/index")
	public String index(ModelMap modelMap, HttpSession session) {
		User checkUser = (User) session.getAttribute("user");
		if (checkUser != null) {
			// check Quantity realtime start
			List<Item> cartItems = itemService.getAllByUser(checkUser.getUserId());
			for (Item item : cartItems) {
				Product product = item.getProduct();
				int availableQuantity = product.getStockQuantity();
				int cartQuantity = item.getQuantity();
				if (product.getStatus() && product.getCategory().getStatus() && product.getShopowner().getStatus()) {
					if (cartQuantity > availableQuantity) {
						item.setQuantity(availableQuantity);
						itemService.save(item);
						modelMap.addAttribute("message", "Product quantity has changed");
					}
				} 
				else {
					itemService.delete(item.getItemId());
				}

			}
			// check Quantity realtime end
			cartItems = itemService.getAllByUser(checkUser.getUserId());
			modelMap.addAttribute("cartItems", cartItems);
			var totalItem = itemService.countItemsByUserId(checkUser.getUserId());
			session.setAttribute("totalItem", totalItem);
			return "users/cart/index";
		}

		return "redirect:/user/account/login";
	}

	@GetMapping("/add_cart")
	public String addToCart(@RequestParam("id") int productId, @RequestParam("quantity") int quantity,
			HttpSession session, ModelMap modelMap) {

		// Check if the user is logged in or not
		User user = (User) session.getAttribute("user");
		if (user != null) {
			// Check the quantity of products left in stock
			boolean checkQuantity = isValidQuantity(quantity, productId);
			if (!checkQuantity) {
				quantity = 0;
			}
			// Check if the product exists in the user's Item list
			List<Item> userItems = itemService.getAllByUser(user.getUserId());
			Item existingItem = null;

			for (Item item : userItems) {
				if (item.getProduct().getProductId().equals(productId)) {
					existingItem = item;
					break;
				}
			}
			LocalDateTime currentDateTime = LocalDateTime.now();
			Timestamp timestamp = Timestamp.valueOf(currentDateTime);

			if (existingItem != null) {
				// Product already exists, update quantity
				int newQuantity = existingItem.getQuantity() + quantity;
				existingItem.setQuantity(newQuantity);
				existingItem.setCreatedAt(timestamp);
			} else {
				// The product does not exist yet, create a new item
				Product product = productService.findProductById(productId, true, null, true);
				if (product == null) {
					modelMap.addAttribute("message", "Product does not exist");
				} else {
					Item newItem = new Item(user, product, quantity, timestamp);
					userItems.add(newItem);
				}
			}

			itemService.saveCartToDb(user, userItems);
			int totalItem = itemService.countItemsByUserId(user.getUserId());
			session.setAttribute("totalItem", totalItem);
		}
		return "redirect:/user/cart/index";
	}

	@GetMapping("/remove")
	public String removeFromCart(@RequestParam("id") int cartItemId, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			Item cartItemToRemove = itemService.findById(cartItemId);
			if (cartItemToRemove != null) {
				itemService.delete(cartItemId);
				var totalItem = itemService.countItemsByUserId(user.getUserId());
				session.setAttribute("totalItem", totalItem);
			}
			return "redirect:/user/cart/index";
		}
		return "redirect:/user/cart/index";
	}

	@PostMapping("/updateCart")
	public String updateCartItem(ModelMap modelMap, @RequestParam int productId, @RequestParam Integer quantity,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		modelMap.addAttribute("message", "");
		if (user != null) {
			List<Item> userItems = itemService.getAllByUser(user.getUserId());

			// Find the product in the user's Item list
			Item existingItem = null;
			for (Item item : userItems) {
				if (item.getProduct().getProductId().equals(productId)) {
					existingItem = item;
					break;
				}
			}

			// Check for existing products
			if (existingItem != null) {
				LocalDateTime currentDateTime = LocalDateTime.now();
				Timestamp timestamp = Timestamp.valueOf(currentDateTime);
				// Check the quantity of products left in stock
				boolean checkQuantity = isValidQuantity(quantity, productId);
				if (!checkQuantity) {
					quantity = 0;
				}
				// update quantity product
				int newQuantity = quantity;
				existingItem.setQuantity(newQuantity);
				existingItem.setCreatedAt(timestamp);
				itemService.saveCartToDb(user, userItems);
				//load new
				userItems = itemService.getAllByUser(user.getUserId());
				modelMap.put("cartItems", userItems);
				return "users/cart/update-cart";
			}

		}
		return "redirect:/user/account/login";
	}

	public boolean isValidQuantity(int quantity, int productId) {
		Product product = productService.findProductById(productId, null, true, true);
		if (product == null) {
			return false;
		}
		int availableQuantity = product.getStockQuantity();

		 return quantity >= 0 && quantity <= availableQuantity;
	}

}
