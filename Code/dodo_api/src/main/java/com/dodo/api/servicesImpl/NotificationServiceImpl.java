package com.dodo.api.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.INotificationService;
import com.dodo.api.dtos.NotificationDto;
import com.dodo.api.repositories.NotificationRepository;

@Service
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<NotificationDto> getList(int userId) {
		try {
			return modelMapper.map(repository.getList(userId), new TypeToken<List<NotificationDto>>() {}.getType());
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public NotificationDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), NotificationDto.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<NotificationDto> findByUserId(int userId, int skip, int take) {
		try {
			return modelMapper.map(repository.findByUserId(userId, skip, take), new TypeToken<List<NotificationDto>>() {}.getType());
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
	public List<NotificationDto> findByUserIdAndTypeAndIsRead(int userId, String type, Boolean isRead, int skip, int take) {
		try {
			return modelMapper.map(repository.findByUserIdAndTypeAndIsRead(userId, type, isRead, skip, take), new TypeToken<List<NotificationDto>>() {}.getType());
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer countByUserId(int userId) {
		try {
			return repository.countByUserId(userId);
		} catch (Exception e) {
			return null;
		}
	}

}
