<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>口语认证</title>
</head>
<body>
<h2>口语认证</h2>

<form action="setup/oralIdentify.do" method="post" enctype="multipart/form-data">
	选择文件:<input type="file" name="files"><br>
	选择文件:<input type="file" name="files"><br>
	选择文件:<input type="file" name="files"><br>
	profession：<input type="text" name="profession"><br>
	rankStr：<input type="text" name="rankStr"><br>
	token:<input type="text" name="token"><br>
	userIdStr:<input type="text" name="userIdStr"><br>
	weixinNo:<input type="text" name="weixinNo"><br>
	phone:<input type="text" name="phone"><br>
	<input type="submit" value="提交"> 
</form>

</body>
</html>