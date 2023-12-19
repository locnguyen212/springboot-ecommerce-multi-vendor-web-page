package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.OrderDto;
import com.dodo.api.modelview.dtos.OrderViewDto;

public interface IOrderService {
	// ====LOC====
	public List<OrderDto> findAll();

	public OrderDto findById(int id);

	public OrderDto save(OrderDto order);

	public boolean saveBool(OrderDto order);

	public boolean delete(int id);
	
	public List<OrderDto> findByUserUsernameAndOrderStatus(String username, String status);
	
	public List<OrderViewDto> findSalesdataByYear(int year);

	public List<OrderViewDto> findSalesdataByYearAndMonth(int year, int month);

	public List<Integer> findSalesdataYear();
	
	public List<OrderDto> findByShopownerOwnerIdAndOrderStatus(int id, String status);

	public List<OrderDto> findByShopownerIdAndStatusOther(int id, List<String> statusList);

	public List<OrderDto> findByPaymentStatusAndUserUserId(String status, int id);

	public List<OrderDto> findByUserIdAndStatusNotIn(int id, List<String> statusList);	
	// ====LOC====
}
