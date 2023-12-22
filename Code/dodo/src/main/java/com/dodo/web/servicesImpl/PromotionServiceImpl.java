package com.dodo.web.servicesImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IPromotionService;
import com.dodo.web.models.Promotion;
import com.dodo.web.repositories.PromotionRepository;

@Service
public class PromotionServiceImpl implements IPromotionService {

	@Autowired
	private PromotionRepository repository;
	
	//====LOC====
	@Override
	public List<Promotion> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Promotion findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Promotion promotion) {
		try {
			repository.save(promotion);
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
	public List<Promotion> findByProductProductId(int id) {
		try {
			return repository.findByProductProductId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<Promotion> findByShopownerOwnerId(int id) {
		try {
			return repository.findByShopownerOwnerId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<Integer> findProductIdGotPromotionByShopownerId(int id) {
		try {
			return repository.findProductIdGotPromotionByShopownerId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	//====LOC====
	

}
