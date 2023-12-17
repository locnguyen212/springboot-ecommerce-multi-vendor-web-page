package com.dodo.api.controllers.shop_owners;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.api.IServices.IOrderCancellationService;
import com.dodo.api.IServices.IOrderDetailService;
import com.dodo.api.IServices.IOrderService;
import com.dodo.api.IServices.IProductService;
import com.dodo.api.IServices.IShopOwnerCouponService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.IServices.IUserService;

@Controller
@RequestMapping("shop-owners/order")
public class OrderShopownerController {
//	@Autowired
//	IUserService userService;
//	
//	@Autowired
//	IShopOwnerService shopOwnerService;
//	
//	@Autowired
//	IShopOwnerCouponService couponService;
//	
//	@Autowired
//	IOrderCancellationService cancellationService;
//	
//	@Autowired
//	IOrderService orderService;
//	
//	@Autowired
//	IProductService productService;
//	
//	@Autowired
//	IOrderDetailService orderDetailService;
//
//	@GetMapping({ "index", "", "/" })
//	public String index(ModelMap modelMap, Authentication authentication, @RequestParam(value = "status", defaultValue = "0") int status) {
//		try {
//			//status = 0: "Waiting For Approval"
//			//status = 1: "Packaging"
//			//status = 2: "Other"
//			//status = 3: "Cancel"			
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			if(status == 0) {
//				modelMap.put("orders", orderService.findByShopownerOwnerIdAndOrderStatus(shopId, "Waiting For Approval"));
//			}else if(status == 1) {
//				modelMap.put("orders", orderService.findByShopownerOwnerIdAndOrderStatus(shopId, "Packaging"));
//			}else if(status == 2) {
//				var otherStatus = List.of("Waiting For Approval", "Packaging", "Cancelled");
//				modelMap.put("orders", orderService.findByShopownerIdAndStatusOther(shopId, otherStatus));
//			}else if(status == 3) {
//				modelMap.put("orders", orderService.findByShopownerOwnerIdAndOrderStatus(shopId, "Cancelled"));
//			}
//			modelMap.put("orderStatusCon", status);
//			
//			return "shopOwners/order/index";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}	
//	}
//	
//	@GetMapping({ "detail/{orderId}" })
//	public String packaging(ModelMap modelMap, Authentication authentication, @PathVariable("orderId") int id) {
//		try {
//			//validate
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			var order = orderService.findById(id);
//			if(order == null || order.getShopowner().getOwnerId() != shopId) {
//				return "redirect:/error/404";
//			}			
//			//validate
//			
//			modelMap.put("orderDetail", orderDetailService.findByOrderOrderId(id));
//			return "shopOwners/order/orderDetail";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}	
//	}
//	
//	@GetMapping({ "cancel/{orderId}" })
//	public String cancel(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes, @PathVariable("orderId") int id) {
//		try {
//			//validate
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			var order = orderService.findById(id);
//			if(order == null || order.getShopowner().getOwnerId() != shopId) {
//				return "redirect:/error/404";
//			}
//			if(!order.getOrderStatus().equals("Waiting For Approval") && !order.getOrderStatus().equals("Packaging")) {
//				redirectAttributes.addFlashAttribute("alertCancel", true);
//				return "redirect:/shop-owners/order/index";
//			}
//			var orderCancel = cancellationService.findByOrderOrderId(id);
//			if(orderCancel!=null) {
//				redirectAttributes.addFlashAttribute("cancellationInProgress", true);
//				return "redirect:/shop-owners/order/index";
//			}
//			//validate
//			order.setOrderStatus("Cancelled");
//			order.setUpdatedAt(new Date());
//			if(orderService.saveBool(order)) {
//				redirectAttributes.addFlashAttribute("successCancel", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alert", true);
//			}
//			return "redirect:/shop-owners/order/index";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}	
//	}
//	
//	@GetMapping({ "approve/{orderId}" })
//	public String approve(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes, @PathVariable("orderId") int id) {
//		try {
//			//validate
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			var order = orderService.findById(id);
//			if(order == null || order.getShopowner().getOwnerId() != shopId) {
//				return "redirect:/error/404";
//			}	
//			if(!order.getOrderStatus().equals("Waiting For Approval")) {
//				redirectAttributes.addFlashAttribute("alertApprove", true);
//				return "redirect:/shop-owners/order/index";
//			}
//			var orderCancel = cancellationService.findByOrderOrderId(id);
//			if(orderCancel!=null) {
//				redirectAttributes.addFlashAttribute("cancellationInProgress", true);
//				return "redirect:/shop-owners/order/index";
//			}
//			//validate
//			
//			order.setOrderStatus("Packaging");
//			order.setUpdatedAt(new Date());
//			if(orderService.saveBool(order)) {
//				redirectAttributes.addFlashAttribute("successApprove", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alert", true);
//			}
//			return "redirect:/shop-owners/order/index";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}	
//	}
//	
//	@GetMapping({ "ready/{orderId}" })
//	public String ready(ModelMap modelMap, Authentication authentication, RedirectAttributes redirectAttributes, @PathVariable("orderId") int id) {
//		try {
//			//validate
//			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
//			var order = orderService.findById(id);
//			if(order == null || order.getShopowner().getOwnerId() != shopId) {
//				return "redirect:/error/404";
//			}	
//			if(!order.getOrderStatus().equals("Packaging")) {
//				redirectAttributes.addFlashAttribute("alertPackaging", true);
//				return "redirect:/shop-owners/order/index";
//			}
//			var orderCancel = cancellationService.findByOrderOrderId(id);
//			if(orderCancel!=null) {
//				redirectAttributes.addFlashAttribute("cancellationInProgress", true);
//				return "redirect:/shop-owners/order/index";
//			}
//			//validate
//				
//			order.setOrderStatus("Ready For Transport");
//			order.setUpdatedAt(new Date());
//			if(orderService.saveBool(order)) {
//				//subtract quantity from product
//				var orderDetail = orderDetailService.findByOrderOrderId(id);
//				for(var e : orderDetail) {
//					var product = e.getProduct();
//					product.setStockQuantity(product.getStockQuantity()-e.getQuantity());
//					product.setUpdatedAt(new Date());
//					productService.save(product);
//				}
//				//subtract quantity from product
//				
//				redirectAttributes.addFlashAttribute("successReady", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alert", true);
//			}
//			return "redirect:/shop-owners/order/index";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/error/500";
//		}	
//	}
}
