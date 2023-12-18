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
public class OrdercancellationDto implements java.io.Serializable {

	private Integer cancellationId;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cancellationDate;
	@NotBlank
	private String reason;
	private Boolean status;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdAt;
	private Integer userUserId;
	private String userUsername;
	@NotBlank
	@NotNull
	private Integer shopownerOwnerId;
	private String shopownerShopName;
	@NotBlank
	@NotNull
	private Integer orderOrderId;
}
