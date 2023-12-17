package com.dodo.web.IServices;

import java.util.Date;
import java.util.List;

import com.dodo.web.models.Category;
import com.dodo.web.models.Shopowner;
import com.dodo.web.models.Shopownercoupon;

public interface IShopOwnerCouponService {
	//====LOC====
	public List<Shopownercoupon> findAll();
	public Shopownercoupon findById(int id);
	public boolean save(Shopownercoupon shopownercoupon);
	public boolean delete(int id);
	
	public List<Shopownercoupon> findByShopownerOwnerId(int id);
	public boolean isCouponCodeExist(String code);
	//====LOC====
	
	
}
