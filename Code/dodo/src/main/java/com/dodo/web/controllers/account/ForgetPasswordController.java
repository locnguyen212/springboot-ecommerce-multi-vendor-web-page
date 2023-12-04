package com.dodo.web.controllers.account;

import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.IMailService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.helpers.MailHelper;
import com.dodo.web.models.Mail;

import jakarta.validation.Valid;

@Controller
@RequestMapping("account/forget-password")
public class ForgetPasswordController {
	@Autowired
	IUserService userService;
	
	@Autowired
	private IMailService mailService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"index", "", "/"})
	public String index(ModelMap modelMap) {	
		modelMap.put("mail", new Mail());
		return "account/forgetPassword";
	}	
	
	@PostMapping({"reset"})
	public String reset(ModelMap modelMap, RedirectAttributes redirectAttributes, @ModelAttribute("mail") @Valid Mail mail, BindingResult bindingResult) {	
		try {
			//validate
			var user = userService.findByEmail(mail.getEmail());
			if(bindingResult.hasErrors()) {		
				return "account/forgetPassword";
			}else {			
				var pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
				if(!pattern.matcher(mail.getEmail()).matches()) {
					bindingResult.rejectValue("email", "Email", null, null);
					return "account/forgetPassword";
				}else if(user==null) {
					bindingResult.rejectValue("email", "EmailNotExist", null, null);
					return "account/forgetPassword";
				}
				
			}
			//validate
				
			//generate token and save
			var token = generateToken();
			user.setToken(token);
			if(userService.save(user)) {
				redirectAttributes.addFlashAttribute("successResetPasswordRequest", true);
				
				//Send mail
//				var fromEmail = environment.getProperty("spring.mail.username");
//				mailService.send(fromEmail, mail.getEmail(), "Reset password mail", MailHelper.getMailResetPassword(user.getUserId(), token));
				//Send mail
			}else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			//generate token and save											
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alert", true);
		}
		
		return "redirect:/login";
	}
	
	@GetMapping({"reset/{id}/{token}"})
	public String reset(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id, @PathVariable("token") String token) {	
		try {
			//validate
			var user = userService.findById(id);	
			if(user==null || user.getToken() == null) {
				return "redirect:/error/404";
			}
			//validate
			
			//validate then return page
			if(user.getToken().equals(token)) {
				modelMap.put("id", id);
				return "account/changePassword";
			}else {
				return "redirect:/error/404";
			}
			//validate then return page
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}	
	
	@PostMapping({"change-password"})
	public String changePassword(ModelMap modelMap, RedirectAttributes redirectAttributes, @RequestParam("password") String password, @RequestParam("id") int id) {	
		try {
			//validate
			var user = userService.findById(id);
			
			if(user==null || user.getToken() == null) {
				return "redirect:/error/404";
			}
			
			if(password.isBlank()) {
				redirectAttributes.addFlashAttribute("changePasswordError", true);
				return "redirect:/account/forget-password/reset/"+id+"/"+user.getToken(); 
			}
			//validate
				
			//change password and save
			var newPassword = bCryptPasswordEncoder.encode(password);
			
			user.setPassword(newPassword);
			user.setToken(null);
			
			if(userService.save(user)) {
				redirectAttributes.addFlashAttribute("successChangePassword", true);
			}else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			//change password and save	
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alert", true);
		}
		
		return "redirect:/login";
	}
	
	private String generateToken() {
		String token = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
		if(userService.isTokenExist(token)) {
			generateToken();
		}
		return token;
	}
} 

