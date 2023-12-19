package com.dodo.api.modelview.dtos;

import com.dodo.api.dtos.ShopownerDto;

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
public class OrderViewDto {
	private ShopownerDto shopowner;
    private Double total;
    private Integer month;
    private Integer year;
}
