package com.tsri.stock.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsri.stock.adapter.SupplierAdapter;
import com.tsri.stock.controller.data.SearchModel;
import com.tsri.stock.controller.data.SupplierData;
import com.tsri.stock.exception.DataNotFoundException;
import com.tsri.stock.exception.ReqValidateException;
import com.tsri.stock.repository.entity.SupplierEntity;
import com.tsri.stock.service.SupplierService;

@RestController
public class SupplierController {

	@Autowired private SupplierService supplierService;
	@Autowired private HttpServletRequest request;	
	
	
	@GetMapping("/service/supplier/search")
	public SearchModel<SupplierData> search(		
		@RequestParam(name="pageNo", 	defaultValue="1") int pageNo,
		@RequestParam(name="pageSize", 	defaultValue="5") int pageSize,
		@RequestParam(name="totalRec", 	defaultValue="0") int totalRec,
		@RequestParam(name="sup_name", 	defaultValue="")  String sup_name) { System.out.println("SEARCH " + totalRec);
		int total_record = totalRec;
		if (total_record == 0)
			total_record = supplierService.count(sup_name);
		long total_page  = (long) Math.ceil(total_record / (double) pageSize);
		List<SupplierEntity> list1 = supplierService.search(pageNo, pageSize, sup_name);		
		
		ArrayList<SupplierData> list2 = new ArrayList<SupplierData>();
		for (SupplierEntity x: list1) {
			list2.add( SupplierAdapter.toData(x) );
		}
		
		SearchModel<SupplierData> result = new SearchModel<>();
		result.setTotal_record(total_record);
		result.setTotal_page(total_page);
		result.setList(list2);
		return result;
	}	
	
	
	@GetMapping("/service/supplier/{id}") 
	public SupplierData getById(@PathVariable int id) {
		SupplierEntity supplierEntity = supplierService.getById(id);
		if (supplierEntity != null) 
			return SupplierAdapter.toData(supplierEntity);
		else 
			throw new DataNotFoundException("Not found id " + id);
	}
	
	
	@PostMapping("/service/supplier/save")
	public SupplierData save(@Valid @RequestBody SupplierData supplierData, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ReqValidateException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
			});
		}
		
		String userid = (String)request.getAttribute("userid");
		SupplierEntity supplierEntity = supplierService.save(userid, supplierData);
		return SupplierAdapter.toData(supplierEntity);
	}
}
