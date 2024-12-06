package com.tsri.stock.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tsri.stock.adapter.CategoryAdapter;
import com.tsri.stock.controller.data.CategoryData;
import com.tsri.stock.repository.CategoryRepository;
import com.tsri.stock.repository.entity.CategoryEntity;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	
	@Override
	public int count(int catGrpId, String catName) {
		long count = categoryRepo.count( new Specification<CategoryEntity>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<CategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (catGrpId != 0)
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("catGrpId"), catGrpId)));	
				if(!catName.equals(""))
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("catName"), "%"+catName+"%")));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		} );
		return (int)count;
	}

	
	@Override
	public List<CategoryEntity> search(int pageNo, int pageSize, int catGrpId, String catName) {
		List<CategoryEntity> list = categoryRepo.findAll( new Specification<CategoryEntity>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<CategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (catGrpId != 0)
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("catGrpId"), catGrpId)));					
				if(!catName.equals(""))
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("catName"), "%"+catName+"%")));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		} //);
		, PageRequest.of(pageNo-1, pageSize, Sort.by("catId")) ).toList();
		return list;
	}

	
	@Override
	public CategoryEntity getById(int catId) {
		return categoryRepo.findById(catId).orElse(null);
	}

	
	@Override
	public CategoryEntity save(String userid, CategoryData categoryData) {
		CategoryEntity categoryEntity = CategoryAdapter.toEntity(null, categoryData);
		categoryEntity.setCatRegisId(userid);
		return categoryRepo.save(categoryEntity);
	}

	
	@Autowired
	private JdbcTemplate jdbcTemplate;	
	// ===============================================
	@Override
	public List<CategoryEntity> getCategoryDropList(int catGrpId) {
		String cmd = "select * from thaisri_db.stock3_category where cat_status='A' and cat_grp_id='"+catGrpId+"'";
		return jdbcTemplate.query(cmd, new ResultSetExtractor<List<CategoryEntity>>() {
			ArrayList<CategoryEntity> tmpList = null;
			
			@Override
			public List<CategoryEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
				tmpList = new ArrayList<>();
				while (rs.next()) {
					CategoryEntity tmp = new CategoryEntity();
					tmp.setCatId(rs.getInt("cat_id"));
					tmp.setCatCode(rs.getString("cat_code"));
					tmp.setCatName(rs.getString("cat_name"));
					tmpList.add(tmp);
				}
				return tmpList;
			}
		});
	}

}
