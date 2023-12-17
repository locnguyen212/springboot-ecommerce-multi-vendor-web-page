package com.dodo.api.models;
// Generated Oct 9, 2023, 11:00:28 PM by Hibernate Tools 4.3.6.Final

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Orderdetail generated by hbm2java
 */
@Entity
@Table(name = "orderdetail")
public class Orderdetail implements java.io.Serializable {

	private Integer orderDetailId;
	private Order order;
	private Product product;
	private Integer quantity;
	private Double unitPrice;
	private Double subtotal;
	private String productName;
	private String productDescription;
	private String productImage;
	private Boolean isReviewed;

	public Orderdetail() {
	}

	public Orderdetail(Order order, Product product, Integer quantity, Double unitPrice,
			Double subtotal, String productName, String productDescription, String productImage, Boolean isReviewed) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.subtotal = subtotal;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productImage = productImage;
		this.isReviewed = isReviewed;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "orderdetailid", unique = true, nullable = false)
	public Integer getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderid")
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "unitprice", precision = 10)
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "subtotal", precision = 10)
	public Double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	@Column(name = "productname")
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "productdescription", length = 65535)
	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Column(name = "productimage")
	public String getProductImage() {
		return this.productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	@Column(name = "isreviewed")
	public Boolean getIsReviewed() {
		return this.isReviewed;
	}

	public void setIsReviewed(Boolean isReviewed) {
		this.isReviewed = isReviewed;
	}

	@Override
	public String toString() {
		return "Orderdetail [orderDetailId=" + orderDetailId + ", order=" + order + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", subtotal=" + subtotal + ", productName=" + productName
				+ ", productDescription=" + productDescription + ", productImage=" + productImage + ", isReviewed=" + isReviewed + "]";
	}
	
	

}
