package com.dodo.web.controllers.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dodo.web.IServices.IUserService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	IUserService userService;
	 
	@GetMapping({"index", "", "/"})
	public String index(ModelMap modelMap, @RequestParam(value = "error", required = false) String error) {	
		if(error!=null) {
			modelMap.put("message", "Invalid login information!");
		} 
		return "account/login";
	}	
}

