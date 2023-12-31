package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.ItemDto;
import com.dodo.api.dtos.UserDto;
import com.dodo.api.modelview.dtos.CartViewDto;

public interface IItemService {
	public List<ItemDto> findAll();
	public ItemDto findById(int id);
	public boolean save(ItemDto item);
	public boolean delete(int id);
	
	
	public List<ItemDto> getAllByUser(int userId);
	
	public int countItemsByUserId(int userId);
	
	public boolean saveCartToDb(UserDto user, List<ItemDto> cartItems);
	
	public ItemDto findByProductIdAndUserId(int productId, int userId);
	
	//LOC
	public List<CartViewDto> getCartViewFromItems(int userId, List<Integer> itemsId);
	//LOC

}
