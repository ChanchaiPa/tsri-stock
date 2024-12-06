package com.tsri.stock.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsri.stock.adapter.UsersAdapter;
import com.tsri.stock.controller.data.UsersData;
import com.tsri.stock.exception.DataNotFoundException;
import com.tsri.stock.exception.ReqValidateException;
import com.tsri.stock.repository.entity.UsersEntity;
import com.tsri.stock.service.UsersService;
import com.tsri.utils.ActiveDirectoryAuthentication;
import com.tsri.utils.JWTUtils;

@RestController
public class AuthenController {
	
	@Autowired
	private UsersService usersService;
	
	
	@PostMapping("/auth/login")
	public UsersData login(HttpServletRequest request, HttpServletResponse response, 
			@Valid @RequestBody UsersData usersData, BindingResult bindingResult) {
		System.out.println(usersData.getUsername() + " LOGIN...."); 
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ReqValidateException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
			});
		}		

		UsersData userData = new UsersData();
		try {
			ActiveDirectoryAuthentication authentication = new ActiveDirectoryAuthentication();
			authentication.setActiveDirectoryAuthentication("tsri.com");
			boolean authResult = authentication.authenticate(usersData.getUsername(), usersData.getPassword());
			if (authResult) {
				UsersEntity userEntity = usersService.findByUsername(usersData.getUsername());
				String jwtToken = JWTUtils.genJWT(userEntity.getUserid(), userEntity.getUsername(), userEntity.getRole());					
				
				userData = UsersAdapter.toData(userEntity);
				userData.setToken(jwtToken);
				userData.setMoreinfo("Signin Success");				
				
				Cookie cookie = new Cookie("mysql-boot-token", jwtToken);
				cookie.setPath(request.getContextPath());
				cookie.setHttpOnly(true);
				cookie.setMaxAge(JWTUtils.jwt_age_minute*60); //Sets the maximum age in seconds for this Cookie
				response.addCookie(cookie);
				response.setStatus(HttpServletResponse.SC_OK);	
			}
			else {				
				userData.setMoreinfo("Login Fail");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);				
			}			
		}
		catch(Exception e) {
			userData.setMoreinfo(e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return userData;		
	}
	
	
	@PostMapping("/auth/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response,
			@RequestBody UsersData userData) {
		try { Thread.sleep(1000); } catch(Exception e) {}
		System.out.println(userData.getUsername() + " LOGOUT...."); 
		
		Cookie cookie = new Cookie("mysql-boot-token", null);
		cookie.setPath(request.getContextPath());
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0); 
		response.addCookie(cookie);
		response.setStatus(HttpServletResponse.SC_OK);	
		return "Logout Success";
	}	
	
	
	@GetMapping("/service/users/{id}")  // for test
	public UsersData getUsers(@PathVariable int id) {
		UsersEntity userEntity = usersService.findById(id);
		if (userEntity == null)
			throw new DataNotFoundException("Not found Id " + id);
		return UsersAdapter.toData(userEntity);
	}	
}
