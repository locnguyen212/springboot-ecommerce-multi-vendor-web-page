package com.dodo.web.IServices;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.dodo.web.models.Item;
import com.dodo.web.models.User;
import com.dodo.web.modelview.CartView;

public interface IItemService {
	public List<Item> findAll();
	public Item findById(int id);
	public boolean save(Item item);
	public boolean delete(int id);
	
	
	public List<Item> getAllByUser(int userId);
	
	public int countItemsByUserId(int userId);
	
	public void saveCartToDb(User user, List<Item> cartItems);
	
	public int getTotalQuantityForProduct(int productId);
	
	public Item findByProductId(int productId, int userId);
	
	//LOC
	public List<CartView> getCartViewFromItems(int userId, List<Integer> itemsId);
	//LOC

}
