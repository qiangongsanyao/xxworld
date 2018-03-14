<%@page import="com.kkch.xxworld.entity.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=no">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录结果!</title>
<style type="text/css">
body {background-color:rgb(238,238,238)}
p {font-size:20px;line-height:1.2em;}
a:link{color:blue;text-decoration:none;}
</style>
</head>
<body>

<%
	Object name = session.getAttribute("username");
	if(name!=null&&name instanceof String){
		out.println("<h4>欢迎! "+name+"</h4>");
		Object roleso = session.getAttribute("roles");
		out.println("<p>");
		if(roleso != null && roleso instanceof Role[]) {
			Role[] roles = (Role[])roleso;
			if(roles.length==0){
				out.println("您尚未创建角色");
			}else{
				out.println("角色列表:");
				out.println("<ol>");
				for(Role role:roles){
					String uuid = role.getRuntime().getLoginId();
					String uri = "/role/enter?uuid="+uuid;
					String text = role.getName()+" lv."+role.getLevel().getLevel();
					String a = "<a href='"+uri+"'>"+text+"</a>";
					out.println("<li>"+a+"</li>");
				}
				out.println("</ol>");
			}if
			(roles.length<5){
				out.println("<div><a href='/user/create-input'>创建新角色</a></div>");
			}
			
        	if(request.getAttribute("error")!=null){
        		out.print("<div><font color='red'>"+request.getAttribute("error")+"</font></div><br/>");
        	}
			
		}else{
			out.println("<font color='red'>角色列表查询失败</font>");
		}
		out.println("</p>");
	}else{
		out.println("请求错误!");
	}
	out.println("<div><a href='/user/input'>返回登录页面</a></div>");
%>

</body>

</html>