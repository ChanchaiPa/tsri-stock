package com.tsri.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tsri.stock.repository.entity.CategoryEntity;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor<CategoryEntity> {
	//
}
