package com.tsri.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.tsri.stock.repository.entity.GroupEntity;


public interface GroupRepository extends JpaRepository<GroupEntity, Integer>, JpaSpecificationExecutor<GroupEntity> {
	List<GroupEntity> findByGrpStatus(String grpStatus);
}
