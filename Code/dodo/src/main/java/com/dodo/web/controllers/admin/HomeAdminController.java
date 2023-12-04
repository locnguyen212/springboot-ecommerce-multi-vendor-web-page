package com.dodo.web.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodo.web.IServices.IUserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/home")
public class HomeAdminController {
	@Autowired
	IUserService userService;
	
	@GetMapping({"index", "", "/"})
	public String index(ModelMap modelMap, HttpSession session, Authentication authentication) {
		session.setAttribute("avatar", userService.findByUsername(authentication.getName()).getAvatar());
		return "admins/home/index"; 
	}
}

