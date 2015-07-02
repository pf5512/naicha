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
import com.sihu.app.mode.Sport;
import com.sihu.app.service.CommentService;
import com.sihu.app.service.SportService;
import com.sihu.app.utils.StringTool;
import com.sihu.utils.Codes;
import com.sihu.utils.StringTools;

@Controller
@RequestMapping("/Activity")
public class ActivityController {

	@Autowired
	private SportService sportService;
	@Autowired
	private CommentService commentService;

	/**
	 * 保存运动信息
	 * 
	 * @author yangxujia
	 * @date 2015-1-16下午4:54:12
	 * @param phone
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("save.do")
	@ResponseBody
	public Map<String, Object> save(String title, String sport,
			String startTime, String endTime, String address,
			String description, String userId, String location,
			HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Sport sport1 = new Sport();
		sport1.setAddress(address);
		sport1.setDescription(description);
		sport1.setEndTime(StringTools.StringToDatetime(endTime));
		sport1.setJoinNum(0);
		String[] xy=location.split(",");
		Double x = new Double(xy[0]); 
		Double y = new Double(xy[1]);
		sport1.setXx(x);
		sport1.setYy(y);
		sport1.setPublicTime(new Date());
		sport1.setSports(sport);
		sport1.setStartTime(StringTools.StringToDatetime(startTime));
		sport1.setTitle(title);
		sport1.setUserId(Integer.parseInt(userId));
		Sport sport2 = sportService.save(sport1);
		map.put("code", Codes.SUCCESS);
		map.put("sport", sport2);
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
		List<Sport> sportList=sportService.findAll(location);
		map.put("code", Codes.SUCCESS);
		map.put("sportList", sportList);
		return map;
	}
	
	/**
	 * 根据运动类型进行排序
	 * @author yangxujia
	 * @date 2015-1-22下午3:52:48
	 * @param location
	 * @param sportType
	 * @param request
	 * @return
	 */
	@RequestMapping("findBySportType.do")
	@ResponseBody
	public Map<String, Object> findBySportType(String location,String sportType, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Sport> sportList=sportService.findBySportType(location,sportType);
		map.put("code", Codes.SUCCESS);
		map.put("sportList", sportList);
		return map;
	}
	
	/**
	 * 根据时间进行排序
	 * @author yangxujia
	 * @date 2015-1-22下午3:53:17
	 * @param location
	 * @param time
	 * @param request
	 * @return
	 */
	@RequestMapping("findByDateTime.do")
	@ResponseBody
	public Map<String, Object> findByDateTime(String location,String time, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Sport> sportList=sportService.findByDateTime(location,time);
		map.put("code", Codes.SUCCESS);
		map.put("sportList", sportList);
		return map;
	}
	
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
		Sport sport = sportService.findById(location,id);
		if(sport==null){
			map.put("code", Codes.ERROR);
			return map;
		}else{
		//带上评论
		int sportId = sport.getId();
		List<Comment> listComment = commentService.findBySportId(sportId);
			map.put("code", Codes.SUCCESS);
			map.put("sport", sport);
			map.put("listComment", listComment);
			return map;
		}
	}
}
