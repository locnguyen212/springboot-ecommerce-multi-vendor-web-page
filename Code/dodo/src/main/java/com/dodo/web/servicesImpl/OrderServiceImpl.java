package com.dodo.web.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IOrderService;
import com.dodo.web.models.Order;
import com.dodo.web.modelview.OrderView;
import com.dodo.web.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository repository;

	// ====LOC====
	@Override
	public List<Order> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Order findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Order save(Order order) {
		try {
			return repository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean saveBool(Order order) {
		try {
			repository.save(order);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Order> findByOrderStatus(String status) {
		try {
			return repository.findByOrderStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findByUserUserId(int id) {
		try {
			return repository.findByUserUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findByUserUsername(String name) {
		try {
			return repository.findByUserUsername(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findByDateRange(Date from, Date to) {
		try {
			return repository.findByDateRange(from, to);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findByUserUsernameAndOrderStatus(String username, String status) {
		try {
			return repository.findByUserUsernameAndOrderStatus(username, status);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<OrderView> findSalesdataByYear(int year) {
		try {
			return repository.findSalesdataByYear(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<OrderView> findSalesdataByYearAndMonth(int year, int month) {
		try {
			return repository.findSalesdataByYearAndMonth(year, month);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Integer> findSalesdataYear() {
		try {
			return repository.findSalesdataYear();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findByShopownerOwnerIdAndOrderStatus(int id, String status) {
		try {
			return repository.findByShopownerOwnerIdAndOrderStatus(id, status);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findByShopownerIdAndStatusOther(int id, List<String> statusList) {
		try {
			return repository.findByShopownerIdAndStatusOther(id, statusList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findByPaymentStatusAndUserUserId(String status, int id) {
		try {
			return repository.findByPaymentStatusAndUserUserId(status, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findByUserIdAndStatusNotIn(int id, List<String> statusList) {
		try {
			return repository.findByUserIdAndStatusNotIn(id, statusList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ====LOC====
	@Override
	public List<Order> getMyOrder(int userId) {
		try {
			String orderStatus = "In Process";
			return repository.getMyOrder(userId, orderStatus);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ====HUY====
	@Override
	public List<Order> findByOwnerId(int ownerId) {
		try {
			return repository.findByOwnerId(ownerId);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public int getTotalOrdersForShopOwner(Integer ownerId) {
		try {
			return repository.countByOwnerId(ownerId);
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public List<Order> getOrdersByShopOwnerAndOrderDateBetween(Integer ownerId, Date start, Date end) {
		try {
			return repository.findOrdersByOwnerIdAndOrderDateBetween(ownerId, start, end);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Order> getOrdersByShopOwnerIdAndStatus(Integer ownerId, String status) {
		try {
			return repository.getOrdersByShopOwnerIdAndStatus(ownerId, status);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Order> getOrdersByShopOwnerAndOrderDateBetween2(Integer ownerId, Date start, Date end) {
		try {
			return repository.findOrdersByOwnerIdAndOrderDateBetween2(ownerId, start, end);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Order> getAllCancelledOrders(Integer ownerId) {
		try {
			return repository.findAllCancelledOrders(ownerId);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public int countCancelledOrdersByOwnerId(int ownerId) {
		try {
			return repository.countCancelledOrdersByOwnerId(ownerId);
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public int countDeliveredOrdersByOwnerId(int ownerId) {
		try {
			return repository.countDeliveredOrdersByOwnerId(ownerId);
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public int countWaitingForApprovalOrdersByOwnerId(int ownerId) {
		try {
			return repository.countWaitingForApprovalOrdersByOwnerId(ownerId);
		} catch (Exception e) {
			return -1;
		}

	}

	public int countInProcessOrdersByOwnerId(int ownerId) {
		try {
			return repository.countInProcessOrdersByOwnerId(ownerId);
		} catch (Exception e) {
			return -1;
		}

	}

	public int countPackagingOrdersByOwnerId(int ownerId) {
		try {
			return repository.countPackagingOrdersByOwnerId(ownerId);
		} catch (Exception e) {
			return -1;
		}

	}

	public int countPaymentStatusOrdersByOwnerId(int ownerId) {
		try {
			return repository.countPaymentStatusOrdersByOwnerId(ownerId);
		} catch (Exception e) {
			return -1;
		}

	}

	public int countPaymentStatusNullOrdersByOwnerId(int ownerId) {
		try {
			return repository.countPaymentStatusNullOrdersByOwnerId(ownerId);
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public List<Object[]> getOrdersByOwnerIdAndDateRange(Integer ownerId, Date startDate, Date endDate) {
		try {
			return repository.findOrderQuantitiesByDateRange(ownerId, startDate, endDate);
		} catch (Exception e) {
			return null;
		}

	}
}