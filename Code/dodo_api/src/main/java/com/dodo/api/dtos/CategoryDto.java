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
public class CategoryDto implements java.io.Serializable {

	private Integer categoryId;
	@NotBlank(message = "Category name must not be blank")
	private String categoryName;
	private String description;
	private String imagePath;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private Boolean status;
	private Integer parentCategoryParentCategoryId;
	private String parentCategoryParentCategoryName;
	private Integer userUserId;
	private String userUsername;

//	public CategoryDto(Integer categoryId, String categoryName, String description, String imagePath, Date createdAt,
//			Date updatedAt, Boolean status, Integer parentCategoryParentCategoryId,
//			String parentCategoryParentCategoryName, Integer userUserId, String userUsername) {
//		this.categoryId = categoryId;
//		this.categoryName = categoryName;
//		this.description = description;
//		this.imagePath = imagePath;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.status = status;
//		this.parentCategoryParentCategoryId = parentCategoryParentCategoryId;
//		this.parentCategoryParentCategoryName = parentCategoryParentCategoryName;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//	}
//
//	public CategoryDto() {
//	}
//
//	public Integer getCategoryId() {
//		return categoryId;
//	}
//
//	public void setCategoryId(Integer categoryId) {
//		this.categoryId = categoryId;
//	}
//
//	public String getCategoryName() {
//		return categoryName;
//	}
//
//	public void setCategoryName(String categoryName) {
//		this.categoryName = categoryName;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getImagePath() {
//		return imagePath;
//	}
//
//	public void setImagePath(String imagePath) {
//		this.imagePath = imagePath;
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
//	public Boolean getStatus() {
//		return status;
//	}
//
//	public void setStatus(Boolean status) {
//		this.status = status;
//	}
//
//	public Integer getParentCategoryParentCategoryId() {
//		return parentCategoryParentCategoryId;
//	}
//
//	public void setParentCategoryParentCategoryId(Integer parentCategoryParentCategoryId) {
//		this.parentCategoryParentCategoryId = parentCategoryParentCategoryId;
//	}
//
//	public String getParentCategoryParentCategoryName() {
//		return parentCategoryParentCategoryName;
//	}
//
//	public void setParentCategoryParentCategoryName(String parentCategoryParentCategoryName) {
//		this.parentCategoryParentCategoryName = parentCategoryParentCategoryName;
//	}
//
//	public Integer getUserUserId() {
//		return userUserId;
//	}
//
//	public void setUserUserId(Integer userUserId) {
//		this.userUserId = userUserId;
//	}
//
//	public String getUserUsername() {
//		return userUsername;
//	}
//
//	public void setUserUsername(String userUsername) {
//		this.userUsername = userUsername;
//	}
//
//	@Override
//	public String toString() {
//		return "CategoryDto [categoryId=" + categoryId + ", categoryName=" + categoryName + ", description="
//				+ description + ", imagePath=" + imagePath + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
//				+ ", status=" + status + ", parentCategoryParentCategoryId=" + parentCategoryParentCategoryId
//				+ ", parentCategoryParentCategoryName=" + parentCategoryParentCategoryName + ", userUserId="
//				+ userUserId + ", userUsername=" + userUsername + "]";
//	}

}
