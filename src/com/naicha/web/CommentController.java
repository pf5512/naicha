package com.naicha.web;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.CommentNaicha;
import com.naicha.app.service.CommentNaichaService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.StringTool;
import com.naicha.web.vo.RespCommentNaicha;


@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentNaichaService  commentNaichaService;

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
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer userId = Integer.parseInt(userIdStr);
		//校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("codes", Codes.TOKEN_IS_OVER_DUE);
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
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 根据id获取评论列表
	 * @author yangxujia
	 * @date 2015年9月25日上午9:59:06
	 */
	@RequestMapping("/getCommentNaicha.do")
	@ResponseBody
	public Map<String, Object> save(String userIdStr,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer userId =  Integer.parseInt(userIdStr);
		List<RespCommentNaicha> commentNaichaList = commentNaichaService.findCommentById(userId);
		List<BigInteger> countList =  commentNaichaService.findRankCount(userId);
		map.put("commentNaichaList", commentNaichaList);
		map.put("countList", countList);
		map.put("good", countList.get(0));
		map.put("mid", countList.get(1));
		map.put("bad", countList.get(2));
		map.put("codes", Codes.SUCCESS);
		return map;
	}
}
