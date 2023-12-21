package com.dodo.web.controllers.users;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.INotificationService;
import com.dodo.web.IServices.IOrderCancellationService;
import com.dodo.web.IServices.IOrderDetailService;
import com.dodo.web.IServices.IOrderService;
import com.dodo.web.IServices.IReviewService;
import com.dodo.web.IServices.IShopOwnerCouponService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.IServices.IWishlistService;
import com.dodo.web.helpers.FileHelper;
import com.dodo.web.models.Ordercancellation;
import com.dodo.web.models.User;
import com.dodo.web.modelview.ChangePassword;
import com.dodo.web.validators.UserEmailUniqueValidator;
import com.dodo.web.validators.UserPasswordValidator;
import com.dodo.web.validators.UserUsernameUniqueValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("user/account")
public class AccountUserController {
	@Autowired
	IUserService userService;

	@Autowired
	IShopOwnerService shopOwnerService;
	
	@Autowired
	INotificationService notificationService;

	@Autowired
	IShopOwnerCouponService couponService;
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IOrderDetailService orderDetailService;

	@Autowired
	IOrderCancellationService cancellationService;
	
	@Autowired
	IWishlistService wishlistService;
	
	@Autowired
	IReviewService reviewService;

	@Autowired
	UserEmailUniqueValidator emailUniqueValidator;

	@Autowired
	UserUsernameUniqueValidator usernameUniqueValidator;

	@Autowired
	UserPasswordValidator userPasswordValidator;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("account-detail")
	public String accountDetail(ModelMap modelMap, HttpSession session, Authentication authentication) {
		modelMap.put("user", userService.findByUsername(authentication.getName()));
		session.setAttribute("userProfile", userService.findByUsername(authentication.getName()));
		return "users/account/accountDetails";
	}

