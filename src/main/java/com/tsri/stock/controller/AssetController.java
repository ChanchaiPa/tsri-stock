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

import com.tsri.stock.adapter.AssetAdapter;
import com.tsri.stock.controller.data.AssetData;
import com.tsri.stock.controller.data.SearchModel;
import com.tsri.stock.exception.DataNotFoundException;
import com.tsri.stock.exception.ReqValidateException;
import com.tsri.stock.repository.entity.AssetEntity;
import com.tsri.stock.service.AssetService;


@RestController
public class AssetController {

	@Autowired private AssetService assetService;
	@Autowired private HttpServletRequest request;
	
	
	@GetMapping("/service/asset/search")
	public SearchModel<AssetData> search(		
		@RequestParam(name="pageNo", 	defaultValue="1") int pageNo,
		@RequestParam(name="pageSize", 	defaultValue="5") int pageSize,
		@RequestParam(name="totalRec", 	defaultValue="0") int totalRec,
		@RequestParam(name="ast_grp_id",defaultValue="0") int ast_grp_id,
		@RequestParam(name="ast_cat_id",defaultValue="0") int ast_cat_id,
		@RequestParam(name="ast_name", 	defaultValue="")  String ast_name) {  //System.out.println(cat_grp_id); System.out.println(cat_name);
		int total_record = totalRec;
		if (total_record == 0)
			total_record = assetService.count(ast_grp_id, ast_cat_id, ast_name);
		long total_page  = (long) Math.ceil(total_record / (double) pageSize);
		List<AssetEntity> list1 = assetService.search(pageNo, pageSize, ast_grp_id, ast_cat_id, ast_name);		
		
		ArrayList<AssetData> list2 = new ArrayList<AssetData>();
		for (AssetEntity x: list1) {
			list2.add( AssetAdapter.toData(x) );
		}
		
		SearchModel<AssetData> result = new SearchModel<>();
		result.setTotal_record(total_record);
		result.setTotal_page(total_page);
		result.setList(list2);
		return result;
	}	
	
	
	@GetMapping("/service/asset/{id}") 
	public AssetData getById(@PathVariable int id) {
		AssetEntity assetEntity = assetService.getById(id);
		if (assetEntity != null) 
			return AssetAdapter.toData(assetEntity);
		else 
			throw new DataNotFoundException("Not found id " + id);
	}		

	
	@PostMapping("/service/asset/save")
	public AssetData save(@Valid @RequestBody AssetData categoryData, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ReqValidateException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
			});
		}
		
		String userid = (String)request.getAttribute("userid");
		AssetEntity groupEntity = assetService.save(userid, categoryData);
		return AssetAdapter.toData(groupEntity);
	}		
	
}
