package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
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
	@NotBlank
	@NotNull
	private Integer shopownerOwnerId;
	private String shopownerShopName;
}
