package com.dodo.web.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Ordercancellation;

@Repository
public interface OrderCancellationRepository extends JpaRepository<Ordercancellation, Integer> {
	//====LOC====
	public List<Ordercancellation> findByStatus(boolean status);
	
	public Ordercancellation findByOrderOrderId(int id);
	
	public List<Ordercancellation> findByShopownerOwnerId(int id);
	public List<Ordercancellation> findByShopownerShopName(String name);
	
	public List<Ordercancellation> findByUserUserId(int id);
	public List<Ordercancellation> findByUserUsername(String name);
	
	@Query("from Ordercancellation where cancellationDate >= :from and cancellationDate <= :to order by cancellationId desc")
	public List<Ordercancellation> findByDateRange(@Param("from") Date from,  @Param("to") Date to);
	//====LOC====
}
