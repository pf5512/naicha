package com.naicha.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.Comment;
import com.naicha.app.service.CommentService;
import com.naicha.app.utils.StringTool;
import com.naicha.utils.Codes;

@Controller
@RequestMapping("/interaction")
public class InteractionController {

	@Autowired
	private CommentService commentService;
	
	/**
	 *用户注册
	 * @author yangxujia
	 * @date 2014-12-23下午6:09:30
	 * @param phone
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("commentSubmit.do")
	@ResponseBody
	public Map<String, Object> commentSubmit(String userId,String sportId,String content,String pid,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringTool.isEmpty(userId)||StringTool.isEmpty(sportId)||StringTool.isEmpty(content)||StringTool.isEmpty(pid)){
			map.put("code", Codes.ERROR);
			map.put("msg", "參數不能為空");
			return map;
		} 	
		Comment comment = new Comment();
		comment.setUserId(Integer.parseInt(userId));
		comment.setSportId(Integer.parseInt(sportId));
		comment.setContent(content);
		comment.setPid(Integer.parseInt(pid));
		comment.setTime(new Date());
		Comment commentReturn = commentService.save(comment);
		map.put("code", Codes.SUCCESS);
		map.put("comment", commentReturn);
		return map;
	}
	
	/**
	 * 对
	 * @author yangxujia
	 * @date 2015-1-24上午4:07:06
	 */
	@RequestMapping("interestComment.do")
	@ResponseBody
	public Map<String, Object> interestComment(String userId,String interestId,String content,String pid,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringTool.isEmpty(userId)||StringTool.isEmpty(interestId)||StringTool.isEmpty(content)||StringTool.isEmpty(pid)){
			map.put("code", Codes.ERROR);
			map.put("msg", "參數不能為空");
			return map;
		} 	
		Comment comment = new Comment();
		comment.setUserId(Integer.parseInt(userId));
		comment.setInterestId(Integer.parseInt(interestId));
		comment.setContent(content);
		comment.setPid(Integer.parseInt(pid));
		comment.setTime(new Date());
		Comment commentReturn = commentService.save(comment);
		map.put("code", Codes.SUCCESS);
		map.put("comment", commentReturn);
		return map;
	}
}
