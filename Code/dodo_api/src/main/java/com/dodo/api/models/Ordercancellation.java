package com.dodo.api.models;
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
import jakarta.validation.constraints.NotBlank;

/**
 * Ordercancellation generated by hbm2java
 */
@Entity
@Table(name = "ordercancellation")
public class Ordercancellation implements java.io.Serializable {

	private Integer cancellationId;
	private Order order;
	private Shopowner shopowner;
	private User user;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cancellationDate;
	@NotBlank
	private String reason;
	private Boolean status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdAt;

	public Ordercancellation() {
	}

	public Ordercancellation(Order order, Shopowner shopowner, User user, Date cancellationDate, String reason,
			Boolean status, Date createdAt) {
		this.order = order;
		this.shopowner = shopowner;
		this.user = user;
		this.cancellationDate = cancellationDate;
		this.reason = reason;
		this.status = status;
		this.createdAt = createdAt;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "cancellationid", unique = true, nullable = false)
	public Integer getCancellationId() {
		return this.cancellationId;
	}

	public void setCancellationId(Integer cancellationId) {
		this.cancellationId = cancellationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderid")
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopownerid")
	public Shopowner getShopowner() {
		return this.shopowner;
	}

	public void setShopowner(Shopowner shopowner) {
		this.shopowner = shopowner;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cancellationdate", length = 10)
	public Date getCancellationDate() {
		return this.cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	@Column(name = "reason", length = 65535)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdat", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}