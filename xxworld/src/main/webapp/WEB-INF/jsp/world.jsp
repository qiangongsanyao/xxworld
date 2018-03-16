<%@page import="com.kkch.xxworld.entity.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
<c:if test="${ unreachable != null }">
	<%@include file="top"%>
	${ unreachable }<br/>
</c:if>
	<c:if test="${ map != null }">
	<c:if test="${ map.name != null }">
		【<c:out value="${ map.name }"></c:out>】<a href="/game/fresh?${ urltail }">刷</a><br/>
	</c:if>
	<c:if test="${ map.detail != null }">
		<c:out value="${ map.detail }"></c:out>
	</c:if>
	
	<br/>
	<c:if test="${ roles != null }">
	<c:set var="size" value="${ fn:length(roles) }"></c:set>
		
		<c:if test="${ size > 0 }">
			玩家:<a href="/game/lookrole?roleid=${ roles[0].id }&${ urltail }">${ roles[0].name }</a><c:if test="${ size > 1 }">|<a href="/game/lookrole?roleid=${ roles[1].id }&${ urltail }">${ roles[1].name }</a></c:if><br/>
			<c:if test="${ size > 2 }"><a href="/game/moreroles?pmr=morerole&${ urltail }">更多..</a><br/></c:if>
		</c:if>
		
	</c:if>
	<c:if test="${ west !=null || east !=null || south !=null || north !=null }">
		【地图出口】<br/>
	</c:if>
	<c:if test="${ west !=null }">
		西:<a href="/game/goWest?${ urltail }">${ west }</a><br/>
	</c:if>
	<c:if test="${ east !=null }">
		东:<a href="/game/goEast?${ urltail }">${ east }</a><br/>
	</c:if>
	<c:if test="${ north !=null }">
		北:<a href="/game/goNorth?${ urltail }">${ north }</a><br/>
	</c:if>
	<c:if test="${ south !=null }">
		南:<a href="/game/goSouth?${ urltail }">${ south }</a><br/>
	</c:if>
	
	<br/>
	<%@include file="bottom"%>
	
	</c:if>
</c:if>

<c:if test="${ uuid == null }">
<a href='/user/login'>请重新登陆</a>
</c:if>
</p>
</body>

</html>