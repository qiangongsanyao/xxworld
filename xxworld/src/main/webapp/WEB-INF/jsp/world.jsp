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
	<c:if test="${ map != null }">
	<c:if test="${ map.name != null }">
		【<c:out value="${ map.name }"></c:out>】<a href="/role/fresh?uuid=${ uuid }">刷</a><br/>
	</c:if>
	<c:if test="${ map.detail != null }">
		<c:out value="${ map.detail }"></c:out>
	</c:if>
</c:if>

	<br/>
	<c:if test="${ west !=null || east !=null || south !=null || north !=null }">
		【地图出口】<br/>
	</c:if>
	<c:if test="${ west !=null }">
		西:<a href="/role/goWest?uuid=${ uuid }">${ west }</a><br/>
	</c:if>
	<c:if test="${ east !=null }">
		东:<a href="/role/goEast?uuid=${ uuid }">${ east }</a><br/>
	</c:if>
	<c:if test="${ south !=null }">
		南:<a href="/role/goSouth?uuid=${ uuid }">${ south }</a><br/>
	</c:if>
	<c:if test="${ north !=null }">
		北:<a href="/role/goNorth?uuid=${ uuid }">${ north }</a><br/>
	</c:if>
	
	<br/>
	<a href="/role/fresh?uuid=${ uuid }">刷新</a>|<a href="/role/city?uuid=${ uuid }">地图</a>|<a href="/role/bag?uuid=${ uuid }">行囊</a><br/>
	<a href="/role/chat?uuid=${ uuid }">传音</a>|<a href="/role/friend?uuid=${ uuid }">道友</a>|<a href="/role/mail?uuid=${ uuid }">飞书</a><br/>
	<a href="/role/status?uuid=${ uuid }">状态</a>|<a href="/role/house?uuid=${ uuid }">仙府</a>|<a href="/role/league?uuid=${ uuid }">仙盟</a><br/>
	<a href="/role/skill?uuid=${ uuid }">仙术</a>|<a href="/role/task?uuid=${ uuid }">任务</a>|<a href="/role/setting?uuid=${ uuid }">设置</a><br/>
	<a href="/role/msg?uuid=${ uuid }">仙讯</a>|<a href="/role/happy?uuid=${ uuid }">福地洞天</a>
	<br/>
	<br/>
	<a href='/user/login'>返回首页</a>
</c:if>
<c:if test="${ uuid == null }">
<a href='/user/login'>请重新登陆</a>
</c:if>
</p>
</body>

</html>