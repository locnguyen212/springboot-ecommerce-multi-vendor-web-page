package com.dodo.web.servicesImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IItemService;
import com.dodo.web.models.Item;
import com.dodo.web.models.User;
import com.dodo.web.modelview.CartView;
import com.dodo.web.repositories.ItemRepository;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private ItemRepository repository;

	public List<Item> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Item findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Item item) {
		try {
			repository.save(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Item> getAllByUser(int userId) {
		try {
			return repository.getAllItemByUser(userId);
		} catch (Exception e) {
			return Collections.emptyList();
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
	public void saveCartToDb(User user, List<Item> cartItems) {
		try {
			 for (Item item : cartItems) {
            item.setUser(user);
        }
        repository.saveAll(cartItems);
		} catch (Exception e) {
			
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
	public Item findByProductId(int productId, int userId) {
		try {
			return repository.findByProductId(productId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	//LOC	
	@Override
	public List<CartView> getCartViewFromItems(int userId, List<Integer> itemsId) {
		try {
			var cartViews = repository.getCartViewFromItems(userId, itemsId);
			List<Item> items = new ArrayList<Item>();
			
			for(var e : itemsId) {
				items.add(repository.findById(e).get());
			}
			
			for(var cartView : cartViews) {
				for(var item : items) {
					if(item.getProduct().getShopowner().getOwnerId() == cartView.getShopowner().getOwnerId()) {
						cartView.getItems().add(item);
					}
				}
			}
			return cartViews;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}	
	}
	
	//LOC
}
