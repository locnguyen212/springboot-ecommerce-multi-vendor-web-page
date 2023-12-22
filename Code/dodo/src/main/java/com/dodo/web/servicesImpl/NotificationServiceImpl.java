package com.dodo.web.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dodo.web.IServices.INotificationService;
import com.dodo.web.models.Notification;
import com.dodo.web.repositories.NotificationRepository;

@Service
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	private NotificationRepository repository;

	@Override
	public List<Notification> getList(int userId) {
		try {
			return repository.getList(userId);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Notification findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Notification> findByUserIdAndTypeAndAll(int userId, String type, int skip, int take) {
		try {
			return repository.findByUserIdAndTypeAndAll(userId, type, skip, take);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<Notification> findByUserId(int userId, int skip, int take) {
		try {
			return repository.findByUserId(userId, skip, take);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer countUnread(int userId) {
		try {
			return repository.countUnread(userId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Notification> findByUserIdAndTypeAndIsRead(int userId, String type, Boolean isRead, int skip,
			int take) {
		try {
			return repository.findByUserIdAndTypeAndIsRead(userId, type, isRead, skip, take);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer countAll(int userId) {
		try {
			return repository.countAll(userId);
		} catch (Exception e) {
			return null;
		}
	}

}
