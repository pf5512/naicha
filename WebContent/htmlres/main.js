var pagesi = "5";//每页行数
var totalPage = "0";//总页数
var currentPage = "1";//当前页
var timeType = "1" //默认为
	
	//主方法
$(function(){
	//menu样式切换
	$('.menu_item').click(function () {
		$('.menu_item').removeClass('selected');
		$(this).addClass('selected');
	});

	//当天本周，本月切换方法
	$('.js_top').click(function () {
		$('.js_top').removeClass('selected');
		$(this).addClass('selected');
	});

	//任务列表
	$('#taskList').click(function(){
		getHtmlData("taskList");
		timeType = "1";
		currentPage = "1";
		getTaskList(1);
	});
	
	//发布任务金额设置
	$('#setTaskReward').click(function(){
		getHtmlData("setTaskReward");
	});
	//1.4评价统计和列表
	$('#commentList').click(function(){
		getHtmlData("commentList");
		timeType = "1";
		currentPage = "1";
		queryForPages();
	});
});

//评价统计和列表
function commentList (){
	//console.log("in commentList()");
	//1.4.1评价统计和列表_当天
	$('#today').click(function(){
		timeType = "1";
		currentPage = "1";
		queryForPages();
	});
	//1.4.2评价统计和列表_本周
	$('#thisWeek').click(function(){
		timeType = "2";
		currentPage = "1";
		queryForPages();
	});
	//1.4.1评价统计和列表_本月
	$('#thisMonth').click(function(){
		timeType = "3";
		currentPage = "1";
		queryForPages();
	});
	//上一页
	$(".page_prev").click(function(){
		if(currentPage>1){
			currentPage-- ;
			$(".page_prev").css({display:"inline-block"});
			queryForPages();
		}
		if(currentPage==1){
			$(".page_prev").hide();
		}
		if(currentPage<totalPage){
			$(".page_next").css({display:"inline-block"});
		}
	});
	//下一页
	$(".page_next").click(function(){
		if(totalPage==1){
			return false;
		}
		if(currentPage<totalPage){
			currentPage++ ;
			$(".page_next").css({display:"inline-block"});
			queryForPages();
		}
		if(currentPage>1){
			$(".page_prev").css({display:"inline-block"});
		}
		if(currentPage==totalPage){
			$(".page_next").hide();
		}
	});
	//跳页
	$('.page_go').click(function(){
		currentPage = $('.goto_area').find('input').val();
		if(currentPage>totalPage){
			$("#errorInput").show();
			setTimeout('$("#errorInput").hide()',3000);
			return false;
		}
		queryForPages();
		if(currentPage==1){
			$(".page_prev").hide();
		}
		if(currentPage>1){
			$(".page_prev").css({display:"inline-block"});
		}
		if(currentPage<totalPage){
			$(".page_next").css({display:"inline-block"});
		}
		if(currentPage==totalPage){
			$(".page_next").hide();
		}
	});
}

//获取评论数据
function queryForPages(){
	$.post("comment/getCommentNaichaByTimeType.do",
		{
			timeType:timeType,
			currentPage: currentPage,
			pageSize: pagesi
		},
		function(data){
			if(data.codes!=1){
				return false;
			}
			//console.log(JSON.stringify(data));
			var good = data.countList[0].count;
			var middle = data.countList[1].count;
			var bad = data.countList[2].count;
			var total = good + middle + bad;
			$('#good').text(good);
			$('#middle').text(middle);
			$('#bad').text(bad);
			$('#total').text(total);
			totalPage = Math.ceil(total/pagesi);//总也数
			$('#currentPage').text(currentPage);
			$('#totalPage').text(totalPage);
			var childhtml = '';
			$.each(data.commentNaichaList, function(idx, obj) {
				var time = obj.time;
				var rank = '';
				switch(obj.rank)
				{
					case 1:
						rank = '差评';
						break;
					case 2:
						rank = '一般';
						break;
					default:
						rank = '好评';
				}
				var content = obj.content;
				var beCommentName = obj.beCommentName;
				var toCommentName = obj.toCommentName;
				childhtml += '<tr>'
				childhtml += '<td class="table_cell js_time">'+time+'</td>';
				childhtml += '<td class="table_cell js_rank">'+rank+'</td>';
				childhtml += '<td class="table_cell js_content">'+content+'</td>';
				childhtml += '<td class="table_cell js_time">'+beCommentName+'</td>';
				childhtml += '<td class="table_cell js_time">'+toCommentName+'</td>';
				childhtml += '</tr>';
			});
			$('#js_detail').html(childhtml);
		});
}

