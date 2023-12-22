package com.dodo.web.models;
// Generated Jun 1, 2022, 9:00:27 PM by Hibernate Tools 4.3.6.Final

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
public class Mail {
	private String name;
	@NotBlank
	private String email;
	private String title;
	private String phone;
	private String content;
}
