package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.ShopownerDto;

public interface IShopOwnerService {
	//====LOC====
	public List<ShopownerDto> findAll();
	public ShopownerDto findById(int id);
	public boolean save(ShopownerDto shopowner);
	public boolean delete(int id);
	public boolean userCheck(int id);
	
	public ShopownerDto findByUserUsername(String name);
	public List<ShopownerDto> findByStatus(Boolean status);
	
	public ShopownerDto findByShopName(String name);
	//====LOC====
}
