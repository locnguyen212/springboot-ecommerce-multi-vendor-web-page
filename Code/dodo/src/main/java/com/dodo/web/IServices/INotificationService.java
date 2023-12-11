package com.dodo.web.IServices;

import java.util.List;

import com.dodo.web.models.Notification;

public interface INotificationService {
	public List<Notification> getList(int userId);
	public Notification findById(int id);
	public Integer countUnread(int userId);
	public List<Notification> findByUserId(int userId, int skip, int take);
	public List<Notification> findByUserIdAndTypeAndAll(int userId, String type, int skip, int take);
	public List<Notification> findByUserIdAndTypeAndIsRead(int userId, String type, Boolean isRead, int skip, int take);
	public Integer countAll(int userId);
}