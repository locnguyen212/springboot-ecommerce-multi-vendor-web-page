package com.dodo.web.IServices;

import java.util.List;

import com.dodo.web.models.Category;
import com.dodo.web.models.Productattribute;

public interface IProductAttributeService {
	//====LOC====
	public List<Productattribute> findAll();
	public Productattribute findById(int id);
	public boolean save(Productattribute productattribute);
	public boolean delete(int id);
	
	public List<Productattribute> findByProductProductId(int id);
	public List<Productattribute> findByProductProductName(String name);
	
	public List<Productattribute> findByAttributeName(String name);
	
	public List<Productattribute> findByAttributeValue(String value);
	//====LOC====
}
