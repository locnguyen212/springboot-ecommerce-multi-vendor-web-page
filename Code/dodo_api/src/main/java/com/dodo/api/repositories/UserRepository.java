package com.dodo.api.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//====LOC====
	public List<User> findByStatus(boolean status);
	public List<User> findByDob(Date dob);
	public List<User> findByRoleName(String name);
	public User findByUsername(String name);
	public User findByEmail(String email);
	public List<User> findByForgetPasswordToken(String token);
	 
	@Query("from User where role.roleId = 3 or role.roleId = 4 order by userId asc")
	public List<User> findByRoleUserAndShopowner();
	
	@Query("from User where role.roleId != 1 order by userId asc")
	public List<User> findAll();
	
	@Query("select count(u) "
			+ "from User u "
			+ "where u.username = :username "
			+ "and u.token = :token "
			+ "and (:refreshToken is null or u.refreshToken = :refreshToken)")
	public int isTokenValid(@Param("username") String username, @Param("token") String token, @Param("refreshToken") String refreshToken);
	//====LOC====
}
