package com.dodo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
