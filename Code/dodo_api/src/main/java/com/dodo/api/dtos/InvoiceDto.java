package com.dodo.api.dtos;

import java.util.Date;

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
public class InvoiceDto implements java.io.Serializable {
	private Integer invoiceId;
	private Date invoiceDate;
	private Double totalAmount;
	private String paymentMethod;
	private Boolean isPaid;
	private Date createdAt;
	private Integer userUserId;
	private String userUsername;
	private Integer shopownerOwnerId;
	private String shopownerShopName;
	private Integer orderOrderId;
	private Integer productProductId;
	private String productProductName;

//	public InvoiceDto() {
//	}
//
//	public InvoiceDto(Integer invoiceId, Date invoiceDate, Double totalAmount, String paymentMethod, Boolean isPaid,
//			Date createdAt, Integer userUserId, String userUsername, Integer shopownerOwnerId, String shopownerShopName,
//			Integer orderOrderId, Integer productProductId, String productProductName) {
//		this.invoiceId = invoiceId;
//		this.invoiceDate = invoiceDate;
//		this.totalAmount = totalAmount;
//		this.paymentMethod = paymentMethod;
//		this.isPaid = isPaid;
//		this.createdAt = createdAt;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//		this.shopownerOwnerId = shopownerOwnerId;
//		this.shopownerShopName = shopownerShopName;
//		this.orderOrderId = orderOrderId;
//		this.productProductId = productProductId;
//		this.productProductName = productProductName;
//	}
//
//	public Integer getInvoiceId() {
//		return invoiceId;
//	}
//
//	public void setInvoiceId(Integer invoiceId) {
//		this.invoiceId = invoiceId;
//	}
//
//	public Date getInvoiceDate() {
//		return invoiceDate;
//	}
//
//	public void setInvoiceDate(Date invoiceDate) {
//		this.invoiceDate = invoiceDate;
//	}
//
//	public Double getTotalAmount() {
//		return totalAmount;
//	}
//
//	public void setTotalAmount(Double totalAmount) {
//		this.totalAmount = totalAmount;
//	}
//
//	public String getPaymentMethod() {
//		return paymentMethod;
//	}
//
//	public void setPaymentMethod(String paymentMethod) {
//		this.paymentMethod = paymentMethod;
//	}
//
//	public Boolean getIsPaid() {
//		return isPaid;
//	}
//
//	public void setIsPaid(Boolean isPaid) {
//		this.isPaid = isPaid;
//	}
//
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public Integer getUserUserId() {
//		return userUserId;
//	}
//
//	public void setUserUserId(Integer userUserId) {
//		this.userUserId = userUserId;
//	}
//
//	public String getUserUsername() {
//		return userUsername;
//	}
//
//	public void setUserUsername(String userUsername) {
//		this.userUsername = userUsername;
//	}
//
//	public Integer getShopownerOwnerId() {
//		return shopownerOwnerId;
//	}
//
//	public void setShopownerOwnerId(Integer shopownerOwnerId) {
//		this.shopownerOwnerId = shopownerOwnerId;
//	}
//
//	public String getShopownerShopName() {
//		return shopownerShopName;
//	}
//
//	public void setShopownerShopName(String shopownerShopName) {
//		this.shopownerShopName = shopownerShopName;
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
//		return "InvoiceDto [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", totalAmount=" + totalAmount
//				+ ", paymentMethod=" + paymentMethod + ", isPaid=" + isPaid + ", createdAt=" + createdAt
//				+ ", userUserId=" + userUserId + ", userUsername=" + userUsername + ", shopownerOwnerId="
//				+ shopownerOwnerId + ", shopownerShopName=" + shopownerShopName + ", orderOrderId=" + orderOrderId
//				+ ", productProductId=" + productProductId + ", productProductName=" + productProductName + "]";
//	}

}
