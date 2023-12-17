package com.dodo.api.IServices;

import java.util.List;

import com.dodo.api.dtos.NotificationDto;

public interface INotificationService {
	public List<NotificationDto> getList(int userId);
	public NotificationDto findById(int id);
	public Integer countUnread(int userId);
	public List<NotificationDto> findByUserId(int userId, int skip, int take);
	public List<NotificationDto> findByUserIdAndTypeAndAll(int userId, String type, int skip, int take);
	public List<NotificationDto> findByUserIdAndTypeAndIsRead(int userId, String type, Boolean isRead, int skip, int take);
	public Integer countAll(int userId);
}