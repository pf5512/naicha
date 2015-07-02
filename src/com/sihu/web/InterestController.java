package com.sihu.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sihu.app.mode.Comment;
import com.sihu.app.mode.Interest;
import com.sihu.app.service.CommentService;
import com.sihu.app.service.InterestService;
import com.sihu.app.utils.StringTool;
import com.sihu.utils.Codes;

@Controller
@RequestMapping("/Interest")
public class InterestController {

	@Autowired
	private InterestService interestService;
	@Autowired
	private CommentService commentService;

	/**
	 * 保存趣闻
	 * @author yangxujia
	 * @date 2015-1-23下午8:10:15
	 */
	@RequestMapping("save.do")
	@ResponseBody
	public Map<String, Object> save( String userId,
			String content, String location,
			HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Interest interest = new Interest();
		interest.setContent(content);
		interest.setTime(new Date());
		interest.setClickCount(0);
		String[] xy=location.split(",");
		Double x = new Double(xy[0]); 
		Double y = new Double(xy[1]);
		interest.setXx(x);
		interest.setYy(y);
		interest.setUserId(Integer.parseInt(userId));
		Interest interest2 = interestService.save(interest);
		map.put("code", Codes.SUCCESS);
		map.put("interest", interest2);
		return map;
	}
	
	/**
	 * 按照距离进行排序
	 * @author yangxujia
	 * @date 2015-1-22下午3:51:48
	 * @param location
	 * @param request
	 * @return
	 */
	@RequestMapping("findAll.do")
	@ResponseBody
	public Map<String, Object> find(String location, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Interest> interestList=interestService.findAll(location);
		map.put("code", Codes.SUCCESS);
		map.put("interestList", interestList);
		return map;
	}
	
	/**
	 * 根据运动类型进行排序
	 * @author yangxujia
	 * @date 2015-1-22下午3:52:48
	 * @param location
	 * @param interestType
	 * @param request
	 * @return
	 */
//	@RequestMapping("findByInterestType.do")
//	@ResponseBody
//	public Map<String, Object> findByInterestType(String location,String interestType, HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Interest> interestList=interestService.findByInterestType(location,interestType);
//		map.put("code", Codes.SUCCESS);
//		map.put("interestList", interestList);
//		return map;
//	}
	
	/**
	 * 根据时间进行排序
	 * @author yangxujia
	 * @date 2015-1-22下午3:53:17
	 * @param location
	 * @param time
	 * @param request
	 * @return
	 */
//	@RequestMapping("findByDateTime.do")
//	@ResponseBody
//	public Map<String, Object> findByDateTime(String location,String time, HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Interest> interestList=interestService.findByDateTime(location,time);
//		map.put("code", Codes.SUCCESS);
//		map.put("interestList", interestList);
//		return map;
//	}
	
	/**
	 * 查找一条数据
	 * @author yangxujia
	 * @date 2015-1-22下午10:09:11
	 * @param location
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("findById.do")
	@ResponseBody
	public Map<String, Object> findById(String location,String id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringTool.isEmpty(location)||StringTool.isEmpty(id)){
			map.put("code", Codes.ERROR);
			return map;
		}
		Interest interest = interestService.findById(location,id);
		if(interest==null){
			map.put("code", Codes.ERROR);
			return map;
		}else{
		//带上评论
		int interestId = interest.getId();
		List<Comment> listComment = commentService.findByInterestId(interestId);
			map.put("code", Codes.SUCCESS);
			map.put("interest", interest);
			map.put("listComment", listComment);
			return map;
		}
	}
}
