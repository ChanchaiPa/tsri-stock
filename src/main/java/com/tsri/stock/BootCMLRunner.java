package com.tsri.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tsri.stock.repository.entity.UsersEntity;
import com.tsri.stock.service.UsersService;


@Component
public class BootCMLRunner implements CommandLineRunner {
	
	@Autowired
	private UsersService usersService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Initial SqliteBoot App");
		usersService.setPassword("chanchai.pa", "pa.chanchai");
	}
	
}
