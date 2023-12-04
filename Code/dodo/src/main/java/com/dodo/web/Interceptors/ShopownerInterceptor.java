package com.dodo.web.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//để dùng interceptor, ta implements HandlerInterceptor
@Component
public class ShopownerInterceptor implements HandlerInterceptor {
	@Autowired
	IUserService userService;
	
	@Autowired
	IShopOwnerService shopOwnerService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		var shop = shopOwnerService.findByUserUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//		System.out.println("interceptor: "+shop.getUser().getUserId());
		if(shop.getStatus()==null || !shop.getStatus()) {
			response.sendRedirect("/error/404");
		    return false;
		}
		return true;
		
	}
}
