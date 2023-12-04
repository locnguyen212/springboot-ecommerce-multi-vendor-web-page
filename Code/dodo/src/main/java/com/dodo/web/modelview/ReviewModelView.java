package com.dodo.web.modelview;

import com.dodo.web.models.Order;
import com.dodo.web.models.Product;

public class ReviewModelView {

	private Order order;
	private Product product;

	public ReviewModelView() {
		super();
	}


	public ReviewModelView(Order order, Product product) {
		super();
		this.order = order;
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public String toString() {
		return "ReviewModelView [order=" + order.getOrderId() + ", product=" + product.getProductId() + "]";
	}




}
