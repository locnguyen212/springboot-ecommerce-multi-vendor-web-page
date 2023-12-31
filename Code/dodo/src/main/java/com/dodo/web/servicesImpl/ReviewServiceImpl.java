package com.dodo.web.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IReviewService;
import com.dodo.web.models.Review;
import com.dodo.web.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements IReviewService {

	@Autowired
	private ReviewRepository repository;
	
	//====LOC====
	@Override
	public List<Review> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Review findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Review review) {
		try {
			repository.save(review);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Review> findByProductProductId(int id) {
		try {
			return repository.findByProductProductId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<Review> findByUserUserId(int id) {
		try {
			return repository.findByUserUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	//====LOC====

	@Override
	public List<Review> getReviewsByShopId(int shopId) {
		try {
			return repository.getReviewsByShopId(shopId);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}