//加载自定义DIV
function getHtmlData(action) {
	var path = "htmlres/" + action + ".htm";
	var myfun = action + "()";//自定义函数
	$.ajax({
		type : 'GET',
		url : path,
		success : function(data) {
			$(".col_main").html(data);
			eval(myfun);
		}
	});
}

function setTaskReward(){
	$.post("reward/getRewardList.do",
		function(data){
			if(data.codes!=1){
				return false;
			}
			var childhtml = '';
			$.each(data.rewardList, function(idx, obj) {
				var reward = obj.reward;
				childhtml += '<tr>';
				childhtml += '<td class="table_cell " id ="reward" style="text-align:right">'+reward+'</td>';
				childhtml += '<td class="table_cell "><a class="btn rewardDelete" >删除</a>	</td>';
				childhtml += '</tr>';
			});
			$('#rewardGroup').html(childhtml);
			//删除
			$('.rewardDelete').click(function(){
				var rew = $(this).parent().prev().text();
				 deleteByReward(rew);
			});
			//增加
			$('#rewardGroupAdd').click(function(){
				var rew = $('#rewardInput').val();
				//console.log("rewardInput : "+rew);
				
				addReward(rew);
				$('#rewardInput').val('');
			});
		});
}

//删除奖金
function deleteByReward(rew){
	$.post("reward/deleteByReward.do",
			{
				rewardStr:rew
			},
			function(data){
				//console.log(JSON.stringify(data));
				setTaskReward();
	});
}

//增加奖金
function addReward(rew){
	$.post("reward/save.do",
			{
		rewardString:rew
			},
			function(data){
				//console.log(data.codes);
				if(data.codes == -3){
					$("#errorInput").children().text("该奖金档位已经存在");
					$("#errorInput").show();
					setTimeout('$("#errorInput").hide()',3000);
					return  false;
				}
				
				setTaskReward();
			});
}


//1.2任务列表
function taskList(){
	console.log("in taskList()");
	//1.2任务统计_当天
	$('#today').click(function(){
		timeType = "1";
		currentPage = "1";
		getTaskList(timeType);
		$('#js_pagebar').show();
	});
	//1.2任务统计_本周
	$('#thisWeek').click(function(){
		timeType = "7";
		currentPage = "1";
		getTaskList(timeType);
		$('#js_pagebar').show();
	});
	//1.2评价统计和列表_本月
	$('#thisMonth').click(function(){
		timeType = "30";
		currentPage = "1";
		getTaskList(timeType);
		$('#js_pagebar').show();
	});
	//1.2按任务id进行查询
	$('#searchByTaskId').click(function(){
		var taskId = $('#searchByTaskId').next().val();
		console.log("taskId : "+taskId);
		getTaskListbyTaskId(taskId);
		$('#js_pagebar').hide();
	});
	//1.4.1按雇主名称进行查询
	$('#searchByName').click(function(){
		var name = $('#searchByName').next().val();
		console.log("name : "+name);
		getTaskListbyName(name);
		$('#js_pagebar').hide();
	});
	
	//上一页
	$(".page_prev").click(function(){
		if(currentPage>1){
			currentPage-- ;
			$(".page_prev").css({display:"inline-block"});
			getTaskList(timeType);
		}
		if(currentPage==1){
			$(".page_prev").hide();
		}
		if(currentPage<totalPage){
			$(".page_next").css({display:"inline-block"});
		}
	});
	//下一页
	$(".page_next").click(function(){
		if(totalPage==1){
			return false;
		}
		if(currentPage<totalPage){
			currentPage++ ;
			$(".page_next").css({display:"inline-block"});
			getTaskList(timeType);
		}
		if(currentPage>1){
			$(".page_prev").css({display:"inline-block"});
		}
		if(currentPage==totalPage){
			$(".page_next").hide();
		}
	});
	//跳页
	$('.page_go').click(function(){
		currentPage = $('.goto_area').find('input').val();
		if(currentPage>totalPage){
			$("#errorInput").show();
			setTimeout('$("#errorInput").hide()',3000);
			return false;
		}
		getTaskList(timeType);
		if(currentPage==1){
			$(".page_prev").hide();
		}
		if(currentPage>1){
			$(".page_prev").css({display:"inline-block"});
		}
		if(currentPage<totalPage){
			$(".page_next").css({display:"inline-block"});
		}
		if(currentPage==totalPage){
			$(".page_next").hide();
		}
	});
}

