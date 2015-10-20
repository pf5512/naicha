package com.naicha.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.CommentNaicha;
import com.naicha.app.service.CommentNaichaService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.StringTool;
import com.naicha.web.vo.RespComment2Naicha;
import com.naicha.web.vo.RespCommentNaicha;
import com.naicha.web.vo.RespRankCount;


@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentNaichaService  commentNaichaService;

	protected static final Logger LOG = LoggerFactory.getLogger(CommentController.class);
	/**
	 * 奶茶评论
	 * @author yangxujia
	 * @date 2015年9月24日上午11:44:48
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String, Object> save(String beCommentIdStr,String rankStr, String content,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(beCommentIdStr)
				||StringTool.isEmpty(userIdStr)||StringTool.isEmpty(content)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer userId = Integer.parseInt(userIdStr);
		//校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("code", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		CommentNaicha commentNaicha = new CommentNaicha();
		commentNaicha.setRank(Integer.parseInt(rankStr));
		commentNaicha.setContent(content);
		commentNaicha.setBeCommentId(Integer.parseInt(beCommentIdStr));
		commentNaicha.setToCommentId(userId);
		commentNaicha.setTime(new Date());
		CommentNaicha updateOrNot = commentNaichaService.save(commentNaicha);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 根据id获取评论列表
	 * @author yangxujia
	 * @date 2015年9月25日上午9:59:06
	 */
	@RequestMapping("/getCommentNaicha.do")
	@ResponseBody
	public Map<String, Object> getCommentNaicha(String userIdStr,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer userId =  Integer.parseInt(userIdStr);
		List<RespCommentNaicha> commentNaichaList = commentNaichaService.findCommentById(userId);
		List<RespRankCount> countList =  commentNaichaService.findRankCount(userId);
		map.put("commentNaichaList", commentNaichaList);
		map.put("countList", countList);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	/**
	 * 获取当天1 ,本周2 ，本月3 的的评价 
	 * @author yangxujia
	 * @date 2015年10月7日下午2:32:23
	 */
	@RequestMapping("/getCommentNaichaByTimeType.do")
	@ResponseBody
	public Map<String, Object> getCommentNaichaByTimeType(String timeType,String currentPage,String pageSize,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		LOG.info("timeType: "+timeType);
		if (StringTool.isEmpty(timeType)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//获取评价列表
		List<RespComment2Naicha> commentNaichaList = commentNaichaService.getCommentNaichaByTimeType(timeType,currentPage,pageSize);
		//获取评论个数
		List<RespRankCount> countList =  commentNaichaService.findRankCountByTimeType(timeType);
		map.put("commentNaichaList", commentNaichaList);
		map.put("countList", countList);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	
	/**
	 * 好评
	 * 根据id获取
	 * @author yangxujia
	 * @date 2015年10月20日下午2:57:28
	 */
	@RequestMapping("/getCommentByIdByRank.do")
	@ResponseBody
	public Map<String, Object> getCommentByIdByRank(String userIdStr,String rank,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)||StringTool.isEmpty(rank)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer userId =  Integer.parseInt(userIdStr);
		List<RespCommentNaicha> commentNaichaList = commentNaichaService.getCommentByIdByRank(userId,Integer.parseInt(rank));
		List<RespRankCount> countList =  commentNaichaService.findRankCount(userId);
		map.put("commentNaichaList", commentNaichaList);
		map.put("countList", countList);
		map.put("code", Codes.SUCCESS);
		return map;
	}
}
