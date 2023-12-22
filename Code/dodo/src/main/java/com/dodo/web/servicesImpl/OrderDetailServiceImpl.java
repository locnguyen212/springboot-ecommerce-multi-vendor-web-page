package com.dodo.web.servicesImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IOrderDetailService;
import com.dodo.web.models.Orderdetail;
import com.dodo.web.modelview.ReviewModelView;
import com.dodo.web.repositories.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

	@Autowired
	private OrderDetailRepository repository;

	// ====LOC====
	@Override
	public List<Orderdetail> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Orderdetail findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(Orderdetail orderdetail) {
		try {
			repository.save(orderdetail);
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
	public List<Orderdetail> findByOrderOrderId(int id) {
		try {
			return repository.findByOrderOrderId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<ReviewModelView> getReviewModelByUserId(int userId) {
		try {
			return repository.getReviewModelByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	// ====LOC====

	// ====Huy Dasboard====
	@Override
	public List<Object[]> findTopSellingProducts(int ownerId) {
		try {
			return repository.findTopSellingProducts(ownerId);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Object[]> findOrderQuantitiesByDateRange(Integer ownerId, Date startDate, Date endDate) {
		try {
			return repository.findOrderQuantitiesByDateRange(ownerId, startDate, endDate);
		} catch (Exception e) {
			return null;
		}

	}
}
