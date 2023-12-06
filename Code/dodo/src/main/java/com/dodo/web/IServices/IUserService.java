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
	
	public List<User> findByStatus(boolean status);
	
	public List<User> findByRoleName(String name);

	public List<User> findByPhoneNumberContain(String phone);
	
	public List<User> findByUsernameContain(String name);
	
	public List<User> findByEmailContain(String email);	
	
	public List<User> findByFirstNameContain(String name);	
	
	public List<User> findByLastNameContain(String name);	
	
	public List<User> findByAddressContain(String address);	
	
	public List<User> findByDateRangeCreated(Date from, Date to);
	
	public List<User> findByDateRangeDob(Date from, Date to);
	
	public List<User> findAllRoleUserAndShopowner();
	//====LOC====
}
