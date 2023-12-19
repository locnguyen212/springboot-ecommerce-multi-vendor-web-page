package com.dodo.api.modelview.dtos;

import java.util.ArrayList;
import java.util.List;

import com.dodo.api.dtos.ItemDto;
import com.dodo.api.dtos.ShopownerDto;
import com.dodo.api.models.Item;

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
public class CartViewDto {
	private ShopownerDto shopowner;
	private List<ItemDto> items;

	public CartViewDto(ShopownerDto shopowner) {
		this.shopowner = shopowner;
		this.items = new ArrayList<ItemDto>();
	}
}
