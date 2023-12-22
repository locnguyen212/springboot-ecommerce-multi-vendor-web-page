package com.dodo.api.servicesImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IOrderService;
import com.dodo.api.dtos.OrderDto;
import com.dodo.api.dtos.ShopownerDto;
import com.dodo.api.models.Order;
import com.dodo.api.modelview.dtos.OrderViewDto;
import com.dodo.api.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	// ====LOC====
	@Override
	public List<OrderDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<OrderDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public OrderDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), OrderDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public OrderDto save(OrderDto dto) {
		try {
			Order model = modelMapper.map(dto, Order.class);
			repository.save(model);
			return modelMapper.map(model, OrderDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean saveBool(OrderDto dto) {
		try {
			Order model = modelMapper.map(dto, Order.class);
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
	public List<OrderDto> findByUserUsernameAndOrderStatus(String username, String status) {
		try {
			return modelMapper.map(repository.findByUserUsernameAndOrderStatus(username, status), new TypeToken<List<OrderDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<OrderViewDto> findSalesdataByYear(int year) {
		try {
			return repository.findSalesdataByYear(year)
					.stream()
					.map(e -> new OrderViewDto(
							modelMapper.map(e.getShopowner(), ShopownerDto.class), 
							e.getTotal(),
							e.getMonth(),
							e.getYear()
							))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<OrderViewDto> findSalesdataByYearAndMonth(int year, int month) {
		try {
			return repository.findSalesdataByYearAndMonth(year, month)
					.stream()
					.map(e -> new OrderViewDto(
							modelMapper.map(e.getShopowner(), ShopownerDto.class), 
							e.getTotal(),
							e.getMonth(),
							e.getYear()
							))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<Integer> findSalesdataYear() {
		try {
			return repository.findSalesdataYear();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<OrderDto> findByShopownerOwnerIdAndOrderStatus(int id, String status) {
		try {
			return modelMapper.map(repository.findByShopownerOwnerIdAndOrderStatus(id, status), new TypeToken<List<OrderDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<OrderDto> findByShopownerIdAndStatusOther(int id, List<String> statusList) {
		try {
			return modelMapper.map(repository.findByShopownerIdAndStatusOther(id, statusList), new TypeToken<List<OrderDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<OrderDto> findByPaymentStatusAndUserUserId(String status, int id) {
		try {
			return modelMapper.map(repository.findByPaymentStatusAndUserUserId(status, id), new TypeToken<List<OrderDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<OrderDto> findByUserIdAndStatusNotIn(int id, List<String> statusList) {
		try {
			return modelMapper.map(repository.findByUserIdAndStatusNotIn(id, statusList), new TypeToken<List<OrderDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ====LOC====
}