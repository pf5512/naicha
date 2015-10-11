var eventType = "ontouchstart" in document.documentElement ? "touchstart" : "click";
var isMobile = eventType == "touchstart";
var WW = $(document).width(), HH = $(document).height();
var _UID = 0, _SID = "";
var oldDataContentid = 0;
$.ajaxSetup({
	  async: true
	  });
function mainInit() {
	initView();
	
	$("div[id=_url_float_icon]").each(function() {
		$(this).on(eventType, function() {
			var contentId = $(this).attr("data-contentid");
			$("div[id=_url_float_icon]").each(function() {
				if(contentId != oldDataContentid) 
					$(this).nextAll("#_float_view").hide();
			});
			oldDataContentid = contentId;
			var url = baseUrl + "appInteraction/findLinkInLinkFraise.do?sid="+SID()+"&contentId="+contentId;
			var clazz  = $(this).nextAll("#_float_view");
			clazz.find("#_float_view_zan").attr("disabled", true);
			clazz.find("#_float_view_addlink").attr("disabled", true);
			clazz.toggle("fast");
			$.getJSON(url, function(data) {
				var praise = data.resultOfPraise;
				var link = data.resultOfLink;
				if(praise > 0) {
					clazz.find("#_float_view_zan").attr("ispraise", "0");
					clazz.find("#_float_view_zan").addClass("zan_H");
				}else {
					clazz.find("#_float_view_zan").attr("ispraise", "1");
				}
				
				if(link > 0) {
					clazz.find("#_float_view_addlink").attr("isaddLink", "1");
					clazz.find("#_float_view_addlink").addClass("add-link_H");
				}else {
					clazz.find("#_float_view_addlink").attr("isaddLink", "0");
				}
				clazz.find("#_float_view_zan").attr("disabled", false);
				clazz.find("#_float_view_addlink").attr("disabled", false);
//				clazz.toggle("fast");
			});
		});
	});
	
	$("div[id=_float_view_zan]").each(function() {
		$(this).on(eventType, function() {
			var isPraise = $(this).attr("isPraise");
			var contentId = $(this).attr("data-contentid");
			var url = baseUrl + "appInteraction/linkInLinkPraiseSave.do?uid="+UID()+"&sid="+SID()+"&contentId="+contentId+"&isPraise="+isPraise;
			var clazz = $(this);
			$.getJSON(url, function(data) {
				if(isPraise > 0) {
					clazz.attr("ispraise", "0");
					clazz.addClass("zan_H");
				}else {
					clazz.attr("ispraise", "1");
					clazz.removeClass("zan_H");
				}
			});
		});
	});
	
	$("div[id=_float_view_addlink]").each(function() {
		$(this).on(eventType, function() {
			var contentId = $(this).attr("data-contentid");
			var url = baseUrl + "appInteraction/linkInLinkSave.do?uid="+UID()+"&sid="+SID()+"&urlid="+contentId;
			var clazz = $(this);
			var isAddLink = clazz.attr("isaddLink");
			var title = clazz.attr("data-title");
			if(isAddLink > 0) {
				alert("此链接您已经添加过！");
				return ;
			}
			$.post(url, {stitle:title}, function(data) {
				if(data.code == "2") {
					clazz.attr("isaddLink", "1");
					clazz.addClass("add-link_H");
				}
			});
		});
	});
	
	/* ############ m_search_keyword.jsp ############# */
	$("div[id=_kw_url_preview]").each(function() {
		$(this).on(eventType, function() {
			var kwUrlIframe = $(this).parent().parent().next("#_kw_url_iframe");
			kwUrlIframe.toggle();
			if($(this).hasClass("kw-url-preview-icon_H")) {
				$(this).removeClass("kw-url-preview-icon_H");
				return ;
			}else {
				$(this).addClass("kw-url-preview-icon_H");
			}
			var iframeUrl = kwUrlIframe.attr("data-url");
			if(kwUrlIframe.find("iframe").length > 0) {
				kwUrlIframe.find("iframe").attr("src", iframeUrl);
				return ;
			}
			var iframeHtml = '<iframe id="_kw_url_iframe" src="'+iframeUrl+'" style="width:100%;height:109px;border:none;"></iframe>';
			kwUrlIframe.html(iframeHtml);
		});
	});   

	//搜索引擎切换
	$("#_kw_tabs a").on(eventType, function() {
		var showLi = $("#_kw_tabs li[isshow='isshow']");
		showLi.removeClass(showLi.attr("data-show-class"));
		showLi.attr("isshow", "noshow");
		$(this).parent().addClass($(this).parent().attr("data-show-class"));
		$(this).parent().attr("isshow", "isshow");
		$("#_m_result_name").text($(this).parent().attr("data-name"));
		
		var dataTarget = $(this).attr("data-target");
		var dataUrl = $(this).attr("data-url");
		dataUrl && (function() {
			if($("#_kw_browser_iframe iframe").length > 0) {
				$("#_kw_browser_iframe iframe").attr("src", dataUrl);
				return ;
			}
			var iframeHeight = HH - 50;
			var iframeHtml = '<iframe id="_browser_iframe" src="'+dataUrl+'" style="width:100%;height:'+iframeHeight+'px;border:none;"></iframe>';
			$("#_kw_browser_iframe").html(iframeHtml);
		})();
		var hideId = dataTarget == "_kw_content" ? "_kw_browser_iframe" : "_kw_content";
		$("#"+hideId).hide();
		$("#"+dataTarget).show();
	});
	
	//loading tools
	var urlids = "";
	$("div[id=_kw_url_tools]").each(function() {
		urlids += "," + $(this).attr("data-urlid");
	});
	urlids = urlids.substr(1);
	$.post(baseUrl + "appSearch/urlAndPraiseCount.do", {
		urlids:urlids,
		uid:UID(),
		sid:SID()
		}, 
		function(data) {
		var isLogin = data.isLogin == "isLogin";
		var countMapJson = data.countMap; 
		if(isLogin && countMapJson) {
			$("div[id=_kw_url_tools]").each(function() {
				var urlid = $(this).attr("data-urlid");
				$(this).find("#_kw_url_addlink").attr("isaddLink", countMapJson[urlid].indexOf("1_") == 0 ? "1" : "0").show();
				$(this).find("#_kw_url_zan").addClass("kw-url-zan-icon_"+countMapJson[urlid]).show();
			});
		}
	});
	
	//添加链接
	$("div[id=_kw_url_addlink]").each(function() {
		$(this).on(eventType, function() {
			var contentId = $(this).parent().attr("data-urlid");
			var url = baseUrl + "appInteraction/linkInLinkSave.do?uid="+UID()+"&sid="+SID()+"&urlid="+contentId;
			var clazz = $(this);
			var isAddLink = clazz.attr("isaddLink");
			var title = clazz.parent().attr("data-title");
			if(isAddLink > 0) {
				alert("此链接您已经添加过！");
				return ;
			}
			$.post(url, {stitle:title}, function(data) {
				if(data.code == "2") {
					clazz.attr("isaddLink", "1");
					alert("添加成功！");
				}
			});
		});
	});
	
	//添加反搜索事件
	$("div[id=_url_fsearch_icon]").on(eventType, function() {
		var urlid = $(this).attr("data-urlid");
		if(urlid) {
			window.location.href = baseUrl + "appSearch/reverseSearch.do?urlid=" + urlid;
		}
	});
};

