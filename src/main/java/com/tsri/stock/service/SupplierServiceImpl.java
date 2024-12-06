package com.tsri.stock.service;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tsri.stock.adapter.SupplierAdapter;
import com.tsri.stock.controller.data.SupplierData;
import com.tsri.stock.repository.SupplierRepository;
import com.tsri.stock.repository.entity.SupplierEntity;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepo;

	
	@Override
	public int count(String supName) {
		long count = supplierRepo.count( new Specification<SupplierEntity>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<SupplierEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if(!supName.equals(""))
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("supName"), "%"+supName+"%")));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		} );
		return (int)count;
	}

	
	@Override
	public List<SupplierEntity> search(int pageNo, int pageSize, String supName) {
		List<SupplierEntity> list = supplierRepo.findAll( new Specification<SupplierEntity>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<SupplierEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if(!supName.equals(""))
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("supName"), "%"+supName+"%")));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		} //);
		, PageRequest.of(pageNo-1, pageSize, Sort.by("supId")) ).toList();
		return list;
	}

	
	@Override
	public SupplierEntity save(String userid, SupplierData supplierData) {
		SupplierEntity supplierEntity = SupplierAdapter.toEntity(null, supplierData);
		supplierEntity.setSupRegisId(userid);
		return supplierRepo.save(supplierEntity);
	}


	@Override
	public SupplierEntity getById(int supId) {
		return supplierRepo.findById(supId).orElse(null);
	}

}
