package com.dodo.api.modelview;

import jakarta.validation.constraints.NotBlank;

public class ResetPassword {
	@NotBlank
	private String password;
	@NotBlank
	private Integer id;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ResetPassword(String password, Integer id) {
		this.password = password;
		this.id = id;
	}

	public ResetPassword() {
	}

	@Override
	public String toString() {
		return "ResetPassword [password=" + password + ", id=" + id + "]";
	}

}
