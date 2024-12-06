package com.tsri.stock.filter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tsri.stock.repository.entity.UsersEntity;
import com.tsri.stock.service.UsersService;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UsersService usersService;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersEntity user = usersService.findByUsername(username);
		if (user != null) {
			GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
			ArrayList<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(authority);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
		}
		throw new UsernameNotFoundException("Username "+username+" not found");
	}
	
	
	
}
