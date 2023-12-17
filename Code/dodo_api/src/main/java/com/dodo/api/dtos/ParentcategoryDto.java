package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
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
public class ParentcategoryDto implements java.io.Serializable {

	private Integer parentCategoryId;
	@NotBlank
	private String parentCategoryName;
	private String parentCategoryDescription;
	private String parentCategoryImagePath;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;

//	public ParentcategoryDto(Integer parentCategoryId, String parentCategoryName, String parentCategoryDescription,
//			String parentCategoryImagePath, Date createdAt, Date updatedAt) {
//		this.parentCategoryId = parentCategoryId;
//		this.parentCategoryName = parentCategoryName;
//		this.parentCategoryDescription = parentCategoryDescription;
//		this.parentCategoryImagePath = parentCategoryImagePath;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}
//
//	public ParentcategoryDto() {
//	}
//
//	public Integer getParentCategoryId() {
//		return parentCategoryId;
//	}
//
//	public void setParentCategoryId(Integer parentCategoryId) {
//		this.parentCategoryId = parentCategoryId;
//	}
//
//	public String getParentCategoryName() {
//		return parentCategoryName;
//	}
//
//	public void setParentCategoryName(String parentCategoryName) {
//		this.parentCategoryName = parentCategoryName;
//	}
//
//	public String getParentCategoryDescription() {
//		return parentCategoryDescription;
//	}
//
//	public void setParentCategoryDescription(String parentCategoryDescription) {
//		this.parentCategoryDescription = parentCategoryDescription;
//	}
//
//	public String getParentCategoryImagePath() {
//		return parentCategoryImagePath;
//	}
//
//	public void setParentCategoryImagePath(String parentCategoryImagePath) {
//		this.parentCategoryImagePath = parentCategoryImagePath;
//	}
//
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public Date getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(Date updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	@Override
//	public String toString() {
//		return "ParentcategoryDto [parentCategoryId=" + parentCategoryId + ", parentCategoryName=" + parentCategoryName
//				+ ", parentCategoryDescription=" + parentCategoryDescription + ", parentCategoryImagePath="
//				+ parentCategoryImagePath + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
//	}

}
