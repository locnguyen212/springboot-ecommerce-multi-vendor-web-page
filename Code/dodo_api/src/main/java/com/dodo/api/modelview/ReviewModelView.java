package com.dodo.api.modelview;

import com.dodo.api.models.Order;
import com.dodo.api.models.Product;

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
public class ReviewModelView {
	private Order order;
	private Product product;
}
