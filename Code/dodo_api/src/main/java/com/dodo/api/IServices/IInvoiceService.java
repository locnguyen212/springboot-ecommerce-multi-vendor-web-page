package com.dodo.api.IServices;

import java.util.Date;
import java.util.List;

import com.dodo.api.dtos.InvoiceDto;

public interface IInvoiceService {
	//====LOC====
	public List<InvoiceDto> findAll();
	public InvoiceDto findById(int id);
	public boolean save(InvoiceDto invoice);
	public boolean delete(int id);
	
	public List<InvoiceDto> findByOrderOrderId(int id);
	
	public List<InvoiceDto> findByProductProductId(int id);
	public List<InvoiceDto> findByProductProductName(String name);
	
	public List<InvoiceDto> findByShopownerOwnerId(int id);
	public List<InvoiceDto> findByShopownerShopName(String name);
	public List<InvoiceDto> findByShopownerOwnerIdAndIsPaid(int id, boolean isPaid);
	
	public List<InvoiceDto> findByUserUserId(int id);
	public List<InvoiceDto> findByUserUsernameAndIsPaid(String name, Boolean isPaid);
	
	public List<InvoiceDto> findByDateRange(Date from, Date to);
	//====LOC====
}
