package com.tsri.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tsri.stock.repository.entity.SupplierEntity;


public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer>, JpaSpecificationExecutor<SupplierEntity> {
	//List<SupplierEntity> findBySupNameContaining(String supName);
}
