package com.dodo.api.IServices;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dodo.api.dtos.UserDto;

public interface IUserService extends UserDetailsService {
	//====LOC====
	public List<UserDto> findAll();
	public UserDto findById(int id);
	public boolean save(UserDto user);
	public boolean delete(int id);
	public boolean isTokenExist(String token);
	public UserDto findByEmail(String email);
	public UserDto findByUsername(String name);
	public List<UserDto> findByRoleUserAndShopowner();	
	//====LOC====
}
