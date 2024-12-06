package com.tsri.stock.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsri.stock.adapter.CategoryAdapter;
import com.tsri.stock.controller.data.BaseModel;
import com.tsri.stock.controller.data.CategoryData;
import com.tsri.stock.controller.data.SearchModel;
import com.tsri.stock.exception.DataNotFoundException;
import com.tsri.stock.exception.ReqValidateException;
import com.tsri.stock.repository.entity.CategoryEntity;
import com.tsri.stock.service.CategoryService;


@RestController
public class CategoryController {

	@Autowired private CategoryService categoryService;
	@Autowired private HttpServletRequest request;
	
	
	@GetMapping("/service/category/search")
	public SearchModel<CategoryData> search(		
		@RequestParam(name="pageNo", 	defaultValue="1") int pageNo,
		@RequestParam(name="pageSize", 	defaultValue="5") int pageSize,
		@RequestParam(name="totalRec", 	defaultValue="0") int totalRec,
		@RequestParam(name="cat_grp_id",defaultValue="0") int cat_grp_id,
		@RequestParam(name="cat_name", 	defaultValue="")  String cat_name) {  //System.out.println(cat_grp_id); System.out.println(cat_name);
		int total_record = totalRec;
		if (total_record == 0)
			total_record = categoryService.count(cat_grp_id, cat_name);
		long total_page  = (long) Math.ceil(total_record / (double) pageSize);
		List<CategoryEntity> list1 = categoryService.search(pageNo, pageSize, cat_grp_id, cat_name);		
		
		ArrayList<CategoryData> list2 = new ArrayList<CategoryData>();
		for (CategoryEntity x: list1) {
			list2.add( CategoryAdapter.toData(x) );
		}
		
		SearchModel<CategoryData> result = new SearchModel<>();
		result.setTotal_record(total_record);
		result.setTotal_page(total_page);
		result.setList(list2);
		return result;
	}	
	
	
	@GetMapping("/service/category/{id}") 
	public CategoryData getById(@PathVariable int id) {
		CategoryEntity categoryEntity = categoryService.getById(id);
		if (categoryEntity != null) 
			return CategoryAdapter.toData(categoryEntity);
		else 
			throw new DataNotFoundException("Not found id " + id);
	}	
	
	
	@GetMapping("/service/category/droplist")
	public List<BaseModel> getAll(@RequestParam(name="cat_grp_id", defaultValue="0") int cat_grp_id) {
		List<CategoryEntity> list1 = categoryService.getCategoryDropList(cat_grp_id);
		ArrayList<BaseModel> list2 = new ArrayList<BaseModel>();
		for (CategoryEntity x: list1) {
			list2.add( new BaseModel("group", x.getCatId().toString(), x.getCatName() ) );
		}		
		return list2;
	}	
	
	
	@PostMapping("/service/category/save")
	public CategoryData save(@Valid @RequestBody CategoryData categoryData, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ReqValidateException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
			});
		}
		
		String userid = (String)request.getAttribute("userid");
		CategoryEntity groupEntity = categoryService.save(userid, categoryData);
		return CategoryAdapter.toData(groupEntity);
	}		

}






