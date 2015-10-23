package com.naicha.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.Pictures;
import com.naicha.app.service.PicturesService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.StringTool;

/**
 * 图片
 * @author yangxujia
 * @date 2015年10月17日上午11:46:48
 */
@Controller
@RequestMapping("/pictures")
public class PicturesController {
	
	@Autowired
	private  PicturesService picturesService;
	
	@RequestMapping("findByUserId.do")
	@ResponseBody
	public Map<String, Object> findByUserId(String userIdStr ,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		List<Pictures> picList = picturesService.findByUserId(Integer.parseInt(userIdStr));
		map.put("picList", picList);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
}
