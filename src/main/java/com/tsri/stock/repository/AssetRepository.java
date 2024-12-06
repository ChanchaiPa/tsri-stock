package com.tsri.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tsri.stock.repository.entity.AssetEntity;


public interface AssetRepository extends JpaRepository<AssetEntity, Integer>, JpaSpecificationExecutor<AssetEntity> {
	//
}
