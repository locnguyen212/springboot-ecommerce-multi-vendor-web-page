package com.dodo.web.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IOrderCancellationService;
import com.dodo.web.models.Ordercancellation;
import com.dodo.web.repositories.OrderCancellationRepository;

@Service
public class OrderCancellationServiceImpl implements IOrderCancellationService {

	@Autowired
	private OrderCancellationRepository repository;

	// ====LOC====
	@Override
	public List<Ordercancellation> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Ordercancellation findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(Ordercancellation ordercancellation) {
		try {
			repository.save(ordercancellation);
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
	public Ordercancellation findByOrderOrderId(int id) {
		try {
			return repository.findByOrderOrderId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Ordercancellation> findByShopownerOwnerId(int id) {
		try {
			return repository.findByShopownerOwnerId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Ordercancellation> findByUserUsername(String name) {
		try {
			return repository.findByUserUsername(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// ====LOC====

}
