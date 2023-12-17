package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.OrdercancellationDto;

public interface IOrderCancellationService {
	//====LOC====
	public List<OrdercancellationDto> findAll();
	public OrdercancellationDto findById(int id);
	public boolean save(OrdercancellationDto ordercancellation);
	public boolean delete(int id);
	public OrdercancellationDto findByOrderOrderId(int id);
	public List<OrdercancellationDto> findByShopownerOwnerId(int id);
	public List<OrdercancellationDto> findByUserUsername(String name);
	//====LOC====
}
