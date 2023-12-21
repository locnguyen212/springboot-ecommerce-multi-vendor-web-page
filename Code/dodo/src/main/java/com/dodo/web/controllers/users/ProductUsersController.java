package com.dodo.web.controllers.users;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.web.IServices.IItemService;
import com.dodo.web.IServices.IOrderDetailService;
import com.dodo.web.IServices.IProductService;
import com.dodo.web.IServices.IReviewService;
import com.dodo.web.helpers.FileHelper;
import com.dodo.web.models.Orderdetail;
import com.dodo.web.models.Product;
import com.dodo.web.models.Review;
import com.dodo.web.models.User;
import com.dodo.web.modelview.ProductView;
import com.dodo.web.modelview.ReviewModelView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = { "user/product" })
public class ProductUsersController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IItemService itemService;

	@Autowired
	private IOrderDetailService orderDetailService;

	@Autowired
	private IReviewService reviewService;

	private boolean reviewPlaced = false;

	@GetMapping({ "detail" })
	public String getListProductByIdCategory(
			ModelMap modelMap, 
			@RequestParam("id") int id, 
			HttpSession session
			) {
		try {
			// Product product = productService.getProductDetail(id);
			Product product = productService.findProductById(id, true, null, true);
			var check = session.getAttribute("shopStatus");
			if (check != null && check.equals(true)) {
				product = productService.findProductById(id, null, null, null);
			}
			if (product == null) {
				return "redirect:/error/404";
			}
			//save id product when view
			session.setAttribute("product_search", product.getProductName());
			session.setAttribute("cate_search", product.getCategory().getCategoryName());

			int totalQuantityInCarts = itemService.getTotalQuantityForProduct(id);
			List<Review> reviews = reviewService.findByProductProductId(id);
			double averageRating = calculateAverageRating(reviews);
			int countRating = reviews.size();

			List<Review> listrv = reviews.stream()
					.sorted(Comparator.comparing(Review::getCreatedAt).reversed())
					.collect(Collectors.toList());
			// Tính sao start
			double percentageFiveStar = calculatePercentage(reviews, 5);
			double percentageFourStar = calculatePercentage(reviews, 4);
			double percentageThreeStar = calculatePercentage(reviews, 3);
			double percentageTwoStar = calculatePercentage(reviews, 2);
			double percentageOneStar = calculatePercentage(reviews, 1);
			modelMap.addAttribute("percentageFiveStar", percentageFiveStar);
			modelMap.addAttribute("percentageFourStar", percentageFourStar);
			modelMap.addAttribute("percentageThreeStar", percentageThreeStar);
			modelMap.addAttribute("percentageTwoStar", percentageTwoStar);
			modelMap.addAttribute("percentageOneStar", percentageOneStar);
			// Tính sao end

			modelMap.addAttribute("product", product);
			modelMap.addAttribute("totalQuantityInCarts", totalQuantityInCarts);
			modelMap.addAttribute("review", listrv);
			modelMap.addAttribute("averageRating", averageRating);
			modelMap.addAttribute("countRating", countRating);
			return "users/product/product-detail";
		} catch (Exception e) {
			return "redirect:/error/404";
		}

	}

	@GetMapping({ "listUnratedProduct" })
	public String listUnratedProduct(HttpSession session, ModelMap modelMap) {
		User checkUser = (User) session.getAttribute("user");
		if (checkUser != null) {
			List<ReviewModelView> product = orderDetailService.getReviewModelByUserId(checkUser.getUserId());
			modelMap.addAttribute("listUnratedProduct", product);
			return "users/product/list-Unrated-Product";
		}
		return "redirect:/user/account/login";
	}

	@GetMapping({ "review" })
	public String order(
			HttpSession session, 
			ModelMap modelMap, 
			@RequestParam("productId") int idP,
			@RequestParam("orderId") int idOd
			) {
		User checkUser = (User) session.getAttribute("user");
		if (checkUser != null) {

			List<ReviewModelView> list = orderDetailService.getReviewModelByUserId(checkUser.getUserId());
			if (list != null) {
				Optional<ReviewModelView> reviewModelViewOptional = list.stream()
						.filter(p -> p.getOrder().getOrderId() == idOd)
						.findFirst();
				if (reviewModelViewOptional.isPresent()) {
					ReviewModelView reviewModelView = reviewModelViewOptional.get();
					List<Orderdetail> orderDetails = new ArrayList<>(reviewModelView.getOrder().getOrderdetails());
					Product checkProduct = productService.findById(idP);
					if (orderDetails != null && checkProduct != null) {
						Orderdetail orderDetail = orderDetails.iterator().next();
						Review rv = new Review();
						rv.setUser(checkUser);
						rv.setProduct(checkProduct);
						rv.setOrderDetail(orderDetail);
						rv.setRating(5);
						rv.setComment("Please enter a review ...");
						modelMap.addAttribute("review", rv);
						return "users/product/review-product";
					}
					return "redirect:/error/500";
				} else {
					return "redirect:/error/500";
				}
			}
		}

		return "redirect:/user/account/login";
	}

	@PostMapping({ "review" })
	public String submitReview(
			@ModelAttribute Review review, 
			HttpSession session, 
			ModelMap modelMap,
			@RequestParam("image") MultipartFile file
			) {
		try {
			User checkUser = (User) session.getAttribute("user");
			if (checkUser != null) {
				if (review != null) {
					Orderdetail orderDetail = orderDetailService.findById(review.getOrderDetail().getOrderDetailId());
					Product product = productService.findById(review.getProduct().getProductId());
					orderDetail.setIsReviewed(true);
					orderDetailService.save(orderDetail);

					Review rv = review;
					rv.setProduct(product);
					rv.setUser(checkUser);
					rv.setOrderDetail(orderDetail);
					rv.setReviewDate(new Date());
					rv.setCreatedAt(new Date());
					rv.setIsVerified(true);
					if (file.getSize() != 0) {
						var fileName = FileHelper.saveImageFile(file);
						rv.setReviewImage(fileName);
					}
					reviewService.save(review);
					reviewPlaced = true;
					return "redirect:/user/product/review-success";
				}
				return "redirect:/error/500";
			}
			return "redirect:/user/account/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/500";
		}
	}

	@GetMapping("/review-success")
	public String reviewSuccess(ModelMap modelMap, HttpSession session) {
		User checkUser = (User) session.getAttribute("user");
		if (checkUser != null) {
			if (reviewPlaced) {
				reviewPlaced = false;
				return "users/product/review-product-success";
			} else {
				return "error/404";
			}
		}
		return "redirect:/user/account/login";
	}

	@GetMapping("/searchByKeyWord")
	public String searchProduct(
			ModelMap modelMap, 
			@RequestParam("productName") String productName,
			@RequestParam("categoryName") String categoryName, 
			@RequestParam(defaultValue = "1") String page, 
			HttpSession session
			) {
		int pageSize = 8;
		int pageNo = Integer.parseInt(page);
		// List<ProductView> listSearchProducts =
		// productService.findProductViewByProductName(productName, categoryName);
		Page<ProductView> listSearchProducts = productService.findProductViewByProductNamePage(productName,
				categoryName, pageNo, pageSize);

		//save id product when view
		List<ProductView> productList = listSearchProducts.getContent();
		if (!productList.isEmpty()) {

			String firstProductId = productList.get(0).getProductName();
			String cateId = productList.get(0).getCategoryName();
			session.setAttribute("product_search", firstProductId);
			session.setAttribute("cate_search", cateId);
		}

		modelMap.addAttribute("listSearchProducts", productList);
		modelMap.addAttribute("currentPage", pageNo);
		modelMap.addAttribute("totalPages", listSearchProducts.getTotalPages());
		modelMap.addAttribute("totalItems", listSearchProducts.getTotalElements());
		modelMap.addAttribute("s_productName", productName);
		modelMap.addAttribute("s_categoryName", categoryName);

		List<Orderdetail> allDetailProduct = orderDetailService.findAll();
		modelMap.addAttribute("allDetailProduct", allDetailProduct);

		return "users/product/search-product";
	}

	@GetMapping("/category")
	public String searchProductByCategory(
			ModelMap modelMap, 
			@RequestParam("id") String categoryId,
			@RequestParam(defaultValue = "1") String page
			) {
		int id = Integer.parseInt(categoryId);
		int pageSize = 8;
		int pageNo = Integer.parseInt(page);

		Page<ProductView> listSearchProductsByCategory = productService.findProductViewByCategoryIdPage(id, pageNo,
				pageSize);
		String categoryName = listSearchProductsByCategory.getContent()
				.stream()
				.map(ProductView::getCategoryName)
				.findFirst()
				.orElse(null);

		modelMap.addAttribute("listSearchProducts", listSearchProductsByCategory.getContent());
		modelMap.addAttribute("currentPage", pageNo);
		modelMap.addAttribute("totalPages", listSearchProductsByCategory.getTotalPages());
		modelMap.addAttribute("totalItems", listSearchProductsByCategory.getTotalElements());
		modelMap.addAttribute("s_categoryName", categoryName);
		modelMap.addAttribute("s_categoryId", categoryId);

		List<Orderdetail> allDetailProduct = orderDetailService.findAll();
		modelMap.addAttribute("allDetailProduct", allDetailProduct);

		return "users/product/search-cate-product";
	}

	private double calculateAverageRating(List<Review> reviews) {
		if (reviews == null || reviews.isEmpty()) {
			return 0.0;
		}
		double totalRating = reviews.stream().mapToDouble(Review::getRating).sum();
		return totalRating / reviews.size();
	}

	private double calculatePercentage(List<Review> reviews, int star) {
		long count = reviews.stream().filter(review -> review.getRating() == star).count();
		long totalReviews = reviews.size();
		double percentage = totalReviews > 0 ? (double) count / totalReviews * 100 : 0;
		return Math.round(percentage * 100.0) / 100.0;
	}
}
