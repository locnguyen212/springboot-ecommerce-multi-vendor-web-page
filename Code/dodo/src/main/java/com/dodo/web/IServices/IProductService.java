package com.dodo.web.IServices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.dodo.web.models.Product;
import com.dodo.web.modelview.ProductView;

public interface IProductService {
	//LOC
	public List<Product> findAll();

	public Product findById(int id);

	public boolean save(Product product);

	public boolean delete(int id);
	
	public List<Product> findByShopownerOwnerId(int id);
	public List<Product> findProductWithoutPromotionByShopownerId(int shopownerId);
	public List<Product> findByShopownerOwnerIdAndStatusActive(int id);
	public Product findByProductNameAndShopownerOwnerId(String productName, int shopId);
	//LOC
	
	//THIEN
	public Product findProductById(int id, Boolean statusPr, Boolean statusCate, Boolean statusShop);

	// search product header
	public List<ProductView> findProductViewByProductName(String productName, String categoryName);

	// get all
	public List<Product> getAllAndStatus();

	public List<Product> getProductsByOwnerId2(Integer ownerId);

	public Page<ProductView> findProductViewByProductNamePage(String productName, String categoryName, int pageNo, int pageSize);

	public Page<ProductView> findProductViewByCategoryIdPage(int id, int pageNo, int pageSize);
	
	public Page<ProductView> getListProductByShop(Boolean statusPr, Boolean statusCate, Boolean statusShop, int idShop, int pageNo, int pageSize);
	//THIEN


	


}