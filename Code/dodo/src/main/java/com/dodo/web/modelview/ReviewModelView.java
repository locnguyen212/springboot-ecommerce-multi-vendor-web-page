package com.dodo.web.modelview;

import com.dodo.web.models.Order;
import com.dodo.web.models.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModelView {
	private Order order;
	private Product product;

	@Override
	public String toString() {
		return "ReviewModelView [order=" + order.getOrderId() + ", product=" + product.getProductId() + "]";
	}
}
