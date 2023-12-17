package com.dodo.api.controllers.errors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController {	 
	@GetMapping({"404"})
	public String error404(ModelMap modelMap) {	
		return "error/404";
	}	
	
	@GetMapping({"500"})
	public String error500(ModelMap modelMap) {	
		return "error/500";
	}
	
	@GetMapping({"400"})
	public String error400(ModelMap modelMap) {	
		return "error/400";
	}
}

