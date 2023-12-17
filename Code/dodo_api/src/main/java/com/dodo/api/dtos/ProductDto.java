package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class ProductDto implements java.io.Serializable {

	private Integer productId;
	@NotBlank
	private String productName;
	private String description;
	@NotNull
	@Min(value = 1, message = "Price must be greater than 0")
	private Double price;
	@NotNull
	@Min(value = 1, message = "Stock quantity must be greater than 0")
	private Integer stockQuantity;
	private String productImage;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date expiryDate;
	private Boolean status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private Integer shopownerOwnerId;
	private String shopownerShopName;
	private Integer categoryCategoryId;
	private String categoryCategoryName;

//	public ProductDto(Integer productId, String productName, String description, Double price, Integer stockQuantity,
//			String productImage, Date expiryDate, Boolean status, Date createdAt, Date updatedAt,
//			Integer shopownerOwnerId, String shopownerShopName, Integer categoryCategoryId,
//			String categoryCategoryName) {
//		this.productId = productId;
//		this.productName = productName;
//		this.description = description;
//		this.price = price;
//		this.stockQuantity = stockQuantity;
//		this.productImage = productImage;
//		this.expiryDate = expiryDate;
//		this.status = status;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.shopownerOwnerId = shopownerOwnerId;
//		this.shopownerShopName = shopownerShopName;
//		this.categoryCategoryId = categoryCategoryId;
//		this.categoryCategoryName = categoryCategoryName;
//	}
//
//	public ProductDto() {
//	}
//
//	public Integer getProductId() {
//		return productId;
//	}
//
//	public void setProductId(Integer productId) {
//		this.productId = productId;
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
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Double getPrice() {
//		return price;
//	}
//
//	public void setPrice(Double price) {
//		this.price = price;
//	}
//
//	public Integer getStockQuantity() {
//		return stockQuantity;
//	}
//
//	public void setStockQuantity(Integer stockQuantity) {
//		this.stockQuantity = stockQuantity;
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
//	public Date getExpiryDate() {
//		return expiryDate;
//	}
//
//	public void setExpiryDate(Date expiryDate) {
//		this.expiryDate = expiryDate;
//	}
//
//	public Boolean getStatus() {
//		return status;
//	}
//
//	public void setStatus(Boolean status) {
//		this.status = status;
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
//	public Integer getCategoryCategoryId() {
//		return categoryCategoryId;
//	}
//
//	public void setCategoryCategoryId(Integer categoryCategoryId) {
//		this.categoryCategoryId = categoryCategoryId;
//	}
//
//	public String getCategoryCategoryName() {
//		return categoryCategoryName;
//	}
//
//	public void setCategoryCategoryName(String categoryCategoryName) {
//		this.categoryCategoryName = categoryCategoryName;
//	}
//
//	@Override
//	public String toString() {
//		return "ProductDto [productId=" + productId + ", productName=" + productName + ", description=" + description
//				+ ", price=" + price + ", stockQuantity=" + stockQuantity + ", productImage=" + productImage
//				+ ", expiryDate=" + expiryDate + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt="
//				+ updatedAt + ", shopownerOwnerId=" + shopownerOwnerId + ", shopownerShopName=" + shopownerShopName
//				+ ", categoryCategoryId=" + categoryCategoryId + ", categoryCategoryName=" + categoryCategoryName + "]";
//	}

}
