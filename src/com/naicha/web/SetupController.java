package com.naicha.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.naicha.app.mode.User;
import com.naicha.app.service.UserService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.ConvertMD5;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.Resize;
import com.naicha.app.utils.StringTool;
import com.naicha.web.vo.RespUser;


@Controller
@RequestMapping("setup")
public class SetupController {
	@Autowired
	private UserService userService;
	
	/**
	 * 更新头像
	 * @author yangxujia
	 * @date 2015年9月14日上午11:06:40
	 */
	@RequestMapping("/updateHeadPicture.do")
	@ResponseBody
	public Map<String, Object> updateHeadPicture(@RequestParam("files") MultipartFile[] files,
			String userIdStr, String token, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//参数的验证
		if (StringTool.isEmpty(token)||StringTool.isEmpty(userIdStr)||files==null||files.length==0) {
			map.put("msg", "参数为空！");
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer userId = Integer.parseInt(userIdStr);
		Integer updateOrNot = null;
		//校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token过期！");
			map.put("code", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		for (int i = 0; i < files.length; i++) {
			try {
				MultipartFile file = files[i];
				ConvertMD5 md5 = new ConvertMD5();
				String picPath = request.getSession().getServletContext().getRealPath("/resource/head");
				/* 从当时时间MD5强制重命名图片 */
				String picTime = String.valueOf(System.currentTimeMillis());
				String picType = file.getContentType();
				String pictureName = md5.getMD5ofStr(picTime);
				String littlePictureName = pictureName;
				if (picType.equals("image/jpeg")) {
					pictureName = pictureName.concat(".jpg");
					littlePictureName = littlePictureName.concat("_little.jpg");
				} else if (picType.equals("image/png")) {
					pictureName = pictureName.concat(".png");
					littlePictureName = littlePictureName.concat("_little.png");
				} else if (picType.equals("image/bmp")) {
					pictureName = pictureName.concat(".bmp");
					littlePictureName = littlePictureName.concat("_little.bmp");
				} else if (picType.equals("image/gif")) {
					pictureName = pictureName.concat(".gif");
					littlePictureName = littlePictureName.concat("_little.gif");
				} else{
					pictureName = pictureName.concat(".jpg");
					littlePictureName = littlePictureName.concat("_little.jpg");
				}
				/* 保存文件 */
				FileUtils.copyInputStreamToFile(file.getInputStream(),	new File(picPath, pictureName));
				BufferedImage image = ImageIO.read(file.getInputStream());
				//保存小图
	             BufferedImage imagelittle =Resize.rize(image,100,100);
	         	 try {
    				 File ff= new File(picPath+"/"+littlePictureName);
					 ImageIO.write(imagelittle, "gif", ff);
				 } catch (IOException e) {
				 	 e.printStackTrace();
				 }
				 updateOrNot = userService.updateHeadPicture("head/" + pictureName,userId);
				 map.put("headPicture","head/" + pictureName);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}

	/**
	 * 更新用户名
	 */
	@RequestMapping("/updateName.do")
	@ResponseBody
	public Map<String, Object> updateUname(String name,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(userIdStr)) {
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
		Integer updateOrNot = userService.updateName(name, userId);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}

	/**
	 * 更新奶茶号
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateNaichaNo.do")
	@ResponseBody
	public Map<String, Object> updateNaichaNo(String naichaNo,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(naichaNo)) {
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
		//查看是否已设置奶茶号
		User user1 = userService.findById(userId);
		if (!"".equals(user1.getNaichaNo())) {
			map.put("msg", "奶茶号只能设置一次");
			map.put("code", Codes.ERROR);
			return map;
		}
		//检查奶茶号是否存在
		User user =  userService.findByNaichaNo(naichaNo);
		if(user!=null){
			map.put("msg", "奶茶号已存在");
			map.put("code", Codes.ERROR);
			return map;
		}
		Integer updateOrNot = userService.updateNaichaNo(naichaNo, userId);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 更新性别
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateSex.do")
	@ResponseBody
	public Map<String, Object> updateSex(String sex,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(sex)) {
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
		Integer updateOrNot = userService.updateSex(Integer.parseInt(sex), userId);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 更新生日
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateBirthday.do")
	@ResponseBody
	public Map<String, Object> updateBirthday(String birthday,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(birthday)||StringTool.isEmpty(userIdStr)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer userId = Integer.parseInt(userIdStr);
		//校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期");
			map.put("code", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		//格式化日期
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM");
		Date birthdaydDate = null;
		try {
			birthdaydDate = sdf.parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer updateOrNot = userService.updateBirthday(birthdaydDate, userId);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 更新职业
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateProfession.do")
	@ResponseBody
	public Map<String, Object> updateProfession(String profession,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(profession)||StringTool.isEmpty(userIdStr)) {
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
		Integer updateOrNot = userService.updateProfession(profession, userId);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	
	/**
	 * 更新地区
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateAddress.do")
	@ResponseBody
	public Map<String, Object> updateAddress(String address,String jinwei,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(address)||StringTool.isEmpty(userIdStr)||StringTool.isEmpty(jinwei)) {
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
		Integer updateOrNot = userService.updateAddress(userId, address,jinwei);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	
	/**
	 * 更新签名
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updatePerSignature.do")
	@ResponseBody
	public Map<String, Object> updatePerSignature(String perSignature,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(perSignature)||StringTool.isEmpty(userIdStr)) {
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
		Integer updateOrNot = userService.updatePerSignature(userId, perSignature);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 更新微信号
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateWeixinNo.do")
	@ResponseBody
	public Map<String, Object> updateWeixinNo(String weixinNo,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(weixinNo)||StringTool.isEmpty(userIdStr)) {
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
		Integer updateOrNot = userService.updateWeixinNo(userId, weixinNo);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 提交口语水平认证接口
	 * @author yangxujia
	 * @date 2015年9月24日下午3:29:36
	 */
	@RequestMapping("/oralIdentify.do")
	@ResponseBody
	public Map<String, Object> oralIdentify(String profession,String rankStr, String weixinNo,String phone,
			@RequestParam("files") MultipartFile[] files,String userIdStr,String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(profession)||StringTool.isEmpty(rankStr)||StringTool.isEmpty(userIdStr)||StringTool.isEmpty(token)) {
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
		String pics="";
		//图片处理
		for (int i = 0; i < files.length; i++) {
			try {
				MultipartFile file = files[i];
				ConvertMD5 md5 = new ConvertMD5();
				String picPath = request.getSession().getServletContext().getRealPath("/resource/identify");
				/* 从当时时间MD5强制重命名图片 */
				String picTime = String.valueOf(System.currentTimeMillis());
				String picType = file.getContentType();
				String pictureName = md5.getMD5ofStr(picTime);
				if (picType.equals("image/jpeg")) {
					pictureName = pictureName.concat(".jpg");
				} else if (picType.equals("image/png")) {
					pictureName = pictureName.concat(".png");
				} else if (picType.equals("image/bmp")) {
					pictureName = pictureName.concat(".bmp");
				} else if (picType.equals("image/gif")) {
					pictureName = pictureName.concat(".gif");
				} else{
					pictureName = pictureName.concat(".jpg");
				}
				/* 保存文件 */
				FileUtils.copyInputStreamToFile(file.getInputStream(),	new File(picPath, pictureName));
				ImageIO.read(file.getInputStream());
				pictureName = "identify/"+pictureName;
				if(pics==""){
					pics=pictureName;
				}else{
				pics = pics + "," +pictureName;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Integer updateOrNot = userService.oralIdentify(profession,rankStr,userId,pics, weixinNo,phone);
		map.put("updateOrNot", updateOrNot);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	
	@RequestMapping("/updatePhone.do")
	@ResponseBody
	public Map<String, Object> updatePhone(String phone,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(phone)||StringTool.isEmpty(userIdStr)) {
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
		// 2、验证手机号
		boolean isExist = userService.isExistFindByPhone(phone);
		if (isExist) {
			map.put("code", Codes.PHONE_NUMBER_IS_EXIST);
			map.put("msg", "手机号码已存在");
			return map;
		}
		Integer updateOrNot = userService.updatePhone(userId, phone);
		if (updateOrNot==1) {
			map.put("code", Codes.SUCCESS);
			return map;
		}else {
			map.put("code", Codes.ERROR);
			return map;
		}
	}
	
	/**
	 * 设置个人服务时间
	 * @author yangxujia
	 * @date 2015年10月20日上午10:07:09
	 */
	@RequestMapping("/updateMyServiceTime.do")
	@ResponseBody
	public Map<String, Object> updateMyServiceTime(String myServiceTime,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(myServiceTime)||StringTool.isEmpty(userIdStr)) {
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
		Integer updateOrNot = userService.updateMyServiceTime(userId, myServiceTime);
		if (updateOrNot==1) {
			map.put("code", Codes.SUCCESS);
			return map;
		}else {
			map.put("code", Codes.ERROR);
			return map;
		}
	}
	
	/**
	 * 获取个人服务时间
	 * @author yangxujia
	 * @date 2015年10月20日上午10:07:09
	 */
	@RequestMapping("/getMyServiceTime.do")
	@ResponseBody
	public Map<String, Object> getMyServiceTime(String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(userIdStr)) {
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
		String myServiceTime = userService.getMyServiceTime(userId);
		map.put("myServiceTime", myServiceTime);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	
	/**
	 * 更新个人技能
	 * @author yangxujia
	 * @date 2015年10月20日上午11:07:22
	 */
	@RequestMapping("/updateServiceType.do")
	@ResponseBody
	public Map<String, Object> updateServiceType(String serviceType,String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(serviceType)||StringTool.isEmpty(userIdStr)) {
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
		Integer updateOrNot = userService.updateServiceType(userId, serviceType);
		if (updateOrNot==1) {
			map.put("code", Codes.SUCCESS);
			return map;
		}else {
			map.put("code", Codes.ERROR);
			return map;
		}
	}
	/**
	 * 获取个人技能
	 * @author yangxujia
	 * @date 2015年10月20日上午11:07:22
	 */
	@RequestMapping("/getServiceType.do")
	@ResponseBody
	public Map<String, Object> getServiceType(String userIdStr, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(userIdStr)) {
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
		String serviceType = userService.getServiceType(userId);
		map.put("serviceType", serviceType);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 根据用户ID查找
	 * @author yangxujia
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @date 2015年10月20日下午3:55:54
	 */
	@RequestMapping("/findByUserId.do")
	@ResponseBody
	public Map<String, Object> findByUserId(String userIdStr, String token,HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)||StringTool.isEmpty(token)) {
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
		User user  = userService.findByUserId(userId);
		RespUser respUser =new RespUser();
		BeanUtils.copyProperties(respUser, user);
		respUser.setRegitsterTime(user.getRegisterTime());
		System.out.println(respUser.getRegitsterTime());
		map.put("user", respUser);
		map.put("code", Codes.SUCCESS);
		return map;
	}
}
