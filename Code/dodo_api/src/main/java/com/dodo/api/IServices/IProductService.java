package com.dodo.api.IServices;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dodo.api.dtos.ProductDto;
import com.dodo.api.modelview.ProductView;

public interface IProductService {
	//LOC
	public List<ProductDto> findAll();

	public ProductDto findById(int id);

	public boolean save(ProductDto product);

	public boolean delete(int id);
	
	public List<ProductDto> findByShopownerOwnerId(int id);
	public List<ProductDto> findProductWithoutPromotionByShopownerId(int shopownerId);
	public List<ProductDto> findByShopownerOwnerIdAndStatusActive(int id);
	public ProductDto findByProductNameAndShopownerOwnerId(String productName, int shopId);
	//LOC
	

	//THIEN
	public int getTotalQuantityByProductId(int productId);
	public ProductDto findByProductIdCustom(int id, Boolean statusPr, Boolean statusCate, Boolean statusShop);

	public List<ProductDto> findByKeyword(String keyword);

	// search product header
	public List<ProductView> findProductViewByProductNameAndCategoryName(String productName, String categoryName);

	// get all
	public List<ProductDto> findByStatusCustom();

	public Page<ProductView> findProductViewPageByProductNameAndCategoryName(String productName, String categoryName, int pageNo, int pageSize);

	public Page<ProductView> findProductViewPageByCategoryId(int id, int pageNo, int pageSize);
	
	public Page<ProductView> getListProductByShop(Boolean statusPr, Boolean statusCate, Boolean statusShop, int idShop, int pageNo, int pageSize);
	//THIEN

}