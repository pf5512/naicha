package com.sihu.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.envers.tools.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sihu.app.mode.Relationship;
import com.sihu.app.service.RelationshipService;
import com.sihu.utils.Codes;

@Controller
@RequestMapping("/Relationship")
public class RelationshipController {

	@Autowired
	private RelationshipService relationshipService;

	/**
	 * 添加好友
	 * user1申請加user2 为好友,user1申请人自己
	 * @author yangxujia
	 * @date 2015-1-27下午10:18:07
	 */
	@RequestMapping("save.do")
	@ResponseBody
	public Map<String, Object> save(String userId1,String userId2,String message,
			HttpServletRequest request) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 if(StringTools.isEmpty(userId1)||StringTools.isEmpty(userId2)){
			 map.put("message", "parameter can not be null");
			 map.put("code", Codes.ERROR);
			 return map;
		 }
		 //1、如果是自己的话，那么申请失败
		 if(userId1.equals(userId2)){
			 map.put("message", "Can not add yourself！");
			 map.put("code", Codes.ERROR);
			 return map;
		 }
		 //2、如果已经申请过
		 
		 if(StringTools.isEmpty(message)){
			 message = "您好，很高兴认识您，可以加为好友吗";
		 }
		 Relationship relationship = new Relationship();
		 relationship.setUser1_id(Integer.parseInt(userId1));
		 relationship.setUser2_id(Integer.parseInt(userId2));
		 relationship.setMessage(message);
		 relationship.setState(0);
		 relationship.setTime(new Date());
		 Relationship relationship2 = relationshipService.save(relationship);
		 map.put("relatinship", relationship2);
		 map.put("code", Codes.SUCCESS);
		 return map;
	}
	
	@RequestMapping("accept.do")
	@ResponseBody
	public Map<String, Object> accept(String userId1,String userId2,String message,
			HttpServletRequest request) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 return map;
	}
	
	@RequestMapping("refuse.do")
	@ResponseBody
	public Map<String, Object> refuse(String userId1,String userId2,String message,
			HttpServletRequest request) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 return map;
	}
	
}
