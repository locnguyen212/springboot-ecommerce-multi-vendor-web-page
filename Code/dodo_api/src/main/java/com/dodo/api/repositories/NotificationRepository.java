package com.dodo.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	@Query("SELECT n "
			+ "FROM Notification n "
			+ "WHERE n.user.userId = :userId "
			+ "ORDER BY n.createdAt DESC")
	public List<Notification> getList(@Param("userId") int userId);
	
	@Query("FROM Notification n "
			+ "WHERE n.user.userId = :userId "
			+ "ORDER BY n.createdAt DESC "
			+ "LIMIT :take OFFSET :skip")
	public List<Notification> findByUserId(@Param("userId") int userId, @Param("skip") int skip, @Param("take") int take);	
	
	@Query("FROM Notification n "
			+ "WHERE n.user.userId = :userId "
			+ "AND (:type IS NULL OR n.notificationType = :type) "
			+ "AND (:isRead IS NULL OR n.isRead = :isRead) "
			+ "ORDER BY n.createdAt DESC "
			+ "LIMIT :take OFFSET :skip")
	public List<Notification> findByUserIdAndTypeAndIsRead(@Param("userId") int userId, @Param("type") String type, @Param("isRead") Boolean isRead, @Param("skip") int skip, @Param("take") int take);
	
	@Query("SELECT COUNT(n) "
			+ "FROM Notification n "
			+ "WHERE n.user.userId = :userId AND n.isRead = false ")
	public int countUnread(@Param("userId") int userId);
	
	@Query("SELECT COUNT(n) "
			+ "FROM Notification n "
			+ "WHERE n.user.userId = :userId ")
	public int countByUserId(@Param("userId") int userId);
}
