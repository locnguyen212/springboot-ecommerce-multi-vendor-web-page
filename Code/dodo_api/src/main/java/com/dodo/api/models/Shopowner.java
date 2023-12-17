package com.dodo.api.models;
// Generated Oct 9, 2023, 11:00:28 PM by Hibernate Tools 4.3.6.Final

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;

/**
 * Shopowner generated by hbm2java
 */
@Entity
@Table(name = "shopowner")
public class Shopowner implements java.io.Serializable {
  
	private Integer ownerId;
	private User user;
	@NotBlank
	private String shopName;
	@NotBlank
	private String shopDescription;
	private String shopLogoPath;
	private Boolean status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updatedAt;
	private Set<Order> orders = new HashSet<Order>(0);
	private Set<Salesdata> salesdatas = new HashSet<Salesdata>(0);
	private Set<Ordercancellation> ordercancellations = new HashSet<Ordercancellation>(0);
	private Set<Shopownercoupon> shopownercoupons = new HashSet<Shopownercoupon>(0);
	private Set<Promotion> promotions = new HashSet<Promotion>(0);
	private Set<Invoice> invoices = new HashSet<Invoice>(0);

	public Shopowner() {
	}

	public Shopowner(User user, String shopName, String shopDescription, String shopLogoPath, Boolean status, Date createdAt,
			Date updatedAt, Set<Order> orders, Set<Salesdata> salesdatas,
			Set<Ordercancellation> ordercancellations, Set<Shopownercoupon> shopownercoupons, Set<Promotion> promotions, Set<Invoice> invoices) {
		this.user = user;
		this.shopName = shopName;
		this.shopDescription = shopDescription;
		this.shopLogoPath = shopLogoPath;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.orders = orders;
		this.salesdatas = salesdatas;
		this.ordercancellations = ordercancellations;
		this.shopownercoupons = shopownercoupons;
		this.invoices = invoices;
		this.status = status;
		this.promotions = promotions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ownerid", unique = true, nullable = false)
	public Integer getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "shopname", length = 100)
	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name = "shopdescription", length = 65535)
	public String getShopDescription() {
		return this.shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	@Column(name = "shoplogopath")
	public String getShopLogoPath() {
		return this.shopLogoPath;
	}

	public void setShopLogoPath(String shopLogoPath) {
		this.shopLogoPath = shopLogoPath;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdat", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedat", length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	public Set<Salesdata> getSalesdatas() {
		return this.salesdatas;
	}

	public void setSalesdatas(Set<Salesdata> salesdatas) {
		this.salesdatas = salesdatas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	public Set<Ordercancellation> getOrdercancellations() {
		return this.ordercancellations;
	}

	public void setOrdercancellations(Set<Ordercancellation> ordercancellations) {
		this.ordercancellations = ordercancellations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	public Set<Shopownercoupon> getShopownercoupons() {
		return this.shopownercoupons;
	}

	public void setShopownercoupons(Set<Shopownercoupon> shopownercoupons) {
		this.shopownercoupons = shopownercoupons;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	public Set<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Shopowner [ownerId=" + ownerId + ", user=" + user.getUserId() + ", shopName=" + shopName + ", shopDescription="
				+ shopDescription + ", shopLogoPath=" + shopLogoPath + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	
}
