package com.tsri.stock.controller.data;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tsri.stock.validation.DigitsCustom;

import lombok.Getter;
import lombok.Setter;


public class AssetData {
	private final String sizeMessage = "over-field-size";
	private final String notBlankMessage = "not-blank";
	private final String digitsMessage = "not-digit-format";

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Digits(integer = 5, fraction = 0, message = digitsMessage)  //int(5)
	private String	ast_id;

	@Getter @Setter
	@Size(max = 10, message = sizeMessage + " {max}")  //varchar(10)
	private String	ast_code;

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Size(max = 150, message = sizeMessage + " {max}")  //varchar(150)
	private String	ast_name;

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Digits(integer = 5, fraction = 0, message = digitsMessage)  //int(5)
	private String	ast_grp_id;

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Digits(integer = 5, fraction = 0, message = digitsMessage)  //int(5)
	private String	ast_cat_id;

	@Getter @Setter
	@DigitsCustom(integer = 5, fraction = 0, message = digitsMessage)  //double(5,0)
	private String	ast_min_purchase;

	@Getter @Setter
	@Size(max = 20, message = sizeMessage + " {max}")  //varchar(20)
	private String	ast_unit;

	@Getter @Setter
	@Size(max = 10, message = sizeMessage + " {max}")  //varchar(10)
	private String	ast_regis_id;

	@Getter @Setter
	private String	ast_regis_dt;

	@Getter @Setter
	@Size(max = 1, message = sizeMessage + " {max}")  //char(1)
	private String	ast_status;

	//*************************
	@Getter @Setter
	private String	ast_grp_name;
	
	@Getter @Setter
	private String	ast_cat_name;
}
