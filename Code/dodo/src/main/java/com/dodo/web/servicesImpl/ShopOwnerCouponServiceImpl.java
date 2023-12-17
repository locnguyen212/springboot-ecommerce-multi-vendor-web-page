package com.dodo.web.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IShopOwnerCouponService;
import com.dodo.web.models.Shopownercoupon;
import com.dodo.web.repositories.ShopOwnerCouponRepository;

@Service
public class ShopOwnerCouponServiceImpl implements IShopOwnerCouponService {

	@Autowired
	private ShopOwnerCouponRepository repository;
	
	//====LOC====
	@Override
	public List<Shopownercoupon> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Shopownercoupon findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Shopownercoupon shopowner) {
		try {
			repository.save(shopowner);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Shopownercoupon> findByShopownerOwnerId(int id) {
		try {
			return repository.findByShopownerOwnerId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean isCouponCodeExist(String code) {
		try {		
			return repository.findByCouponCode(code).size()!=0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//====LOC====

}
