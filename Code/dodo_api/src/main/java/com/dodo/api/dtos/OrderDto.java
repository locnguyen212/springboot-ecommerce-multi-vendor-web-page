package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class OrderDto implements java.io.Serializable {

	private Integer orderId;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date orderDate;
	private Double totalAmount;
	private String shippingAddress;
	private String paymentMethod;
	private String orderStatus;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private String paymentStatus;
	private Integer userUserId;
	private String userUsername;
	private Integer shopownerOwnerId;
	private String shopownerShopName;

//	public OrderDto(Integer orderId, Date orderDate, Double totalAmount, String shippingAddress, String paymentMethod,
//			String orderStatus, Date updatedAt, String paymentStatus, Integer userUserId, String userUsername,
//			Integer shopownerOwnerId, String shopownerShopName) {
//		this.orderId = orderId;
//		this.orderDate = orderDate;
//		this.totalAmount = totalAmount;
//		this.shippingAddress = shippingAddress;
//		this.paymentMethod = paymentMethod;
//		this.orderStatus = orderStatus;
//		this.updatedAt = updatedAt;
//		this.paymentStatus = paymentStatus;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//		this.shopownerOwnerId = shopownerOwnerId;
//		this.shopownerShopName = shopownerShopName;
//	}
//
//	public OrderDto() {
//	}
//
//	public Integer getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(Integer orderId) {
//		this.orderId = orderId;
//	}
//
//	public Date getOrderDate() {
//		return orderDate;
//	}
//
//	public void setOrderDate(Date orderDate) {
//		this.orderDate = orderDate;
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
//	public String getShippingAddress() {
//		return shippingAddress;
//	}
//
//	public void setShippingAddress(String shippingAddress) {
//		this.shippingAddress = shippingAddress;
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
//	public String getOrderStatus() {
//		return orderStatus;
//	}
//
//	public void setOrderStatus(String orderStatus) {
//		this.orderStatus = orderStatus;
//	}
//
//	public Date getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(Date updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	public String getPaymentStatus() {
//		return paymentStatus;
//	}
//
//	public void setPaymentStatus(String paymentStatus) {
//		this.paymentStatus = paymentStatus;
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
//	@Override
//	public String toString() {
//		return "OrderDto [orderId=" + orderId + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount
//				+ ", shippingAddress=" + shippingAddress + ", paymentMethod=" + paymentMethod + ", orderStatus="
//				+ orderStatus + ", updatedAt=" + updatedAt + ", paymentStatus=" + paymentStatus + ", userUserId="
//				+ userUserId + ", userUsername=" + userUsername + ", shopownerOwnerId=" + shopownerOwnerId
//				+ ", shopownerShopName=" + shopownerShopName + "]";
//	}

}
