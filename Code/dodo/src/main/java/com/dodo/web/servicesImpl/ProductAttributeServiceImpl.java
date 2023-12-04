package com.dodo.web.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IProductAttributeService;
import com.dodo.web.models.Productattribute;
import com.dodo.web.repositories.ProductAttributeRepository;

@Service
public class ProductAttributeServiceImpl implements IProductAttributeService {

	@Autowired
	private ProductAttributeRepository repository;
	
	//====LOC====
	@Override
	public List<Productattribute> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Productattribute findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Productattribute productattribute) {
		try {
			repository.save(productattribute);
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
	public List<Productattribute> findByProductProductId(int id) {
		try {
			return repository.findByProductProductId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Productattribute> findByProductProductName(String name) {
		try {
			return repository.findByProductProductName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Productattribute> findByAttributeName(String name) {
		try {
			return repository.findByAttributeName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Productattribute> findByAttributeValue(String value) {
		try {
			return repository.findByAttributeValue(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//====LOC====


}
