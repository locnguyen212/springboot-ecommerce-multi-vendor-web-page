package com.dodo.api.controllers.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
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

import com.dodo.api.IServices.ICategoryService;
import com.dodo.api.IServices.IParentCategoryService;
import com.dodo.api.IServices.IUserService;
import com.dodo.api.models.Category;
import com.dodo.api.models.Parentcategory;
import com.dodo.api.validators.CategoryUniqueValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/category")
public class CategoryAdminController {
	@Autowired
	ICategoryService categoryService;

	@Autowired
	IUserService userService;

	@Autowired
	IParentCategoryService parentCategoryService;

	@Autowired
	CategoryUniqueValidator uniqueValidator;

	@GetMapping({ "index", "", "/" })
	public String index(ModelMap modelMap, @RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "8") int size) {
//		Page<Category> categoryPage = categoryService.findPaginated(PageRequest.of(page - 1, size),
//				categoryService.findByStatusNotNull());
//		if (status == 1) {
//			categoryPage = categoryService.findPaginated(PageRequest.of(page - 1, size),
//					categoryService.findByStatus(true));
//		} else if (status == 2) {
//			categoryPage = categoryService.findPaginated(PageRequest.of(page - 1, size),
//					categoryService.findByStatus(false));
//		} else if (status > 2 || status < 0) {
//			return "redirect:/error/404";
//		}
//		modelMap.put("categories", categoryPage);
//		modelMap.put("subtitle", "allApproved");
//		modelMap.put("selectedStatus", status);
//		modelMap.put("currentPage", page);
		return "admins/category/index";
	}
   
	@GetMapping({ "waiting-for-approval-categories" })
	public String waitingForApproval(ModelMap modelMap) {
//		modelMap.put("subtitle", "Waiting For Approval");
//		modelMap.put("categories", categoryService.findByStatus(null));
		return "admins/category/index";
	}

	@GetMapping({ "create" })
	public String create(ModelMap modelMap) {
//		modelMap.put("category", new Category());
//
//		modelMap.put("parentCategories", parentCategoryService.findAll());
		return "admins/category/create";
	}

	@PostMapping({ "create" })
	public String create(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@RequestParam(value = "image", required = false) MultipartFile file,
			@ModelAttribute("category") @Valid Category category, BindingResult bindingResult,
			Authentication authentication) {
		try {
//			// validate
//			uniqueValidator.validate(category, bindingResult);
////			if (bindingResult.hasErrors() || file.getSize() == 0) {
////				if (file.getSize() == 0) {
////					bindingResult.rejectValue("imagePath", "Upload");
////				}
////				modelMap.put("parentCategories", parentCategoryService.findAll());
////				return "admins/category/create";
////			}
//			if (bindingResult.hasErrors()) {
//				modelMap.put("parentCategories", parentCategoryService.findAll());
//				return "admins/category/create";
//			}
//			// validate
//
//			// file handling
////			var fileName = FileHelper.saveImageFile(file);
//			// file handling
// 
//			// create category
//			category.setStatus(true);
//			category.setUser(userService.findByUsername(authentication.getName()));
//			category.setCreatedAt(new Date());
////			category.setImagePath(fileName);
//			if (categoryService.save(category)) {
//				redirectAttributes.addFlashAttribute("successCreated", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alertCreated", true);
//			}
			// create category
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertCreated", true);
		}
		return "redirect:/admin/category";
	}

	@GetMapping({ "edit/{id}" })
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		try {
//			var category = categoryService.findById(id);
//
//			// validate
//			if (category == null) {
//				return "redirect:/error/404";
//			}
//			// validate
//
//			modelMap.put("category", category);
//			modelMap.put("parentCategories", parentCategoryService.findAll());

			return "admins/category/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/404";
		}

	}

	@PostMapping({ "edit" })
	public String edit(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@RequestParam(value = "image", required = false) MultipartFile file,
			@ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
		try {
//			// validate
//			uniqueValidator.validate(category, bindingResult);
//			if (bindingResult.hasErrors()) {
//				modelMap.put("parentCategories", parentCategoryService.findAll());
//				return "admins/category/edit";
//			}
//			// validate
//
//			// file handling
////			if (file.getSize() != 0) {
////				var fileName = FileHelper.saveImageFile(file);
////				FileHelper.deleteImageFile(category.getImagePath());
////				category.setImagePath(fileName);
////			}
//			// file handling
//
//			// edit category
//			category.setUpdatedAt(new Date());
//			if (categoryService.save(category)) {
//				redirectAttributes.addFlashAttribute("successUpdated", true);
//			} else {
//				redirectAttributes.addFlashAttribute("alertUpdated", true);
//			}
//			// edit category
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertUpdated", true);
		}
		return "redirect:/admin/category";
	}

	@GetMapping({ "delete/{id}" })
	public String delete(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") int id,
			Authentication authentication) {
		try {
//			//validate
//			String userRole = authentication.getAuthorities().iterator().next().toString();
//			if (!userRole.contains("SUPER")) {
//				return "redirect:/error/404";   
//			}
//			//validate
//			
////			var baseCategory = categoryService.findById(id);
//
//			// actual delete
//			if (categoryService.delete(id)) {
//				// delete file
////				FileHelper.deleteImageFile(baseCategory.getImagePath());
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
		return "redirect:/admin/category";
	}

	@GetMapping({ "edit-parent-category" })
	public String editSelectedCategory(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@RequestParam(value = "selectedCategories", required = false) List<Integer> selectedCategories) {
//		// validate
//		if (selectedCategories == null || selectedCategories.size() <= 1) {
//			redirectAttributes.addFlashAttribute("alertSelected", true);
//			return "redirect:/admin/category";
//		}
//		// validate
//		modelMap.put("selectedCategories", selectedCategories);
//		modelMap.put("parentCategories", parentCategoryService.findAll());
		return "admins/category/editParentCategory";
	}

	@PostMapping({ "edit-parent-category" })
	public String editSelectedCategory(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@RequestParam("selectedCategories") List<Integer> selectedCategories,
			@RequestParam("selectedParentCategory") Integer selectedParentCategory) {
//		try {
//			var parentCategory = new Parentcategory();
//			parentCategory.setParentCategoryId(selectedParentCategory);
//
//			for (var e : selectedCategories) {
//				var category = categoryService.findById(e);
//				if (category != null) {
//					category.setParentcategory(parentCategory);
//					categoryService.save(category);
//				}
//			}
//
//			redirectAttributes.addFlashAttribute("successUpdated", true);
//		} catch (Exception e) {
//			redirectAttributes.addFlashAttribute("alertUpdated", true);
//			e.printStackTrace();
//		}

		return "redirect:/admin/category";
	}
	
	@GetMapping({ "waiting-for-approval-category" })
	public String waitingForApprovalAction(ModelMap modelMap, RedirectAttributes redirectAttributes,
			@RequestParam(value = "action", required = true) String action, @RequestParam(value = "id", required = true) int id) {
		try {
//			//validate
//			if(!action.equalsIgnoreCase("approve") && !action.equalsIgnoreCase("refuse")) {
//				return "redirect:/error/400"; 
//			}
//			var category = categoryService.findById(id);
//			if(category == null || category.getStatus() != null) {
//				return "redirect:/error/400";
//			}
//			//validate
//			
//			if(action.equalsIgnoreCase("approve")) {
//				category.setStatus(true);
//				category.setUpdatedAt(new Date());
//				
//				if(categoryService.save(category)) {
//					redirectAttributes.addFlashAttribute("successUpdated", true);
//				}else {
//					redirectAttributes.addFlashAttribute("alertUpdated", true);
//				}
//			}else if(action.equalsIgnoreCase("refuse")) {
//				if (categoryService.delete(id)) {
//					// delete file
////					FileHelper.deleteImageFile(baseCategory.getImagePath());
//					// delete file
//					redirectAttributes.addFlashAttribute("successDeleted", true);
//				} else {
//					redirectAttributes.addFlashAttribute("alertDeleted", true);
//				}
//			}
			return "redirect:/admin/category/waiting-for-approval-categories";
							
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}

//	@GetMapping({ "edit-status" })
//	public String editStatus(ModelMap modelMap, RedirectAttributes redirectAttributes,
//			@RequestParam(value = "selectedCategories", required = false) List<Integer> selectedCategories) {
//		// validate
//		if (selectedCategories == null || selectedCategories.size() <= 1) {
//			redirectAttributes.addFlashAttribute("alertSelected", true);
//			return "redirect:/admin/category";
//		}
//		for (var e : selectedCategories) {
//			var category = categoryService.findById(e);
//			if (category == null || category.getStatus() == null) {
//				return "redirect:/error/500";
//			}
//		}
//		// validate
//		modelMap.put("selectedCategories", selectedCategories);
//		return "admins/category/editStatus";
//	}

//	@GetMapping({ "approve" })
//	public String approve(ModelMap modelMap, RedirectAttributes redirectAttributes,
//			@RequestParam(value = "selectedCategories", required = false) List<Integer> selectedCategories) {
//		// validate
//		if (selectedCategories == null || selectedCategories.size() <= 1) {
//			redirectAttributes.addFlashAttribute("alertSelected", true);
//			return "redirect:/admin/category/waiting-for-approval-categories";
//		}
//		for (var e : selectedCategories) {
//			var category = categoryService.findById(e);
//			if (category == null || category.getStatus() != null) {
//				return "redirect:/error/500";
//			}
//		}
//		// validate
//		modelMap.put("selectedCategories", selectedCategories);
//		return "admins/category/editStatus";
//	}

//	@PostMapping({ "edit-status" })
//	public String editStatus(ModelMap modelMap, RedirectAttributes redirectAttributes,
//			@RequestParam("selectedCategories") List<Integer> selectedCategories,
//			@RequestParam("status") boolean status) {
//		try {
//			for (var e : selectedCategories) {
//				var category = categoryService.findById(e);
//				if (category != null) {
//					category.setStatus(status);
//					;
//					categoryService.save(category);
//				}
//			}
//
//			redirectAttributes.addFlashAttribute("successUpdated", true);
//		} catch (Exception e) {
//			redirectAttributes.addFlashAttribute("alertUpdated", true);
//			e.printStackTrace();
//		}
//
//		return "redirect:/admin/category";
//	}
}
