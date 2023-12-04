package com.dodo.web.controllers.users;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.web.IServices.IItemService;
import com.dodo.web.models.Item;
import com.dodo.web.models.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/api/cart")
public class CartRestController {

	@Autowired
	private IItemService itemService;

	@PostMapping("/updateCart")
	public ResponseEntity<String> updateCartItem(@RequestParam int productId, @RequestParam Integer quantity,
			HttpSession session) {
		User user = (User) session.getAttribute("user");

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

			//Check for existing products
			if (existingItem != null) {
	            LocalDateTime currentDateTime = LocalDateTime.now();
	            Timestamp timestamp = Timestamp.valueOf(currentDateTime);
				// update quantity product
				int newQuantity = quantity;
				existingItem.setQuantity(newQuantity);
				existingItem.setCreatedAt(timestamp);
				itemService.saveCartToDb(user, userItems);

				return new ResponseEntity<>("Update Success", HttpStatus.OK);
			}
		}

		return new ResponseEntity<>("The product does not exist or an error has occurred", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getTotalQuantityForProduct")
	public ResponseEntity<Integer> getTotalQuantityForProduct(@RequestParam int productId, HttpSession session) {
		int totalQuantityInCarts = itemService.getTotalQuantityForProduct(productId);
	    return new ResponseEntity<>(totalQuantityInCarts, HttpStatus.OK);
	}

	@GetMapping("/getCountItem")
	public ResponseEntity<Integer> getCountItem(HttpSession session) {
		User user = (User) session.getAttribute("user");

		if (user != null) {
			int totalItem = itemService.countItemsByUserId(user.getUserId());
			return new ResponseEntity<>(totalItem, HttpStatus.OK);

		}
		return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
	}


}
