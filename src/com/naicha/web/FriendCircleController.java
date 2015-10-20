package com.naicha.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.naicha.app.mode.FriendCircle;
import com.naicha.app.mode.Pictures;
import com.naicha.app.service.FriendCircleService;
import com.naicha.app.service.PicturesService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.ConvertMD5;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.Resize;
import com.naicha.app.utils.StringTool;
import com.naicha.web.vo.RespFriendCircle;

@Controller
@RequestMapping("/friendCircle")
public class FriendCircleController {
	@Autowired
	private FriendCircleService friendCircleService;
	@Autowired
	private PicturesService picturesService;
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String, Object> save(@RequestParam("files") MultipartFile[] files,
			String userIdStr,String content,String location,String jinwei,String token,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//非空判断
		if(StringTool.isEmpty(userIdStr)||StringTool.isEmpty(content)||StringTool.isEmpty(token)){
			map.put("msg", "参数不能为空");
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期！");
			map.put("code", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		//数据保存
		FriendCircle friendCircle = new FriendCircle();
		friendCircle.setContent(content);
		friendCircle.setLocation(location);
		friendCircle.setJinwei(jinwei);
		friendCircle.setUserId(Integer.parseInt(userIdStr));
		friendCircle.setTime(new Date());
		FriendCircle friendCircleReturn = new FriendCircle();
		friendCircleReturn = friendCircleService.save(friendCircle);
		Integer friendCircleId = friendCircleReturn.getId();
		try {
			if (files!=null&&files.length!=0) {
				//上传并保存图片
				pictureUpload(friendCircleId,files,request);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 图片上传保存	
	 */
     public String pictureUpload(Integer friendCircleId,@RequestParam("files") MultipartFile[] files,HttpServletRequest request) throws IOException{

    	 if(files!=null&&files.length>0){
    		 for(int i = 0;i<files.length;i++){
        		 MultipartFile file = files[i];
	        	 ConvertMD5 md5 = new ConvertMD5();
	             String pic_path =  request.getSession().getServletContext().getRealPath("/resource/news");  
	             /* 从当时时间MD5强制重命名图片*/
	             String pic_time = String.valueOf(System.currentTimeMillis());  
	             String pic_type = file.getContentType();
	             String file_ture_name = md5.getMD5ofStr(pic_time);
	             String littleFileName = file_ture_name;
	             if(pic_type.equals("image/jpeg")){
	                 file_ture_name =   file_ture_name.concat(".jpg");
	                 littleFileName = littleFileName.concat("_little.jpg");
	             } else if (pic_type.equals("image/png")){
	                 file_ture_name = file_ture_name.concat(".png");
	                 littleFileName = littleFileName.concat("_little.png");
	             } else if(pic_type.equals("image/bmp")){
	                 file_ture_name =  file_ture_name.concat(".bmp");
	                 littleFileName = littleFileName.concat("_little.bmp");
	             } else if(pic_type.equals("image/gif")){
	                 file_ture_name = file_ture_name.concat(".gif");
	                 littleFileName = littleFileName.concat("_little.gif");
	             } else{
	            	 file_ture_name = file_ture_name.concat(".jpg");
	             	littleFileName = littleFileName.concat("_little.jpg");
	             }
	             /*保存文件*/
	             BufferedImage image = ImageIO.read(file.getInputStream());
	             FileUtils.copyInputStreamToFile(file.getInputStream(), new File(pic_path, file_ture_name)); 
	             //保存小图
	             BufferedImage imagelittle =Resize.rize(image,200,200);
	         	 try {
    				 File ff= new File(pic_path+"/"+littleFileName);
					 ImageIO.write(imagelittle, "gif", ff);
				 } catch (IOException e) {
				 	 e.printStackTrace();
				 }
	         	Pictures pictures =  new Pictures();
	    		pictures.setFriendCircleId(friendCircleId);
	    		pictures.setHight(image.getHeight());
	    		pictures.setWidth(image.getWidth());
	    		pictures.setPath("friendCircle/"+file_ture_name);
	    		pictures.setTime(new Date());
	    		picturesService.save(pictures);
    		 }
    	 }
    	 return Codes.SUCCESS;
     }
 	@RequestMapping("/findNearby.do")
 	@ResponseBody
 	public Map<String, Object> find(HttpServletRequest request,String jinwei){
 		Map<String, Object> map = new HashMap<String, Object>();
 		if (StringTool.isEmpty(jinwei)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
 		List<RespFriendCircle> friendCircleList =  new ArrayList<RespFriendCircle>();
 		friendCircleList = friendCircleService.findNearbyOrderByDistance(jinwei);
 		map.put("list", friendCircleList);
 		map.put("code", Codes.SUCCESS);
 		return map;
 	}
 	@RequestMapping("/findPicture.do")
 	@ResponseBody
 	public Map<String, Object> findPicture(HttpServletRequest request,String jinwei){
 		Map<String, Object> map = new HashMap<String, Object>();
 		List<Integer> idList = new ArrayList<Integer>();
 		idList.add(4);
 		idList.add(5);
 		idList.add(6);
 		List<Pictures> picList = picturesService.findByFriendCircleId(idList);
 		map.put("picList", picList);
 		map.put("code", Codes.SUCCESS);
 		return map;
 	}
 	
 	/**
 	 * 根据id查找个人朋友圈内容
 	 * @author yangxujia
 	 * @date 2015年9月26日上午10:42:18
 	 */
 	@RequestMapping("/findByUserId.do")
 	@ResponseBody
 	public Map<String, Object> findByUserId(HttpServletRequest request,String userIdStr){
 		Map<String, Object> map = new HashMap<String, Object>();
 		if(StringTool.isEmpty(userIdStr)){
 			map.put("code", Codes.PARAMETER_IS_EMPTY);
 			return map;
 		}
		List<RespFriendCircle> friendCircleList =  new ArrayList<RespFriendCircle>();
 		friendCircleList = friendCircleService.findByUserId(userIdStr);
 		map.put("friendCircleList", friendCircleList);
 		map.put("code", Codes.SUCCESS);
 		return map;
 	}
}
