package com.dodo.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Product;
import com.dodo.api.modelview.ProductView;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("from Product where category.categoryId  =:idCategory")
	public List<Product> listProductByIdCategory(@Param("idCategory") int idCategory);

	@Query("from Product p "
			+ "LEFT JOIN p.category c "
			+ "LEFT JOIN p.shopowner sh "
			+ "WHERE p.productId =:id "
			+ "AND (:statusPr IS NULL OR p.status =:statusPr) " 
			+ "AND (:statusCate IS NULL OR c.status =:statusCate) " 
			+ "AND (:statusShop IS NULL OR sh.status =:statusShop) " 
			+ "GROUP BY p.productName "
			+ "ORDER BY COALESCE(p.updatedAt, p.createdAt) DESC")
	public Product findProductById(@Param("id") int id, @Param("statusPr") Boolean statusPr, @Param("statusCate") Boolean statusCate, @Param("statusShop") Boolean statusShop );

	@Query("from Product where productName like %:term%")
	public List<String> searchByTerm(@Param("term") String term);

	@Query(value = "select * from Product order by createdAt desc limit :n", nativeQuery = true)
	public List<Product> getNewProduct(@Param("n") int n);

	// recommend search tagbar header
	@Query("SELECT new com.dodo.api.modelview.ProductView(p.productId, p.productName, p.price, p.stockQuantity, p.productImage, p.status, c.categoryId, c.categoryName, sh.user.userId, sh.shopName, sh.shopLogoPath, pr.discountAmount, pr.startDate, pr.endDate, COALESCE(COUNT(r.comment), 0) AS totalComments, COALESCE(AVG(r.rating), 0) AS averageRating) "
			+ "FROM Product p LEFT JOIN p.category c "
			+ "LEFT JOIN p.shopowner sh "
			+ "LEFT JOIN Promotion pr ON p.productId = pr.product.productId "
			+ "LEFT JOIN Review r ON p.productId = r.product.productId " 
			+ "WHERE (:productName IS NULL OR p.productName LIKE %:productName%) " 
			+ "AND (:categoryName IS NULL OR c.categoryName LIKE %:categoryName%) " 
			+ "AND (p.status = true AND c.status = true AND sh.status = true) " 
			+ "GROUP BY p.productName "
			+ "ORDER BY COALESCE(p.updatedAt, p.createdAt) DESC limit :n")
	public List<ProductView> findProductViewByProductName(@Param("productName") String productName,
			@Param("categoryName") String categoryName, @Param("n") int n);

	//@Query("from Product where status=:status ")
	@Query("from Product p "
			+ "LEFT JOIN p.category c "
			+ "LEFT JOIN p.shopowner sh "
			+ "WHERE (:statusPr IS NULL OR p.status =:statusPr) "
			+ "AND (:statusCate IS NULL OR c.status =:statusCate) " 
			+ "AND (:statusShop IS NULL OR sh.status =:statusShop) " 
			+ "GROUP BY p.productName "
			+ "ORDER BY COALESCE(p.updatedAt, p.createdAt) DESC")
	public List<Product> getAllAndStatus(@Param("statusPr") Boolean statusPr, @Param("statusCate") Boolean statusCate, @Param("statusShop") Boolean statusShop );
	
	@Query("SELECT p FROM Product p WHERE p.shopowner.id = :ownerId")
	public List<Product> findByOwnerId2(@Param("ownerId") Integer ownerId);

	@Query(value = "SELECT new com.dodo.api.modelview.ProductView(p.productId, p.productName, p.price, p.stockQuantity, p.productImage, p.status, c.categoryId, c.categoryName, sh.user.userId, sh.shopName, sh.shopLogoPath, pr.discountAmount, pr.startDate, pr.endDate, COALESCE(COUNT(r.comment), 0) AS totalComments, COALESCE(AVG(r.rating), 0) AS averageRating) "
			+ "FROM Product p LEFT JOIN p.category c "
			+ "LEFT JOIN p.shopowner sh "
			+ "LEFT JOIN Promotion pr ON p.productId = pr.product.productId "
			+ "LEFT JOIN Review r ON p.productId = r.product.productId "
			+ "WHERE (:productName IS NULL OR p.productName LIKE %:productName%) " 
			+ "AND (:categoryName IS NULL OR c.categoryName LIKE %:categoryName%) " 
			+ "AND (p.status = true AND c.status = true) " 
			+ "GROUP BY p.productName "
			+ "ORDER BY COALESCE(p.updatedAt, p.createdAt) DESC")
	public Page<ProductView> findProductViewByProductNamePage(@Param("productName") String productName,
			@Param("categoryName") String categoryName, Pageable pageable);

	@Query(value = "SELECT new com.dodo.api.modelview.ProductView(p.productId, p.productName, p.price, p.stockQuantity, p.productImage, p.status, c.categoryId, c.categoryName, sh.user.userId, sh.shopName, sh.shopLogoPath, pr.discountAmount, pr.startDate, pr.endDate, COALESCE(COUNT(r.comment), 0) AS totalComments, COALESCE(AVG(r.rating), 0) AS averageRating) "
			+ "FROM Product p LEFT JOIN p.category c "
			+ "LEFT JOIN p.shopowner sh "
			+ "LEFT JOIN Promotion pr ON p.productId = pr.product.productId "
			+ "LEFT JOIN Review r ON p.productId = r.product.productId "
			+ "WHERE c.categoryId = :id "
			+ "AND (p.status = true AND c.status = true) " 
			+ "GROUP BY p.productName "
			+ "ORDER BY COALESCE(p.updatedAt, p.createdAt) DESC")
	public Page<ProductView> findProductViewByCategoryIdPage(@Param("id") int id, Pageable pageable);

	//sản phẩm của shop
	@Query(value = "SELECT new com.dodo.api.modelview.ProductView(p.productId, p.productName, p.price, p.stockQuantity, p.productImage, p.status, c.categoryId, c.categoryName, sh.user.userId, sh.shopName, sh.shopLogoPath, pr.discountAmount, pr.startDate, pr.endDate, COALESCE(COUNT(r.comment), 0) AS totalComments, COALESCE(AVG(r.rating), 0) AS averageRating) "
			+ "FROM Product p LEFT JOIN p.category c "
			+ "LEFT JOIN p.shopowner sh "
			+ "LEFT JOIN Promotion pr ON p.productId = pr.product.productId "
			+ "LEFT JOIN Review r ON p.productId = r.product.productId "
			+ "WHERE (:statusPr IS NULL OR p.status =:statusPr) "
			+ "AND (:statusCate IS NULL OR c.status =:statusCate) " 
			+ "AND (:statusShop IS NULL OR sh.status =:statusShop) " 
			+ "AND sh.ownerId = :idShop "
			+ "GROUP BY p.productName "
			+ "ORDER BY COALESCE(p.updatedAt, p.createdAt) DESC")
	public Page<ProductView> getListProductByShop(@Param("statusPr") Boolean statusPr, @Param("statusCate") Boolean statusCate, @Param("statusShop") Boolean statusShop, @Param("idShop") int idShop, Pageable pageable);
  
	//tính sao, review cho shop

	//LOC
	@Query("SELECT p FROM Product p WHERE p.shopowner.ownerId = :id ORDER BY p.status DESC, p.createdAt DESC")
	public List<Product> findByShopownerOwnerId(@Param("id")int id);
	
	@Query("SELECT p "
		+ "FROM Product p "
		+ "WHERE p.shopowner.ownerId = :id AND (p.status = true AND p.stockQuantity > 0) "
		+ "ORDER BY p.status DESC, p.createdAt DESC")
	public List<Product> findByShopownerOwnerIdAndStatusActive(@Param("id")int id);
	
	@Query("SELECT p "
			+ "FROM Product p "
			+ "WHERE p.shopowner.ownerId = :shopownerId AND p.productId NOT IN :ids AND (p.status = true AND p.stockQuantity > 0)")
	public List<Product> findByIdsNotInAndShopownerIdAndStatusActive(@Param("ids") List<Integer> ids, @Param("shopownerId") int shopownerId);
	
	public Product findByProductNameAndShopownerOwnerId(String productName, int shopId);
	//LOC
	
}
