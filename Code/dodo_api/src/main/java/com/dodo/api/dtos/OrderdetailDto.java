package com.dodo.api.dtos;

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
public class OrderdetailDto implements java.io.Serializable {
	private Integer orderDetailId;
	private Integer quantity;
	private Double unitPrice;
	private Double subtotal;
	private String productName;
	private String productDescription;
	private String productImage;
	private Boolean isReviewed;
	private Integer orderOrderId;
	private Integer productProductId;
	private String productProductName;
}
