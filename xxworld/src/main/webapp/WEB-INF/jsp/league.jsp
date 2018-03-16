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
	<c:if test="${ league != null }"><br/>
	</c:if>
	<c:if test="${ league == null }">您尚未加入仙盟<br/>
	</c:if>
	<br/>
	<%@include file="bottom"%>
	
</c:if>
<c:if test="${ uuid == null }">
<a href='/user/login'>请重新登陆</a>
</c:if>
</p>
</body>

</html>