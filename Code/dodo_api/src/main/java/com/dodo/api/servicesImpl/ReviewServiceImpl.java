package com.dodo.api.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IReviewService;
import com.dodo.api.dtos.ReviewDto;
import com.dodo.api.models.Review;
import com.dodo.api.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements IReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//====LOC====
	@Override
	public List<ReviewDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<ReviewDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ReviewDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), ReviewDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(ReviewDto dto) {
		try {
			Review model = modelMapper.map(dto, Review.class);
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
	public List<ReviewDto> findByProductProductId(int id) {
		try {
			return modelMapper.map(repository.findByProductProductId(id), new TypeToken<List<ReviewDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ReviewDto> findByUserUserId(int id) {
		try {
			return modelMapper.map(repository.findByUserUserId(id), new TypeToken<List<ReviewDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//====LOC====

	@Override
	public List<ReviewDto> findByShopId(int shopId) {
		try {
			return modelMapper.map(repository.findByShopId(shopId), new TypeToken<List<ReviewDto>>() {}.getType());
		} catch (Exception e) {
			return null;
		}
	}
}
