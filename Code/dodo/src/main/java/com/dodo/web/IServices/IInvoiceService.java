package com.dodo.web.IServices;

import java.util.Date;
import java.util.List;

import com.dodo.web.models.Invoice;

public interface IInvoiceService {
	//====LOC====
	public List<Invoice> findAll();
	public Invoice findById(int id);
	public boolean save(Invoice invoice);
	public boolean delete(int id);
	
	public List<Invoice> findByOrderOrderId(int id);
	
	public List<Invoice> findByProductProductId(int id);
	public List<Invoice> findByProductProductName(String name);
	
	public List<Invoice> findByShopownerOwnerId(int id);
	public List<Invoice> findByShopownerShopName(String name);
	public List<Invoice> findByShopownerOwnerIdAndIsPaid(int id, boolean isPaid);
	
	public List<Invoice> findByUserUserId(int id);
	public List<Invoice> findByUserUsernameAndIsPaid(String name, Boolean isPaid);
	
	public List<Invoice> findByDateRange(Date from, Date to);
	//====LOC====
}