	@PostMapping({ "account-detail" })
	public String edit(
			ModelMap modelMap, 
			RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile file, 
			@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult, 
			HttpSession session, 
			Authentication authentication
			) {
		try {
			// validate
			var baseUser = userService.findById(user.getUserId());
 
			emailUniqueValidator.validate(user, bindingResult);
			if (bindingResult.hasErrors()) {
				return "users/account/accountDetails";
			}
			// validate

			// file handling
			if (file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				FileHelper.deleteImageFile(user.getAvatar());
				user.setAvatar(fileName);
			}
			// file handling

			// update user
			user.setUserId(baseUser.getUserId());
			user.setCreatedAt(baseUser.getCreatedAt());
			user.setStatus(baseUser.getStatus());
			user.setUsername(baseUser.getUsername());
			user.setRole(baseUser.getRole());
			user.setPassword(baseUser.getPassword());
			user.setUpdatedAt(new Date());

			if (userService.save(user)) {
				// check if image is editted
				if (file.getSize() != 0) {
					session.setAttribute("avatar", user.getAvatar());
				}
				// check if image is editted

				redirectAttributes.addFlashAttribute("successUpdated", true);
			} else {
				redirectAttributes.addFlashAttribute("alertUpdated", true);
			}
			// update user
			return "redirect:/user/account/account-detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
	
	@GetMapping("password")
	public String password(ModelMap modelMap) {
		modelMap.put("changePassword", new ChangePassword());

		return "users/account/password";
	}

	@PostMapping({ "change-password" })
	public String changePassword(
			ModelMap modelMap, 
			RedirectAttributes redirectAttributes,
			@ModelAttribute("changePassword") @Valid ChangePassword changePassword, 
			BindingResult bindingResult,
			Authentication authentication
			) {
		try {
			// validate
			var baseUser = userService.findByUsername(authentication.getName());

			if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
				bindingResult.rejectValue("confirmPassword", "ConfirmPassword");
			}
			
			if(!changePassword.getPassword().isBlank() && !bCryptPasswordEncoder.matches(changePassword.getPassword(), baseUser.getPassword())) {
				bindingResult.rejectValue("password", "WrongPassword");
			}
			
			if (bindingResult.hasErrors()) {
				return "users/account/password";
			}
			// validate

			// update user
			var newPassword = bCryptPasswordEncoder.encode(changePassword.getNewPassword());
			baseUser.setPassword(newPassword);
			baseUser.setUpdatedAt(new Date());
			if (userService.save(baseUser)) {
				redirectAttributes.addFlashAttribute("successUpdated", true);
			} else {
				redirectAttributes.addFlashAttribute("alertUpdated", true);
			}
			// update user
			return "redirect:/user/account/password";
		} catch (Exception e) {
			e.printStackTrace();  
			return "redirect:/error/500";
		}
	}
	   
	@GetMapping("order")
	public String order(ModelMap modelMap, Authentication authentication) {
		var user = userService.findByUsername(authentication.getName());
		var otherStatus = List.of("Waiting For Approval", "Packaging", "Cancelled", "Delivered");
		modelMap.put("ordersRecent", orderService.findByUserUsernameAndOrderStatus(authentication.getName(), "Waiting For Approval")); 
		modelMap.put("ordersDelivered", orderService.findByUserUsernameAndOrderStatus(authentication.getName(), "Delivered")); 
		modelMap.put("ordersPackaging", orderService.findByUserUsernameAndOrderStatus(authentication.getName(), "Packaging")); 
		modelMap.put("ordersInProcess", orderService.findByUserIdAndStatusNotIn(user.getUserId(), otherStatus)); 
		modelMap.put("ordersCancelled", orderService.findByUserUsernameAndOrderStatus(authentication.getName(), "Cancelled")); 
		modelMap.put("cancelRequest", cancellationService.findByUserUsername(authentication.getName())); 
		return "users/account/order";
	}
	
	@GetMapping("order-detail/{orderId}")
	public String orderDetail(
			ModelMap modelMap, 
			@PathVariable("orderId") int id, 
			Authentication authentication
			) {			
		try {
			//validate
			var orderDetail = orderDetailService.findByOrderOrderId(id);
			
			if(orderDetail == null) {
				return "redirect:/error/404";
			}
			
			var userId = orderService.findById(id).getUser().getUserId();
			var loggedInUserId = userService.findByUsername(authentication.getName()).getUserId();		
			if(userId != loggedInUserId) {
				return "redirect:/error/404";
			}
			//validate
			var order = orderService.findById(id);
			if(order.getPaymentStatus() != null && order.getPaymentStatus().equalsIgnoreCase("Payed")) {
				modelMap.put("isDeliveredAndPayed", true); 
			}
			
			modelMap.put("orderDetail", orderDetail); 
			return "users/account/orderDetail";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}
	}
	
	@GetMapping("wishlist")
	public String wishlist(ModelMap modelMap, Authentication authentication) {
		var userId = userService.findByUsername(authentication.getName()).getUserId();
		modelMap.put("wishlists", wishlistService.findByUserUserId(userId)); 
		
		return "users/account/wishlist";
	}
	
	@GetMapping("wishlist/remove/{id}")
	public String wishlistRemove(
			ModelMap modelMap, 
			@PathVariable("id") int id, 
			Authentication authentication
			) {
		try {
			//validate
			var wishlist = wishlistService.findById(id);
			
			if(wishlist == null) {
				return "redirect:/error/404";
			}
			
			var userId = wishlist.getUser().getUserId();
			var loggedInUserId = userService.findByUsername(authentication.getName()).getUserId();		
			if(userId != loggedInUserId) {
				return "redirect:/error/404";
			}
			//validate
			wishlistService.delete(id);
			
			return "redirect:/user/account/wishlist";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}
	}
	
	@GetMapping("invoice")
	public String invoice(ModelMap modelMap, Authentication authentication) {
		var user = userService.findByUsername(authentication.getName());
		modelMap.put("invoices", orderService.findByPaymentStatusAndUserUserId("Payed", user.getUserId())); 
		 
		return "users/account/invoice";
	}
	
	@GetMapping("order-cancel/{orderId}")
	public String orderCancel(
			ModelMap modelMap, 
			@PathVariable("orderId") int id, 
			Authentication authentication, 
			RedirectAttributes redirectAttributes
			) {	
		try {
			//validate
			var order = orderService.findById(id);
			
			if(order == null) {
				return "redirect:/error/404";
			}
			
			var loggedInUser = userService.findByUsername(authentication.getName());		
			if(order.getUser().getUserId() != loggedInUser.getUserId()) {
				return "redirect:/error/404";
			}
			
			var orderCancelCheck = cancellationService.findByOrderOrderId(id);
			if(orderCancelCheck!=null) {
				redirectAttributes.addFlashAttribute("alreadyCancel", true);
				return "redirect:/user/account/order";
			}
			//validate
			var orderCancel = new Ordercancellation();
			orderCancel.setOrder(order);
			orderCancel.setShopowner(order.getShopowner());
			orderCancel.setUser(loggedInUser);
			
			modelMap.put("orderCancel", orderCancel);
			return "users/account/orderCancel";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}
	}
	
	@GetMapping("receive-order/{orderId}")
	public String receiveOrder(
			ModelMap modelMap, 
			@PathVariable("orderId") int id, 
			Authentication authentication, 
			RedirectAttributes redirectAttributes
			) {
		try {
			//validate
			var order = orderService.findById(id);
			
			if(order==null) {
				return "redirect:/error/404";
			}
			
			var loggedInUser = userService.findByUsername(authentication.getName());		
			if(order.getUser().getUserId() != loggedInUser.getUserId()) {
				return "redirect:/error/404";
			}
			
			if(!order.getOrderStatus().equalsIgnoreCase("Delivered") || (order.getOrderStatus().equalsIgnoreCase("Delivered") && !order.getPaymentStatus().equalsIgnoreCase("Await Payment"))) {
				return "redirect:/error/404";
			}
			//validate
			
			order.setPaymentStatus("Payed");
			order.setUpdatedAt(new Date());
			orderService.save(order);
			return "redirect:/user/account/order";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}
	}
	
	@PostMapping({ "order-cancel" })
	public String orderCancel(
			ModelMap modelMap, 
			RedirectAttributes redirectAttributes,
			@ModelAttribute("orderCancel") @Valid Ordercancellation orderCancel, 
			BindingResult bindingResult,
			Authentication authentication) {
		try {
			// validate			
			if (bindingResult.hasErrors()) {
				return "users/account/orderCancel";
			}
			// validate

  
			// update user
			orderCancel.setCreatedAt(new Date());
			if (cancellationService.save(orderCancel)) {
				redirectAttributes.addFlashAttribute("successCancel", true);
			} else {
				redirectAttributes.addFlashAttribute("alertCancel", true);
			}
			// update user
			return "redirect:/user/account/order";
		} catch (Exception e) {
			e.printStackTrace();  
			return "redirect:/error/500";
		}
	}
	
	@GetMapping("review")
	public String review(ModelMap modelMap, Authentication authentication) {
		try {
			var userId = userService.findByUsername(authentication.getName()).getUserId();
			modelMap.put("reviews", reviewService.findByUserUserId(userId));
			return "users/account/review";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}
	}
	
	@GetMapping("notification")
	public String notification(ModelMap modelMap, Authentication authentication) {
		var userId = userService.findByUsername(authentication.getName()).getUserId(); 
		modelMap.put("notifications", notificationService.findByUserId(userId, 0, 5));
		return "users/account/notification";
	}
	
	@GetMapping("addition-notification")
	public String additionNotification(
			ModelMap modelMap, 
			Authentication authentication, 
			@RequestParam("lastestRow") int lastestRow, 
			@RequestParam("isRead") String isRead
			) {
		var userId = userService.findByUsername(authentication.getName()).getUserId();
		
		//check if all notifications are present
		var allNotiCount = notificationService.countAll(userId);
		if(lastestRow >= allNotiCount) {
			modelMap.put("notificationEnd", true);
		}
		//check if all notifications are present
		
		if(isRead.equalsIgnoreCase("all")) {
			modelMap.put("notifications", notificationService.findByUserId(userId, lastestRow, 5));
		}else if(isRead.equalsIgnoreCase("unread")) {
			modelMap.put("notifications", notificationService.findByUserIdAndTypeAndIsRead(userId, null, false, lastestRow, 5));
		}
		
		return "users/account/partials/notificationAjax";
	}
	
	@GetMapping("change-notification")
	public String changeNotification(
			ModelMap modelMap, 
			Authentication authentication, 
			@RequestParam("isRead") String isRead
			) {
		var userId = userService.findByUsername(authentication.getName()).getUserId();
		if(isRead.equalsIgnoreCase("all")) {
			modelMap.put("notifications", notificationService.findByUserId(userId, 0, 5));
		}else if(isRead.equalsIgnoreCase("unread")) {
			modelMap.put("notifications", notificationService.findByUserIdAndTypeAndIsRead(userId, null, false, 0, 5));
		}
		
		return "users/account/partials/notificationAjax";
	}
}
