package com.dodo.web.models;
// Generated Jun 1, 2022, 9:00:27 PM by Hibernate Tools 4.3.6.Final

import jakarta.validation.constraints.NotBlank;

public class Mail {
	private String name;
	@NotBlank
	private String email;
	private String title;
	private String phone;
	private String content;

	public Mail(String name, String email, String title, String phone, String content) {
		super();
		this.name = name;
		this.email = email;
		this.title = title;
		this.phone = phone;
		this.content = content;
	}

	public Mail() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Mail [name=" + name + ", email=" + email + ", title=" + title + ", phone=" + phone + ", content="
				+ content + "]";
	}

}
