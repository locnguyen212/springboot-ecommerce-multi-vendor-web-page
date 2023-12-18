package com.dodo.api.modelview;

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
public class ChangePassword {
	@NotBlank
	private String password;
	@NotBlank
	private String newPassword;
	@NotBlank
	private String confirmPassword;
}
