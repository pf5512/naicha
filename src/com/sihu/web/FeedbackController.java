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

import com.sihu.app.mode.Feedback;
import com.sihu.app.service.FeedbackService;
import com.sihu.utils.Codes;

@Controller
@RequestMapping("/Feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	/**
	 * 个人设置保存
	 * @author yangxujia
	 * @date 2015-1-25下午10:47:54
	 */
	@RequestMapping("save.do")
	@ResponseBody
	public Map<String, Object> save(String message,String userId,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringTools.isEmpty(userId)||StringTools.isEmpty(message)){
			map.put("code", Codes.ERROR);
		}
		Feedback feedback = new Feedback();
		feedback.setMessage(message);
		feedback.setTime(new Date());
		feedback.setUserId(Integer.parseInt(userId));
		Feedback fb = feedbackService.save(feedback);
		map.put("feedback", fb);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	

}
