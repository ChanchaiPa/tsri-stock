package com.tsri.stock.controller.data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class SearchModel<T> {
	@Getter @Setter
	private long total_record;
	
	@Getter @Setter
	private long total_page;
	
	@Getter @Setter
	private List<T> list;
}
