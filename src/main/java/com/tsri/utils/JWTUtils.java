package com.tsri.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	private static final String jwt_key = "_AbCdEf1234567890-";
	public  static final int    jwt_age_minute = 2*60; //minute
	//public  static final int    jwt_age_minute = 1; //minute

	public static String genJWT(String userid, String username, String roleid) {
		Claims claims = Jwts.claims().setSubject("jwt-token").setIssuer("zurich-claim").setAudience("all");
		claims.put("userid", userid);
		claims.put("username", username);
		claims.put("roleid", roleid);
		
		String token = Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + (jwt_age_minute*60*1000)))
		.signWith(SignatureAlgorithm.HS256, jwt_key).compact();		
		return token;
	}
	
	public static String[] validateJWT(String token) throws Exception {
		String profile[] = {"", "", ""};
		Header header = Jwts.parser().setSigningKey(jwt_key).parseClaimsJws(token).getHeader();
		Claims claims = Jwts.parser().setSigningKey(jwt_key).parseClaimsJws(token).getBody();
		//System.out.println(header.toString());
		//System.out.println(claims.toString());		
		
		String userid   = (String)claims.get("userid");		profile[0] = userid;
		String username = (String)claims.get("username");	profile[1] = username;
		String roleid   = (String)claims.get("roleid");		profile[2] = roleid;
		return profile;
	}
}
