package com.dodo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Productattribute;

@Repository
public interface ProductAttributeRepository extends JpaRepository<Productattribute, Integer> {
	//====LOC====
	public List<Productattribute> findByProductProductId(int id);
	public List<Productattribute> findByProductProductName(String name);
	
	public List<Productattribute> findByAttributeName(String name);
	
	public List<Productattribute> findByAttributeValue(String value);
	//====LOC====
}
