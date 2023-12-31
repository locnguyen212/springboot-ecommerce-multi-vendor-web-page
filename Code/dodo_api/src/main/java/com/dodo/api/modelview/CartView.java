package com.dodo.api.modelview;

import java.util.ArrayList;
import java.util.List;

import com.dodo.api.models.Item;
import com.dodo.api.models.Shopowner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartView {
	private Shopowner shopowner;
	private List<Item> items;

	public CartView(Shopowner shopowner) {
		this.shopowner = shopowner;
		this.items = new ArrayList<Item>();
	}
}
