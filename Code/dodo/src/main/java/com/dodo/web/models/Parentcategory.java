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
 * Parentcategory generated by hbm2java
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parentcategory")
public class Parentcategory implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "parentcategoryid", unique = true, nullable = false)
	private Integer parentCategoryId;

	@Column(name = "parentcategoryname")
	@NotBlank
	private String parentCategoryName;

	@Column(name = "parentcategorydescription", length = 65535)
	private String parentCategoryDescription;

	@Column(name = "parentcategoryimagepath")
	private String parentCategoryImagePath;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdat", length = 19)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedat", length = 19)
	private Date updatedAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentcategory")
	private Set<Category> categories = new HashSet<Category>(0);
	
	@Override
	public String toString() {
		return "Parentcategory [parentCategoryId=" + parentCategoryId + ", parentCategoryName=" + parentCategoryName
				+ ", parentCategoryDescription=" + parentCategoryDescription + ", parentCategoryImagePath="
				+ parentCategoryImagePath + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	

}
