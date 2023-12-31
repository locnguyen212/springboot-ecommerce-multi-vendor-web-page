package com.dodo.web.models;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Shopowner generated by hbm2java
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopowner")
public class Shopowner implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ownerid", unique = true, nullable = false)
	private Integer ownerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private User user;

	@Column(name = "shopname", length = 100)
	@NotBlank
	private String shopName;
	
	@Column(name = "shopdescription", length = 65535)
	@NotBlank
	private String shopDescription;

	@Column(name = "shoplogopath")
	private String shopLogoPath;

	@Column(name = "status")
	private Boolean status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdat", length = 19)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedat", length = 19)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updatedAt;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	private Set<Order> orders = new HashSet<Order>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	private Set<Salesdata> salesdatas = new HashSet<Salesdata>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	private Set<Ordercancellation> ordercancellations = new HashSet<Ordercancellation>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	private Set<Shopownercoupon> shopownercoupons = new HashSet<Shopownercoupon>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	private Set<Promotion> promotions = new HashSet<Promotion>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopowner")
	private Set<Invoice> invoices = new HashSet<Invoice>(0);
  
	@Override
	public String toString() {
		return "Shopowner [ownerId=" + ownerId + ", user=" + user.getUserId() + ", shopName=" + shopName + ", shopDescription="
				+ shopDescription + ", shopLogoPath=" + shopLogoPath + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	
}
