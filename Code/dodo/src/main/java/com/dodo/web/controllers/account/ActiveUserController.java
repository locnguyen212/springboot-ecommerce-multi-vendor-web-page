package com.dodo.web.controllers.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.IUserService;

@Controller
@RequestMapping("account/active")
public class ActiveUserController {
	@Autowired
	IUserService userService;
	 
	@GetMapping({"activate/{id}"})
	public String index(ModelMap modelMap, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {	
		var user = userService.findById(id);
		if(user.getStatus()) {
			return "errors/500";
		}
		
		user.setStatus(true);
		if(userService.save(user)) {
			redirectAttributes.addFlashAttribute("successActive", true);
		}else {
			redirectAttributes.addFlashAttribute("alert", true);
		}
		return "redirect:/login";
	}	
}

