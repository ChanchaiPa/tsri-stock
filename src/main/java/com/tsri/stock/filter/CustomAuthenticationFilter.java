package com.tsri.stock.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tsri.stock.adapter.UsersAdapter;
import com.tsri.stock.controller.data.UsersData;
import com.tsri.stock.repository.entity.UsersEntity;
import com.tsri.stock.service.UsersService;
import com.tsri.utils.JWTUtils;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private UsersService usersService;		

	public CustomAuthenticationFilter() {
		this.setRequiresAuthenticationRequestMatcher( new AntPathRequestMatcher("/auth/signin", "POST") );
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try { Thread.sleep(2000); } catch(Exception e) {}
		String username="unknown", password="unknown";
		try {
			UsersData userRequest = new ObjectMapper().readValue(request.getInputStream(), UsersData.class);
			username = userRequest.getUsername();
			password = userRequest.getPassword();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}		
		
		System.out.println("Attemp Signin " + username + "----" + password);
		return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken( username, password, new ArrayList<GrantedAuthority>() ));
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("Signin Success");
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authResult.getPrincipal();
		UsersEntity userEntity = usersService.findByUsername(user.getUsername());
		String jwtToken = JWTUtils.genJWT(userEntity.getUserid(), userEntity.getUsername(), userEntity.getRole());	
		
		UsersData userData = UsersAdapter.toData(userEntity);
		userData.setToken(jwtToken);
		userData.setMoreinfo("Signin Success");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String userJson = ow.writeValueAsString(userData);		
		
		Cookie cookie = new Cookie("mysql-boot-token", jwtToken);
		cookie.setPath(request.getContextPath());
		cookie.setHttpOnly(true);
		cookie.setMaxAge(JWTUtils.jwt_age_minute*60); //Sets the maximum age in seconds for this Cookie
		
		response.addCookie(cookie);		
		response.addHeader("Content-Type", "application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Credentials", "true");		
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type,Accept,Authorization");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print( userJson );		
	}
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println("Signin Fail");
		UsersData userData = new UsersData();
		userData.setMoreinfo("Signin Fail");	
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String userJson = ow.writeValueAsString(userData);		
		
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type,Accept,Authorization");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().print( userJson );			
	}
}


