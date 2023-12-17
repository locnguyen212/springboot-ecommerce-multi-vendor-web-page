package com.dodo.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Shopowner;

@Repository
public interface ShopOwnerRepository extends JpaRepository<Shopowner, Integer> {
	//====LOC====
	public List<Shopowner> findByUserUserId(int id);
	public Shopowner findByUserUsername(String name);
	public List<Shopowner> findByStatus(Boolean status);
	
	public Shopowner findByShopName(String name);
	//====LOC====
}
