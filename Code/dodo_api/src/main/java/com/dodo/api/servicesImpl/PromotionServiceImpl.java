package com.dodo.api.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IPromotionService;
import com.dodo.api.dtos.PromotionDto;
import com.dodo.api.models.Promotion;
import com.dodo.api.repositories.PromotionRepository;

@Service
public class PromotionServiceImpl implements IPromotionService {

	@Autowired
	private PromotionRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//====LOC====
	@Override
	public List<PromotionDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<PromotionDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PromotionDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), PromotionDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(PromotionDto dto) {
		try {
			Promotion model = modelMapper.map(dto, Promotion.class);
			repository.save(model);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(repository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<PromotionDto> findByProductProductId(int id) {
		try {
			return modelMapper.map(repository.findByProductProductId(id), new TypeToken<List<PromotionDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<PromotionDto> findByShopownerOwnerId(int id) {
		try {
			return modelMapper.map(repository.findByShopownerOwnerId(id), new TypeToken<List<PromotionDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Integer> findProductIdGotPromotionByShopownerId(int id) {
		try {
			return repository.findProductIdGotPromotionByShopownerId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//====LOC====
	

}