//1.2 通过时间获取任务列表
function getTaskList(timeType){

	$.post("task/findByTimeType.do",
		{
		timeType:timeType,
			currentPage: currentPage,
			pageSize: pagesi
		},
		function(data){
			console.log(JSON.stringify(data));
			if(data.codes!=1){
				return false;
			}
			var total = data.total;
			
			totalPage = Math.ceil(total/pagesi);//总也数
			$('#currentPage').text(currentPage);
			$('#totalPage').text(totalPage);
			var childhtml = '';
			$.each(data.taskList, function(idx, obj) {
				var publicTime = obj.publicTime;//发布时间
				var id =  obj.id;//任务编号
				var name = obj.name;//雇主名称
				var servicesTime = obj.servicesTime//服务时间
				var timeLength = obj.timeLength;//时长
				var notes =  obj.notes;//备注
				var status =  obj.status;//状态
				var signupCount = obj.signupCount;//报名人数
				var reward = obj.reward;//报名人数
				var totop =  '';//置顶情况
				switch(obj.totop){
				case 3:
					totop = '已置顶';
					break;
				case 2:
					totop = '已刷新';
					break;
				case 0:
					totop = '已隐藏';
					break;
				default:
					break;
				}
				childhtml += '<tr>'
				+ '<td class="table_cell publicTime">'+publicTime+'</td>'
				+ '<td class="table_cell id">'+id+'</td>'
				+ '<td class="table_cell name">'+name+'</td>'
				+ '<td class="table_cell reward">'+reward+'</td>'
				+ '<td class="table_cell servicesTime">'+servicesTime+'</td>'
				+ '<td class="table_cell timeLength">'+timeLength+'</td>'
				+ '<td class="table_cell notes">'+notes+'</td>'
				+ '<td class="table_cell status">'+status+'</td>'
				+ '<td class="table_cell signupCount">'+signupCount+'</td>'
				+ '<td class="table_cell totop">' +totop+'</td>'//置顶结果
				+ '<td class="table_cell opera">'
					+ '<a href="javascript:void(0)" class="js_toTop">置顶</a>&nbsp'
					+ '<a href="javascript:void(0)" class="js_refresh">刷新</a>&nbsp'
					+ '<a href="javascript:void(0)" class="js_hide">隐藏</a>/'
					+ '<a href="javascript:void(0)" class="js_show">显示</a>'
				+ '</td>'
				+ '</tr>';
			});
			$('#js_detail').html(childhtml);
			//1.2任务统计_置顶
			$('.js_toTop').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				console.log("id input : "+id);
				totop(3,id);
				getTaskList(timeType);
			});
			//1.2刷新
			$('.js_refresh').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				console.log("id input : "+id);
				totop(2,id);
				getTaskList(timeType);
			});
			//1.2显示
			$('.js_show').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				console.log("id input : "+id);
				totop(1,id);
				getTaskList(timeType);
			});
			//1.2隐藏
			$('.js_hide').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				console.log("id input : "+id);
				totop(0,id);
				getTaskList(timeType);
			});
		});
}

//1.2 置顶，刷新，隐藏，显示
function totop(top,id){
	console.log("top_id"+top+"_"+id);
	$.ajax({
		type:'post',
		url:'task/toTop.do',
		data:{totop:top, id:id},
		dataType:'json',
		async:false,
		success: function(data){
			console.log(JSON.stringify(data));
			if(data.codes!=1){
				return false;
			}
			if(data.codes == -3){
				$("#errorInput").children().text("该奖金档位已经存在");
				$("#errorInput").show();
				setTimeout('$("#errorInput").hide()',3000);
				return  false;
			}
			return false;
		}
	});
}

