package com.dodo.web.IServices;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.dodo.web.models.Category;
import com.dodo.web.models.Ordercancellation;
import com.dodo.web.models.Promotion;

public interface IPromotionService {
	//====LOC====
	public List<Promotion> findAll();
	public Promotion findById(int id);
	public boolean save(Promotion promotion);
	public boolean delete(int id);
	
	public List<Promotion> findByProductProductId(int id);
	public List<Promotion> findByProductProductName(String name);
	
	public List<Promotion> findByStartDate(Date date);
	
	public List<Promotion> findByEndDate(Date date);
	
	public List<Promotion> findByShopownerOwnerId(int id);
	
	public List<Integer> findProductIdGotPromotionByShopownerId(int id);
	//====LOC====
}
