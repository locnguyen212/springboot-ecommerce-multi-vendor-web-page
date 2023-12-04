package com.dodo.web.IServices;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dodo.web.models.Category;
import com.dodo.web.models.Orderstatus;

public interface IOrderStatusService {
	//====LOC====
	public List<Orderstatus> findAll();
	public Orderstatus findById(int id);
	public boolean save(Orderstatus orderstatus);
	public boolean delete(int id);
	
	public List<Orderstatus> findByStatus(String status);
	
	public List<Orderstatus> findByOrderOrderId(int id);
	
	public List<Orderstatus> findByStatusAndDateRange(String status, Date from, Date to);
	//====LOC====
}
