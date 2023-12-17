package com.dodo.api.controllers.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.dodo.api.IServices.IParentCategoryService;
import com.dodo.api.helpers.FileHelper;
import com.dodo.api.models.Parentcategory;
import com.dodo.api.validators.ParentCategoryUniqueValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/parent-category")
public class ParentCategoryAdminController {

	@Autowired
	IParentCategoryService parentCategoryService;

	@Autowired
	ParentCategoryUniqueValidator uniqueValidator;

	@GetMapping({ "index", "", "/" })
	public String index(ModelMap modelMap) {
//		modelMap.put("parentCategories", parentCategoryService.findAll());
		return "admins/parentCategory/index";
	}

	@GetMapping({ "create" })
	public String createParentCategory(ModelMap modelMap) {
//		var parentCategory = new Parentcategory();
//		modelMap.put("parentCategory", parentCategory);
		return "admins/parentCategory/create";
	}

	@PostMapping({ "create" })
	public String create(ModelMap modelMap, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile file,
			@ModelAttribute("parentCategory") @Valid Parentcategory parentCategory, BindingResult bindingResult) {
		try {
//			//validate
//			uniqueValidator.validate(parentCategory, bindingResult);
//			if (file.getSize() == 0) {
//				bindingResult.rejectValue("parentCategoryImagePath", "Upload");
//			}
//			if (bindingResult.hasErrors() || file.getSize() == 0) {
//				return "admins/parentCategory/create";
//			}
//			//validate
//			
//			//file handling
//			var fileName = FileHelper.saveImageFile(file);
//			//file handling
//			
//			//create parent category
//			parentCategory.setCreatedAt(new Date());
//			parentCategory.setParentCategoryImagePath(fileName);
//			if(parentCategoryService.save(parentCategory)) {
//				redirectAttributes.addFlashAttribute("successCreated", true);
//			}else {
//				redirectAttributes.addFlashAttribute("alertCreated", true);
//			}
//			//create parent category
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertCreated", true);
		}
		return "redirect:/admin/parent-category";
	}
	
	@GetMapping({ "edit/{id}" })
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		try {
//			var parentCategory = parentCategoryService.findById(id);
//			
//			//validate
//			if(parentCategory == null) {
//				return "redirect:/error/404";
//			}
//			//validate
//			
//			modelMap.put("parentCategory", parentCategory);
			
			return "admins/parentCategory/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}
		
	}
	
	@PostMapping({ "edit" })
	public String edit(ModelMap modelMap, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile file,
			@ModelAttribute("parentCategory") @Valid Parentcategory parentCategory, BindingResult bindingResult) {
		try {
//			//validate
//			uniqueValidator.validate(parentCategory, bindingResult);
//			if (bindingResult.hasErrors()) {
//				return "admins/parentCategory/edit";
//			}
//			//validate
//
//			//file handling
//			if (file.getSize() != 0) {
//				var fileName = FileHelper.saveImageFile(file);
//				FileHelper.deleteImageFile(parentCategory.getParentCategoryImagePath());
//				parentCategory.setParentCategoryImagePath(fileName);
//			}
//			//file handling
//
//			//edit parent category
//			parentCategory.setUpdatedAt(new Date());
//			if(parentCategoryService.save(parentCategory)) {
//				redirectAttributes.addFlashAttribute("successUpdated", true);
//			}else {
//				redirectAttributes.addFlashAttribute("alertUpdated", true);
//			}
//			//edit parent category
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertUpdated", true);
		}
		return "redirect:/admin/parent-category";
	}
	
	@GetMapping({ "delete/{id}" })
	public String delete(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id) {
		try {
//			var baseParentCategory = parentCategoryService.findById(id);
//			
//			//actual delete
//			if(parentCategoryService.delete(id)) {
//				//delete file
//				FileHelper.deleteImageFile(baseParentCategory.getParentCategoryImagePath());
//				//delete file
//				
//				redirectAttributes.addFlashAttribute("successDeleted", true);
//			}else {
//				redirectAttributes.addFlashAttribute("alertDeleted", true);
//			}
//			//actual delete
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertDeleted", true);
		}
		return "redirect:/admin/parent-category";
	}
}
