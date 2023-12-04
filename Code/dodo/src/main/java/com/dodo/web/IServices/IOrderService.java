package com.dodo.web.IServices;

import java.util.Date;
import java.util.List;
import com.dodo.web.models.Order;
import com.dodo.web.modelview.OrderView;

public interface IOrderService {
	// ====LOC====
	public List<Order> findAll();

	public Order findById(int id);

	public Order save(Order order);

	public boolean saveBool(Order order);

	public boolean delete(int id);

	public List<Order> findByOrderStatus(String status);

	public List<Order> findByUserUserId(int id);

	public List<Order> findByUserUsername(String name);

	public List<Order> findByUserUsernameAndOrderStatus(String username, String status);

	public List<Order> findByDateRange(Date from, Date to);

	public List<OrderView> findSalesdataByYear(int year);

	public List<OrderView> findSalesdataByYearAndMonth(int year, int month);

	public List<Integer> findSalesdataYear();

	public List<Order> findByShopownerOwnerIdAndOrderStatus(int id, String status);

	public List<Order> findByShopownerIdAndStatusOther(int id, List<String> statusList);

	public List<Order> findByPaymentStatusAndUserUserId(String status, int id);

	public List<Order> findByUserIdAndStatusNotIn(int id, List<String> statusList);
	// ====LOC====

	public List<Order> getMyOrder(int userId);

	public List<Order> findByOwnerId(int ownerId);

	// ====Huy Dasboard====
	public int getTotalOrdersForShopOwner(Integer ownerId);
	// ====Huy Dasboard====

	// Trong IOrderService
	List<Order> getOrdersByShopOwnerAndOrderDateBetween(
			Integer ownerId,
			Date startDate,
			Date endDate);

	public List<Order> getOrdersByShopOwnerIdAndStatus(Integer ownerId, String status);

	// ====Huy Dasboard====
	List<Order> getOrdersByShopOwnerAndOrderDateBetween2(
			Integer ownerId,
			Date startDate,
			Date endDate);

	List<Order> getAllCancelledOrders(Integer ownerId);

	int countCancelledOrdersByOwnerId(int ownerId);

	int countDeliveredOrdersByOwnerId(int ownerId);

	int countWaitingForApprovalOrdersByOwnerId(int ownerId);

	int countInProcessOrdersByOwnerId(int ownerId);

	int countPackagingOrdersByOwnerId(int ownerId);

	int countPaymentStatusOrdersByOwnerId(int ownerId);

	int countPaymentStatusNullOrdersByOwnerId(int ownerId);

	List<Object[]> getOrdersByOwnerIdAndDateRange(Integer ownerId, Date startDate, Date endDate);
	// ====Huy Dasboard====
}
