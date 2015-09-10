package com.naicha.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.hibernate.envers.tools.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.naicha.app.mode.User;
import com.naicha.app.service.UserService;
import com.naicha.app.utils.ConvertMD5;
import com.naicha.app.utils.Resize;
import com.naicha.utils.Codes;

@Controller
@RequestMapping("/User")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 个人设置保存
	 * @author yangxujia
	 * @date 2015-1-25下午10:47:54
	 */
	@RequestMapping("save.do")
	@ResponseBody
	public Map<String, Object> save(String headPicture, String username,
			String sid, String sex, String age,
			String hometown, String hobby, String personalNote,String location,
			HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		String[] xy=location.split(",");
		Double x = new Double(xy[0]); 
		Double y = new Double(xy[1]);
		user.setXx(x);
		user.setYy(y);
		user.setRegistrantionTime(new Date());
		
		user.setHeadPicture(headPicture);
		user.setUsername(username);
		user.setSid(sid);
		user.setSex(Integer.parseInt(sex));
		user.setAge(Integer.parseInt(age));
		user.setHometown(hometown);
		user.setHobby(hobby);
		user.setPersonalNote(personalNote);
		User user2 = userService.save(user);
		map.put("code", Codes.SUCCESS);
		map.put("user", user2);
		return map;
	}
	
	/**
	 * 更新头像
	 * @author yangxujia
	 * @date 2015-1-26下午11:02:00
	 * @param files
	 * @param idstr
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateHeadPicture.do")
	@ResponseBody
	public Map<String, Object> updateHeadPicture(
			@RequestParam("files") MultipartFile[] files, String  idstr,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer updateOrNot = null;
		int id = Integer.parseInt(idstr);
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				try {
					MultipartFile file = files[i];
					ConvertMD5 md5 = new ConvertMD5();
					String pic_path = request.getSession().getServletContext()
							.getRealPath("/resource/setup");
					/* 从当时时间MD5强制重命名图片 */
					String pic_time = String
							.valueOf(System.currentTimeMillis());
					String pic_type = file.getContentType();
					String file_ture_name = md5.getMD5ofStr(pic_time);
					String littleFileName = file_ture_name;
					if (pic_type.equals("image/jpeg")) {
						file_ture_name = file_ture_name.concat(".jpg");
						littleFileName = littleFileName.concat("_.jpg");
					} else if (pic_type.equals("image/png")) {
						file_ture_name = file_ture_name.concat(".png");
						littleFileName = littleFileName.concat("_.png");
					} else if (pic_type.equals("image/bmp")) {
						file_ture_name = file_ture_name.concat(".bmp");
						littleFileName = littleFileName.concat("_.bmp");
					} else if (pic_type.equals("image/gif")) {
						file_ture_name = file_ture_name.concat(".gif");
						littleFileName = littleFileName.concat("_.gif");
					} else{
						file_ture_name = file_ture_name.concat(".jpg");
						littleFileName = littleFileName.concat("_.jpg");
						}
					/* 保存文件 */
					BufferedImage image = ImageIO.read(file.getInputStream());
					
					FileUtils.copyInputStreamToFile(file.getInputStream(),	new File(pic_path, file_ture_name));
					//保存小图
		             BufferedImage imagelittle =Resize.rize(image,100,100);
		         	 try {
	    				 File ff= new File(pic_path+"/"+littleFileName);
						 ImageIO.write(imagelittle, "gif", ff);
					 } catch (IOException e) {
					 	 e.printStackTrace();
					 }
					 updateOrNot = userService.updateHeadPicture("setup/" + file_ture_name, id);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		map.put("updateOrNot", updateOrNot);
		map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	@RequestMapping("/updateSex.do")
	@ResponseBody
	public Map<String,Object> updateSex(String idStr,String sex,HttpServletRequest request){
		Map<String,Object > map = new HashMap<String,Object>();
		if(StringTools.isEmpty(idStr)||StringTools.isEmpty(sex)){
			map.put("code", Codes.ERROR);
			return map;
		}
		int id = Integer.parseInt(idStr);
		Integer updateResult = userService.updateSex(sex,id);
		map.put("updateOrNot", updateResult);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	@RequestMapping("/updateHometown.do")
	@ResponseBody
	public Map<String,Object> updateHometown(String idStr,String hometown,HttpServletRequest request){
		Map<String,Object > map = new HashMap<String,Object>();
		if(StringTools.isEmpty(idStr)||StringTools.isEmpty(hometown)){
			map.put("code", Codes.ERROR);
			return map;
		}
		int id = Integer.parseInt(idStr);
		Integer updateResult = userService.updateHometown(hometown,id);
		map.put("updateOrNot", updateResult);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	@RequestMapping("/updateHobby.do")
	@ResponseBody
	public Map<String,Object> updateHobby(String idStr,String hobby,HttpServletRequest request){
		Map<String,Object > map = new HashMap<String,Object>();
		if(StringTools.isEmpty(idStr)||StringTools.isEmpty(hobby)){
			map.put("code", Codes.ERROR);
			return map;
		}
		int id = Integer.parseInt(idStr);
		Integer updateResult = userService.updateHobby(hobby,id);
		map.put("updateOrNot", updateResult);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	@RequestMapping("/updateUsername.do")
	@ResponseBody
	public Map<String,Object> updateUsername(String idStr,String username,HttpServletRequest request){
		Map<String,Object > map = new HashMap<String,Object>();
		if(StringTools.isEmpty(idStr)||StringTools.isEmpty(username)){
			map.put("code", Codes.ERROR);
			return map;
		}
		int id = Integer.parseInt(idStr);
		Integer updateResult = userService.updateUsername(username,id);
		map.put("updateOrNot", updateResult);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	
	@RequestMapping("/updateSid.do")
	@ResponseBody
	public Map<String,Object> updateSid(String idStr,String sid,HttpServletRequest request){
		Map<String,Object > map = new HashMap<String,Object>();
		if(StringTools.isEmpty(idStr)||StringTools.isEmpty(sid)){
			map.put("code", Codes.ERROR);
			return map;
		}
		int id = Integer.parseInt(idStr);
		Integer updateResult = userService.updateSid(sid,id);
		map.put("updateOrNot", updateResult);
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	
		@RequestMapping("/updateAge.do")
		@ResponseBody
		public Map<String,Object> updateAge(String idStr,String age,HttpServletRequest request){
			Map<String,Object > map = new HashMap<String,Object>();
			if(StringTools.isEmpty(idStr)||StringTools.isEmpty(age)){
				map.put("code", Codes.ERROR);
				return map;
			}
			int id = Integer.parseInt(idStr);
			Integer updateResult = userService.updateAge(age,id);
			map.put("updateOrNot", updateResult);
			map.put("code", Codes.SUCCESS);
			return map;
		}
		
		/**
		 * 更新个人签名
		 * @author yangxujia
		 * @date 2015-1-27下午7:14:21
		 */
		@RequestMapping("/updatePersonalNote.do")
		@ResponseBody
		public Map<String,Object> updatePersonalNote(String idStr,String personalNote,HttpServletRequest request){
			Map<String,Object > map = new HashMap<String,Object>();
			if(StringTools.isEmpty(idStr)||StringTools.isEmpty(personalNote)){
				map.put("code", Codes.ERROR);
				return map;
			}
			int id = Integer.parseInt(idStr);
			Integer updateResult = userService.updatePersonalNote(personalNote,id);
			map.put("updateOrNot", updateResult);
			map.put("code", Codes.SUCCESS);
			return map;
		}
		
		/**
		 * 修改密码
		 * @author yangxujia
		 * @date 2015-1-27下午7:13:59
		 */
		@RequestMapping("/resetPassword.do")
		@ResponseBody
		public Map<String,Object> resetPassword(String idStr,String password,HttpServletRequest request){
			Map<String,Object > map = new HashMap<String,Object>();
			if(StringTools.isEmpty(idStr)||StringTools.isEmpty(password)){
				map.put("code", Codes.ERROR);
				return map;
			}
			ConvertMD5 md5 = new ConvertMD5();
			String passwordMd5 = md5.getMD5ofStr(password.trim()).toLowerCase();
			
			int id = Integer.parseInt(idStr);
			Integer updateResult = userService.resetPassword(passwordMd5,id);
			map.put("updateOrNot", updateResult);
			map.put("code", Codes.SUCCESS);
			return map;
		}
		
		/**
		 * 绑定手机
		 */
}
