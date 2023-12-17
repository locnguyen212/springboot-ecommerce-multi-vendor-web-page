package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
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
public class UserDto implements java.io.Serializable {

	private Integer userId;
	@NotBlank
	private String username;
	private String password;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String address;
	@NotBlank
	private String phoneNumber;
	private String avatar;
	private String gender;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	@NotNull
	private Date dob;
	private Boolean status;
	private String token;
	private String refreshToken;
	private String forgetPasswordToken;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date updatedAt;
	private Integer roleRoleId;

//	public UserDto() {
//	}
//
//	public UserDto(Integer userId, String username, String password, String email, String firstName, String lastName,
//			String address, String phoneNumber, String avatar, String gender, Date dob, Boolean status, String token,
//			String refreshToken, String forgetPasswordToken, Date createdAt, Date updatedAt, Integer roleRoleId) {
//		this.userId = userId;
//		this.username = username;
//		this.password = password;
//		this.email = email;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.address = address;
//		this.phoneNumber = phoneNumber;
//		this.avatar = avatar;
//		this.gender = gender;
//		this.dob = dob;
//		this.status = status;
//		this.token = token;
//		this.refreshToken = refreshToken;
//		this.forgetPasswordToken = forgetPasswordToken;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.roleRoleId = roleRoleId;
//	}
//
//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public String getAvatar() {
//		return avatar;
//	}
//
//	public void setAvatar(String avatar) {
//		this.avatar = avatar;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {
//		this.dob = dob;
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
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
//
//	public String getRefreshToken() {
//		return refreshToken;
//	}
//
//	public void setRefreshToken(String refreshToken) {
//		this.refreshToken = refreshToken;
//	}
//
//	public String getForgetPasswordToken() {
//		return forgetPasswordToken;
//	}
//
//	public void setForgetPasswordToken(String forgetPasswordToken) {
//		this.forgetPasswordToken = forgetPasswordToken;
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
//	public Integer getRoleRoleId() {
//		return roleRoleId;
//	}
//
//	public void setRoleRoleId(Integer roleRoleId) {
//		this.roleRoleId = roleRoleId;
//	}
//
//	@Override
//	public String toString() {
//		return "UserDto [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
//				+ ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", phoneNumber="
//				+ phoneNumber + ", avatar=" + avatar + ", gender=" + gender + ", dob=" + dob + ", status=" + status
//				+ ", token=" + token + ", refreshToken=" + refreshToken + ", forgetPasswordToken=" + forgetPasswordToken
//				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", roleRoleId=" + roleRoleId + "]";
//	}

}
