package com.dodo.api.controllers.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.dodo.api.IServices.IMailService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.helpers.FileHelper;
import com.dodo.api.models.Role;
import com.dodo.api.models.User;
import com.dodo.api.validators.UserEmailUniqueValidator;
import com.dodo.api.validators.UserPasswordValidator;
import com.dodo.api.validators.UserUsernameUniqueValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/user")
public class UserAdminController {
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

	@GetMapping({ "index", "", "/" })
	public String index(ModelMap modelMap, Authentication authentication) {
//		String userRole = authentication.getAuthorities().iterator().next().toString();
////		String userName = authentication.getName();
////		boolean isLoggedIn = authentication instanceof AnonymousAuthenticationToken;
////		System.out.println(isLoggedIn);
//		
//		if (userRole.contains("SUPER")) {
//			modelMap.put("users", userService.findAll());
//		} else {
//			modelMap.put("users", userService.findAllRoleUserAndShopowner());
//		} 

		return "admins/user/index";
	}

	@GetMapping({ "create" })
	public String create(ModelMap modelMap) {
//		var user = new User();
//		user.setGender("female");
//		modelMap.put("user", user);
		return "admins/user/create";
	}

	@PostMapping({ "create" })
	public String create(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile file, @ModelAttribute("user") @Valid User user,
			BindingResult bindingResult, Authentication authentication) {
		try {
//			// validate
//			emailUniqueValidator.validate(user, bindingResult);
//			usernameUniqueValidator.validate(user, bindingResult);
//			userPasswordValidator.validate(user, bindingResult);
//			if (file.getSize() == 0) {
//				bindingResult.rejectValue("avatar", "Upload");
//			}
//			if (bindingResult.hasErrors()) {
//				return "admins/user/create";
//			}
//			// validate
//
//			// file handling
//			var fileName = FileHelper.saveImageFile(file);
//			// file handling
//
//			// create user
//			String userRole = authentication.getAuthorities().iterator().next().toString();
//			if (!userRole.contains("SUPER")) {
//				var role = new Role();
//				role.setRoleId(4);
//				user.setRole(role);
//			}
//			  
//			user.setStatus(false);
//			user.setCreatedAt(new Date());
//			user.setAvatar(fileName);
//			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//
//			if (userService.save(user)) {
//				// Send mail
////				var fromEmail = environment.getProperty("spring.mail.username");
////				mailService.send(fromEmail, user.getEmail(), "Active account noti", MailHelper.getEmailActiveAccount(user.getUserId()));
//				// Send mail
//
//				redirectAttributes.addFlashAttribute("successCreated", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alertCreated", true);
//			}
//			// create user
			return "redirect:/admin/user";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
	
	@GetMapping({ "switch-status/{id}" })
	public String switchStatus(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id, Authentication authentication) {	
		try {
//			//validate			
//			var user = userService.findById(id);			
//			if(user == null || user.getRole().getName().contains("SUPER")) {
//				return "redirect:/error/404";
//			}
//			//validate
//			
//			if(user.getStatus()==true) {
//				user.setStatus(false);
//			}else if(user.getStatus()==false) {
//				user.setStatus(true);
//			}
//			
//			user.setUpdatedAt(new Date());
//			
//			if (userService.save(user)) {
//				redirectAttributes.addFlashAttribute("successUpdated", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alertUpdated", true);
//			}
//			
			return "redirect:/admin/user";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}
	}

	@GetMapping({ "edit/{id}" })
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {	
		try {
//			//validate
//			var user = userService.findById(id);		
//			if(user == null || user.getRole().getName().contains("ADMIN")) {
//				return "redirect:/error/404";
//			}			
//			//validate
//			user.setPassword("");
//			modelMap.put("user", user); 
//			modelMap.put("notProfile", true);
			
			return "admins/user/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}
	}
	 
	@GetMapping({ "edit-profile" })
	public String editProfile(ModelMap modelMap, Authentication authentication) {
//		String userName = authentication.getName();
//		var user = userService.findByUsername(userName);
//		user.setPassword("");
//		modelMap.put("user", user);
//		modelMap.put("profile", true);
		return "admins/user/edit";
	}    

	@PostMapping({ "edit" })
	public String edit(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile file, @ModelAttribute("user") @Valid User user,
			BindingResult bindingResult, HttpSession session, Authentication authentication) {
		try {
//			// validate
//			var baseUser = userService.findById(user.getUserId());
//			if (!user.getPassword().isBlank()) {
//				userPasswordValidator.validate(user, bindingResult);
//				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//			} else {
//				user.setPassword(baseUser.getPassword());
//			}
//
//			emailUniqueValidator.validate(user, bindingResult);
//			usernameUniqueValidator.validate(user, bindingResult);
//			if (bindingResult.hasErrors()) {
//				return "admins/user/edit";
//			}
//			// validate
//
//			// file handling
//			if (file.getSize() != 0) {
//				var fileName = FileHelper.saveImageFile(file);
//				FileHelper.deleteImageFile(user.getAvatar());
//				user.setAvatar(fileName);
//			}
//			// file handling
//
//			// update user
//			user.setUpdatedAt(new Date());
//			
//			if (userService.save(user)) {
//				// check if this is edit profile and also image is editted
//				String userName = authentication.getName();
//				var logedInUserId = userService.findByUsername(userName).getUserId();
//				if(user.getUserId() == logedInUserId && file.getSize() != 0) {
//					session.setAttribute("avatar", user.getAvatar());
//				}
//				// check if this is edit profile and also image is editted
//				
//				redirectAttributes.addFlashAttribute("successUpdated", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alertUpdated", true);
//			}
//			// update user
			return "redirect:/admin/user";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}   
	}   

	@GetMapping({ "delete/{id}" })
	public String delete(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@PathVariable("id") int id, Authentication authentication) {
		try {
//			//validate
//			String userRole = authentication.getAuthorities().iterator().next().toString();
//			String userName = authentication.getName();
//			var logedInUserId = userService.findByUsername(userName).getUserId();
//			
//			if(!userRole.contains("SUPER") || logedInUserId == id) {
//				return "redirect:/error/404";
//			}
//			
//			var baseUser = userService.findById(id);
//			if(baseUser == null) {
//				return "redirect:/error/404";
//			}
//			//validate
//			
//			// actual delete
//			if (userService.delete(id)) {
//				// delete file
//				FileHelper.deleteImageFile(baseUser.getAvatar());
//				// delete file
//
//				redirectAttributes.addFlashAttribute("successDeleted", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alertDeleted", true);
//			}  
//			// actual delete
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertDeleted", true);
		}
		return "redirect:/admin/user";
	}
}
