package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.ShopownercouponDto;

public interface IShopOwnerCouponService {
	//====LOC====
	public List<ShopownercouponDto> findAll();
	public ShopownercouponDto findById(int id);
	public boolean save(ShopownercouponDto shopownercoupon);
	public boolean delete(int id);
	public List<ShopownercouponDto> findByShopownerOwnerId(int id);
	public boolean isCouponCodeExist(String code);
	//====LOC====
	
	
}
