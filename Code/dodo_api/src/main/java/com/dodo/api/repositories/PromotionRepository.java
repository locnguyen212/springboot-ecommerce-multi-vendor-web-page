package com.dodo.api.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
	//====LOC====
	public List<Promotion> findByProductProductId(int id);
	public List<Promotion> findByProductProductName(String name);
	
	public List<Promotion> findByShopownerOwnerId(int id);
	
	public List<Promotion> findByStartDate(Date date);
	
	public List<Promotion> findByEndDate(Date date);
	
	@Query("SELECT p.product.productId FROM Promotion p WHERE p.shopowner.ownerId = :id")
	public List<Integer> findProductIdGotPromotionByShopownerId(@Param("id") int id);
	//====LOC====
}
