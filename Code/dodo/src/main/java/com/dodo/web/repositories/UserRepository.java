package com.dodo.web.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//====LOC====
	public List<User> findByStatus(boolean status);
	public List<User> findByDob(Date dob);
	public List<User> findByRoleName(String name);
	public User findByUsername(String name);
	public User findByEmail(String email);
	public List<User> findByToken(String token);

	@Query("from User where phoneNumber like %:phone%")
	public List<User> findByPhoneNumberContain(@Param("phone") String phone);
	
	@Query("from User where username like %:name%")
	public List<User> findByUsernameContain(@Param("name") String name);
	
	@Query("from User where email like %:email%")
	public List<User> findByEmailContain(@Param("email") String email);	
	
	@Query("from User where firstName like %:name%")
	public List<User> findByFirstNameContain(@Param("name") String name);	
	
	@Query("from User where lastName like %:name%")
	public List<User> findByLastNameContain(@Param("name") String name);	
	
	@Query("from User where address like %:address%")
	public List<User> findByAddressContain(@Param("address") String address);	
	
	@Query("from User where createdAt >= :from and createdAt <= :to order by userId desc")
	public List<User> findByDateRangeCreated(@Param("from") Date from,  @Param("to") Date to);
	
	@Query("from User where dob >= :from and dob <= :to order by userId desc")
	public List<User> findByDateRangeDob(@Param("from") Date from,  @Param("to") Date to);
	 
	@Query("from User where role.roleId = 3 or role.roleId = 4 order by userId asc")
	public List<User> findAllRoleUserAndShopowner();
	
	@Query("from User where role.roleId != 1 order by userId asc")
	public List<User> findAll();
	//====LOC====
}
