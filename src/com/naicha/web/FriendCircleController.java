package com.naicha.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.naicha.app.mode.FriendCircle;
import com.naicha.app.service.FriendCircleService;
import com.naicha.app.service.PicturesService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.StringTool;

@Controller
@RequestMapping("/friendCircle")
public class FriendCircleController {
	@Autowired
	private FriendCircleService friendCircleService;
	@Autowired
	private PicturesService picturesService;
	
	@RequestMapping("/save.do")
	public Map<String, Object> save(@RequestParam("files") MultipartFile[] files,
			String userId,String content,String location,String jinwei,String phone,String token,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//非空判断
		if(StringTool.isEmpty(userId)||StringTool.isEmpty(content)||StringTool.isEmpty(phone)||StringTool.isEmpty(token)){
			map.put("msg", "参数不能为空");
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(phone);
		if(!token.equals(tokenOld)){
			map.put("codes", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		//数据保存
		FriendCircle friendCircle = new FriendCircle();
		friendCircle.setContent(content);
		friendCircle.setLocation(location);
		friendCircle.setJinwei(jinwei);
		friendCircle.setUserId(Integer.parseInt(userId));
		friendCircle.setTime(new Date());
		FriendCircle friendCircleReturn = new FriendCircle();
		friendCircleReturn = friendCircleService.save(friendCircle);
		friendCircleReturn.getId();
		return map;
		
	}
	
}
