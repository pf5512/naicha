var pagesi = "5";//每页行数
var totalPage = "0";//总页数
var currentPage = "1";//当前页
var timeType = "1" //默认为
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

	//发布任务金额设置
	$('#setTaskReward').click(function(){
		getHtmlData("htmlres/setTaskReward.htm");
		});
	//1.4评价统计和列表
	$('#commentList').click(function(){
		getHtmlData("htmlres/commentList.htm");
		timeType = "1";
		currentPage = "1";
		queryForPages();
	});
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
			return;
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
			return;
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

});

//通过AJAX请求数据
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


function getHtmlData(path){
	
	$.ajax({
		
		 type:'GET',
		 url: path,
		 ansync:false,
	      success:function(data)		              {
	    	  $(".col_main").html(data);
	    	  
	    	  $("#today").on({
	  			click: function(){
	  				console.log("in on click");
	  			}
	  			}, "a");
		        }});
}