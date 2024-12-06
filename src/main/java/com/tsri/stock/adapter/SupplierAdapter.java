package com.tsri.stock.adapter;

import com.tsri.stock.controller.data.SupplierData;
import com.tsri.stock.repository.entity.SupplierEntity;

public class SupplierAdapter {

	public static SupplierData toData(SupplierEntity supplierEntity) {
		SupplierData supplierData = new SupplierData();
		
		supplierData.setSup_id(  X.Integer2String(supplierEntity.getSupId())  );
		supplierData.setSup_code(  X.Null2String(supplierEntity.getSupCode())  );
		supplierData.setSup_name(  X.Null2String(supplierEntity.getSupName())  );
		supplierData.setSup_addr1(  X.Null2String(supplierEntity.getSupAddr1())  );
		supplierData.setSup_addr2(  X.Null2String(supplierEntity.getSupAddr2())  );
		supplierData.setSup_addr3(  X.Null2String(supplierEntity.getSupAddr3())  );
		supplierData.setSup_email(  X.Null2String(supplierEntity.getSupEmail())  );
		supplierData.setSup_tel(  X.Null2String(supplierEntity.getSupTel())  );
		supplierData.setSup_fax(  X.Null2String(supplierEntity.getSupFax())  );
		supplierData.setSup_contact_name(  X.Null2String(supplierEntity.getSupContactName())  );
		supplierData.setSup_regis_id(  X.Null2String(supplierEntity.getSupRegisId())  );
		supplierData.setSup_regis_dt(  X.DateTime2String(supplierEntity.getSupRegisDt())  );
		supplierData.setSup_status(  X.Null2String(supplierEntity.getSupStatus())  );
		
		return supplierData;
	}

	
	public static SupplierEntity toEntity(SupplierEntity supplierEntity, SupplierData supplierData) {
		if (supplierEntity == null)
			supplierEntity =  new SupplierEntity();
		
		supplierEntity.setSupId(  X.String2Integer(supplierData.getSup_id())  );
		supplierEntity.setSupCode(  X.String2Null(supplierData.getSup_code())  );
		supplierEntity.setSupName(  X.String2Null(supplierData.getSup_name())  );
		supplierEntity.setSupAddr1(  X.String2Null(supplierData.getSup_addr1())  );
		supplierEntity.setSupAddr2(  X.String2Null(supplierData.getSup_addr2())  );
		supplierEntity.setSupAddr3(  X.String2Null(supplierData.getSup_addr3())  );
		supplierEntity.setSupEmail(  X.String2Null(supplierData.getSup_email())  );
		supplierEntity.setSupTel(  X.String2Null(supplierData.getSup_tel())  );
		supplierEntity.setSupFax(  X.String2Null(supplierData.getSup_fax())  );
		supplierEntity.setSupContactName(  X.String2Null(supplierData.getSup_contact_name())  );
		supplierEntity.setSupRegisId(  X.String2Null(supplierData.getSup_regis_id())  );
		//supplierEntity.setSupRegisDt(  X.String2DateTime(supplierData.getSup_regis_dt())  );
		supplierEntity.setSupStatus(  X.String2Null(supplierData.getSup_status())  );
		
		if (supplierEntity.getSupStatus()==null || supplierEntity.getSupStatus().equals(""))
			supplierEntity.setSupStatus("A");
		return supplierEntity;
	}
	
}
