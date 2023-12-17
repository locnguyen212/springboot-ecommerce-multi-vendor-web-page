package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
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
public class ShopownerDto implements java.io.Serializable {

	private Integer ownerId;
	@NotBlank
	private String shopName;
	@NotBlank
	private String shopDescription;
	private String shopLogoPath;
	private Boolean status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private Integer userUserId;
	private String userUsername;

//	public ShopownerDto() {
//	}
//
//	public ShopownerDto(Integer ownerId, String shopName, String shopDescription, String shopLogoPath, Boolean status,
//			Date createdAt, Date updatedAt, Integer userUserId, String userUsername) {
//		this.ownerId = ownerId;
//		this.shopName = shopName;
//		this.shopDescription = shopDescription;
//		this.shopLogoPath = shopLogoPath;
//		this.status = status;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//	}
//
//	public Integer getOwnerId() {
//		return ownerId;
//	}
//
//	public void setOwnerId(Integer ownerId) {
//		this.ownerId = ownerId;
//	}
//
//	public String getShopName() {
//		return shopName;
//	}
//
//	public void setShopName(String shopName) {
//		this.shopName = shopName;
//	}
//
//	public String getShopDescription() {
//		return shopDescription;
//	}
//
//	public void setShopDescription(String shopDescription) {
//		this.shopDescription = shopDescription;
//	}
//
//	public String getShopLogoPath() {
//		return shopLogoPath;
//	}
//
//	public void setShopLogoPath(String shopLogoPath) {
//		this.shopLogoPath = shopLogoPath;
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
//		return "ShopownerDto [ownerId=" + ownerId + ", shopName=" + shopName + ", shopDescription=" + shopDescription
//				+ ", shopLogoPath=" + shopLogoPath + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt="
//				+ updatedAt + ", userUserId=" + userUserId + ", userUsername=" + userUsername + "]";
//	}

}
