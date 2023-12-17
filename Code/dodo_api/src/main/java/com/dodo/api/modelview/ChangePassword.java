package com.dodo.api.modelview;

import jakarta.validation.constraints.NotBlank;

public class ChangePassword {
	@NotBlank
	private String password;
	@NotBlank
	private String newPassword;
	@NotBlank
	private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public ChangePassword(String password, String newPassword, String confirmPassword) {
		super();
		this.password = password;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public ChangePassword() {
		super();
	}

	@Override
	public String toString() {
		return "ChangePassword [password=" + password + ", newPassword=" + newPassword + ", confirmPassword="
				+ confirmPassword + "]";
	}

}
