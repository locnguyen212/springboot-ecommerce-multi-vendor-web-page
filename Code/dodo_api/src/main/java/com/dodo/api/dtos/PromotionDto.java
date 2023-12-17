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
public class PromotionDto implements java.io.Serializable {
	private Integer promotionId;
	@NotNull
	@Min(value = 1, message = "Discount amount must be greater than 1")
	@Max(value = 100, message = "Discount amount must be lesser than 100")
	private Double discountAmount;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	@NotNull
	private Date startDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	@NotNull
	private Date endDate;
	private String description;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private Integer shopownerOwnerId;
	private String shopownerShopName;
	private Integer productProductId;
	private String productProductName;

//	public PromotionDto(Integer promotionId, Double discountAmount, Date startDate, Date endDate, String description,
//			Date createdAt, Date updatedAt, Integer shopownerOwnerId, String shopownerShopName,
//			Integer productProductId, String productProductName) {
//		this.promotionId = promotionId;
//		this.discountAmount = discountAmount;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.description = description;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.shopownerOwnerId = shopownerOwnerId;
//		this.shopownerShopName = shopownerShopName;
//		this.productProductId = productProductId;
//		this.productProductName = productProductName;
//	}
//
//	public PromotionDto() {
//	}
//
//	public Integer getPromotionId() {
//		return promotionId;
//	}
//
//	public void setPromotionId(Integer promotionId) {
//		this.promotionId = promotionId;
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
//	public Date getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//
//	public Date getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
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
//		return "PromotionDto [promotionId=" + promotionId + ", discountAmount=" + discountAmount + ", startDate="
//				+ startDate + ", endDate=" + endDate + ", description=" + description + ", createdAt=" + createdAt
//				+ ", updatedAt=" + updatedAt + ", shopownerOwnerId=" + shopownerOwnerId + ", shopownerShopName="
//				+ shopownerShopName + ", productProductId=" + productProductId + ", productProductName="
//				+ productProductName + "]";
//	}

	
}
