package com.naicha.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.User;
import com.naicha.app.service.UserService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.ConvertMD5;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.SMSAPI;
import com.naicha.app.utils.StringTool;
import com.naicha.web.vo.RespUser;
import com.test.TestSMSAPI;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	/**
	 * 验证，保存，登录
	 * @author yangxujia
	 * @date 2015年9月8日下午5:54:53
	 */
	@RequestMapping("validateAndSave.do")
	@ResponseBody
	public Map<String, Object> find(String phone, String password, String name,
			String validateCode, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 1、验证参数是否为空
		if (StringTool.isEmpty(validateCode) || StringTool.isEmpty(phone)
				|| StringTool.isEmpty(password) || StringTool.isEmpty(name)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			map.put("msg", "参数不能为空");
			return map;
		}
		// 2、验证手机号
		boolean isExist = userService.isExistFindByPhone(phone);
		if (isExist) {
			map.put("code", Codes.PHONE_NUMBER_IS_EXIST);
			map.put("msg", "手机号码已存在");
			return map;
		}
		// 3、验证码校验
		HttpSession session = request.getSession();
		String code2 = (String) session.getAttribute("validateCode");
		System.out.println("------------------code2------" + code2);
		System.out.println("------------------validateCode------"
				+ validateCode);
		// session.removeAttribute("code");//取完后去掉session
		if (!code2.equals(validateCode)) {
			map.put("message", "验证码不正确");
			return map;
		} else {
			//2、保存数据
			User user = new User();
			user.setPhone(phone);
			// user.setNaichaNo(naichaNo);
			user.setName(name);
			ConvertMD5 md5 = new ConvertMD5();
			password = md5.getMD5ofStr(password);
			user.setPassword(password);
			User userReturn = new User();
			userReturn = userService.save(user);
			String token = UUID.randomUUID().toString().replaceAll("-","").toLowerCase();
			MemCached cache = MemCached.getInstance();
			cache.add(phone, token);//将token放进缓存中
			map.put("code", Codes.SUCCESS);
			map.put("token", token);
			return map;
		}
	}

	/**
	 * 发送验证码
	 * @author yangxujia
	 * @date 2015年9月8日下午5:55:51
	 */
	@RequestMapping("sendValidateCode.do")
	@ResponseBody
	public Map<String, Object> sendValidateCode(String phone,Integer isReset,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(phone)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			map.put("msg", "参数不能为空");
			return map;
		}
//			Start 2015-09-09 
//			String validateCode = getValidateCode();//生成验证码
//			//发送验证码
//			System.out.println("validateCode:  "+validateCode);
//			boolean retcode =  send(phone,validateCode);
//			end 2015-09-09
//		         鉴于目前未开通短信功能,先写死
			String validateCode ="0755";
			boolean retcode = true;
