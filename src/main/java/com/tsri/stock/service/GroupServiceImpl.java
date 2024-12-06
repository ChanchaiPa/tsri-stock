package com.tsri.stock.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tsri.stock.adapter.GroupAdapter;
import com.tsri.stock.controller.data.GroupData;
import com.tsri.stock.repository.GroupRepository;
import com.tsri.stock.repository.entity.GroupEntity;


@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupRepository groupRepo;

	
	@Override
	public int count(String grpName) {
		long count = groupRepo.count( new Specification<GroupEntity>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<GroupEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if(!grpName.equals(""))
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("grpName"), "%"+grpName+"%")));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		} );
		return (int)count;
	}

	
	@Override
	public List<GroupEntity> search(int pageNo, int pageSize, String grpName) {
		List<GroupEntity> list = groupRepo.findAll( new Specification<GroupEntity>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<GroupEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if(!grpName.equals(""))
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("grpName"), "%"+grpName+"%")));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		} //);
		, PageRequest.of(pageNo-1, pageSize, Sort.by("grpId")) ).toList();
		return list;
	}

	
	@Override
	public GroupEntity getById(int grpId) {
		return groupRepo.findById(grpId).orElse(null);
	}

	
	@Override
	public GroupEntity save(String userid, GroupData groupData) {
		GroupEntity groupEntity = GroupAdapter.toEntity(null, groupData);
		groupEntity.setGrpRegisId(userid);
		GroupEntity newEntity = groupRepo.save(groupEntity);
		
		groupList = groupRepo.findByGrpStatus("A");
		return newEntity;
	}
	
	
	private List<GroupEntity> groupList = null;
	// ===============================================
	@PostConstruct
	private void init() {
		groupList = groupRepo.findByGrpStatus("A");  //System.out.println(groupList.size());
	}
	public List<GroupEntity> getGroupDropList() {  //System.out.println(groupList.size());
		return groupList;
	}	
}
