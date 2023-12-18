package com.dodo.api.IServices;
import java.util.List;

import com.dodo.api.dtos.OrderdetailDto;
import com.dodo.api.modelview.ReviewModelView;
import com.dodo.api.modelview.ReviewModelViewDto;

public interface IOrderDetailService {
	//====LOC====
	public List<OrderdetailDto> findAll();
	public OrderdetailDto findById(int id);
	public boolean save(OrderdetailDto orderdetail);
	public boolean delete(int id);
	
	public List<OrderdetailDto> findByOrderOrderId(int id);
	//====LOC====
	
	public List<ReviewModelViewDto> getReviewModelByUserId(int userId);
}
