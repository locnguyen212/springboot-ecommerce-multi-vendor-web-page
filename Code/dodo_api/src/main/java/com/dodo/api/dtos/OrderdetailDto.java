package com.dodo.api.dtos;

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
public class OrderdetailDto implements java.io.Serializable {
	private Integer orderDetailId;
	private Integer quantity;
	private Double unitPrice;
	private Double subtotal;
	private String productName;
	private String productDescription;
	private String productImage;
	private Boolean isReviewed;
	private Integer orderOrderId;
	private Integer productProductId;
	private String productProductName;

//	public OrderdetailDto(Integer orderDetailId, Integer quantity, Double unitPrice, Double subtotal,
//			String productName, String productDescription, String productImage, Boolean isReviewed,
//			Integer orderOrderId, Integer productProductId, String productProductName) {
//		this.orderDetailId = orderDetailId;
//		this.quantity = quantity;
//		this.unitPrice = unitPrice;
//		this.subtotal = subtotal;
//		this.productName = productName;
//		this.productDescription = productDescription;
//		this.productImage = productImage;
//		this.isReviewed = isReviewed;
//		this.orderOrderId = orderOrderId;
//		this.productProductId = productProductId;
//		this.productProductName = productProductName;
//	}
//
//	public OrderdetailDto() {
//	}
//
//	public Integer getOrderDetailId() {
//		return orderDetailId;
//	}
//
//	public void setOrderDetailId(Integer orderDetailId) {
//		this.orderDetailId = orderDetailId;
//	}
//
//	public Integer getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//
//	public Double getUnitPrice() {
//		return unitPrice;
//	}
//
//	public void setUnitPrice(Double unitPrice) {
//		this.unitPrice = unitPrice;
//	}
//
//	public Double getSubtotal() {
//		return subtotal;
//	}
//
//	public void setSubtotal(Double subtotal) {
//		this.subtotal = subtotal;
//	}
//
//	public String getProductName() {
//		return productName;
//	}
//
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
//
//	public String getProductDescription() {
//		return productDescription;
//	}
//
//	public void setProductDescription(String productDescription) {
//		this.productDescription = productDescription;
//	}
//
//	public String getProductImage() {
//		return productImage;
//	}
//
//	public void setProductImage(String productImage) {
//		this.productImage = productImage;
//	}
//
//	public Boolean getIsReviewed() {
//		return isReviewed;
//	}
//
//	public void setIsReviewed(Boolean isReviewed) {
//		this.isReviewed = isReviewed;
//	}
//
//	public Integer getOrderOrderId() {
//		return orderOrderId;
//	}
//
//	public void setOrderOrderId(Integer orderOrderId) {
//		this.orderOrderId = orderOrderId;
//	}
//
//	public Integer getProductProductId() {
//		return productProductId;
//	}
//
//	public void setProductProductId(Integer productProductId) {
//		this.productProductId = productProductId;
//	}
//
//	public String getProductProductName() {
//		return productProductName;
//	}
//
//	public void setProductProductName(String productProductName) {
//		this.productProductName = productProductName;
//	}
//
//	@Override
//	public String toString() {
//		return "OrderdetailDto [orderDetailId=" + orderDetailId + ", quantity=" + quantity + ", unitPrice=" + unitPrice
//				+ ", subtotal=" + subtotal + ", productName=" + productName + ", productDescription="
//				+ productDescription + ", productImage=" + productImage + ", isReviewed=" + isReviewed
//				+ ", orderOrderId=" + orderOrderId + ", productProductId=" + productProductId + ", productProductName="
//				+ productProductName + "]";
//	}

}
