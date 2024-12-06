package com.tsri.stock.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tsri.stock.repository.UsersRepository;
import com.tsri.stock.repository.entity.UsersEntity;


@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository usersRepo;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	
	@Override
	public UsersEntity findById(Integer id) {
		return usersRepo.findById(id).orElse(null);
	}
	
	@Override
	public UsersEntity findByUsername(String username) {
		return usersRepo.findByUsername(username).orElse(null);
	}

	@Override
	public void setPassword(String username, String password) {
		UsersEntity usersEntity = usersRepo.findByUsername(username).orElse(null);
		if (usersEntity != null) {
			usersEntity.setPassword( pwdEncoder.encode(password) );
			usersRepo.save(usersEntity);
		}
	}
}
