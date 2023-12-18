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
public class OrderDto implements java.io.Serializable {

	private Integer orderId;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date orderDate;
	private Double totalAmount;
	private String shippingAddress;
	private String paymentMethod;
	private String orderStatus;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private String paymentStatus;
	private Integer userUserId;
	private String userUsername;
	private Integer shopownerOwnerId;
	private String shopownerShopName;
}
