package com.dodo.web.controllers.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.IMailService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.helpers.FileHelper;
import com.dodo.web.helpers.MailHelper;
import com.dodo.web.models.Role;
import com.dodo.web.models.User;
import com.dodo.web.validators.UserEmailUniqueValidator;
import com.dodo.web.validators.UserPasswordValidator;
import com.dodo.web.validators.UserUsernameUniqueValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("account/register")
public class RegisterController {
	@Autowired
	IUserService userService;
	 
	@Autowired
	private IMailService mailService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	UserEmailUniqueValidator emailUniqueValidator;
	
	@Autowired
	UserUsernameUniqueValidator usernameUniqueValidator;
	
	@Autowired
	UserPasswordValidator userPasswordValidator;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"index", "", "/"})
	public String index(ModelMap modelMap) {	
		var user = new User();
		user.setGender("female");
		modelMap.put("user", user);
		return "account/register";
	}	
	
	@PostMapping({"sign-up"})
	public String register(
			ModelMap modelMap, 
			RedirectAttributes redirectAttributes, 
			@RequestParam("image") MultipartFile file, 
			@ModelAttribute("user") @Valid User user, 
			BindingResult bindingResult
			) {		
		try {
			//validate
			emailUniqueValidator.validate(user, bindingResult);
			usernameUniqueValidator.validate(user, bindingResult);
			userPasswordValidator.validate(user, bindingResult);
			if (file.getSize() == 0) {
				bindingResult.rejectValue("avatar", "Upload");
			}
			if(bindingResult.hasErrors()) {		
				return "account/register";
			}	
			//validate
			
			//file handling
			var fileName = FileHelper.saveImageFile(file);
			//file handling
			  
			//create user
			var role = new Role();
			role.setRoleId(4);
			user.setRole(role);
			user.setStatus(false);
			user.setCreatedAt(new Date());
			user.setAvatar(fileName);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			 
			if(userService.save(user)) {			
				//Send mail
//				var fromEmail = environment.getProperty("spring.mail.username");
//				mailService.send(fromEmail, user.getEmail(), "Active account noti", MailHelper.getEmailActiveAccount(user.getUserId()));
				//Send mail
				
				
				redirectAttributes.addFlashAttribute("successRegister", true);
			}else {
				redirectAttributes.addFlashAttribute("alert", true);
			}			
			//create user
			return "redirect:/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}

	}
} 

