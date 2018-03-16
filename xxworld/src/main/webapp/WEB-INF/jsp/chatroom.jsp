<%@page import="com.kkch.xxworld.entity.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=no">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仙侠世界</title>
<style type="text/css">
body {background-color:rgb(238,238,238)}
p {font-size:20px;line-height:1.2em;}
a:link{color:blue;text-decoration:none;}
</style>
</head>
<body  class="index">
<p>
<c:if test="${ uuid != null }">
<c:set var="urltail" value="uuid=${ uuid }&t=${ t }" />
	<%@include file="top"%>
	<a href="/game/fresh?${ urltail }">【返回游戏】</a><br/>
	
	【千里传音】<br/>
	<c:if test="${ senderror != null }">${ senderror }<br/></c:if>
	<c:forEach var="msg" items = "${ msgs }">
		<c:if test="${ msg.roleId == role.id }">你</c:if><c:if test="${ msg.roleId != role.id }"><a href="/game/lookrole?roleid=${ msg.roleId }&${ urltail }">${ msg.roleName }</a></c:if>:${ msg.msg }
		<br/>
	</c:forEach>
	<c:if test="${ last != null }"><a href="/game/chat?page=${ last }&${ urltail }">上一页</a></c:if><c:if test="${ last == null }">上一页</c:if>|<c:if test="${ next != null }"><a href="/game/chat?page=${ next }&${ urltail }">下一页</a></c:if><c:if test="${ next == null }">下一页</c:if>
	<br/>
	<form action="/game/chat-input">
		<input name="msg" type="text" maxlength="50" /><br/>
		<input type='hidden' name='t' value='${ t }' /> 
		<input type='hidden' name='uuid' value='${ uuid }' /> 
		<input type='submit' value='&#x53d1;&#x9001;'/> <a href="/game/chat?${ urltail }">刷新</a>
	</form>
	<br/>
	<br/>
	<a href="/game/fresh?${ urltail }">【返回游戏】</a><br/>
</c:if>
<c:if test="${ uuid == null }">
<a href='/user/login'>请重新登陆</a>
</c:if>
</p>
</body>

</html>