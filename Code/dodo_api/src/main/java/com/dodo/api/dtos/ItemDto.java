package com.dodo.api.dtos;

import java.util.Date;

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
public class ItemDto implements java.io.Serializable {
	private Integer itemId;
	private Integer quantity;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@NotBlank
	@NotNull
	private Integer productProductId;
	private String productProductName;
	private Integer userUserId;
	private String userUsername;
}
