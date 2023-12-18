package com.dodo.api.modelview;

import com.dodo.api.dtos.OrderDto;
import com.dodo.api.dtos.ProductDto;

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
public class ReviewModelViewDto {
	private OrderDto order;
	private ProductDto product;
}
