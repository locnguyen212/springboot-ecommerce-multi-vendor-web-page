package com.dodo.web.IServices;

import java.util.List;

import com.dodo.web.models.Category;
import com.dodo.web.models.Role;

public interface IRoleService {
	public List<Role> findAll();
	public Role findById(int id);
	public boolean save(Role role);
	public boolean delete(int id);
	
	public Role findByName(String name);
}
