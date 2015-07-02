<%@ page language="java" contentType="text/html; "
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; ">
<title>文件上传</title>
</head>
<body>
	<h2>上传多个文件 实例</h2>

	<form action="appDiary/save.do" method="post"
		enctype="multipart/form-data">
		<p>	
			uid:<input type="text" name="uid">
		<p>	
			sid:<input type="text" name="sid">
		<p>	
			title:<input type="text" name="title">
		<p>	
			content:<textarea name="content"></textarea>
		<p>	
			privatePublic:<input type="text" name="privatePublic">
		<p>	
			---------------------------------------
		<p>	
			选择文件:<input type="file" name="files">
		<p>
			选择文件:<input type="file" name="files">
		<p>
			选择文件:<input type="file" name="files">
		<p>
			<input type="submit" value="提交">
	</form>

</body>
</html>