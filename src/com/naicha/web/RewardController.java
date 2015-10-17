package com.naicha.web;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.Reward;
import com.naicha.app.service.RewardService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.StringTool;

@Controller
@RequestMapping("/reward")
public class RewardController {

	@Autowired
	private RewardService rewardService;
	/**
	 * 增加一条数据
	 * @author yangxujia
	 * @date 2015年10月12日下午2:23:02
	 */
	@RequestMapping("save.do")
	@ResponseBody
	public Map<String , Object> save(String rewardString,HttpServletRequest request){
		Map<String , Object> map =  new HashMap<String, Object>();
		if (StringTool.isEmpty(rewardString)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//查找是否存在
		BigInteger isExist = rewardService.isExist(rewardString);
		if (isExist.intValue()>0) {
			map.put("codes", Codes.REWARD_IS_EXIST);
			return map;
		}
		Reward reward  = new Reward();
		reward.setReward(Integer.parseInt(rewardString));
		reward.setTime(new Date());
		 Reward reward2 = rewardService.save(reward);
		 map.put("reward", reward2);
		 map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 获取奖金列表信息
	 * @author yangxujia
	 * @date 2015年10月12日下午2:23:19
	 */
	@RequestMapping("getRewardList.do")
	@ResponseBody
	public Map<String , Object> getRewardList(HttpServletRequest request){
		Map<String , Object> map =  new HashMap<String, Object>();
		List<Reward> rewardList =  rewardService.getRewardList();
		 map.put("rewardList", rewardList);
		 map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 删除一条记录
	 * @author yangxujia
	 * @date 2015年10月12日下午2:24:34
	 */
	@RequestMapping("deleteById.do")
	@ResponseBody
	public Map<String , Object> deleteById(String idString,HttpServletRequest request){
		Map<String , Object> map =  new HashMap<String, Object>();
		if (StringTool.isEmpty(idString)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer rtnCode = rewardService.deleteById(idString);
		 map.put("rtnCode", rtnCode);
		 map.put("codes", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 删除一条记录
	 * @author yangxujia
	 * @date 2015年10月12日下午2:24:34
	 */
	@RequestMapping("deleteByReward.do")
	@ResponseBody
	public Map<String , Object> deleteByReward(String rewardStr,HttpServletRequest request){
		Map<String , Object> map =  new HashMap<String, Object>();
		if (StringTool.isEmpty(rewardStr)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		Integer rtnCode = rewardService.deleteByReward(rewardStr);
		 map.put("rtnCode", rtnCode);
		 map.put("codes", Codes.SUCCESS);
		return map;
	}
}
