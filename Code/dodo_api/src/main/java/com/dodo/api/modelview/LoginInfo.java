package com.dodo.api.modelview;

public class LoginInfo {
	private String username;
	private String password;

	public LoginInfo(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public LoginInfo() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginInfo [username=" + username + ", password=" + password + "]";
	}
}
