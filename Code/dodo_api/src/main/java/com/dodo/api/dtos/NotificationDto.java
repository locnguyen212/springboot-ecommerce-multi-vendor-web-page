package com.dodo.api.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class NotificationDto implements java.io.Serializable {

	private Integer notificationId;
	private String notificationType;
	private String message;
	private Boolean isRead;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	private Integer userUserId;
	private String userUsername;

//	public Notification(Integer notificationId, String notificationType, String message, Boolean isRead, Date createdAt,
//			Integer userUserId, String userUsername) {
//		this.notificationId = notificationId;
//		this.notificationType = notificationType;
//		this.message = message;
//		this.isRead = isRead;
//		this.createdAt = createdAt;
//		this.userUserId = userUserId;
//		this.userUsername = userUsername;
//	}
//
//	public Notification() {
//	}
//
//	public Integer getNotificationId() {
//		return notificationId;
//	}
//
//	public void setNotificationId(Integer notificationId) {
//		this.notificationId = notificationId;
//	}
//
//	public String getNotificationType() {
//		return notificationType;
//	}
//
//	public void setNotificationType(String notificationType) {
//		this.notificationType = notificationType;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public Boolean getIsRead() {
//		return isRead;
//	}
//
//	public void setIsRead(Boolean isRead) {
//		this.isRead = isRead;
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
//	public Integer getUserUserId() {
//		return userUserId;
//	}
//
//	public void setUserUserId(Integer userUserId) {
//		this.userUserId = userUserId;
//	}
//
//	public String getUserUsername() {
//		return userUsername;
//	}
//
//	public void setUserUsername(String userUsername) {
//		this.userUsername = userUsername;
//	}
//
//	@Override
//	public String toString() {
//		return "Notification [notificationId=" + notificationId + ", notificationType=" + notificationType
//				+ ", message=" + message + ", isRead=" + isRead + ", createdAt=" + createdAt + ", userUserId="
//				+ userUserId + ", userUsername=" + userUsername + "]";
//	}

}
