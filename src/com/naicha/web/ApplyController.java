package com.naicha.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.service.ApplyService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.StringTool;

/**
 * 报名
 * @author yangxujia
 * @date 2015年10月5日上午11:31:15
 */
@Controller
@RequestMapping("/apply")
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;

	/**
	 * 报名功能
	 * @author yangxujia
	 * @date 2015年10月5日上午11:32:48
	 */
	@RequestMapping("apply.do")
	@ResponseBody
	public Map<String, Object> find(String taskIdStr,String userIdStr ,String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(taskIdStr)||StringTool.isEmpty(userIdStr)||StringTool.isEmpty(token)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//2.校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期！");
			map.put("codes", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		applyService.updateState(Integer.parseInt(taskIdStr), Integer.parseInt(userIdStr));
		map.put("codes", Codes.SUCCESS);
		return map;
	}
}
