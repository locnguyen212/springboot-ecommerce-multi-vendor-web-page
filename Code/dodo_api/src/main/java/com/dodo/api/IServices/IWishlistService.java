package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.WishlistDto;

public interface IWishlistService {
	//====LOC====
	public List<WishlistDto> findAll();
	public WishlistDto findById(int id);
	public boolean save(WishlistDto wishlist);
	public boolean delete(int id);
	public List<WishlistDto> findByUserUserId(int id);
	//====LOC====
}
