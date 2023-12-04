package com.dodo.web.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Shopownercoupon;

@Repository
public interface ShopOwnerCouponRepository extends JpaRepository<Shopownercoupon, Integer> {
	//====LOC====
	public List<Shopownercoupon> findByShopownerOwnerId(int id);
	public List<Shopownercoupon> findByShopownerShopName(String name);
	
	public List<Shopownercoupon> findByIsActive(boolean status);
	
	public List<Shopownercoupon> findByCouponCode(String code);
	
	public List<Shopownercoupon> findByExpiryDate(Date date);
	//====LOC====
	
	//====HUY====
	@Query("SELECT c FROM Shopownercoupon c WHERE c.shopowner.ownerId = :ownerId")
	List<Shopownercoupon> findByOwnerId(@Param("ownerId") int ownerId);
	
	

}
