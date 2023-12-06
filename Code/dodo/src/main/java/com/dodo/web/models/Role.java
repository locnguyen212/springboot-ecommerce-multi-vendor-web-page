package com.dodo.web.models;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "role", catalog = "dodo")
public class Role implements java.io.Serializable {

	private Integer roleId;
	private String name;
	private Boolean status;
	private Date createdAt;
	private Date updatedAt;
	private Set<User> users = new HashSet<User>(0);

	public Role() {
	}

	public Role(String name, Boolean status, Date createdAt, Date updatedAt, Set<User> users) {
		this.name = name;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
	}
	
	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "roleid", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedat", length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}