package com.dodo.web.IServices;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dodo.web.models.Invoice;
import com.dodo.web.models.Ordercancellation;
import com.dodo.web.models.Shopownercoupon;

public interface IOrderCancellationService {
	//====LOC====
	public List<Ordercancellation> findAll();
	public Ordercancellation findById(int id);
	public boolean save(Ordercancellation ordercancellation);
	public boolean delete(int id);
	
	public List<Ordercancellation> findByStatus(boolean status);
	
	public Ordercancellation findByOrderOrderId(int id);
	
	public List<Ordercancellation> findByShopownerOwnerId(int id);
	public List<Ordercancellation> findByShopownerShopName(String name);
	
	public List<Ordercancellation> findByUserUserId(int id);
	public List<Ordercancellation> findByUserUsername(String name);
	
	public List<Ordercancellation> findByDateRange(Date from, Date to);
	//====LOC====
	//====HUY====
		public List<Ordercancellation> findByOwnerId(int ownerId);
}
