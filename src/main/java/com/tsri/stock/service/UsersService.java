package com.tsri.stock.service;

import com.tsri.stock.repository.entity.UsersEntity;

public interface UsersService {

	UsersEntity findById(Integer id);
	UsersEntity findByUsername(String username);
	void setPassword(String username, String password);
	
}
