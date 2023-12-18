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
	@NotBlank
	@NotNull
	private Integer shopownerOwnerId;
	private String shopownerShopName;
	@NotBlank
	@NotNull
	private Integer categoryCategoryId;
	private String categoryCategoryName;
}
