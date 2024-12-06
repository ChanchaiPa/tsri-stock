package com.tsri.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsri.stock.adapter.AssetAdapter;
import com.tsri.stock.controller.data.AssetData;
import com.tsri.stock.repository.AssetRepository;
import com.tsri.stock.repository.entity.AssetEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


@Service
public class AssetServiceImpl implements AssetService {
	
	@Autowired
	private AssetRepository assetRepo;

	
	@Override
	public int count(int astGrpId, int astCatId, String astName) {
		long count = assetRepo.count( new Specification<AssetEntity>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<AssetEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (astGrpId != 0)
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("astGrpId"), astGrpId)));	
				if (astCatId != 0)
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("astCatId"), astCatId)));					
				if(!astName.equals(""))
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("astName"), "%"+astName+"%")));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		} );
		return (int)count;
	}

	
	@Override
	public List<AssetEntity> search(int pageNo, int pageSize, int astGrpId, int astCatId, String astName) {
		List<AssetEntity> list = assetRepo.findAll( new Specification<AssetEntity>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<AssetEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (astGrpId != 0)
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("astGrpId"), astGrpId)));	
				if (astCatId != 0)
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("astCatId"), astCatId)));					
				if(!astName.equals(""))
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("astName"), "%"+astName+"%")));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		} //);
		, PageRequest.of(pageNo-1, pageSize, Sort.by("astId")) ).toList();
		return list;
	}

	
	@Override
	public AssetEntity getById(int astId) {
		return assetRepo.findById(astId).orElse(null);
	}

	@Override
	public AssetEntity save(String userid, AssetData assetData) {
		AssetEntity assetEntity = AssetAdapter.toEntity(null, assetData);
		assetEntity.setAstRegisId(userid);
		return assetRepo.save(assetEntity);
	}

}
