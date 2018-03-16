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
	[背包]<br/>
	金币:${ cash }<br/>
	${weight}<br/>
	<c:forEach var="item" varStatus="status" items = "${ items }">
		${ status.count + startIndex }.<a href="#">${ item.name }</a><c:if test="${ item.num > 1 }">*${ item.num }</c:if><c:if test="${ item.property > 0 }">+${ item.property }</c:if>
		<br/>
	</c:forEach>
	<c:if test="${ last !=null }">
		<a href='/game/bag?${ urltail }&page=${ last }'>上一页</a><br/>
	</c:if>
	<c:if test="${ next !=null }">
		<a href='/game/bag?${ urltail }&page=${ next }'>下一页</a><br/>
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