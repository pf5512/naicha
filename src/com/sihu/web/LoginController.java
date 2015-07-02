package com.sihu.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sihu.app.mode.User;
import com.sihu.app.service.UserService;
import com.sihu.app.utils.ConvertMD5;
import com.sihu.utils.Codes;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	/**
	 *用户注册
	 * @author yangxujia
	 * @date 2014-12-23下午6:09:30
	 * @param phone
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("validate.do")
	@ResponseBody
	public Map<String, Object> find(String phone ,String password,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.findByPhone(phone);
		if(user==null){
			map.put("code", Codes.ERROR);
			return map;
		}
//		if(idUser.getState()!=1){
//			map.put("message", "账号未激活！");
//			return map;
//		}
		String password2 = user.getPassword();
		ConvertMD5 md5 = new ConvertMD5();
        password = md5.getMD5ofStr(password.trim()).toLowerCase();
        
		if(password!=null&&password2.equals(password)){
			String token = UUID.randomUUID().toString();
			System.out.println("<<<<<<<<<<<<<<<<<<< token"+token);
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(7*24*60*60);
			session.setAttribute("token", token);
			map.put("token", token);
			map.put("idUser", user);
			map.put("code", Codes.SUCCESS);
			System.out.println(request.getSession().getAttribute("token"));
			return map;
		}else{
			map.put("code", Codes.ERROR);
			return map;
		}
	}
	
	@RequestMapping("forTest.do")
	@ResponseBody
	public String forTest(HttpServletRequest request) {
		
		return request.getSession().getAttribute("token")+"";
	}
}
