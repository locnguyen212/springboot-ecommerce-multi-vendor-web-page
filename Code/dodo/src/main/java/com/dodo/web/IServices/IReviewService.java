package com.dodo.web.IServices;

import java.util.List;

import com.dodo.web.models.Review;

public interface IReviewService {
	//====LOC====
	public List<Review> findAll();
	public Review findById(int id);
	public boolean save(Review review);
	public boolean delete(int id);
	
	public List<Review> findByProductProductId(int id);
	
	public List<Review> findByUserUserId(int id);
	//====LOC====

	public List<Review> getReviewsByShopId(int shopId);
}
