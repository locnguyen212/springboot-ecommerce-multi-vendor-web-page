package com.dodo.api.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IUserService;
import com.dodo.api.dtos.UserDto;
import com.dodo.api.models.User;
import com.dodo.api.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	// ====LOC====
	@Override
	public List<UserDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<UserDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserDto findById(int id) {
		try {
			return modelMapper.map(repository.findById(id).get(), UserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(UserDto dto) {
		try {
			User model = modelMapper.map(dto, User.class);
			repository.save(model);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(repository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserDto findByUsername(String name) {
		try {
			return modelMapper.map(repository.findByUsername(name), UserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserDto findByEmail(String email) {
		try {
			return modelMapper.map(repository.findByEmail(email), UserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserDto> findByRoleUserAndShopowner() {
		try {
			return modelMapper.map(repository.findByRoleUserAndShopowner(), new TypeToken<List<UserDto>>() {}.getType());
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
