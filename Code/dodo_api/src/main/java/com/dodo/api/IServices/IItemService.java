package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.ItemDto;
import com.dodo.api.dtos.UserDto;
import com.dodo.api.modelview.CartView;

public interface IItemService {
	public List<ItemDto> findAll();
	public ItemDto findById(int id);
	public boolean save(ItemDto item);
	public boolean delete(int id);
	
	
	public List<ItemDto> getAllByUser(int userId);
	
	public int countItemsByUserId(int userId);
	
	public void saveCartToDb(UserDto user, List<ItemDto> cartItems);
	
	public int getTotalQuantityForProduct(int productId);
	
	public ItemDto findByProductId(int productId, int userId);
	
	//LOC
	public List<CartView> getCartView(int userId);
	public List<CartView> getCartViewFromItems(int userId, List<Integer> itemsId);
	//LOC

}
