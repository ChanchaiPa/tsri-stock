package com.tsri.stock.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tsri.stock.repository.entity.UsersEntity;


public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
	Optional<UsersEntity> findByUsername(String username);  //chanchai.pa	
}
