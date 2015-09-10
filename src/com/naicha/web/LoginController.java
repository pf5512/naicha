package com.naicha.web;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.User;
import com.naicha.app.service.UserService;
import com.naicha.app.utils.ConvertMD5;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.StringTool;
import com.naicha.utils.Codes;
import com.naicha.web.vo.RespUser;
/**
 * 登录功能
 * @author yangxujia
 * @date 2015年9月10日上午9:30:08
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;

	/**
	 * 登录
	 * @author yangxujia
	 * @date 2015年9月9日上午9:29:32
	 */
	@RequestMapping("validate.do")
	@ResponseBody
	public Map<String, Object> find(String phoneOrNaicha ,String password,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 1、验证参数是否为空
		if (StringTool.isEmpty(phoneOrNaicha) || StringTool.isEmpty(password)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			map.put("msg", "参数不能为空");
			return map;
		}
		phoneOrNaicha = phoneOrNaicha.trim();
		if(phoneOrNaicha.length()==11&&phoneOrNaicha.startsWith("1")){//判断是属于手机号还是奶茶号,如果为11位并且开头为1的认定为手机
			User user = userService.findByPhone(phoneOrNaicha);
			if(user==null){
				map.put("code", Codes.ERROR);
				return map;
			}else {
				String passwordOld = user.getPassword();
				ConvertMD5 md5 = new ConvertMD5();
				password = md5.getMD5ofStr(password);
				if (password.equals(passwordOld)) {//如果密码正确成功生成token
					String tokenString = UUID.randomUUID().toString().replaceAll("-", "");
					MemCached cached =  MemCached.getInstance();
					String phone = user.getPhone();
					cached.add(phone, tokenString);
					cached.replace(phone, tokenString);
					map.put("token", tokenString);
					map.put("code", Codes.SUCCESS);
					try {
						RespUser respUser =new RespUser();
						BeanUtils.copyProperties(respUser, user);
						map.put("user", respUser);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					return map;
				}else {
					map.put("msg", "密码错误");
					map.put("code", Codes.ERROR);
					return map;
				}
			}
		}else{//如果使用奶茶号登录
			User user =  userService.findByNaichaNo(phoneOrNaicha);
			if(user==null){
				map.put("code", Codes.ERROR);
				return map;
			}else{
				String passwordOld = user.getPassword();
				ConvertMD5 md5 = new ConvertMD5();
				password = md5.getMD5ofStr(password);
				if (password.equals(passwordOld)) {//如果密码正确成功生成token
					String tokenString = UUID.randomUUID().toString().replaceAll("-", "");
					MemCached cached =  MemCached.getInstance();
					String phone = user.getPhone();
					cached.add(phone, tokenString);
					cached.replace(phone, tokenString);
					map.put("token", tokenString);
					map.put("code", Codes.SUCCESS);
					RespUser respUser =new RespUser();
					try {
						BeanUtils.copyProperties(respUser, user);
						map.put("user", respUser);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					return map;
				}else {
					map.put("msg", "密码错误");
					map.put("code", Codes.ERROR);
					return map;
				}
			}
		}
	}
}
