package com.dodo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	//====LOC====
	public List<Wishlist> findByProductProductId(int id);
	public List<Wishlist> findByProductProductName(String name);
	
	public List<Wishlist> findByUserUserId(int id);
	public List<Wishlist> findByUserUsername(String name);
	//====LOC====
}
