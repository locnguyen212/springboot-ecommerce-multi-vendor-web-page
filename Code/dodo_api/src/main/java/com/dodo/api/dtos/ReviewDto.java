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
}
