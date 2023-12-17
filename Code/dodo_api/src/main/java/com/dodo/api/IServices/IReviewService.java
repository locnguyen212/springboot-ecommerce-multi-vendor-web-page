package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.ReviewDto;
import com.dodo.api.models.Review;

public interface IReviewService {
	//====LOC====
	public List<ReviewDto> findAll();
	public ReviewDto findById(int id);
	public boolean save(ReviewDto review);
	public boolean delete(int id);
	public List<ReviewDto> findByProductProductId(int id);
	public List<ReviewDto> findByUserUserId(int id);
	//====LOC====

	public List<ReviewDto> findByShopId(int shopId);
}
