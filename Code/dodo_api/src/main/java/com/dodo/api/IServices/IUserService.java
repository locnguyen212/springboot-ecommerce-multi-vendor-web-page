package com.dodo.api.IServices;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dodo.api.dtos.UserDto;
import com.dodo.api.models.User;

public interface IUserService extends UserDetailsService {
	//====LOC====
	public List<UserDto> findAll();
	public UserDto findById(int id);
	public User findByIdModel(int id);
	public boolean save(UserDto user);
	public boolean saveModel(User user);
	public boolean delete(int id);
	public boolean isForgetPasswordTokenExist(String token);
	public UserDto findByEmail(String email);
	public UserDto findByUsername(String name);
	public User findByUsernameModel(String name);
	public List<UserDto> findByRoleUserAndShopowner();	
	//====LOC====
}
