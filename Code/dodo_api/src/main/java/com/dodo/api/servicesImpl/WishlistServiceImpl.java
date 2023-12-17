package com.dodo.api.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IWishlistService;
import com.dodo.api.dtos.WishlistDto;
import com.dodo.api.models.Wishlist;
import com.dodo.api.repositories.WishlistRepository;

@Service
public class WishlistServiceImpl implements IWishlistService {

	@Autowired
	private WishlistRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//====LOC====
	@Override
	public List<WishlistDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<WishlistDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public WishlistDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), WishlistDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(WishlistDto dto) {
		try {
			Wishlist model = modelMapper.map(dto, Wishlist.class);
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
	public List<WishlistDto> findByUserUserId(int id) {
		try {
			return modelMapper.map(repository.findByUserUserId(id), new TypeToken<List<WishlistDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//====LOC====

	
}
