package com.dodo.web.IServices;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dodo.web.models.Category;
import com.dodo.web.models.Shopowner;
import com.dodo.web.models.Shopownercoupon;
import com.dodo.web.models.User;

public interface IShopOwnerService {
	//====LOC====
	public List<Shopowner> findAll();
	public Shopowner findById(int id);
	public boolean save(Shopowner shopowner);
	public boolean delete(int id);
	public boolean userCheck(int id);
	
	public List<Shopowner> findByUserUserId(int id);
	public Shopowner findByUserUsername(String name);
	public List<Shopowner> findByStatus(Boolean status);
	
	public Shopowner findByShopName(String name);
	//====LOC====
}
