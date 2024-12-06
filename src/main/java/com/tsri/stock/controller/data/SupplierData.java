package com.tsri.stock.controller.data;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


public class SupplierData {
	private final String sizeMessage = "over-field-size";
	private final String notBlankMessage = "not-blank";
	private final String digitsMessage = "not-digit-format";

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Digits(integer = 5, fraction = 0, message = digitsMessage)  //int(5)
	private String	sup_id;

	@Getter @Setter
	@Size(max = 10, message = sizeMessage + " {max}")  //varchar(10)
	private String	sup_code;

	@Getter @Setter
	@NotBlank(message = notBlankMessage)
	@Size(max = 150, message = sizeMessage + " {max}")  //varchar(150)
	private String	sup_name;

	@Getter @Setter
	@Size(max = 300, message = sizeMessage + " {max}")  //varchar(300)
	private String	sup_addr1;

	@Getter @Setter
	@Size(max = 300, message = sizeMessage + " {max}")  //varchar(300)
	private String	sup_addr2;

	@Getter @Setter
	@Size(max = 300, message = sizeMessage + " {max}")  //varchar(300)
	private String	sup_addr3;

	@Getter @Setter
	@Size(max = 30, message = sizeMessage + " {max}")  //varchar(30)
	private String	sup_email;

	@Getter @Setter
	@Size(max = 30, message = sizeMessage + " {max}")  //varchar(30)
	private String	sup_tel;

	@Getter @Setter
	@Size(max = 30, message = sizeMessage + " {max}")  //varchar(30)
	private String	sup_fax;

	@Getter @Setter
	@Size(max = 50, message = sizeMessage + " {max}")  //varchar(50)
	private String	sup_contact_name;

	@Getter @Setter
	@Size(max = 10, message = sizeMessage + " {max}")  //varchar(10)
	private String	sup_regis_id;

	@Getter @Setter
	private String	sup_regis_dt;

	@Getter @Setter
	@Size(max = 1, message = sizeMessage + " {max}")  //char(1)
	private String	sup_status;
}
