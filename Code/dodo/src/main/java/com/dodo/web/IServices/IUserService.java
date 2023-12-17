package com.dodo.web.IServices;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dodo.web.models.Shopownercoupon;
import com.dodo.web.models.User;

public interface IUserService extends UserDetailsService {
	//====LOC====
	public List<User> findAll();
	public User findById(int id);
	public boolean save(User user);
	public boolean delete(int id);
	public boolean isTokenExist(String token);
	
	public User findByEmail(String email);
	public User findByUsername(String name);
	
	public List<User> findAllRoleUserAndShopowner();
	//====LOC====
}
