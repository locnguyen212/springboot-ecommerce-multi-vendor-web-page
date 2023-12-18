package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank
	@NotNull
	private Integer parentCategoryParentCategoryId;
	private String parentCategoryParentCategoryName;
	private Integer userUserId;
	private String userUsername;
}
