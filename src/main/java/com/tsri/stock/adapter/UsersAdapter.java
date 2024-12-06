package com.tsri.stock.adapter;

import com.tsri.stock.controller.data.UsersData;
import com.tsri.stock.repository.entity.UsersEntity;

public class UsersAdapter {

	public static UsersData toData(UsersEntity userEntity) {
		UsersData userData = new UsersData();
		userData.setId( X.Integer2String(userEntity.getId()) );
		userData.setUserid( X.Null2String(userEntity.getUserid()) );
		userData.setUsername( X.Null2String(userEntity.getUsername()) );
		userData.setFirstname( X.Null2String(userEntity.getFirstname()) );
		userData.setLastname( X.Null2String(userEntity.getLastname()) );
		userData.setRole( X.Null2String(userEntity.getRole()) );
		userData.setEmail( X.Null2String(userEntity.getEmail()) );
		userData.setCreateDate( X.DateTime2String(userEntity.getCreateDate()) );
		return userData;
	}
	
	/*public static UsersEntity toEntity(UsersEntity userEntity, UsersData userData) {
		if (userEntity == null)
			userEntity =  new UsersEntity();
		userEntity.setUsername( X.String2Null(userData.getUsername()) );
		userEntity.setPassword( X.String2Null(userData.getPassword()) );
		userEntity.setRole( X.String2Null(userData.getRole()) );
		userEntity.setFullname( X.String2Null(userData.getFullname()) );
		userEntity.setEmail( X.String2Null(userData.getEmail()) );
		return userEntity;
	}*/
}
