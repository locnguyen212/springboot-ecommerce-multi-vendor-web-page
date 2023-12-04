package com.dodo.web.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Orderstatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<Orderstatus, Integer> {
	//====LOC====
	public List<Orderstatus> findByStatus(String status);
	
	public List<Orderstatus> findByOrderOrderId(int id);
	
	@Query("from Orderstatus where status = :status and statusDate >= :from and statusDate <= :to order by orderStatusId desc")
	public List<Orderstatus> findByStatusAndDateRange(@Param("status") String status, @Param("from") Date from,  @Param("to") Date to);
	//====LOC====
}
