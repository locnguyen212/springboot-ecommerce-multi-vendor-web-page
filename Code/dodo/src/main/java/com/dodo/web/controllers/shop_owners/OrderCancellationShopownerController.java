package com.dodo.web.controllers.shop_owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.IOrderCancellationService;
import com.dodo.web.IServices.IOrderService;
import com.dodo.web.IServices.IShopOwnerCouponService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;

@Controller
@RequestMapping("shop-owners/order-cancellation")
public class OrderCancellationShopownerController {
	@Autowired
	IUserService userService;
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IShopOwnerService shopOwnerService;
	
	@Autowired
	IShopOwnerCouponService couponService;
	
	@Autowired
	IOrderCancellationService cancellationService;
 
	@GetMapping({ "index", "", "/" })
	public String index(ModelMap modelMap, Authentication authentication) {
		var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
		modelMap.put("orderCancellation", cancellationService.findByShopownerOwnerId(shopId));
		return "shopOwners/orderCancellation/index";
	}
	 
	@GetMapping({ "accept/{id}" })
	public String accept(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes, @PathVariable("id") int id) {
		try {
			//validate
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
			var cancel = cancellationService.findById(id);
			if(cancel == null || cancel.getShopowner().getOwnerId() != shopId) {
				return "redirect:/error/404";
			}
			//validate
			var order = orderService.findById(cancel.getOrder().getOrderId());
			
			order.setOrderStatus("Cancelled");
			order.setUpdatedAt(new java.util.Date());
			
			cancel.setStatus(true);
			cancel.setCancellationDate(new java.util.Date());
			
			if(cancellationService.save(cancel) && orderService.saveBool(order)) {
				redirectAttributes.addFlashAttribute("successReady", true);
			} else {
				redirectAttributes.addFlashAttribute("alert", true);
			}	
			
			return "redirect:/shop-owners/order-cancellation/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
		
	}
	
	@GetMapping({ "refuse/{id}" })
	public String refuse(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes, @PathVariable("id") int id) {
		try {
			//validate
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
			var cancel = cancellationService.findById(id);
			if(cancel == null || cancel.getShopowner().getOwnerId() != shopId) {
				return "redirect:/error/404";
			}
			//validate			
			cancel.setStatus(false);
			cancel.setCancellationDate(new java.util.Date());
			
			if(cancellationService.save(cancel)) {
				redirectAttributes.addFlashAttribute("successReady", true);
			} else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			
			return "redirect:/shop-owners/order-cancellation/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}

//	@GetMapping("/cancel/{cancellationId}")
//	public String cancel(ModelMap modelMap, RedirectAttributes redirectAttributes,
//			@PathVariable("cancellationId") int cancellationId, HttpSession session) {
//
//		try {
//			Integer ownerId = (Integer) session.getAttribute("ownerId");
//			Integer userId = (Integer) session.getAttribute("userId");
//
//			if (ownerId != null && userId != null) {
//				modelMap.put("ownerId", ownerId);
//				modelMap.put("userId", userId);
//
//				Ordercancellation ordercancellation = cancellationService.findById(cancellationId);
//
//				if (ordercancellation != null) {
//					Boolean status = ordercancellation.getStatus();
//
//					// Kiểm tra status và xác định việc cho phép cancel
//					if (status == null) {
//						Date upDate = new Date(System.currentTimeMillis());
//						ordercancellation.setCancellationDate(upDate);
//						ordercancellation.setStatus(true);
//
//						if (userId.equals(ownerId)) {
//							if (cancellationService.save(ordercancellation)) {
//								redirectAttributes.addFlashAttribute("successMessage", "Order canceled successfully");
//							} else {
//								redirectAttributes.addFlashAttribute("errorMessage", "Order cancellation failed");
//							}
//						} else {
//							redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized access");
//							return "redirect:/error/404";
//						}
//					} else {
//						redirectAttributes.addFlashAttribute("errorMessage",
//								"Unable to cancel. Order status is not pending.");
//						return "redirect:/error/404";
//					}
//				} else {
//					redirectAttributes.addFlashAttribute("errorMessage", "Order not found");
//				}
//			} else {
//				return "redirect:/login";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("alertDeleted", true);
//		}
//		return "redirect:/shop-owners/order-Cancell-Ation";
//	}
//
//	@GetMapping("/notcancel/{cancellationId}")
//	public String notcancel(ModelMap modelMap, RedirectAttributes redirectAttributes,
//			@PathVariable("cancellationId") int cancellationId, HttpSession session) {
//
//		try {
//			Integer ownerId = (Integer) session.getAttribute("ownerId");
//			Integer userId = (Integer) session.getAttribute("userId");
//
//			if (ownerId != null && userId != null) {
//				modelMap.put("ownerId", ownerId);
//				modelMap.put("userId", userId);
//
//				Ordercancellation ordercancellation = cancellationService.findById(cancellationId);
//
//				if (ordercancellation != null) {
//					Boolean status = ordercancellation.getStatus();
//
//					// Kiểm tra status và xác định việc không cho phép hủy bỏ
//					if (status == null) {
//						Date upDate = new Date(System.currentTimeMillis());
//						ordercancellation.setCancellationDate(upDate);
//						ordercancellation.setStatus(false);
//
//						if (userId.equals(ownerId)) {
//							if (cancellationService.save(ordercancellation)) {
//								redirectAttributes.addFlashAttribute("successMessage",
//										"Order not canceled successfully");
//							} else {
//								redirectAttributes.addFlashAttribute("errorMessage", "Unable to update order status");
//							}
//						} else {
//							redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized access");
//							return "redirect:/error/404";
//						}
//					} else {
//						redirectAttributes.addFlashAttribute("errorMessage",
//								"Unable to update. Order status is not pending.");
//						return "redirect:/error/404";
//					}
//				} else {
//					redirectAttributes.addFlashAttribute("errorMessage", "Order not found");
//				}
//			} else {
//				return "redirect:/login";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("alertDeleted", true);
//		}
//		return "redirect:/shop-owners/order-Cancell-Ation";
//	}
}
