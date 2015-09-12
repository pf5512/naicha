<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新职业</title>
</head>
<body>
<h2>更新职业</h2>

<form action="setup/updateProfession.do" method="post" >
	profession:<input type="text" name="profession"><br>
	token:<input type="text" name="token"><br>
	phone:<input type="text" name="phone"><br>
	<input type="submit" value="提交"> 
</form>

</body>
</html>