package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class ReviewDto implements java.io.Serializable {

	private Integer reviewId;
	@NotNull
	private Integer rating;
	@NotBlank
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
	@NotNull
	private Integer productProductId;
	private String productProductName;
	private Integer userUserId;
	private String userUsername;
	@NotNull
	private Integer orderdetailsOrderDetailId;
}
