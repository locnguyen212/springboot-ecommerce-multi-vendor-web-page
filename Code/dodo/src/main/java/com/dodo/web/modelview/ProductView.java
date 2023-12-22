package com.dodo.web.modelview;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dodo.web.models.Shopowner;

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
	
}
