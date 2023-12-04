package com.dodo.web.modelview;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class ProductView {

	private Integer productId;
    private String productName;
    private Double price;
    private int stockQuantity;
    private String productImage;
	private Boolean status;

    private Integer categoryId;
	private String categoryName;

	private Integer sh_shopId;
	private String sh_shopName;
	private String sh_shopImage;

	private Double pr_discountAmount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pr_startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pr_endDate;

	private Long totalComments;
	private Double averageRating;
	public ProductView() {

    }
	
	public ProductView(Integer productId, String productName, Double price, int stockQuantity, String productImage,
			Boolean status, Integer categoryId, String categoryName, Integer sh_shopId, String sh_shopName,
			String sh_shopImage, Double pr_discountAmount, Date pr_startDate, Date pr_endDate, Long totalComments,
			Double averageRating) {
		super();		
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.productImage = productImage;
		this.status = status;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.sh_shopId = sh_shopId;
		this.sh_shopName = sh_shopName;
		this.sh_shopImage = sh_shopImage;
		this.pr_discountAmount = pr_discountAmount;
		this.pr_startDate = pr_startDate;
		this.pr_endDate = pr_endDate;
		this.totalComments = totalComments;
		this.averageRating = averageRating;
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getSh_shopId() {
		return sh_shopId;
	}
	public void setSh_shopId(Integer sh_shopId) {
		this.sh_shopId = sh_shopId;
	}
	public String getSh_shopName() {
		return sh_shopName;
	}
	public void setSh_shopName(String sh_shopName) {
		this.sh_shopName = sh_shopName;
	}
	public String getSh_shopImage() {
		return sh_shopImage;
	}
	public void setSh_shopImage(String sh_shopImage) {
		this.sh_shopImage = sh_shopImage;
	}
	public Double getPr_discountAmount() {
		return pr_discountAmount;
	}
	public void setPr_discountAmount(Double pr_discountAmount) {
		this.pr_discountAmount = pr_discountAmount;
	}
	public Date getPr_startDate() {
		return pr_startDate;
	}
	public void setPr_startDate(Date pr_startDate) {
		this.pr_startDate = pr_startDate;
	}
	public Date getPr_endDate() {
		return pr_endDate;
	}
	public void setPr_endDate(Date pr_endDate) {
		this.pr_endDate = pr_endDate;
	}
	public Long getTotalComments() {
		return totalComments;
	}
	public void setTotalComments(Long totalComments) {
		this.totalComments = totalComments;
	}
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	
}
