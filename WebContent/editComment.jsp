<%@ page language="java" contentType="text/html; "
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>对新闻的评论</title>
</head>
<body>
	<h2>对新闻的评论</h2>
uid=86&sid=006006&contentId=2&comment=pinglunneirong&pid=0
	<form action="appInteraction/newsCommentSave.do" method="post"
		enctype="multipart/form-data">
		<p>	
			uid:<input type="text" name="uid">
		<p>	
			sid:<input type="text" name="sid">
		<p>	
			contentId:<input type="text" name="contentId">
		<p>	
			comment:<textarea name="comment"></textarea>
		<p>	
			pid:<input type="text" name="pid">
		<p>	
			<input type="submit" value="提交">
	</form>

</body>
</html>