package com.tsri.stock.controller.data;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {

	@Getter @Setter
	private String type;
	
	@Getter @Setter
	private String code;
	
	@Getter @Setter
	private String name;

}