//			鉴于目前未开通短信功能,先写死
			if(retcode){
				HttpSession session = request.getSession();
				session.setAttribute("validateCode"+phone, validateCode);// 将获取到的值放进session里边
				map.put("code", Codes.SUCCESS);
				return map;
			}else{
				map.put("code", Codes.ERROR);
				return map;
			}
	}
	//发送验证码
	private boolean send(String phone, String validateCode) {
		SMSAPI api = new SMSAPI();
        String httpResponse =  api.testSend(phone,validateCode);
         try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
                return true;
            }else{
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
                return false;
            }
        } catch (JSONException ex) {
            Logger.getLogger(TestSMSAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
		return false;
	}

	//随机生成四位验证码算法
	private String getValidateCode() {
		// 等确定后再调用
		Random rand = new Random();
		int a = rand.nextInt(9999);
		String code = a+"";
		while(code.length()<4){
			code ="0"+code;
		}
		return code;
	}
	
	/**
	 * 注册第一步
	 * @author yangxujia
	 * @date 2015年9月10日上午9:42:43
	 * @return
	 */
	@RequestMapping("validatePhonePwd.do")
	@ResponseBody
	public Map<String, Object> validatePhonePwd(String phone,String password,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(phone)||StringTool.isEmpty(password)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			map.put("msg", "参数不能为空");
			return map;
		}
		// 验证手机号码
		Boolean isExist = userService.isExistFindByPhone(phone);
		if (isExist) {
			map.put("code", Codes.PHONE_NUMBER_IS_EXIST);
			map.put("msg", "手机号码已存在");
			return map;
		} else {
			map.put("code", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 注册第二步
	 * 验证码校验
	 * @author yangxujia
	 * @date 2015年9月10日上午9:46:59
	 */
	@RequestMapping("validateCode.do")
	@ResponseBody
	public Map<String, Object> validateCode(String phone,String validateCode,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(phone)||StringTool.isEmpty(validateCode)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			map.put("msg", "参数不能为空");
			return map;
		}
		// 验证码校验
 		HttpSession session = request.getSession();
		String code2 = (String) session.getAttribute("validateCode"+phone);
		System.out.println("------------------code2------" + code2);
		System.out.println("------------------validateCode------"+ validateCode);
		// session.removeAttribute("code");//取完后去掉session
		if (code2==null||!code2.equals(validateCode)) {
			map.put("message", "验证码不正确");
			map.put("code", Codes.ERROR);
			return map;
		} else {
			map.put("code", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 注册第三步 选择角色并登录
	 * @author yangxujia
	 * @date 2015年9月10日上午10:00:30
	 */
	@RequestMapping("chooseTypeAndLogin.do")
	@ResponseBody
	public Map<String, Object> chooseRoleAndLogin(String phone,String password,String userType,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(phone)||StringTool.isEmpty(password)||StringTool.isEmpty(userType)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			map.put("msg", "参数不能为空");
			return map;
		}else{
			// 验证手机号码
			Boolean isExist = userService.isExistFindByPhone(phone);
			if (isExist) {
				map.put("code", Codes.PHONE_NUMBER_IS_EXIST);
				map.put("msg", "手机号码已存在");
				return map;
			}
		//2、保存数据
		User user = new User();
		user.setPhone(phone);
		ConvertMD5 md5 = new ConvertMD5();
		password = md5.getMD5ofStr(password);
		user.setPassword(password);
		user.setUserType(Integer.parseInt(userType));
		user.setRegisterTime(new Date());
		User userReturn = new User();
		userReturn = userService.save(user);
		//将token放进缓存中
		String token = UUID.randomUUID().toString().replaceAll("-","").toLowerCase();
		MemCached cache = MemCached.getInstance();
		cache.add(phone, token);
		map.put("code", Codes.SUCCESS);
		map.put("token", token);
		try {
			RespUser respUser =new RespUser();
			BeanUtils.copyProperties(respUser, userReturn);
			respUser.setRegitsterTime(userReturn.getRegisterTime());
			map.put("user", respUser);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return map;
		}
	}
	
	/**
	 * 重置密码
	 * @author yangxujia
	 * @date 2015年9月10日上午10:58:24
	 */
	@RequestMapping("reSetPassword.do")
	@ResponseBody
	public Map<String, Object> reSetPassword(String userIdStr,String phone,String validateCode,String password,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)||StringTool.isEmpty(phone)||StringTool.isEmpty(validateCode)||StringTool.isEmpty(password)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			map.put("msg", "参数不能为空");
			return map;
		}
		Integer userId = Integer.parseInt(userIdStr);
		//检查手机号码是否存在
		Boolean isExist = userService.isExistFindByPhone(phone);
		if (!isExist) {
			map.put("code", Codes.PHONE_NUMBER_IS_NOT_EXIST);
			map.put("msg", "手机不存在,请重新输入");
			return map;
		}
		// 验证码校验
		HttpSession session = request.getSession();
		String code2 = (String) session.getAttribute("validateCode"+phone);
		System.out.println("------------------code2------" + code2);
		System.out.println("------------------validateCode------"+ validateCode);
		// session.removeAttribute("code");//取完后去掉session
		if (code2==null||!code2.equals(validateCode)) {
			map.put("message", "验证码不正确");
			map.put("code", Codes.ERROR);
			return map;
		} else {
			//重置密码
			ConvertMD5 md5 = new ConvertMD5();
			password = md5.getMD5ofStr(password);
			Integer retCode = userService.updatePassword(userId,password);
			if (retCode==1) {
				map.put("code", Codes.SUCCESS);
				return map;
			}else {
				map.put("code", "重置失败");
				map.put("code", Codes.ERROR);
				return map;
			}
		}
	}
}
