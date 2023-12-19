package com.dodo.api.controllers.api.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IProductService;
import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.dtos.ProductDto;
import com.dodo.api.modelview.ProductView;

@RestController
@RequestMapping("api/data/product")
public class ProductApiController {
	@Autowired
	IShopOwnerService ownerService;
	
	@Autowired
	IProductService productService;

	//allow all
	@GetMapping(value = { "find-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> findById(
			@RequestParam(value = "productId", required = true) int id
			) {
		try {
			return new ResponseEntity<ProductDto>(productService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ProductDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-by-shop-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> findByShopownerOwnerId(
			Authentication auth, 
			@RequestParam(value = "shopId", required = true) int id
			) {
		try {
			return new ResponseEntity<List<ProductDto>>(productService.findByShopownerOwnerId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-product-without-promotion-by-current-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> findProductWithoutPromotionByShopownerId(Authentication auth) {
		try {
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			return new ResponseEntity<List<ProductDto>>(productService.findProductWithoutPromotionByShopownerId(shopId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-active-product-by-current-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> findByShopownerOwnerIdAndStatusActive(Authentication auth) {
		try {
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			return new ResponseEntity<List<ProductDto>>(productService.findByShopownerOwnerIdAndStatusActive(shopId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = { "find-by-name-and-current-shop" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> findByProductNameAndShopownerOwnerId(
			Authentication auth,
			@RequestParam(value = "name", required = true) String name
			) {
		try {
			var shopId = ownerService.findByUserUsername(auth.getName()).getOwnerId();
			return new ResponseEntity<ProductDto>(productService.findByProductNameAndShopownerOwnerId(name, shopId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ProductDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "get-total-quantity-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getTotalQuantityByProductId(
			Authentication auth,
			@RequestParam(value = "productId", required = true) int id
			) {
		try {
			return new ResponseEntity<Integer>(productService.getTotalQuantityByProductId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-product-custom" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> findByProductIdCustom(
			@RequestParam(value = "productId", required = true) int id,
			@RequestParam(value = "productStatus", required = true) Boolean productStatus,
			@RequestParam(value = "categoryStatus", required = true, defaultValue = "1") Boolean categoryStatus,
			@RequestParam(value = "shopStatus", required = true) Boolean shopStatus
			) {
		try {
			return new ResponseEntity<ProductDto>(productService.findByProductIdCustom(id, productStatus, categoryStatus, shopStatus), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ProductDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-by-keyword" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> findByKeyword(
			@RequestParam(value = "keyword", required = true) String keyword
			) {
		try {
			return new ResponseEntity<List<ProductDto>>(productService.findByKeyword(keyword), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-product-view-by-product-name-and-category-name" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductView>> findProductViewByProductNameAndCategoryName(
			@RequestParam(value = "productName", required = true) String productName,
			@RequestParam(value = "categoryName", required = true) String categoryName
			) {
		try {
			return new ResponseEntity<List<ProductView>>(productService.findProductViewByProductNameAndCategoryName(productName, categoryName), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductView>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-by-status-custom" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> findByStatusCustom() {
		try {
			return new ResponseEntity<List<ProductDto>>(productService.findByStatusCustom(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductDto>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-product-view-by-name-and-category-name-paginate" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ProductView>> findProductViewPageByProductNameAndCategoryName(
			@RequestParam(value = "productName", required = true) String productName,
			@RequestParam(value = "categoryName", required = true) String categoryName,
			@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "size", defaultValue = "10") int size
			) {
		try {
			return new ResponseEntity<Page<ProductView>>(productService.findProductViewPageByProductNameAndCategoryName(productName, categoryName, page, size), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Page<ProductView>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-product-view-by-category-id-paginate" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ProductView>> findProductViewPageByCategoryId(
			@RequestParam(value = "categoryId", required = true) int id,
			@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "size", defaultValue = "10") int size
			) {
		try {
			return new ResponseEntity<Page<ProductView>>(productService.findProductViewPageByCategoryId(id, page, size), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Page<ProductView>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//allow all
	@GetMapping(value = { "find-by-shop-id-custom-paginate" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ProductView>> getListProductByShop(
			@RequestParam(value = "shopId", required = true) int id,
			@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "productStatus", required = true) Boolean productStatus,
			@RequestParam(value = "categoryStatus", required = true, defaultValue = "1") Boolean categoryStatus,
			@RequestParam(value = "shopStatus", required = true) Boolean shopStatus
			) {
		try {
			return new ResponseEntity<Page<ProductView>>(productService.getListProductByShop(productStatus, categoryStatus, shopStatus, id, page, size), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Page<ProductView>>(HttpStatus.BAD_REQUEST);
		}

	}
}
