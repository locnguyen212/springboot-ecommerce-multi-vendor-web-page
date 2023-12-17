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
public class OrdercancellationDto implements java.io.Serializable {

	private Integer cancellationId;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cancellationDate;
	@NotBlank
	private String reason;
	private Boolean status;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdAt;
	private Integer userUserId;
	private String userUsername;
	private Integer shopownerOwnerId;
	private String shopownerShopName;
	private Integer orderOrderId;

//	public OrdercancellationDto(Integer cancellationId, Date cancellationDate, String reason, Boolean status,
//			Date createdAt, Integer userUserId, String userUsername, Integer shopownerOwnerId, String shopownerShopName,
//			Integer orderOrderId) {
//		this.cancellationId = cancellationId;
//		this.cancellationDate = cancellationDate;
//		this.reason = reason;
//		this.status = status;
//		this.createdAt = createdAt;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//		this.shopownerOwnerId = shopownerOwnerId;
//		this.shopownerShopName = shopownerShopName;
//		this.orderOrderId = orderOrderId;
//	}
//
//	public OrdercancellationDto() {
//	}
//
//	public Integer getCancellationId() {
//		return cancellationId;
//	}
//
//	public void setCancellationId(Integer cancellationId) {
//		this.cancellationId = cancellationId;
//	}
//
//	public Date getCancellationDate() {
//		return cancellationDate;
//	}
//
//	public void setCancellationDate(Date cancellationDate) {
//		this.cancellationDate = cancellationDate;
//	}
//
//	public String getReason() {
//		return reason;
//	}
//
//	public void setReason(String reason) {
//		this.reason = reason;
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
//	@Override
//	public String toString() {
//		return "OrdercancellationDto [cancellationId=" + cancellationId + ", cancellationDate=" + cancellationDate
//				+ ", reason=" + reason + ", status=" + status + ", createdAt=" + createdAt + ", userUserId="
//				+ userUserId + ", userUsername=" + userUsername + ", shopownerOwnerId=" + shopownerOwnerId
//				+ ", shopownerShopName=" + shopownerShopName + ", orderOrderId=" + orderOrderId + "]";
//	}

}
