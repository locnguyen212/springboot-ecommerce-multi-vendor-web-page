package com.dodo.api.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IItemService;
import com.dodo.api.dtos.ItemDto;
import com.dodo.api.dtos.UserDto;
import com.dodo.api.models.Item;
import com.dodo.api.modelview.CartView;
import com.dodo.api.repositories.ItemRepository;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private ItemRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public List<ItemDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<ItemDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ItemDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), ItemDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(ItemDto dto) {
		try {
			Item model = modelMapper.map(dto, Item.class);
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
	public List<ItemDto> getAllByUser(int userId) {
		try {
			return modelMapper.map(repository.getAllItemByUser(userId), new TypeToken<List<ItemDto>>() {
			}.getType());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int countItemsByUserId(int userId) {
		try {
			return repository.countItemsByUserId(userId);
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public void saveCartToDb(UserDto user, List<ItemDto> cartItems) {
		try {
			for (ItemDto item : cartItems) {
				item.setUserUserId(user.getUserId());
			}
			List<Item> items = modelMapper.map(cartItems, new TypeToken<List<Item>>() {}.getType());
			repository.saveAll(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getTotalQuantityForProduct(int productId) {
		try {
			return repository.getTotalQuantityForProduct(productId);
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public ItemDto findByProductId(int productId, int userId) {
		try {
			return modelMapper.map(repository.findByProductId(productId, userId), ItemDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// LOC
	@Override
	public List<CartView> getCartView(int userId) {
		try {
			var cartViews = repository.getCartView(userId);
			var items = repository.getAllItemByUser(userId);
			for (var cartView : cartViews) {
				for (var item : items) {
					if (item.getProduct().getShopowner().getOwnerId() == cartView.getShopowner().getOwnerId()) {
						cartView.getItems().add(item);
					}
				}
			}
			return cartViews;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CartView> getCartViewFromItems(int userId, List<Integer> itemsId) {
		try {
			var cartViews = repository.getCartViewFromItems(userId, itemsId);
			List<Item> items = new ArrayList<Item>();

			for (var e : itemsId) {
				items.add(repository.findById(e).get());
			}

			for (var cartView : cartViews) {
				for (var item : items) {
					if (item.getProduct().getShopowner().getOwnerId() == cartView.getShopowner().getOwnerId()) {
						cartView.getItems().add(item);
					}
				}
			}
			return cartViews;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// LOC
}
