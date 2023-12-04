package com.dodo.web.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Orderdetail;
import com.dodo.web.modelview.ReviewModelView;

@Repository
public interface OrderDetailRepository extends JpaRepository<Orderdetail, Integer> {
	// ====LOC====
	public List<Orderdetail> findByOrderOrderId(int id);

	public List<Orderdetail> findByProductProductId(int id);

	public List<Orderdetail> findByProductProductName(String name);

	// public List<Orderdetail> findByShopownerOwnerId(int id);
	// public List<Orderdetail> findByShopownerShopName(String name);
	// ====LOC====

	@Query("select new com.dodo.web.modelview.ReviewModelView(od.order, od.product) "
			+ "from Orderdetail od "
			+ "where od.order.user.userId=:userId and od.isReviewed = false and od.order.paymentStatus='Payed'")
	public List<ReviewModelView> getReviewModelByUserId(@Param("userId") int userId);

	// Huy
	@Query("SELECT od.product.productId, od.product.productName, SUM(od.quantity) as totalQuantity " +
			"FROM Orderdetail od " +
			"WHERE od.order.shopowner.id = :ownerId " +
			"GROUP BY od.product.productId, od.product.productName " +
			"ORDER BY totalQuantity DESC")
	List<Object[]> findTopSellingProducts(@Param("ownerId") int ownerId);

	@Query("SELECT DATE(od.order.orderDate) as orderDate, SUM(od.quantity) as totalQuantity " +
			"FROM Orderdetail od " +
			"WHERE od.order.shopowner.id = :ownerId " +
			"AND od.order.orderDate BETWEEN :startDate AND :endDate " +
			"GROUP BY DATE(od.order.orderDate)")
	List<Object[]> findOrderQuantitiesByDateRange(
			@Param("ownerId") int ownerId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}
