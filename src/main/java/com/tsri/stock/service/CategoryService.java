package com.tsri.stock.service;

import java.util.List;

import com.tsri.stock.controller.data.CategoryData;
import com.tsri.stock.repository.entity.CategoryEntity;

public interface CategoryService {

	int count(int catGrpId, String catName);
	List<CategoryEntity> search(int pageNo, int pageSize, int catGrpId, String catName);
	
	CategoryEntity getById(int catId);
	CategoryEntity save(String userid, CategoryData categoryData);

	List<CategoryEntity> getCategoryDropList(int catGrpId);
}
