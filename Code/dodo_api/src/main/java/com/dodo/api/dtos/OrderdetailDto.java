package com.dodo.api.dtos;

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
public class OrderdetailDto implements java.io.Serializable {
	private Integer orderDetailId;
	@NotNull
	private Integer quantity;
	@NotNull
	private Double unitPrice;
	@NotNull
	private Double subtotal;
	private String productName;
	private String productDescription;
	private String productImage;
	private Boolean isReviewed;
	@NotNull
	private Integer orderOrderId;
	@NotNull
	private Integer productProductId;
	private String productProductName;
}
