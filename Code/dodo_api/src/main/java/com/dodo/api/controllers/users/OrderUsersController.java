package com.dodo.api.controllers.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodo.api.IServices.IItemService;
import com.dodo.api.IServices.IOrderDetailService;
import com.dodo.api.IServices.IOrderService;
import com.dodo.api.IServices.IProductService;
import com.dodo.api.IServices.IPromotionService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.models.Item;
import com.dodo.api.models.Order;
import com.dodo.api.models.Orderdetail;
import com.dodo.api.models.Product;
import com.dodo.api.models.Promotion;
import com.dodo.api.models.Shopowner;
import com.dodo.api.models.User;
import com.dodo.api.modelview.CartView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = { "user/order" })
public class OrderUsersController {

//	@Autowired
//	private IProductService productService;
//
//	@Autowired
//	private IItemService itemService;
//
//	@Autowired
//	private IShopOwnerService shopOwnerService;
//
//	@Autowired
//	private IOrderService orderService;
//
//	@Autowired
//	private IOrderDetailService orderDetailService;
//
//	@Autowired
//	private IPromotionService promotionService;
//
//	private boolean orderPlaced = false;
//
//	@PostMapping("/index")
//	public String order(@RequestParam("listItemId") String listItemId, HttpSession session, ModelMap modelMap) {
//		modelMap.addAttribute("message", null);
//		User checkUser = (User) session.getAttribute("user");
//		if (listItemId == "") {
//			return "redirect:/user/cart/index";
//		}
//		if (checkUser != null) {
//			// check Quantity realtime start
//			List<Item> cartItems = itemService.getAllByUser(checkUser.getUserId());
//			for (Item item : cartItems) {
//				Product product = item.getProduct();
//				int availableQuantity = product.getStockQuantity();
//				int cartQuantity = item.getQuantity();
//				if (product.getStatus() && product.getCategory().getStatus() && product.getShopowner().getStatus()) {
//					if (cartQuantity > availableQuantity) {
//						item.setQuantity(availableQuantity);
//						itemService.save(item);
//						modelMap.addAttribute("message", "Product quantity has changed");
//					}
//				} else {
//					itemService.delete(item.getItemId());
//				}
//
//			}
//			// check Quantity realtime end
//			List<Integer> itemIdList = Arrays.stream(listItemId.split(",")).map(Integer::valueOf)
//					.collect(Collectors.toList());
//			List<CartView> productsInItem = itemService.getCartViewFromItems(checkUser.getUserId(), itemIdList);
//			List<CartView> filteredProducts = new ArrayList<>();
//
//			//check số lượng realtime nếu hết hàng remove hiển thị
//			for (CartView product : productsInItem) {
//				List<Item> filteredItems = product.getItems().stream()
//					.filter(item -> item.getQuantity() != 0)
//					.collect(Collectors.toList());
//				if (!filteredItems.isEmpty()) {
//					filteredProducts.add(new CartView(product.getShopowner(), filteredItems));
//				}
//			}
//
//			modelMap.addAttribute("productsInItem", filteredProducts);
//			return "users/order/index";
//		}
//
//		return "redirect:/user/account/login";
//	}
//
//	@PostMapping("/checkout")
//	public String checkout(@RequestParam("listProductId") String listProductId, HttpSession session,
//			ModelMap modelMap) {
//		modelMap.addAttribute("message", null);
//		User checkUser = (User) session.getAttribute("user");
//		if (listProductId == "") {
//			return "redirect:/user/cart/index";
//		}
//		if (checkUser != null) {
//			// check Quantity realtime start
//			List<Item> cartItems = itemService.getAllByUser(checkUser.getUserId());
//			for (Item item : cartItems) {
//				Product product = item.getProduct();
//				int availableQuantity = product.getStockQuantity();
//				int cartQuantity = item.getQuantity();
//
//				if (product.getStatus() && product.getCategory().getStatus() && product.getShopowner().getStatus()) {
//					if (cartQuantity > availableQuantity) {
//						item.setQuantity(availableQuantity);
//						itemService.save(item);
//						return "errors/500";
//					}
//				} else {
//					itemService.delete(item.getItemId());
//					return "errors/500";
//				}
//			}
//			// check Quantity realtime end
//
//			List<Integer> productIdList = Arrays.stream(listProductId.split(",")).map(Integer::valueOf)
//					.collect(Collectors.toList());
//
//			Map<Integer, List<Integer>> shopProductMap = new HashMap<>();
//			for (int productId : productIdList) {
//				Product product = productService.findProductById(productId, true, null, true);
//				int shopOwnerId = product.getShopowner().getOwnerId();
//				if (!shopProductMap.containsKey(shopOwnerId)) {
//					shopProductMap.put(shopOwnerId, new ArrayList<>());
//				}
//				shopProductMap.get(shopOwnerId).add(productId);
//			}
//
//			for (Map.Entry<Integer, List<Integer>> entry : shopProductMap.entrySet()) {
//				int shopOwnerId = entry.getKey();
//				List<Integer> shopProductIds = entry.getValue();
//
//				// Tạo một đơn hàng theo shopowner
//				Shopowner shop = shopOwnerService.findById(shopOwnerId);
//				Order order = new Order();
//				// Thông tin đơn hàng
//				order.setShopowner(shop);
//				order.setOrderDate(new Date());
//				order.setOrderStatus("Waiting For Approval");
//				order.setPaymentMethod("Cash on Delivery");
//				order.setUser(checkUser);
//				order.setShippingAddress(checkUser.getAddress());
//				Order savedOrder = orderService.save(order);
//
//				for (int productId : shopProductIds) {
//					Product product = productService.findProductById(productId, true, null, true);
//					Item item = itemService.findByProductId(productId, checkUser.getUserId());
//					// Tạo đơn hàng chi tiết cho sản phẩm
//					int quantityItem = item.getQuantity();
//					Double itemPrice = item.getProduct().getPrice(); // Giá gốc của sản phẩm
//					Double unitPrice;
//
//					List<Promotion> promotion = promotionService.findByProductProductId(productId);
//					if (!promotion.isEmpty()) {
//						Date now = new Date();
//						Date startDate = promotion.get(0).getStartDate();
//						Date endDate = promotion.get(0).getEndDate();
//						if (now.after(startDate) && now.before(endDate)) {
//							// Giảm giá đang diễn ra
//							Double discountAmount = promotion.get(0).getDiscountAmount();
//							unitPrice = itemPrice * (1 - discountAmount / 100); // Giá sau giảm giá
//						} else {
//							// Không có giảm giá
//							unitPrice = itemPrice;
//						}
//					} else {
//						// Không có giảm giá, sử dụng giá gốc
//						unitPrice = itemPrice;
//					}
//					Double subtotal = unitPrice * quantityItem;
//
//					Orderdetail orderDetail = new Orderdetail();
//					// Thông tin đơn hàng chi tiết
//					orderDetail.setProduct(product);
//					orderDetail.setOrder(savedOrder);
//					orderDetail.setQuantity(quantityItem);
//					orderDetail.setUnitPrice(unitPrice);
//					orderDetail.setSubtotal(subtotal);
//					orderDetail.setProductName(product.getProductName());
//					orderDetail.setProductDescription(product.getDescription());
//					orderDetail.setProductImage(product.getProductImage());
//					orderDetail.setIsReviewed(false);
//					orderDetailService.save(orderDetail);
//
//					// Xóa sản phẩm đã được thêm vào đơn hàng khỏi giỏ hàng
//					itemService.delete(item.getItemId());
//				}
//				List<Orderdetail> listOrderdetail = orderDetailService.findByOrderOrderId(savedOrder.getOrderId());
//				Double totalAmount = calculateTotalAmount(listOrderdetail);
//				// Cập nhật giá TotalAmount cho đơn hàng
//				savedOrder.setTotalAmount(totalAmount);
//				orderService.save(savedOrder);
//				var totalItem = itemService.countItemsByUserId(checkUser.getUserId());
//				session.setAttribute("totalItem", totalItem);
//				orderPlaced = true;
//			}
//
//			return "redirect:/user/order/checkout-success";
//		}
//
//		return "redirect:/user/account/login";
//	}
//
//	@GetMapping("/checkout-success")
//	public String checkoutsuccess(ModelMap modelMap, HttpSession session) {
//		User checkUser = (User) session.getAttribute("user");
//		if (checkUser != null) {
//			if (orderPlaced) {
//				orderPlaced = false;
//				return "users/order/checkout-success";
//			} else {
//				return "error/404";
//			}
//		}
//		return "redirect:/user/account/login";
//
//	}
//
//	private Double calculateTotalAmount(List<Orderdetail> orderdetail) {
//		Double totalAmount = 0.0;
//
//		for (Orderdetail orderDetail : orderdetail) {
//			Double subtotal = orderDetail.getSubtotal();
//			totalAmount += subtotal;
//		}
//		return totalAmount;
//	}
}
