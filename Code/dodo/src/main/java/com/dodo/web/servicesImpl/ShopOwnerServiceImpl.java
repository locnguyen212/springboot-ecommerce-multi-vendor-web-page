package com.dodo.web.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.models.Shopowner;
import com.dodo.web.models.User;
import com.dodo.web.repositories.ShopOwnerRepository;

@Service
public class ShopOwnerServiceImpl implements IShopOwnerService {

	@Autowired
	private ShopOwnerRepository repository;
	
	//====LOC====
	@Override
	public List<Shopowner> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Shopowner findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Shopowner shopowner) {
		try {
			repository.save(shopowner);
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
	public Shopowner findByUserUsername(String name) {
		try {
			return repository.findByUserUsername(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Shopowner findByShopName(String name) {
		try {
			return repository.findByShopName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Shopowner> findByStatus(Boolean status) {
		try {
			return repository.findByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	//====LOC====
}
