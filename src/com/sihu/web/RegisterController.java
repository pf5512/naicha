package com.sihu.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sihu.app.utils.ConvertMD5;
import com.sihu.app.utils.StringTool;
import com.sihu.app.mode.User;
import com.sihu.app.service.UserService;
import com.sihu.utils.Codes;

@Controller
@RequestMapping("/register")
public class RegisterController {

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
	@RequestMapping("save.do")
	@ResponseBody
	public Map<String, Object> find(String phone,String password,String name,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("hellow");
		
		if(StringTool.isEmpty(phone)||StringTool.isEmpty(password)||StringTool.isEmpty(name)){
			map.put("code", Codes.ERROR);
			map.put("msg", "參數不能為空");
			return map;
		}
		
		User user = new User();
		user.setPhone(phone);
		ConvertMD5 md5 = new ConvertMD5();
        password = md5.getMD5ofStr(password).toLowerCase();
		user.setPassword(password);
		User userReturn  =  new User();
		user.setRegistrantionTime(new Date());
		userReturn = userService.save(user);
		map.put("code", Codes.SUCCESS);
		map.put("user", userReturn);
		return map;
	}
	
	@RequestMapping("validate.do")
	@ResponseBody
	public Map<String, Object> validate(String phone,String password,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("validate");
		
		if(StringTool.isEmpty(phone)){
			map.put("code", Codes.ERROR);
			map.put("msg", "參數不能為空");
			return map;
		}
		//如果手機號碼已經被註冊了
		Boolean isExist = userService.isExistFindByPhone(phone);
		if(isExist){
			map.put("code", Codes.ERROR);
			map.put("msg", "手機號碼已經被註冊了");
			return map;
		}else{
			map.put("code", Codes.SUCCESS);
			return map;
		}
	}
}
