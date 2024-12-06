package com.tsri.stock;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tsri.stock.filter.CustomAuthenticationFilter;
import com.tsri.stock.filter.CustomUserDetailService;
import com.tsri.stock.filter.JWTAuthorizationFilter;


@EnableWebSecurity
public class BootSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().
		antMatchers("/auth/login", "/auth/logout", "/welcome.html", "/app/**", "/auth/signin").permitAll().
		//antMatchers("/auth/register").hasAnyAuthority("ADMIN").
		antMatchers("/", "/login",  "/index.html", "/favicon.ico", "/manifest.json", "/logo192.png", "/static/**").permitAll().
		anyRequest().authenticated().
		and().exceptionHandling().authenticationEntryPoint((req, res, err)->{ res.sendError(HttpServletResponse.SC_UNAUTHORIZED); }).
		and().addFilter(authenticationFilter()).sessionManagement().  //for demo
		and().addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}	
	
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(pwdEncoder);
	}	
	
	
	@Bean
	UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
		UsernamePasswordAuthenticationFilter filter = new CustomAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}		
}

