package com.dodo.api.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.dtos.ShopownerDto;
import com.dodo.api.models.Shopowner;
import com.dodo.api.repositories.ShopOwnerRepository;

@Service
public class ShopOwnerServiceImpl implements IShopOwnerService {

	@Autowired
	private ShopOwnerRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//====LOC====
	@Override
	public List<ShopownerDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<ShopownerDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public ShopownerDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), ShopownerDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(ShopownerDto dto) {
		try {
			Shopowner model = modelMapper.map(dto, Shopowner.class);
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
	public ShopownerDto findByUserUsername(String name) {
		try {
			return modelMapper.map(repository.findByUserUsername(name), ShopownerDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ShopownerDto findByShopName(String name) {
		try {
			return modelMapper.map(repository.findByShopName(name), ShopownerDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<ShopownerDto> findByStatus(Boolean status) {
		try {
			return modelMapper.map(repository.findByStatus(status), new TypeToken<List<ShopownerDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public boolean userCheck(int id) {
		try {
			return repository.findByUserUserId(id).size()==1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//====LOC====
}
