package com.tsri.stock.service;

import java.util.List;

import com.tsri.stock.controller.data.GroupData;
import com.tsri.stock.repository.entity.GroupEntity;

public interface GroupService {
	
	int count(String grpName);
	List<GroupEntity> search(int pageNo, int pageSize, String grpName);
	
	GroupEntity getById(int grpId);
	GroupEntity save(String userid, GroupData groupData);

	List<GroupEntity> getGroupDropList();
}
