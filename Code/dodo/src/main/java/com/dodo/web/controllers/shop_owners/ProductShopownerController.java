package com.dodo.web.controllers.shop_owners;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.dodo.web.IServices.ICategoryService;
import com.dodo.web.IServices.IOrderCancellationService;
import com.dodo.web.IServices.IParentCategoryService;
import com.dodo.web.IServices.IProductService;
import com.dodo.web.IServices.IPromotionService;
import com.dodo.web.IServices.IShopOwnerCouponService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.helpers.FileHelper;
import com.dodo.web.models.Product;
import com.dodo.web.validators.CategoryUniqueValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("shop-owners/product")
public class ProductShopownerController {
	@Autowired
	IUserService userService;

	@Autowired
	IShopOwnerService shopOwnerService;

	@Autowired
	IShopOwnerCouponService couponService;

	@Autowired
	IOrderCancellationService cancellationService;

	@Autowired
	ICategoryService categoryService;

	@Autowired
	IParentCategoryService parentCategoryService;

	@Autowired
	CategoryUniqueValidator uniqueValidator;

	@Autowired
	IPromotionService promotionService;

	@Autowired
	IProductService productService;

	@GetMapping({ "index", "", "/" })
	public String index(ModelMap modelMap, Authentication authentication) {
		var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
		var products = productService.findByShopownerOwnerId(shopId);
		modelMap.put("products", products);
		return "shopOwners/product/index";
	}



	@GetMapping({ "create" })
	public String create(ModelMap modelMap, HttpSession session) {
		modelMap.put("product", new Product());
		modelMap.put("categories", categoryService.findByStatus(true));

		return "shopOwners/product/create";
	}
 
	@PostMapping({ "create" })
	public String create(
			ModelMap modelMap, 
			Authentication authentication, 
			@RequestParam("image") MultipartFile file,
			RedirectAttributes redirectAttributes, 
			@ModelAttribute("product") @Valid Product product,
			BindingResult bindingResult
			) {
		try {
			// validate
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();			
			if (file.getSize() == 0) {
				bindingResult.rejectValue("productImage", "Upload");
			}
			
			if(product.getProductName()!=null || !product.getProductName().isBlank()){
				var pro = productService.findByProductNameAndShopownerOwnerId(product.getProductName(), shopId);	
				if(pro!=null){
					bindingResult.rejectValue("productName", "product.uniqueName");
				}
			}
					
			if (bindingResult.hasErrors()) {				
				modelMap.put("categories", categoryService.findByStatus(true));
				return "shopOwners/product/create";
			}
			// validate

			// file handling
			var fileName = FileHelper.saveImageFile(file);
			// file handling

			// create product
			var shop = shopOwnerService.findByUserUsername(authentication.getName());
			product.setShopowner(shop);
			product.setCreatedAt(new Date());
			product.setProductImage(fileName);
			if (productService.save(product)) {
				redirectAttributes.addFlashAttribute("successCreated", true);
			} else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			// create product

			return "redirect:/shop-owners/product";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}

	}

	@GetMapping({ "switch-status/{id}" })
	public String switchStatus(
			ModelMap modelMap, 
			RedirectAttributes redirectAttributes, 
			@PathVariable("id") int id,
			Authentication authentication
			) {
		try {
			// validate
			var product = productService.findById(id);
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();

			if (product == null || product.getShopowner().getOwnerId() != shopId) {
				return "redirect:/error/404";
			}
			// validate

			//switch status
			if (product.getStatus() == true) {
				product.setStatus(false);
			} else if (product.getStatus() == false) {
				product.setStatus(true);
			}
			//switch status

			// update product
			product.setUpdatedAt(new Date());
			if (productService.save(product)) {
				redirectAttributes.addFlashAttribute("successUpdated", true);
			} else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			// update product
			return "redirect:/shop-owners/product";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
                   
	@GetMapping({ "edit/{id}" })
	public String edit(
			ModelMap modelMap, 
			RedirectAttributes redirectAttributes, 
			Authentication authentication,
			@PathVariable("id") int id, 
			HttpSession session
			) {
		try {
			var product = productService.findById(id);
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
			if (product == null || product.getShopowner().getOwnerId() != shopId) {
				return "redirect:/error/404";
			}
 
			modelMap.put("product", product);
			modelMap.put("categories", categoryService.findByStatus(true));
			return "shopOwners/product/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
 
	@PostMapping({ "edit" })
	public String edit(
			ModelMap modelMap, 
			Authentication authentication, 
			@RequestParam(value="image", required = false) MultipartFile file,
			RedirectAttributes redirectAttributes, 
			@ModelAttribute("product") @Valid Product product,
			BindingResult bindingResult
			) {
		try {
			// validate
			if(product.getProductId() == null || productService.findById(product.getProductId()) == null) {
				return "redirect:/error/400";
			}
			
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
			var baseProduct = productService.findById(product.getProductId());
			if(baseProduct == null || baseProduct.getShopowner().getOwnerId()!=shopId) {
				return "redirect:/error/400";
			}
			
			if((product.getProductName()!=null || !product.getProductName().isBlank()) && (baseProduct!=null && !baseProduct.getProductName().equalsIgnoreCase(product.getProductName()))){
				var pro = productService.findByProductNameAndShopownerOwnerId(product.getProductName(), shopId);	
				if(pro!=null){
					bindingResult.rejectValue("productName", "product.uniqueName");
				}
			}
			
			if (bindingResult.hasErrors()) {
				modelMap.put("categories", categoryService.findByStatus(true));
				return "shopOwners/product/edit";
			}
			// validate
 
			// file handling
			if (file.getSize() != 0) {
				var fileName = FileHelper.saveImageFile(file);
				FileHelper.deleteImageFile(product.getProductImage());
				product.setProductImage(fileName);
			}
			// file handling

			// update product
			product.setUpdatedAt(new Date());
			if (productService.save(product)) {
				redirectAttributes.addFlashAttribute("successUpdated", true);
			} else {
				redirectAttributes.addFlashAttribute("alert", true);
			}
			// update product
			return "redirect:/shop-owners/product";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
	
	@GetMapping({ "delete/{id}" })
	public String delete(
			ModelMap modelMap, 
			Authentication authentication, 
			RedirectAttributes redirectAttributes,
			@PathVariable("id") int id
			) {
		try {
			// validate
			var product = productService.findById(id);
			var shopId = shopOwnerService.findByUserUsername(authentication.getName()).getOwnerId();
			if (product == null || product.getShopowner().getOwnerId() != shopId) {
				return "redirect:/error/404";
			}
			// validate

			// actual delete
			if (productService.delete(id)) {
				// delete file
				FileHelper.deleteImageFile(product.getProductImage());
				// delete file

				redirectAttributes.addFlashAttribute("successDelete", true);
			} else {
				redirectAttributes.addFlashAttribute("alertDelete", true);
			}
			// actual delete
			return "redirect:/shop-owners/product";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}
}
