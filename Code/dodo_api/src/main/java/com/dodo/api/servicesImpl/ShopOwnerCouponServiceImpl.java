package com.dodo.api.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IShopOwnerCouponService;
import com.dodo.api.dtos.ShopownercouponDto;
import com.dodo.api.models.Shopownercoupon;
import com.dodo.api.repositories.ShopOwnerCouponRepository;

@Service
public class ShopOwnerCouponServiceImpl implements IShopOwnerCouponService {

	@Autowired
	private ShopOwnerCouponRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//====LOC====
	@Override
	public List<ShopownercouponDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<ShopownercouponDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ShopownercouponDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), ShopownercouponDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(ShopownercouponDto dto) {
		try {
			Shopownercoupon model = modelMapper.map(dto, Shopownercoupon.class);
			repository.save(model);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(repository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ShopownercouponDto> findByShopownerOwnerId(int id) {
		try {
			return modelMapper.map(repository.findByShopownerOwnerId(id), new TypeToken<List<ShopownercouponDto>>() {}.getType());
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
