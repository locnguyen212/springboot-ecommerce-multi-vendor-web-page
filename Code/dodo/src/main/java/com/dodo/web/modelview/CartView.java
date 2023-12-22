package com.dodo.web.modelview;

import java.util.ArrayList;
import java.util.List;

import com.dodo.web.models.Item;
import com.dodo.web.models.Shopowner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartView {
	private Shopowner shopowner;
	private List<Item> items;
	
	public CartView(Shopowner shopowner) {
		this.shopowner = shopowner;
		this.items = new ArrayList<Item>();
	}
}
