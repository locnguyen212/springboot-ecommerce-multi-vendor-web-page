package com.dodo.web.models;
// Generated Oct 9, 2023, 11:00:28 PM by Hibernate Tools 4.3.6.Final

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Shopownercoupon generated by hbm2java
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopownercoupon")
public class Shopownercoupon implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "shopownercouponid", unique = true, nullable = false)
	private Integer shopOwnerCouponId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopownerid")
	private Shopowner shopowner;
    
	@Column(name = "couponcode", length = 20)
	private String couponCode;	

	@Column(name = "discountamount", precision = 10)
	@Min(value = 1, message = "Discount amount must be greater than 0")
	@Max(value = 100, message = "Discount amount must be lesser than 100")
	@NotNull
	private Double discountAmount;	

	@Temporal(TemporalType.DATE)
	@Column(name = "expirydate", length = 10)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date expiryDate;

	@Column(name = "isactive")
	private Boolean isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdat", length = 19)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedat", length = 19)
	private Date updatedAt;

	
	@Override
	public String toString() {
		return "Shopownercoupon [shopOwnerCouponId=" + shopOwnerCouponId + ", shopowner=" + shopowner.getOwnerId() + ", couponCode="
				+ couponCode + ", discountAmount=" + discountAmount + ", expiryDate=" + expiryDate + ", isActive="
				+ isActive + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	
}