function getTaskListbyTaskId(taskId){
	$.ajax({
		type: 'POST',
		url: 'task/findByTaskId.do',
		data: {taskId:taskId},
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			if(data.codes!=1){
				return false;
			}
			var total = data.total;
			
			totalPage = 1;//总也数
			$('#currentPage').text(currentPage);
			$('#totalPage').text(totalPage);
			var childhtml = '';
			$.each(data.taskList, function(idx, obj) {
				var publicTime = obj.publicTime;//发布时间
				var id =  obj.id;//任务编号
				var name = obj.name;//雇主名称
				var servicesTime = obj.servicesTime//服务时间
				var timeLength = obj.timeLength;//时长
				var notes =  obj.notes;//备注
				var status =  obj.status;//状态
				var signupCount = obj.signupCount;//报名人数
				var reward = obj.reward;//报名人数
				var totop =  '';//置顶情况
				switch(obj.totop){
				case 3:
					totop = '已置顶';
					break;
				case 2:
					totop = '已刷新';
					break;
				case 0:
					totop = '已隐藏';
					break;
				default:
					break;
				}
				childhtml += '<tr>'
				+ '<td class="table_cell publicTime">'+publicTime+'</td>'
				+ '<td class="table_cell id">'+id+'</td>'
				+ '<td class="table_cell name">'+name+'</td>'
				+ '<td class="table_cell reward">'+reward+'</td>'
				+ '<td class="table_cell servicesTime">'+servicesTime+'</td>'
				+ '<td class="table_cell timeLength">'+timeLength+'</td>'
				+ '<td class="table_cell notes">'+notes+'</td>'
				+ '<td class="table_cell status">'+status+'</td>'
				+ '<td class="table_cell signupCount">'+signupCount+'</td>'
				+ '<td class="table_cell totop">' +totop+'</td>'//置顶结果
				+ '<td class="table_cell opera">'
					+ '<a href="javascript:void(0)" class="js_toTop">置顶</a>&nbsp'
					+ '<a href="javascript:void(0)" class="js_refresh">刷新</a>&nbsp'
					+ '<a href="javascript:void(0)" class="js_hide">隐藏</a>/'
					+ '<a href="javascript:void(0)" class="js_show">显示</a>'
				+ '</td>'
				+ '</tr>';
			});
			$('#js_detail').html(childhtml);
			//1.2任务统计_置顶
			$('.js_toTop').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				totop(3,id);
				getTaskListbyTaskId(taskId);
				console.log("id: "+id);
				console.log("taskId: "+taskId);
			});
			//1.2刷新
			$('.js_refresh').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				console.log("id input : "+id);
				totop(2,id);
				getTaskListbyTaskId(taskId);
			});
			//1.2显示
			$('.js_show').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				console.log("id input : "+id);
				totop(1,id);
				getTaskListbyTaskId(taskId);
			});
			//1.2隐藏
			$('.js_hide').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				console.log("id input : "+id);
				totop(0,id);
				getTaskListbyTaskId(taskId);
			});
			return false;
		}
	});
}

function getTaskListbyName(name){
	$.ajax({
		type: 'POST',
		url: 'task/findByName.do',
		data: {name:name},
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			if(data.codes!=1){
				return false;
			}
			var total = data.total;
			
			totalPage = 1;//总也数
			$('#currentPage').text(currentPage);
			$('#totalPage').text(totalPage);
			var childhtml = '';
			$.each(data.taskList, function(idx, obj) {
				var publicTime = obj.publicTime;//发布时间
				var id =  obj.id;//任务编号
				var name = obj.name;//雇主名称
				var servicesTime = obj.servicesTime//服务时间
				var timeLength = obj.timeLength;//时长
				var notes =  obj.notes;//备注
				var status =  obj.status;//状态
				var signupCount = obj.signupCount;//报名人数
				var reward = obj.reward;//报名人数
				var totop =  '';//置顶情况
				switch(obj.totop){
				case 3:
					totop = '已置顶';
					break;
				case 2:
					totop = '已刷新';
					break;
				case 0:
					totop = '已隐藏';
					break;
				default:
					break;
				}
				childhtml += '<tr>'
				+ '<td class="table_cell publicTime">'+publicTime+'</td>'
				+ '<td class="table_cell id">'+id+'</td>'
				+ '<td class="table_cell name">'+name+'</td>'
				+ '<td class="table_cell reward">'+reward+'</td>'
				+ '<td class="table_cell servicesTime">'+servicesTime+'</td>'
				+ '<td class="table_cell timeLength">'+timeLength+'</td>'
				+ '<td class="table_cell notes">'+notes+'</td>'
				+ '<td class="table_cell status">'+status+'</td>'
				+ '<td class="table_cell signupCount">'+signupCount+'</td>'
				+ '<td class="table_cell totop">' +totop+'</td>'//置顶结果
				+ '<td class="table_cell opera">'
					+ '<a href="javascript:void(0)" class="js_toTop">置顶</a>&nbsp'
					+ '<a href="javascript:void(0)" class="js_refresh">刷新</a>&nbsp'
					+ '<a href="javascript:void(0)" class="js_hide">隐藏</a>/'
					+ '<a href="javascript:void(0)" class="js_show">显示</a>'
				+ '</td>'
				+ '</tr>';
			});
			$('#js_detail').html(childhtml);
			//1.2任务统计_置顶
			$('.js_toTop').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				totop(3,id);
				getTaskListbyName(name);
			});
			//1.2刷新
			$('.js_refresh').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				totop(2,id);
				getTaskListbyName(name);
			});
			//1.2显示
			$('.js_show').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				totop(1,id);
				getTaskListbyName(name);
			});
			//1.2隐藏
			$('.js_hide').click(function(){
				var id = $(this).parent().parent().find('.id').text();
				totop(0,id);
				getTaskListbyName(name);
			});
			return false;
		}
	});
}
