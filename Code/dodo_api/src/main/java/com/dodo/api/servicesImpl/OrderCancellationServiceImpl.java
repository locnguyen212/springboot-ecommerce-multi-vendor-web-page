package com.dodo.api.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IOrderCancellationService;
import com.dodo.api.dtos.OrdercancellationDto;
import com.dodo.api.models.Ordercancellation;
import com.dodo.api.repositories.OrderCancellationRepository;

@Service
public class OrderCancellationServiceImpl implements IOrderCancellationService {

	@Autowired
	private OrderCancellationRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//====LOC====
	@Override
	public List<OrdercancellationDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<OrdercancellationDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public OrdercancellationDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), OrdercancellationDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(OrdercancellationDto dto) {
		try {
			Ordercancellation model = modelMapper.map(dto, Ordercancellation.class);
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
	public OrdercancellationDto findByOrderOrderId(int id) {
		try {
			return modelMapper.map(repository.findByOrderOrderId(id), OrdercancellationDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<OrdercancellationDto> findByShopownerOwnerId(int id) {
		try {
			return modelMapper.map(repository.findByShopownerOwnerId(id), new TypeToken<List<OrdercancellationDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<OrdercancellationDto> findByUserUsername(String name) {
		try {
			return modelMapper.map(repository.findByUserUsername(name), new TypeToken<List<OrdercancellationDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	//====LOC====


}
