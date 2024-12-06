package com.tsri.stock.controller.data;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


public class CategoryData {
	private final String sizeMessage = "over-field-size";
	private final String notBlankMessage = "not-blank";
	private final String digitsMessage = "not-digit-format";		

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Digits(integer = 5, fraction = 0, message = digitsMessage)  //int(5)
	private String	cat_id;

	@Getter @Setter
	@Size(max = 10, message = sizeMessage + " {max}")  //varchar(10)
	private String	cat_code;

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Size(max = 150, message = sizeMessage + " {max}")  //varchar(150)
	private String	cat_name;

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Digits(integer = 5, fraction = 0, message = digitsMessage)  //int(5)
	private String	cat_grp_id;

	@Getter @Setter
	@Size(max = 10, message = sizeMessage + " {max}")  //varchar(10)
	private String	cat_regis_id;

	@Getter @Setter
	private String	cat_regis_dt;

	@Getter @Setter
	@Size(max = 1, message = sizeMessage + " {max}")  //char(1)
	private String	cat_status;
	
	@Getter @Setter
	private String	cat_grp_name;
}
