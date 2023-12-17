package com.dodo.web.IServices;

import java.util.List;

import com.dodo.web.models.Wishlist;

public interface IWishlistService {
	//====LOC====
	public List<Wishlist> findAll();
	public Wishlist findById(int id);
	public boolean save(Wishlist wishlist);
	public boolean delete(int id);
	
	public List<Wishlist> findByUserUserId(int id);
	//====LOC====
}
