var documentWith = $(document).width() - 16;
var eventType = "ontouchstart" in document.documentElement ? "touchstart" : "click";
var isMobile = eventType == "touchstart";
var baseUrl = "http://192.168.1.114:8080/idbearWeb/";
var tempZan = '<li><div class="faceimg"><img alt="faceimg" src="resource/{src}"><span>{text}</span></div></li>';
var tempComment = '<li><div class="leftFace">'
+'<img alt="userFace" src="resource/{src}"></div>'
+'<div class="rightTxt" id="rightTxt">'
+'<div class="user-name">'
+'{user}<span>{time}</span></div>'
+'<div class="comment-text">{text}</div></div></li>';

var initPos = {},
lastPos = {},
isClick, clickTimer;
var onTouchStart = function(e) {
var t = e.touches[0];
isClick = true;
clickTimer = setTimeout(function () {
  isClick = false;
}, 300);
initPos.x = lastPos.x = t.clientX;
initPos.y = lastPos.y = t.clientY;
};
var onTouchMove = function(e) {
var t = e.touches[0];
lastPos.x = t.clientX;
lastPos.y = t.clientY;
};
var onTouchEnd = function (e) {
clearTimeout(clickTimer);
if (initPos.x === lastPos.x &&
  initPos.y === lastPos.y &&
  isClick) {
  dispatchEvent(e);
}
};

document.addEventListener('touchstart', onTouchStart, false);
document.addEventListener('touchmove', onTouchMove, false);
document.addEventListener('touchend', onTouchEnd, false);

(function() {
	documentWith = $("#content").width() / 2;
	$("#toolbar #comment").on(eventType, function() {
		if($(this).hasClass("commentA")) {
			$(this).removeClass("commentA");
			$("#toolcomment").hide();
			$("#toolzan").hide();
		}else {
			$(this).addClass("commentA");
			var contentid = $(this).attr("data-contentid");
			var type = $(this).attr("data-type");
			if(type == 1)
				newsComment(contentid);
			else if(type == 2)
				diaryComment(contentid);
			else
				linkComment(contentid);
		}
	});
	
	$("#toolbar #zan").on(eventType, function() {
	});
	
	$("#picList img").on(eventType, function() {
		$("#flickerImage").flicker();
		$("#flickerImage").show();
		$("#main").hide();
	});
	
	$("#flickerImage").on(eventType, function() {
//		$("#flickerImage").hide();
//		$("#main").show();
	});
	
})();

function newsComment(contentId) {
	var url = baseUrl + "appInteraction/findAllNewsFraiseComment.do?contentId=" + contentId;
	$.getJSON(url, function(data) {
		builderHtml(data);
	});
}

function diaryComment(contentId) {
	var url = baseUrl + "appInteraction/findAllDiaryFraiseComment.do?contentId=" + contentId;
	$.getJSON(url, function(data) {
		builderHtml(data);
	});
}

function linkComment(contentId) {
	var url = baseUrl + "appInteraction/findAllLinkFraiseComment.do?contentId=" + contentId;
	$.getJSON(url, function(data) {
		builderHtml(data);
	});
}

function builderHtml(data) {
	var listPraise = data.listPraise;
	var listComment = data.listComment;
	var zanHtmlArr = [], commentHtmlArr = [];
	for(var i=0; i<listPraise.length; i++) {
		var praise = listPraise[i];
		var liHtml = tempZan.replace("{src}", praise.headPicture).replace("{text}", praise.uName);
		zanHtmlArr.push(liHtml);
	}
	var zanHtml = zanHtmlArr.join("");
	$("#toolzan ul").html(zanHtml);
	$("#toolzan").show();
	
	for(var i=0; i<listComment.length; i++) {
		var comment = listComment[i];
		var liHtml = tempComment.replace("{src}", comment.headPicture).
		replace("{text}", comment.comment).
		replace("{user}", comment.uName).
		replace("{time}", comment.time);
		commentHtmlArr.push(liHtml);
	}
	var commentHtml = commentHtmlArr.join("");
	$("#toolcomment ul").html(commentHtml);
	$("#toolcomment").show();
}