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
public class WishlistDto implements java.io.Serializable {

	private Integer wishlistId;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	private Integer userUserId;
	private String userUsername;
	private Integer productProductId;
	private String productProductName;

//	public WishlistDto() {
//	}
//
//	public WishlistDto(Integer wishlistId, Date createdAt, Integer userUserId, String userUsername,
//			Integer productProductId, String productProductName) {
//		this.wishlistId = wishlistId;
//		this.createdAt = createdAt;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//		this.productProductId = productProductId;
//		this.productProductName = productProductName;
//	}
//
//	public Integer getWishlistId() {
//		return wishlistId;
//	}
//
//	public void setWishlistId(Integer wishlistId) {
//		this.wishlistId = wishlistId;
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
//		return "WishlistDto [wishlistId=" + wishlistId + ", createdAt=" + createdAt + ", userUserId=" + userUserId
//				+ ", userUsername=" + userUsername + ", productProductId=" + productProductId + ", productProductName="
//				+ productProductName + "]";
//	}

}
