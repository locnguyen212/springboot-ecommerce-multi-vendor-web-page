package com.dodo.api.models;
// Generated Oct 9, 2023, 11:00:28 PM by Hibernate Tools 4.3.6.Final

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Order generated by hbm2java
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
public class Order implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "orderid", unique = true, nullable = false)
	private Integer orderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopownerid")
	private Shopowner shopowner;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orderdate", length = 19)
	private Date orderDate;
	
	@Column(name = "totalamount", precision = 10)
	private Double totalAmount;
	
	@Column(name = "shippingaddress")
	private String shippingAddress;
	
	@Column(name = "paymentmethod", length = 50)
	private String paymentMethod;
	
	@Column(name = "orderstatus", length = 50)
	private String orderStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedat", length = 19)
	private Date updatedAt;
	
	@Column(name = "paymentstatus", length = 100)
	private String paymentStatus;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<Salesdata> salesdatas = new HashSet<Salesdata>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<Orderdetail> orderdetails = new HashSet<Orderdetail>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<Invoice> invoices = new HashSet<Invoice>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<Ordercancellation> ordercancellations = new HashSet<Ordercancellation>(0);

}
