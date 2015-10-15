package com.naicha.web;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.dialect.Ingres10Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.Task;
import com.naicha.app.mode.User;
import com.naicha.app.service.TaskService;
import com.naicha.app.service.UserService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.StringTool;
import com.naicha.app.utils.StringTools;
import com.naicha.web.vo.RespUser;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskservice;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String, Object> save(String taskType,String reward,String servicesTime,String timeLength,String notes,
			String jinwei,String userIdStr,String token,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//1.判断参数是否非空
		if (StringTool.isEmpty(token)||StringTool.isEmpty(userIdStr)
				||StringTool.isEmpty(taskType)||StringTool.isEmpty(reward)||StringTool.isEmpty(servicesTime)
				||StringTool.isEmpty(timeLength)) {
			map.put("msg", "参数不能为空！");
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//2.校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期！");
			map.put("codes", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		//3.插入数据
		Task task = new Task();
		task.setUserId(Integer.parseInt(userIdStr));
		task.setTaskType(Integer.parseInt(taskType));
		task.setReward(Integer.parseInt(reward));
		task.setPublicTime(new Date());
		task.setServicesTime(StringTools.StringToDatetime(servicesTime));
		task.setTimeLength(Integer.parseInt(timeLength));
		task.setNotes(notes);
		Task rtnTask = new Task();
		rtnTask=taskservice.save(task);
		if (rtnTask!=null) {
			map.put("rtnTask", rtnTask);
			map.put("codes", Codes.SUCCESS);
			return map;	
		}else{
			map.put("codes", Codes.ERROR);
			return map;	
		}
	}
	
	/**
	 * 任务首页加载，首次加载10条,按时间
	 * @author yangxujia
	 * @date 2015年9月17日上午11:52:01
	 */
	@RequestMapping("/findByTime.do")
	@ResponseBody
	public Map<String, Object> findByTime(String userIdStr,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (userIdStr==null) {
			userIdStr ="-1";
		}
		List<Task> taskList = taskservice.findByTime(Integer.parseInt(userIdStr));
		if (taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 上滑
	 * @author yangxujia
	 * @date 2015年9月17日下午4:51:11
	 */
	@RequestMapping("/findByTimeSlipeUp.do")
	@ResponseBody
	public Map<String, Object> findByTimeSlipeUp(String userIdStr,String servicesTime,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (StringTool.isEmpty(servicesTime)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		if (userIdStr==null) {
			userIdStr ="-1";
		}
		List<Task> taskList = taskservice.findByTimeSlipeUp(servicesTime,Integer.parseInt(userIdStr));
		if (taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	/**
	 * 任务详情
	 * @author yangxujia
	 * @date 2015年9月18日下午12:00:34
	 */
	@RequestMapping("/findDetail.do")
	@ResponseBody
	public Map<String, Object> findDetail(String id,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(id)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;	
		}
		Task taskDetail = taskservice.findDetail(Integer.parseInt(id));
		if (taskDetail==null) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskDetail);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 任务首页加载，首次加载20条,按距离排序
	 * @author yangxujia
	 * @date 2015年9月17日上午11:52:01
	 */
	@RequestMapping("/fingTA.do")
	@ResponseBody
	public Map<String, Object> findTA(String jinwei,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(jinwei)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		List<User> userList = taskservice.findTA(jinwei);
		if (userList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("userList", userList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 根据性别查找奶茶
	 * @author yangxujia
	 * @date 2015年9月23日下午2:14:00
	 */
	@RequestMapping("/findTABySex.do")
	@ResponseBody
	public Map<String, Object> findTABySex(String jinwei,String sex,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(jinwei)||StringTool.isEmpty(sex)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		List<User> userList = taskservice.findTABySex(jinwei,sex);
		if (userList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("userList", userList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 按口语水平查找奶茶
	 * @author yangxujia
	 * @date 2015年9月23日下午2:42:43
	 */
	@RequestMapping("/findTAByRank.do")
	@ResponseBody
	public Map<String, Object> findTAByRank(String jinwei,String pageStr,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(jinwei)||StringTool.isEmpty(pageStr)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		List<User> userList = taskservice.findTAByRank(jinwei,Integer.parseInt(pageStr));
		if (userList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("userList", userList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 查找男生发布的任务
	 * @author yangxujia
	 * @date 2015年9月22日上午9:56:40
	 */
	@RequestMapping("/findByTimeByBoy.do")
	@ResponseBody
	public Map<String, Object> findByTimeByBoy(String userIdStr,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (userIdStr==null) {
			userIdStr ="-1";
		}
		List<Task> taskList = taskservice.findByTimeByBoy(Integer.parseInt(userIdStr));
		if (taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 按截止时间查找男生
	 * 上拉查找更多
	 * @author yangxujia
	 * @date 2015年9月22日上午10:04:55
	 */
	@RequestMapping("/findByTimeByBoySlipeUp.do")
	@ResponseBody
	public Map<String, Object> findByTimeByBoySlipeUp(String userIdStr,String servicesTime,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (StringTool.isEmpty(servicesTime)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		if (userIdStr==null) {
			userIdStr ="-1";
		}
		List<Task> taskList = taskservice.findByTimeByBoySlipeUp(servicesTime,Integer.parseInt(userIdStr));
		if (taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 查找女生发布的任务
	 * @author yangxujia
	 * @date 2015年9月22日上午9:57:08
	 */
	@RequestMapping("/findByTimeByGirl.do")
	@ResponseBody
	public Map<String, Object> findByTimeByGirl(String userIdStr,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (userIdStr==null) {
			userIdStr ="-1";
		}
		List<Task> taskList = taskservice.findByTimeByGirl(Integer.parseInt(userIdStr));
		if (taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 按截止时间查找女生
	 * 上拉查找更多
	 * @author yangxujia
	 * @date 2015年9月22日上午10:04:55
	 */
	@RequestMapping("/findByTimeByGirlSlipeUp.do")
	@ResponseBody
	public Map<String, Object> findByTimeByGirlSlipeUp(String userIdStr,String servicesTime,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (StringTool.isEmpty(servicesTime)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		if (userIdStr==null) {
			userIdStr ="-1";
		}
		List<Task> taskList = taskservice.findByTimeByGirlSlipeUp(servicesTime,Integer.parseInt(userIdStr));
		if (taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 奶茶详情
	 */
	@RequestMapping("/findTADetail.do")
	@ResponseBody
	public Map<String, Object> findTADetail(String userIdStr,String jinwei,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)||StringTool.isEmpty(jinwei)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		RespUser user = userService.findTADetail(userIdStr,jinwei);
		if (user==null) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("user", user);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 雇主详情
	 */
	@RequestMapping("/findGuzhuDetail.do")
	@ResponseBody
	public Map<String, Object> findGuzhuDetail(String userIdStr,String jinwei,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)||StringTool.isEmpty(jinwei)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		RespUser user = userService.findGuzhuDetail(userIdStr,jinwei);
		if (user==null) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("user", user);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 根据雇主Id查看已发布的任务
	 * @author yangxujia
	 * @date 2015年9月25日下午3:53:16
	 */
	@RequestMapping("/findTaskByUserId.do")
	@ResponseBody
	public Map<String, Object> findTaskByUserId(String userIdStr,String token,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(userIdStr)||StringTool.isEmpty(token)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//2.校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期！");
			map.put("codes", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		List<Task> userList = taskservice.findTaskByUserId(userIdStr);
			map.put("userList", userList);
			map.put("codes", Codes.SUCCESS);
			return map;
	}
	
	/**
	 * 管理后台
	 * 查找当天的任务
	 * @param timeType 1 为当天，7为一周之内，30 为一个月之内
	 * @author yangxujia
	 * @date 2015年10月14日上午11:13:13
	 */
	@RequestMapping("/findByTimeType.do")
	@ResponseBody
	public Map<String, Object> findByTimeType(String timeType, String currentPage, String pageSize,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(timeType)||StringTool.isEmpty(currentPage)||StringTool.isEmpty(pageSize)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//获取总条数
		BigInteger total = taskservice.findByTimeTypeCount(timeType);
		List<Task> taskList = taskservice.findByTimeType(timeType,currentPage,pageSize);
		if (taskList==null||taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("total", total);
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 置顶功能top =3
	 * 刷新 totop =2
	 * 显示 totop =1
	 * 隐藏 totop =0
	 * @author yangxujia
	 * @date 2015年10月14日下午5:35:03
	 */
	@RequestMapping("/toTop.do")
	@ResponseBody
	public Map<String, Object> toTop(String id,String totop, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(id)||StringTool.isEmpty(totop)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//获取总条数
		Integer rtnCode = taskservice.toTop(totop,id);
			map.put("codes", rtnCode);
			return map;
	}
	
	/**
	 * 管理后台
	 * 按任务编号查询
	 * @author yangxujia
	 * @date 2015年10月14日上午11:13:13
	 */
	@RequestMapping("/findByTaskId.do")
	@ResponseBody
	public Map<String, Object> findByTaskId(String taskId,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(taskId)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//由于前端显示需要，这里采用List存一条数据
		List<Task> taskList = taskservice.findByTaskId(taskId);
		if (taskList==null||taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	/**
	 * 管理后台
	 * 按雇主名称进行搜索
	 * @author yangxujia
	 * @date 2015年10月14日上午11:13:13
	 */
	@RequestMapping("/findByName.do")
	@ResponseBody
	public Map<String, Object> findByName(String name,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(name)) {
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		List<Task> taskList = taskservice.findByName(name);
		if (taskList==null||taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
}
