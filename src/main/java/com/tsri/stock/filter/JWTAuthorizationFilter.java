package com.tsri.stock.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.tsri.utils.JWTUtils;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request.getMethod().equals("OPTIONS")) {
			SecurityContextHolder.getContext().setAuthentication( new UsernamePasswordAuthenticationToken("preflight", null, null) );	
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type,Accept,Authorization");	
			response.setHeader("Access-Control-Allow-Credentials", "true");	
		}
		else {
			String token = "";//request.getHeader("Authorization");
			try {
				javax.servlet.http.Cookie[] cookies = request.getCookies();
				for (javax.servlet.http.Cookie ck : cookies) {
					if (("mysql-boot-token").equals(ck.getName())) {
						token = ck.getValue();
						break;
					}
				}				
			}
			catch(Exception e) {}			
			if (token!=null && token.length()>0) {
				UsernamePasswordAuthenticationToken authentication = getAuthorization(request, token);
				SecurityContextHolder.getContext().setAuthentication(authentication);			
			}	
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");	
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type,Accept,Authorization");	
			response.setHeader("Access-Control-Allow-Credentials", "true");	
		}
		chain.doFilter(request, response);
	}

	
	private UsernamePasswordAuthenticationToken getAuthorization(HttpServletRequest request, String token) {		
		String userid="", username="", roleid="";
		ArrayList<GrantedAuthority> authorities = null;
		try {
			String[] profile = JWTUtils.validateJWT(token);
			userid   = profile[0];
			username = profile[1];
			roleid   = profile[2];		
			authorities = new ArrayList<>();	
			authorities.add(new SimpleGrantedAuthority(roleid));
			
			request.setAttribute("userid",   userid);
			request.setAttribute("username", username);
			request.setAttribute("roleid",   roleid);
		}
		catch(Exception e) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(username, null, authorities);
	}	
}

