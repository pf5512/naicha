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


//任务列表
function taskList(){
	console.log("in taskList()");
	//1.2任务统计_当天
	$('#toTop').click(function(){
		alert();
		top = "3";
		var id = $(this).parent().find('.id').text();
		alert(id);
		totop(top,id);
	});
	//1.2刷新
	$('#refresh').click(function(){
		timeType = "1";
		currentPage = "1";
		getTaskList(timeType);
	});
	//1.2隐藏
	$('#hide').click(function(){
		timeType = "1";
		currentPage = "1";
		getTaskList(timeType);
	});
	//1.2展示
	$('#show').click(function(){
		timeType = "1";
		currentPage = "1";
		getTaskList(timeType);
	});
	//1.2任务统计_当天
	$('#today').click(function(){
		timeType = "1";
		currentPage = "1";
		getTaskList(timeType);
	});
	//1.2任务统计_本周
	$('#thisWeek').click(function(){
		timeType = "7";
		currentPage = "1";
		getTaskList(timeType);
	});
	//1.4.1评价统计和列表_本月
	$('#thisMonth').click(function(){
		timeType = "30";
		currentPage = "1";
		getTaskList(timeType);
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

//1.2获取任务列表
function getTaskList(timeType){

	$.post("task/findByTimeType.do",
		{
		timeType:timeType,
			currentPage: currentPage,
			pageSize: pagesi
		},
		function(data){
			//console.log(JSON.stringify(data));
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
				childhtml += '<tr>';
				childhtml += '<td class="table_cell publicTime">'+publicTime+'</td>';
				childhtml += '<td class="table_cell id">'+id+'</td>';
				childhtml += '<td class="table_cell name">'+name+'</td>';
				childhtml += '<td class="table_cell reward">'+reward+'</td>';
				childhtml += '<td class="table_cell servicesTime">'+servicesTime+'</td>';
				childhtml += '<td class="table_cell timeLength">'+timeLength+'</td>';
				childhtml += '<td class="table_cell notes">'+notes+'</td>';
				childhtml += '<td class="table_cell status">'+status+'</td>';
				childhtml += '<td class="table_cell signupCount">'+signupCount+'</td>';
				childhtml += '<td class="table_cell signupCount"><a id="toTop">置顶</a>&nbsp<a id="refresh">刷新</a>&nbsp<a id="hide">隐藏</a>/<a id="show">显示</a></td>';
				childhtml += '</tr>';
			});
			$('#js_detail').html(childhtml);
		});
}

//1.2 置顶，刷新，隐藏，显示
function totop(top,id){
	$.post("task/toTop.do",
			{
			totop:top,
			id:id
			},
			function(data){
				if(data.codes == -3){
					$("#errorInput").children().text("该奖金档位已经存在");
					$("#errorInput").show();
					setTimeout('$("#errorInput").hide()',3000);
					return  false;
				}
				setTaskReward();
			});
}


