package com.dodo.web.controllers.shop_owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodo.web.IServices.IOrderDetailService;
import com.dodo.web.IServices.IOrderService;
import com.dodo.web.IServices.IShopOwnerCouponService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("shop-owners")
public class HomeShopController {
	@Autowired
	IUserService userService;

	@Autowired
	IShopOwnerService shopOwnerService;

	@Autowired
	IShopOwnerCouponService couponService;

	@Autowired
	IOrderService orderService;

	@Autowired
	IOrderDetailService detailService;


	@GetMapping({ "index", "", "/" })
	public String index(Model model, HttpSession session, Authentication authentication) {
		
		return "shopOwners/home/index";
	}

}
