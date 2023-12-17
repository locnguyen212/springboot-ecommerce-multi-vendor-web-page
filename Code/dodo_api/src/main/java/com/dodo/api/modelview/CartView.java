package com.dodo.api.modelview;

import java.util.ArrayList;
import java.util.List;

import com.dodo.api.models.Item;
import com.dodo.api.models.Shopowner;

public class CartView {
	private Shopowner shopowner;
	private List<Item> items;
	
	public CartView(Shopowner shopowner, List<Item> items) {
		this.shopowner = shopowner;
		this.items = items;
	}
	
	public CartView(Shopowner shopowner) {
		this.shopowner = shopowner;
		this.items = new ArrayList<Item>();
	}

	public CartView() {
		
	}

	public Shopowner getShopowner() {
		return shopowner;
	}

	public void setShopowner(Shopowner shopowner) {
		this.shopowner = shopowner;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
