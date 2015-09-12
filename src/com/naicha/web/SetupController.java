package com.naicha.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Sides;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.language.bm.Rule.Phoneme;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.naicha.app.service.UserService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.ConvertMD5;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.Resize;
import com.naicha.app.utils.StringTool;


@Controller
@RequestMapping("setup")
public class SetupController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/updateHeadPicture.do")
	@ResponseBody
	public Map<String, Object> findIndividual(@RequestParam("files") MultipartFile[] files,
			String phone, String token, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//参数的验证
		if (StringTool.isEmpty(token)||StringTool.isEmpty(phone)||files==null||files.length==0) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer updateOrNot = null;
		//校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(phone);
		if(!token.equals(tokenOld)){
			map.put("codes", Codes.TOKEN_IS_OVER_DUE);
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
				 updateOrNot = userService.updateHeadPicture("head/" + pictureName,phone);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}

	/**
	 * 更新用户名
	 */
	@RequestMapping("/updateName.do")
	@ResponseBody
	public Map<String, Object> updateUname(String name,String phone, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(phone)) {
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
		Integer updateOrNot = userService.updateName(name, phone);
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}

	/**
	 * 更新奶茶号
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateNaichaNo.do")
	@ResponseBody
	public Map<String, Object> updateNaichaNo(String naichaNo,String phone, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(naichaNo)) {
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
		Integer updateOrNot = userService.updateNaichaNo(naichaNo, phone);
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 更新性别
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateSex.do")
	@ResponseBody
	public Map<String, Object> updateSex(String sex,String phone, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(sex)) {
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
		Integer updateOrNot = userService.updateSex(Integer.parseInt(sex), phone);
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 更新生日
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateBirthday.do")
	@ResponseBody
	public Map<String, Object> updateBirthday(String birthday,String phone, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(birthday)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(phone);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期");
			map.put("codes", Codes.TOKEN_IS_OVER_DUE);
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
		Integer updateOrNot = userService.updateBirthday(birthdaydDate, phone);
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 更新职业
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateProfession.do")
	@ResponseBody
	public Map<String, Object> updateProfession(String profession,String phone, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(profession)) {
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
		Integer updateOrNot = userService.updateProfession(profession, phone);
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	
	/**
	 * 更新地区
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateAddress.do")
	@ResponseBody
	public Map<String, Object> updateAddress(String address,String phone, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(address)) {
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
		Integer updateOrNot = userService.updateAddress(phone, address);
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	
	/**
	 * 更新签名
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updatePerSignature.do")
	@ResponseBody
	public Map<String, Object> updatePerSignature(String perSignature,String phone, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(perSignature)) {
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
		Integer updateOrNot = userService.updatePerSignature(phone, perSignature);
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 更新微信号
	 * @author yangxujia
	 * @date 2015年9月11日下午4:48:42
	 */
	@RequestMapping("/updateWeixinNo.do")
	@ResponseBody
	public Map<String, Object> updateWeixinNo(String weixinNo,String phone, String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(token)||StringTool.isEmpty(weixinNo)) {
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
		Integer updateOrNot = userService.updateWeixinNo(phone, weixinNo);
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
}
