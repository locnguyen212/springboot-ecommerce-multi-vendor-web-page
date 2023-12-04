package com.dodo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Item;
import com.dodo.web.modelview.CartView;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	  @Query("from Item i where i.user.userId=:userId order by i.createdAt desc") 
	  public List<Item> getAllItemByUser(@Param("userId") int userId);
	  
	  @Query("select count(i) from Item i where i.user.userId=:userId")
	  public int countItemsByUserId(@Param("userId") int userId);
	  
	  @Query("SELECT p.stockQuantity FROM Product p WHERE p.productId=:productId")
	  public int getTotalQuantityForProduct(@Param("productId") int productId);
	  
	  @Query("from Item where product.productId=:productId and user.userId=:userId") 
	  public Item findByProductId(@Param("productId") int productId, @Param("userId") int userId);
	 
	  //LOC
	  @Query("select new com.dodo.web.modelview.CartView(i.product.shopowner) "
				+ "from Item i "
				+ "where i.user.userId = :userId "
				+ "group by i.product.shopowner "
				+ "order by i.product.shopowner.ownerId asc")
	  public List<CartView> getCartView(@Param("userId") int userId);
	  
	  @Query("select new com.dodo.web.modelview.CartView(i.product.shopowner) "
				+ "from Item i "
				+ "where i.user.userId = :userId and i.itemId in :itemsId "
				+ "group by i.product.shopowner "
				+ "order by i.product.shopowner.ownerId asc")
	  public List<CartView> getCartViewFromItems(@Param("userId") int userId, @Param("itemsId") List<Integer> itemsId);
	  
	  //LOC
}