function initView() {
	$("#_sre_home_center").length > 0 && $("#_sre_home_center").width(WW - 10);
	$("#_sre_url_url").length > 0 && $("#_sre_url_url").width(WW - 35);
	$("#_sre_homeurl").length > 0 && (function() {
		$("div[id=_sre_homeurl]").each(function() {
			$(this).width(WW - 70);
			$(this).find("#_sre_homelink_txt").width($(this).width() - 5 - 5 - 35 - 5);
		});
	})();
}

function connectWebViewJavascriptBridge(callback) {
	if (window.WebViewJavascriptBridge) {
		callback(WebViewJavascriptBridge)
	} else {
		document.addEventListener('WebViewJavascriptBridgeReady', function() {
                                  callback(WebViewJavascriptBridge)
                                  }, false)
	}
}

//if(UID() == 0) {
connectWebViewJavascriptBridge(function(bridge) {
	bridge.init(function(message, responseCallback) {
		var data = { 'Javascript Responds':'Wee!' }
		responseCallback(data)
	})
	
	bridge.callHandler('webGetLoginInfo', {}, function(response) {
		var resJson = $.parseJSON(response);
		_UID = resJson.uid;
		_SID = resJson.sid;
	});
	
	$("#_user_face").on(eventType, function(event) {
		var sid = $(this).attr("data-sid");
		if(sid) {
			bridge.callHandler('webSIDSearchShowSOKE', {"sid":sid}, function(response) {});
		}
	});
	
	$("div[id='_sre_faceimg_div']").on(eventType, function(event) {
		var sid = $(this).attr("data-sid");
		alert("sid---"+sid);
		if(sid) {
			bridge.callHandler('webSIDSearchShowSOKE', {"sid":sid}, function(response) {});
			
		}
	});
});
//}
mainInit();

function SID() {
	var logins = LOGIN();
	return logins["sid"];
}

function UID() {
	var logins = LOGIN();
	return logins["uid"];
}

function LOGIN() {
	return {"sid":_SID, "uid":_UID};
}