package com.tsri.stock.adapter;

import com.tsri.stock.controller.data.AssetData;
import com.tsri.stock.repository.entity.AssetEntity;

public class AssetAdapter {

	public static AssetData toData(AssetEntity assetEntity) {
		AssetData assetData = new AssetData();
		
		assetData.setAst_id(  X.Integer2String(assetEntity.getAstId())  );
		assetData.setAst_code(  X.Null2String(assetEntity.getAstCode())  );
		assetData.setAst_name(  X.Null2String(assetEntity.getAstName())  );
		assetData.setAst_grp_id(  X.Integer2String(assetEntity.getAstGrpId())  );
		assetData.setAst_cat_id(  X.Integer2String(assetEntity.getAstCatId())  );
		assetData.setAst_min_purchase(  X.Double2StringI(assetEntity.getAstMinPurchase())  );
		assetData.setAst_unit(  X.Null2String(assetEntity.getAstUnit())  );
		assetData.setAst_regis_id(  X.Null2String(assetEntity.getAstRegisId())  );
		assetData.setAst_regis_dt(  X.DateTime2String(assetEntity.getAstRegisDt())  );
		assetData.setAst_status(  X.Null2String(assetEntity.getAstStatus())  );		
		
		if (assetEntity.getGroupObj() != null)
			assetData.setAst_grp_name( assetEntity.getGroupObj().getGrpName() );
		if (assetEntity.getCategoryObj() != null)
			assetData.setAst_cat_name( assetEntity.getCategoryObj().getCatName() );		
		return assetData;
	}
	
	
	public static AssetEntity toEntity(AssetEntity assetEntity, AssetData assetData) {
		if (assetEntity == null)
			assetEntity = new AssetEntity();
		
		assetEntity.setAstId(  X.String2Integer(assetData.getAst_id())  );
		assetEntity.setAstCode(  X.String2Null(assetData.getAst_code())  );
		assetEntity.setAstName(  X.String2Null(assetData.getAst_name())  );
		assetEntity.setAstGrpId(  X.String2Integer(assetData.getAst_grp_id())  );
		assetEntity.setAstCatId(  X.String2Integer(assetData.getAst_cat_id())  );
		assetEntity.setAstMinPurchase(  X.String2Double(assetData.getAst_min_purchase())  );
		assetEntity.setAstUnit(  X.String2Null(assetData.getAst_unit())  );
		assetEntity.setAstRegisId(  X.String2Null(assetData.getAst_regis_id())  );
		//assetEntity.setAstRegisDt(  X.String2DateTime(assetData.getAst_regis_dt())  );
		assetEntity.setAstStatus(  X.String2Null(assetData.getAst_status())  );
		
		if (assetEntity.getAstStatus()==null || assetEntity.getAstStatus().equals("")) 
			assetEntity.setAstStatus("A");		
		return assetEntity;
	}

}
