package com.tsri.stock.service;

import java.util.List;

import com.tsri.stock.controller.data.SupplierData;
import com.tsri.stock.repository.entity.SupplierEntity;

public interface SupplierService {

	int count(String supName);
	List<SupplierEntity> search(int pageNo, int pageSize, String supName);
	
	SupplierEntity getById(int supId);
	SupplierEntity save(String userid, SupplierData supplierData);
	
}
