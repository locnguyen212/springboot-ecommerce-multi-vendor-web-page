package com.dodo.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Orderdetail;
import com.dodo.api.modelview.ReviewModelView;

@Repository
public interface OrderDetailRepository extends JpaRepository<Orderdetail, Integer> {
	// ====LOC====
	public List<Orderdetail> findByOrderOrderId(int id);

	public List<Orderdetail> findByProductProductId(int id);

	public List<Orderdetail> findByProductProductName(String name);
	// ====LOC====

	@Query("select new com.dodo.api.modelview.ReviewModelView(od.order, od.product) "
			+ "from Orderdetail od "
			+ "where od.order.user.userId=:userId and od.isReviewed = false and od.order.paymentStatus='Payed'")
	public List<ReviewModelView> getReviewModelByUserId(@Param("userId") int userId);
}
