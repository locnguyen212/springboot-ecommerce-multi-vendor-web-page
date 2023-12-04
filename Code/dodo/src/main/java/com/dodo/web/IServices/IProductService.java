package com.dodo.web.IServices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.dodo.web.models.Product;
import com.dodo.web.modelview.ProductView;

public interface IProductService {
	public List<Product> findAll();

	public Product findById(int id);

	public boolean save(Product product);

	public boolean delete(int id);

	//public Product findProductById(int id);
	public Product findProductById(int id, Boolean statusPr, Boolean statusCate, Boolean statusShop);

	public List<Product> listProductByIdCategory(int idCategory);

	public List<String> searchByTerm(String term);

	public Product getProductDetail(int id);

	// search product header
	public List<ProductView> findProductViewByProductName(String productName, String categoryName);

	// get all
	public List<Product> getAllAndStatus();

	public Page<Product> getProductsByOwnerId(Integer ownerId, Pageable pageable);
	public List<Product> getProductsByOwnerId2(Integer ownerId);

	Page<ProductView> findProductViewByProductNamePage(String productName, String categoryName, int pageNo, int pageSize);

	Page<ProductView> findProductViewByCategoryIdPage(int id, int pageNo, int pageSize);

	Page<Product> getProductsByOwnerIdAndStatus(Integer ownerId, Boolean status, Pageable pageable);
	Page<Product> findByOwnerIdAndCategoryId(Integer ownerId, int categoryId, Pageable pageable);
	
	//LOC
	public List<Product> findByShopownerOwnerId(int id);
	public List<Product> findProductWithoutPromotionByShopownerId(int shopownerId);
	public List<Product> findByShopownerOwnerIdAndStatusActive(int id);
	public Product findByProductNameAndShopownerOwnerId(String productName, int shopId);
	//LOC

	//THIEN
	public Page<ProductView> getListProductByShop(@Param("statusPr") Boolean statusPr, @Param("statusCate") Boolean statusCate, @Param("statusShop") Boolean statusShop, @Param("idShop") int idShop, int pageNo, int pageSize);
	//THIEN

}