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

import com.tsri.stock.adapter.GroupAdapter;
import com.tsri.stock.controller.data.BaseModel;
import com.tsri.stock.controller.data.GroupData;
import com.tsri.stock.controller.data.SearchModel;
import com.tsri.stock.exception.DataNotFoundException;
import com.tsri.stock.exception.ReqValidateException;
import com.tsri.stock.repository.entity.GroupEntity;
import com.tsri.stock.service.GroupService;


@RestController
public class GroupController {

	@Autowired private GroupService groupService;
	@Autowired private HttpServletRequest request;
	
	
	@GetMapping("/service/group/search")
	public SearchModel<GroupData> search(		
		@RequestParam(name="pageNo", 	defaultValue="1") int pageNo,
		@RequestParam(name="pageSize", 	defaultValue="5") int pageSize,
		@RequestParam(name="totalRec", 	defaultValue="0") int totalRec,
		@RequestParam(name="grp_name", 	defaultValue="")  String grp_name) {  
		int total_record = totalRec;
		if (total_record == 0)
			total_record = groupService.count(grp_name);
		long total_page  = (long) Math.ceil(total_record / (double) pageSize);
		List<GroupEntity> list1 = groupService.search(pageNo, pageSize, grp_name);		
		
		ArrayList<GroupData> list2 = new ArrayList<GroupData>();
		for (GroupEntity x: list1) {
			list2.add( GroupAdapter.toData(x) );
		}
		
		SearchModel<GroupData> result = new SearchModel<>();
		result.setTotal_record(total_record);
		result.setTotal_page(total_page);
		result.setList(list2);
		return result;
	}	
	
	
	@GetMapping("/service/group/{id}") 
	public GroupData getById(@PathVariable int id) {
		GroupEntity groupEntity = groupService.getById(id);
		if (groupEntity != null) 
			return GroupAdapter.toData(groupEntity);
		else 
			throw new DataNotFoundException("Not found id " + id);
	}
	
	
	@GetMapping("/service/group/droplist")
	public List<BaseModel> getAll() {
		List<GroupEntity> list1 = groupService.getGroupDropList();
		ArrayList<BaseModel> list2 = new ArrayList<BaseModel>();
		for (GroupEntity x: list1) {
			list2.add( new BaseModel("group", x.getGrpId().toString(), x.getGrpName() ) );
		}		
		return list2;
	}
	
	
	@PostMapping("/service/group/save")
	public GroupData save(@Valid @RequestBody GroupData groupData, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ReqValidateException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
			});
		}
		
		String userid = (String)request.getAttribute("userid");
		GroupEntity groupEntity = groupService.save(userid, groupData);
		return GroupAdapter.toData(groupEntity);
	}	
	
}




