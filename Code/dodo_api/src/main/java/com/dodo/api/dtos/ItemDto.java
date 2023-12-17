package com.dodo.api.dtos;

import java.util.Date;

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
public class ItemDto implements java.io.Serializable {
	private Integer itemId;
	private Integer quantity;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	private Integer productProductId;
	private String productProductName;
	private Integer userUserId;
	private String userUsername;

//	public ItemDto(Integer itemId, Integer quantity, Date createdAt, Integer productProductId,
//			String productProductName, Integer userUserId, String userUsername) {
//		this.itemId = itemId;
//		this.quantity = quantity;
//		this.createdAt = createdAt;
//		this.productProductId = productProductId;
//		this.productProductName = productProductName;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//	}
//
//	public ItemDto() {
//	}
//
//	public Integer getItemId() {
//		return itemId;
//	}
//
//	public void setItemId(Integer itemId) {
//		this.itemId = itemId;
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
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
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
//	@Override
//	public String toString() {
//		return "ItemDto [itemId=" + itemId + ", quantity=" + quantity + ", createdAt=" + createdAt
//				+ ", productProductId=" + productProductId + ", productProductName=" + productProductName
//				+ ", userUserId=" + userUserId + ", userUsername=" + userUsername + "]";
//	}

}
