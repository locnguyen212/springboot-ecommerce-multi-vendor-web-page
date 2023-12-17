package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.PromotionDto;

public interface IPromotionService {
	//====LOC====
	public List<PromotionDto> findAll();
	public PromotionDto findById(int id);
	public boolean save(PromotionDto promotion);
	public boolean delete(int id);
	public List<PromotionDto> findByProductProductId(int id);
	public List<PromotionDto> findByShopownerOwnerId(int id);
	public List<Integer> findProductIdGotPromotionByShopownerId(int id);
	//====LOC====
}
