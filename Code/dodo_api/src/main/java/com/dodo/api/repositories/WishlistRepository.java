package com.dodo.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	//====LOC====
	public List<Wishlist> findByProductProductId(int id);
	public List<Wishlist> findByProductProductName(String name);
	
	public List<Wishlist> findByUserUserId(int id);
	public List<Wishlist> findByUserUsername(String name);
	
	public Wishlist findByProductProductIdAndUserUserId(int productId, int userId);
	//====LOC====
}
