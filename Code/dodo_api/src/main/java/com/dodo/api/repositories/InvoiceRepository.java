package com.dodo.api.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	//====LOC====
	public List<Invoice> findByOrderOrderId(int id);
	
	public List<Invoice> findByProductProductId(int id);
	public List<Invoice> findByProductProductName(String name);
	
	public List<Invoice> findByShopownerOwnerId(int id);
	public List<Invoice> findByShopownerShopName(String name);
	public List<Invoice> findByShopownerOwnerIdAndIsPaid(int id, boolean isPaid);
	
	public List<Invoice> findByUserUserId(int id);
	public List<Invoice> findByUserUsernameAndIsPaid(String name, Boolean isPaid);
	
	
	
	@Query("from Invoice where invoiceDate >= :from and invoiceDate <= :to order by invoiceId desc")
	public List<Invoice> findByDateRange(@Param("from") Date from,  @Param("to") Date to);
	//====LOC====
}
