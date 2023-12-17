package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class ShopownercouponDto implements java.io.Serializable {

	private Integer shopOwnerCouponId;
	private String couponCode;
	@Min(value = 1, message = "Discount amount must be greater than 0")
	@Max(value = 100, message = "Discount amount must be lesser than 100")
	@NotNull
	private Double discountAmount;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	@NotNull
	private Date expiryDate;
	private Boolean isActive;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private Integer shopownerOwnerId;
	private String shopownerShopName;

//	public ShopownercouponDto() {
//	}
//
//	public ShopownercouponDto(Integer shopOwnerCouponId, String couponCode, Double discountAmount, Date expiryDate,
//			Boolean isActive, Date createdAt, Date updatedAt, Integer shopownerOwnerId, String shopownerShopName) {
//		this.shopOwnerCouponId = shopOwnerCouponId;
//		this.couponCode = couponCode;
//		this.discountAmount = discountAmount;
//		this.expiryDate = expiryDate;
//		this.isActive = isActive;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.shopownerOwnerId = shopownerOwnerId;
//		this.shopownerShopName = shopownerShopName;
//	}
//
//	public Integer getShopOwnerCouponId() {
//		return shopOwnerCouponId;
//	}
//
//	public void setShopOwnerCouponId(Integer shopOwnerCouponId) {
//		this.shopOwnerCouponId = shopOwnerCouponId;
//	}
//
//	public String getCouponCode() {
//		return couponCode;
//	}
//
//	public void setCouponCode(String couponCode) {
//		this.couponCode = couponCode;
//	}
//
//	public Double getDiscountAmount() {
//		return discountAmount;
//	}
//
//	public void setDiscountAmount(Double discountAmount) {
//		this.discountAmount = discountAmount;
//	}
//
//	public Date getExpiryDate() {
//		return expiryDate;
//	}
//
//	public void setExpiryDate(Date expiryDate) {
//		this.expiryDate = expiryDate;
//	}
//
//	public Boolean getIsActive() {
//		return isActive;
//	}
//
//	public void setIsActive(Boolean isActive) {
//		this.isActive = isActive;
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
//	public Date getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(Date updatedAt) {
//		this.updatedAt = updatedAt;
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
//		return "ShopownercouponDto [shopOwnerCouponId=" + shopOwnerCouponId + ", couponCode=" + couponCode
//				+ ", discountAmount=" + discountAmount + ", expiryDate=" + expiryDate + ", isActive=" + isActive
//				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", shopownerOwnerId=" + shopownerOwnerId
//				+ ", shopownerShopName=" + shopownerShopName + "]";
//	}

}
