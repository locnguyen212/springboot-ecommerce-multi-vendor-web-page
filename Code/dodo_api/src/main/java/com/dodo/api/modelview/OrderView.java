package com.dodo.api.modelview;

import com.dodo.api.models.Shopowner;

public class OrderView {

	private Shopowner shopowner;
    private Double total;
    private Integer month;
    private Integer year;

	public OrderView() {

    }

	public OrderView(Shopowner shopowner, Double total, Integer month, Integer year) {
		super();
		this.shopowner = shopowner;
		this.total = total;
		this.month = month;
		this.year = year;
	}

	public Shopowner getShopowner() {
		return shopowner;
	}

	public void setShopowner(Shopowner shopowner) {
		this.shopowner = shopowner;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "OrderView [shopowner=" + shopowner.getOwnerId() + ", total=" + total + ", month=" + month + ", year=" + year + "]";
	}

	

}
