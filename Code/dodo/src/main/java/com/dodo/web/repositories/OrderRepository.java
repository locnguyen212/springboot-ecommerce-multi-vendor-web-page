package com.dodo.web.repositories;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Order;
import com.dodo.web.modelview.OrderView;

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

	@Query("select new com.dodo.web.modelview.OrderView(o.shopowner, sum(o.totalAmount), month(o.orderDate), year(o.orderDate)) "
			+ "from Order o "
			+ "where o.paymentStatus = 'Payed' and year(o.orderDate) = :year "
			+ "group by o.shopowner.ownerId, month(o.orderDate), year(o.orderDate) "
			+ "order by o.shopowner.ownerId asc, month(o.orderDate) asc")
	public List<OrderView> findSalesdataByYear(@Param("year") int year);

	@Query("select new com.dodo.web.modelview.OrderView(o.shopowner, sum(o.totalAmount), month(o.orderDate), year(o.orderDate)) "
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

	@Query("from Order od where od.user.userId =:userId and od.orderStatus =:orderStatus order by od.orderDate desc")
	public List<Order> getMyOrder(@Param("userId") int userId, @Param("orderStatus") String orderStatus);

	@Query("SELECT c FROM Order c WHERE c.shopowner.ownerId = :ownerId")
	List<Order> findByOwnerId(@Param("ownerId") int ownerId);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.shopowner.id = :ownerId")
	public int countByOwnerId(@Param("ownerId") Integer ownerId);

	@Query("SELECT o FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderDate BETWEEN :startDate AND :endDate")
	List<Order> findOrdersByOwnerIdAndOrderDateBetween(@Param("ownerId") Integer shopOwnerId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Query("SELECT o FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderStatus = :status")
	List<Order> getOrdersByShopOwnerIdAndStatus(@Param("ownerId") Integer ownerId, @Param("status") String status);

	// Huy làm chart
	@Query("SELECT o FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderDate BETWEEN :startDate AND :endDate AND o.orderStatus = 'Cancelled'")
	List<Order> findOrdersByOwnerIdAndOrderDateBetween2(@Param("ownerId") Integer shopOwnerId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Query("SELECT o FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderStatus = 'Cancelled'")
	List<Order> findAllCancelledOrders(@Param("ownerId") Integer ownerId);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderStatus = 'Cancelled'")
	int countCancelledOrdersByOwnerId(@Param("ownerId") Integer ownerId);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderStatus = 'Delivered'")
	int countDeliveredOrdersByOwnerId(@Param("ownerId") Integer ownerId);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderStatus = 'Waiting For Approval'")
	int countWaitingForApprovalOrdersByOwnerId(@Param("ownerId") Integer ownerId);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderStatus = 'In Process'")
	int countInProcessOrdersByOwnerId(@Param("ownerId") Integer ownerId);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.shopowner.id = :ownerId AND o.orderStatus = 'Packaging'")
	int countPackagingOrdersByOwnerId(@Param("ownerId") Integer ownerId);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.shopowner.id = :ownerId AND o.paymentStatus = 'Payed'")
	int countPaymentStatusOrdersByOwnerId(@Param("ownerId") Integer ownerId);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.shopowner.id = :ownerId AND  o.paymentStatus IS NULL")
	int countPaymentStatusNullOrdersByOwnerId(@Param("ownerId") Integer ownerId);

	@Query("SELECT DATE(o.orderDate) AS orderDate, COUNT(o.orderId) AS orderCount " +
			"FROM Order o " +
			"WHERE o.shopowner.id = :ownerId AND o.orderDate BETWEEN :startDate AND :endDate " +
			"GROUP BY DATE(o.orderDate)")
	List<Object[]> findOrderQuantitiesByDateRange(
			@Param("ownerId") int ownerId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

}
