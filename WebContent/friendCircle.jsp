<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>朋友圈保存</title>
</head>
<body>
<h2>文件上传实例</h2>

<form action="friendCircle/save.do" method="post" enctype="multipart/form-data">
	选择文件:<input type="file" name="files"><br>	
	选择文件:<input type="file" name="files"><br>
	选择文件:<input type="file" name="files"><br>
	选择文件:<input type="file" name="files"><br>
	Token:<input type="text" name="token"><br>
	userIdStr:<input type="text" name="userIdStr"><br>
	content:<input type="text" name="content"><br>
	location:<input type="text" name="location"><br>
	jinwei:<input type="text" name="jinwei"><br>
	<input type="submit" value="提交"> 
</form>

</body>
</html>