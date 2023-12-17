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
public class ReviewDto implements java.io.Serializable {

	private Integer reviewId;
	private Integer rating;
	private String comment;
	private Date reviewDate;
	private Integer helpfulCount;
	private Integer reportCount;
	private Boolean isVerified;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private String reviewImage;
	private Integer productProductId;
	private String productProductName;
	private Integer userUserId;
	private String userUsername;
	private Integer orderdetailsOrderDetailId;

//	public ReviewDto() {
//	}
//
//	public ReviewDto(Integer reviewId, Integer rating, String comment, Date reviewDate, Integer helpfulCount,
//			Integer reportCount, Boolean isVerified, Date createdAt, Date updatedAt, String reviewImage,
//			Integer productProductId, String productProductName, Integer userUserId, String userUsername,
//			Integer orderdetailsOrderDetailId) {
//		this.reviewId = reviewId;
//		this.rating = rating;
//		this.comment = comment;
//		this.reviewDate = reviewDate;
//		this.helpfulCount = helpfulCount;
//		this.reportCount = reportCount;
//		this.isVerified = isVerified;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.reviewImage = reviewImage;
//		this.productProductId = productProductId;
//		this.productProductName = productProductName;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//		this.orderdetailsOrderDetailId = orderdetailsOrderDetailId;
//	}
//
//	public Integer getReviewId() {
//		return reviewId;
//	}
//
//	public void setReviewId(Integer reviewId) {
//		this.reviewId = reviewId;
//	}
//
//	public Integer getRating() {
//		return rating;
//	}
//
//	public void setRating(Integer rating) {
//		this.rating = rating;
//	}
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	public Date getReviewDate() {
//		return reviewDate;
//	}
//
//	public void setReviewDate(Date reviewDate) {
//		this.reviewDate = reviewDate;
//	}
//
//	public Integer getHelpfulCount() {
//		return helpfulCount;
//	}
//
//	public void setHelpfulCount(Integer helpfulCount) {
//		this.helpfulCount = helpfulCount;
//	}
//
//	public Integer getReportCount() {
//		return reportCount;
//	}
//
//	public void setReportCount(Integer reportCount) {
//		this.reportCount = reportCount;
//	}
//
//	public Boolean getIsVerified() {
//		return isVerified;
//	}
//
//	public void setIsVerified(Boolean isVerified) {
//		this.isVerified = isVerified;
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
//	public String getReviewImage() {
//		return reviewImage;
//	}
//
//	public void setReviewImage(String reviewImage) {
//		this.reviewImage = reviewImage;
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
//	public Integer getOrderdetailsOrderDetailId() {
//		return orderdetailsOrderDetailId;
//	}
//
//	public void setOrderdetailsOrderDetailId(Integer orderdetailsOrderDetailId) {
//		this.orderdetailsOrderDetailId = orderdetailsOrderDetailId;
//	}
//
//	@Override
//	public String toString() {
//		return "ReviewDto [reviewId=" + reviewId + ", rating=" + rating + ", comment=" + comment + ", reviewDate="
//				+ reviewDate + ", helpfulCount=" + helpfulCount + ", reportCount=" + reportCount + ", isVerified="
//				+ isVerified + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", reviewImage=" + reviewImage
//				+ ", productProductId=" + productProductId + ", productProductName=" + productProductName
//				+ ", userUserId=" + userUserId + ", userUsername=" + userUsername + ", orderdetailsOrderDetailId="
//				+ orderdetailsOrderDetailId + "]";
//	}

}
