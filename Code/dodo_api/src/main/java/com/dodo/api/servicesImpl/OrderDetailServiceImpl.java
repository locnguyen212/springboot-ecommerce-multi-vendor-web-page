package com.dodo.api.servicesImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IOrderDetailService;
import com.dodo.api.dtos.OrderDto;
import com.dodo.api.dtos.OrderdetailDto;
import com.dodo.api.dtos.ProductDto;
import com.dodo.api.models.Orderdetail;
import com.dodo.api.modelview.ReviewModelView;
import com.dodo.api.modelview.dtos.ReviewModelViewDto;
import com.dodo.api.repositories.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

	@Autowired
	private OrderDetailRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	// ====LOC====
	@Override
	public List<OrderdetailDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<OrderdetailDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public OrderdetailDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), OrderdetailDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(OrderdetailDto dto) {
		try {
			Orderdetail model = modelMapper.map(dto, Orderdetail.class);
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
	public List<OrderdetailDto> findByOrderOrderId(int id) {
		try {
			return modelMapper.map(repository.findByOrderOrderId(id), new TypeToken<List<OrderdetailDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<ReviewModelViewDto> getReviewModelByUserId(int userId) {
		try {
			return repository
					.getReviewModelByUserId(userId)
					.stream()
					.map(e -> {
						var orderDto = modelMapper.map(e.getOrder(), OrderDto.class);
						var productDto = modelMapper.map(e.getProduct(), ProductDto.class);
						return new ReviewModelViewDto(orderDto, productDto);
					})
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	// ====LOC====
}
