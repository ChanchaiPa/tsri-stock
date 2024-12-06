package com.tsri.stock.service;

import java.util.List;

import com.tsri.stock.controller.data.AssetData;
import com.tsri.stock.repository.entity.AssetEntity;


public interface AssetService {

	int count(int astGrpId, int astCatId, String astName);
	List<AssetEntity> search(int pageNo, int pageSize, int astGrpId, int astCatId, String astName);
	
	AssetEntity getById(int astId);
	AssetEntity save(String userid, AssetData assetData);	
	
}
