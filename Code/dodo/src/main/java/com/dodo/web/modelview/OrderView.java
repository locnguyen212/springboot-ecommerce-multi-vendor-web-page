package com.dodo.web.modelview;

import com.dodo.web.models.Shopowner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderView {
	private Shopowner shopowner;
    private Double total;
    private Integer month;
    private Integer year;

	@Override
	public String toString() {
		return "OrderView [shopowner=" + shopowner.getOwnerId() + ", total=" + total + ", month=" + month + ", year=" + year + "]";
	}

	

}
