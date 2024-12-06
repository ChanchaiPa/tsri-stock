package com.tsri.stock.adapter;

import com.tsri.stock.controller.data.CategoryData;
import com.tsri.stock.repository.entity.CategoryEntity;

public class CategoryAdapter {

	public static CategoryData toData(CategoryEntity categoryEntity) {
		CategoryData categoryData = new CategoryData();
		
		categoryData.setCat_id(  X.Integer2String(categoryEntity.getCatId())  );
		categoryData.setCat_code(  X.Null2String(categoryEntity.getCatCode())  );
		categoryData.setCat_name(  X.Null2String(categoryEntity.getCatName())  );
		categoryData.setCat_grp_id(  X.Integer2String(categoryEntity.getCatGrpId())  );
		categoryData.setCat_regis_id(  X.Null2String(categoryEntity.getCatRegisId())  );
		categoryData.setCat_regis_dt(  X.DateTime2String(categoryEntity.getCatRegisDt())  );
		categoryData.setCat_status(  X.Null2String(categoryEntity.getCatStatus())  );		
		
		if (categoryEntity.getGroupObj() != null)
			categoryData.setCat_grp_name( categoryEntity.getGroupObj().getGrpName() );
		return categoryData;
	}
	
	public static CategoryEntity toEntity(CategoryEntity categoryEntity, CategoryData categoryData) {
		if (categoryEntity == null)
			categoryEntity = new CategoryEntity();
		
		categoryEntity.setCatId(  X.String2Integer(categoryData.getCat_id())  );
		categoryEntity.setCatCode(  X.String2Null(categoryData.getCat_code())  );
		categoryEntity.setCatName(  X.String2Null(categoryData.getCat_name())  );
		categoryEntity.setCatGrpId(  X.String2Integer(categoryData.getCat_grp_id())  );
		categoryEntity.setCatRegisId(  X.String2Null(categoryData.getCat_regis_id())  );
		//categoryEntity.setCat_regis_dt(  X.String2DateTime(categoryData.getCat_regis_dt())  );
		categoryEntity.setCatStatus(  X.String2Null(categoryData.getCat_status())  );
		
		if (categoryEntity.getCatStatus()==null || categoryEntity.getCatStatus().equals("")) 
			categoryEntity.setCatStatus("A");
		return categoryEntity;
	}
	

}
