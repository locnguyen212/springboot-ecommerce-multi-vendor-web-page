package com.dodo.web.IServices;
import java.util.Date;
import java.util.List;

import com.dodo.web.models.Orderdetail;
import com.dodo.web.modelview.ReviewModelView;

public interface IOrderDetailService {
	//====LOC====
	public List<Orderdetail> findAll();
	public Orderdetail findById(int id);
	public boolean save(Orderdetail orderdetail);
	public boolean delete(int id);
	
	public List<Orderdetail> findByOrderOrderId(int id);
	//====LOC====
	
	public List<ReviewModelView> getReviewModelByUserId(int userId);


	//====Huy Daboard====
	public List<Object[]> findTopSellingProducts(int ownerId);
	
	List<Object[]> findOrderQuantitiesByDateRange(Integer ownerId, Date startDate, Date endDate);
}
