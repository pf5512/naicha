package com.naicha.app.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.FriendCircleDao;
import com.naicha.app.mode.FriendCircle;
import com.naicha.app.mode.Pictures;
import com.naicha.app.service.FriendCircleService;
import com.naicha.app.service.PicturesService;
import com.naicha.app.utils.AppUtils;
import com.naicha.app.utils.Geohash;
import com.naicha.web.vo.RespFriendCircle;

@Service
@Transactional
public class FriendCircleServiceImpl implements FriendCircleService {

	@Autowired
	private FriendCircleDao friendCircleDao;
	@Autowired
	private  PicturesService picturesService;
	
	@Override
	public FriendCircle save(FriendCircle friendCircle) {
		return friendCircleDao.save(friendCircle);
	}

	@Override
	public List<RespFriendCircle> findNearbyOrderByDistance(String jinwei) {
		String geohash = new Geohash().getGeohashCode(jinwei);
		String geohashCode="";
		List<RespFriendCircle> respFriendCircleList= new ArrayList<RespFriendCircle>();
		geohashCode = geohash.substring(0,4)+"%";//4,代表20公里内
		List<Object[]>  objectsList = friendCircleDao.findNearbyOrderByDistance(geohashCode);
		List<Integer> friendIdList =  new ArrayList<Integer>();
		for (Object[] obj : objectsList) {
			friendIdList.add((Integer)obj[0]);//收集朋友圈id
			RespFriendCircle respFriendCircle =  new RespFriendCircle();
			respFriendCircle.setId((Integer)obj[0]);
			respFriendCircle.setUserId((Integer) obj[1]);
			respFriendCircle.setContent((String)obj[2]);
			Date dateTime = (Date)obj[3];
			String time = calcDate(dateTime);
			respFriendCircle.setTime(time);//
			respFriendCircle.setHeadPicture((String)obj[4]);
			respFriendCircle.setName((String)obj[5]);
			respFriendCircle.setSex((Integer)obj[6]);
			respFriendCircle.setAddress((String)obj[7]);
			String friendCircleJinwei = (String)obj[8];
			//通过经纬度计算距离
			Double distance =  getDistance(jinwei,friendCircleJinwei);
			respFriendCircle.setDistance(distance);
			respFriendCircleList.add(respFriendCircle);
		}
		//2、获取朋友圈图片
		List<Pictures> picList =  picturesService.findByFriendCircleId(friendIdList);
		//3、获取朋友圈评论
		//4、获取朋友圈赞
		//5、拼上图片
		List<RespFriendCircle> respListNew = new ArrayList<RespFriendCircle>();
		for (RespFriendCircle respFriendCircle : respFriendCircleList){
			Integer friendCircleId = respFriendCircle.getId();
			List<Pictures> picList2 =  new AppUtils<Pictures>().findByContentId(friendCircleId, picList);
			respFriendCircle.setPictursList(picList2);
			respListNew.add(respFriendCircle);
		}
		 //排序
	    Comparator<RespFriendCircle> comparator = new Comparator<RespFriendCircle>() {
	        public int compare(RespFriendCircle s1, RespFriendCircle s2) {
	                return (int) (s1.getDistance() - s2.getDistance());
	        }
	    };
	    Collections.sort(respListNew,comparator);
		return respListNew;
	}
	
	private Double getDistance(String jinwei, String friendCircleJinwei) {
		double PI = 3.14159265358979323; // 圆周率
		double R = 6371229; // 地球的半径
		String str[]=jinwei.split(",");
		double longt1= Double.parseDouble(str[0]);
		double lat1 = Double.parseDouble(str[1]);
		String str2[] = friendCircleJinwei.split(",");
		double longt2=Double.parseDouble(str2[0]);
		double lat2=Double.parseDouble(str2[1]);
        double x, y, distance;
        x = (longt2 - longt1) * PI * R
                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);
        return distance;
    
	}

	/**
	 * 时间计算方法
	 * @param date
	 * @return
	 */
	private String calcDate(Object date){
		Date d = (Date)date;
		Long t= d.getTime();
		Long now = new Date().getTime();
		  long ss=(now-t)/(1000); //共计秒数  
		  int MM = (int)ss/60;   //共计分钟数  
		  int hh=(int)ss/3600;  //共计小时数  
		  int dd=(int)hh/24;   //共计天数 
		  if(dd>0){
			  if(dd==1){
				  return "昨天";
			  }else{
				  return dd+"天前"; 
			  }
		  }else if(hh>0){
			 return hh+"小时前";
		  }else if(MM>0){
			 return MM +"分钟前";
		  }else{
			 return "刚刚"; 
		  }
	}
}
