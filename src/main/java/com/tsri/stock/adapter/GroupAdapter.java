package com.tsri.stock.adapter;

import com.tsri.stock.controller.data.GroupData;
import com.tsri.stock.repository.entity.GroupEntity;

public class GroupAdapter {

	public static GroupData toData(GroupEntity groupEntity) {
		GroupData groupData = new GroupData();
		
		groupData.setGrp_id(  X.Integer2String(groupEntity.getGrpId())  );
		groupData.setGrp_code(  X.Null2String(groupEntity.getGrpCode())  );
		groupData.setGrp_name(  X.Null2String(groupEntity.getGrpName())  );
		groupData.setGrp_regis_id(  X.Null2String(groupEntity.getGrpRegisId())  );
		groupData.setGrp_regis_dt(  X.DateTime2String(groupEntity.getGrpRegisDt())  );
		groupData.setGrp_status(  X.Null2String(groupEntity.getGrpStatus())  );
		
		return groupData;
	}
	
	public static GroupEntity toEntity(GroupEntity groupEntity, GroupData groupData) {
		if (groupEntity == null)
			groupEntity = new GroupEntity();
		
		groupEntity.setGrpId(  X.String2Integer(groupData.getGrp_id())  );
		groupEntity.setGrpCode(  X.String2Null(groupData.getGrp_code())  );
		groupEntity.setGrpName(  X.String2Null(groupData.getGrp_name())  );
		groupEntity.setGrpRegisId(  X.String2Null(groupData.getGrp_regis_id())  );
		//groupEntity.setGrpRegisDt(  X.String2DateTime(groupData.getGrp_regis_dt())  );
		groupEntity.setGrpStatus(  X.String2Null(groupData.getGrp_status())  );		
		
		if (groupEntity.getGrpStatus()==null || groupEntity.getGrpStatus().equals(""))
			groupEntity.setGrpStatus("A");
		return groupEntity;
	}
	
}
