<%@page import="com.kkch.xxworld.entity.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=no">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>创建角色</title>
</head>
<body>
<form action="/user/create-role">
<p>
创建游戏角色

</p>
请输入名字：
	<div class='padding'><input name='rolename' type='text' size='5'/></div>
请选择性别：
	<div class='padding'>
		<input type="radio" name='sex' value="male" checked="checked"/>男 
		<input type="radio" name='sex' value="female"/>女
		
	</div>
	<br/>
<%
if(request.getAttribute("error")!=null){
	out.print("<div><font color='red'>"+request.getAttribute("error")+"</font></div><br/>");
}
%>
	<input value="重置" type="reset"/>
	<input value="确定" type="submit"/>
</form>

</body>
<style type="text/css">
.padding{
	padding-top: 10px;
	padding-left: 20px;
	padding-bottom: 12px;
}
body {background-color:rgb(238,238,238)}
p {font-size:20px;line-height:1.2em;}
a:link{color:blue;text-decoration:none;}
</style>
</html>