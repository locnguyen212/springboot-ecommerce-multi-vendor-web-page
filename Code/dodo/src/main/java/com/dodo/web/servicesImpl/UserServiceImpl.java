package com.dodo.web.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IUserService;
import com.dodo.web.models.User;
import com.dodo.web.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repository;

	// ====LOC====
	@Override
	public List<User> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(User category) {
		try {
			repository.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User findByUsername(String name) {
		try {
			return repository.findByUsername(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		try {
			return repository.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> findAllRoleUserAndShopowner() {
		try {
			return repository.findAllRoleUserAndShopowner();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isTokenExist(String token) {
		try {
			return repository.findByToken(token).size() != 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// LOGIN
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			var user = repository.findByUsername(username);
			System.out.println(user);
			if (user == null) {
				throw new UsernameNotFoundException("Account not found!");
			} else {
				// trích xuất tên roles dc gán cho account và đặt nó trong GrantedAuthority
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
				return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
						user.getStatus(), true, true, true, authorities);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Account not found!");
		}
	}
	// LOGIN

	// ====LOC====

}
