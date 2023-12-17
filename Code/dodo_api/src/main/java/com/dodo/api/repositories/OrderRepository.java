package com.dodo.api.repositories;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Order;
import com.dodo.api.modelview.OrderView;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	// ====LOC====
	public List<Order> findByOrderStatus(String status);

	@Query("from Order o where o.user.userId = :id order by o.orderDate desc")
	public List<Order> findByUserUserId(int id);

	@Query("from Order o where o.user.username = :name order by o.orderDate desc")
	public List<Order> findByUserUsername(String name);

	@Query("from Order o where o.user.username = :username and o.orderStatus = :status order by o.orderDate desc")
	public List<Order> findByUserUsernameAndOrderStatus(String username, String status);

	@Query("from Order o where o.paymentStatus = :status and o.user.userId = :id order by o.orderDate desc")
	public List<Order> findByPaymentStatusAndUserUserId(String status, int id);

	@Query("select new com.dodo.api.modelview.OrderView(o.shopowner, sum(o.totalAmount), month(o.orderDate), year(o.orderDate)) "
			+ "from Order o "
			+ "where o.paymentStatus = 'Payed' and year(o.orderDate) = :year "
			+ "group by o.shopowner.ownerId, month(o.orderDate), year(o.orderDate) "
			+ "order by o.shopowner.ownerId asc, month(o.orderDate) asc")
	public List<OrderView> findSalesdataByYear(@Param("year") int year);

	@Query("select new com.dodo.api.modelview.OrderView(o.shopowner, sum(o.totalAmount), month(o.orderDate), year(o.orderDate)) "
			+ "from Order o "
			+ "where o.paymentStatus = 'Payed' and year(o.orderDate) = :year and month(o.orderDate) = :month "
			+ "group by o.shopowner.ownerId, month(o.orderDate), year(o.orderDate) "
			+ "order by o.shopowner.ownerId asc, month(o.orderDate) asc")
	public List<OrderView> findSalesdataByYearAndMonth(@Param("year") int year, @Param("month") int month);

	@Query("select year(o.orderDate) "
			+ "from Order o "
			+ "where o.paymentStatus = 'Payed' "
			+ "group by year(o.orderDate) "
			+ "order by year(o.orderDate) asc")
	public List<Integer> findSalesdataYear();

	@Query("from Order where orderDate >= :from and orderDate <= :to order by orderId desc")
	public List<Order> findByDateRange(@Param("from") Date from, @Param("to") Date to);

	@Query("from Order o where o.shopowner.ownerId = :id and o.orderStatus = :status order by o.orderDate desc")
	public List<Order> findByShopownerOwnerIdAndOrderStatus(int id, String status);

	@Query("from Order o where o.shopowner.ownerId = :id and o.orderStatus not in :statusList order by o.orderStatus asc, o.orderDate desc")
	public List<Order> findByShopownerIdAndStatusOther(@Param("id") int id,
			@Param("statusList") List<String> statusList);

	@Query("from Order o where o.user.userId = :id and o.orderStatus not in :statusList order by o.orderStatus asc, o.orderDate desc")
	public List<Order> findByUserIdAndStatusNotIn(@Param("id") int id, @Param("statusList") List<String> statusList);
	// ====LOC====
}
