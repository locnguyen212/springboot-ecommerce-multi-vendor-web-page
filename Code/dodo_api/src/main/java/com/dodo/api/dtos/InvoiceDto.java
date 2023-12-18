package com.dodo.api.dtos;

import java.util.Date;

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
public class InvoiceDto implements java.io.Serializable {
	private Integer invoiceId;
	private Date invoiceDate;
	private Double totalAmount;
	private String paymentMethod;
	private Boolean isPaid;
	private Date createdAt;
	private Integer userUserId;
	private String userUsername;
	private Integer shopownerOwnerId;
	private String shopownerShopName;
	private Integer orderOrderId;
	private Integer productProductId;
	private String productProductName;
}
