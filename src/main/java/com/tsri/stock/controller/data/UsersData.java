package com.tsri.stock.controller.data;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class UsersData {
	private final String sizeMessage = "over-field-size";
	private final String notBlankMessage = "not-blank";
	private final String digitsMessage = "not-digit-format";
	
	@Getter @Setter
	private String id;	
	
	@Getter @Setter
	private String userid;		
	
	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	private String username;
	
	@Getter
	@NotBlank(message = notBlankMessage)
	private String password;
	
	@Getter @Setter
	private String role;
	
	@Getter @Setter
	private String firstname;	
	
	@Getter @Setter
	private String lastname;		
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String createDate;
	
	
	//*********************
	@Getter @Setter
	private String token;
	
	@Getter @Setter
	private String moreinfo;	
}
