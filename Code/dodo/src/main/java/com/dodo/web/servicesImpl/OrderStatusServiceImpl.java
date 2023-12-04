package com.dodo.web.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IOrderStatusService;
import com.dodo.web.models.Orderstatus;
import com.dodo.web.repositories.OrderStatusRepository;

@Service
public class OrderStatusServiceImpl implements IOrderStatusService {

	@Autowired
	private OrderStatusRepository repository;
	
	//====LOC====
	@Override
	public List<Orderstatus> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Orderstatus findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Orderstatus orderstatus) {
		try {
			repository.save(orderstatus);
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
	public List<Orderstatus> findByStatus(String status) {
		try {
			return repository.findByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Orderstatus> findByOrderOrderId(int id) {
		try {
			return repository.findByOrderOrderId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Orderstatus> findByStatusAndDateRange(String status, Date from, Date to) {
		try {
			return repository.findByStatusAndDateRange(status, from, to);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//====LOC====


}
