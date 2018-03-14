<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
   	  			
   <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=no" />
    <link href="http://zlpt-data.oss-cn-shenzhen.aliyuncs.com/upload/ico.png" type="image/x-icon" rel="shortcut icon" />
    <script src="http://image.yomo.cn/znpt/login/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<title>贪玩仙侣</title>
	 <script src="http://image.yomo.cn/znpt/js/shoucang.js" type="text/javascript"></script>
	     <!--[if IE]> 
         	<style>
         		.last{bottom:25px;}
         		.return{top:60%;}
         		.bigallbox{width:400px;}
         	</style>
          <![endif]-->
          
              	<style type="text/css">
	
	@charset "utf-8";
/* CSS Document */
  /* 初始化CSS  重置浏览器样式*/
html, body, ul, li, ol, dl, dd, dt, p, h1, h2, h3, h4, h5, h6, form, fieldset, legend, img,button,input { margin:0; padding:0; }
fieldset, img,input { border:none; outline-style:none;} /*为了照顾ie6 链接图片有边框*/
button,input{ vertical-align: middle;}
ul, ol ,li{ list-style:none; }
input {  font-family: "SimSun","宋体";}
img{vertical-align:top;} /*取消图片底侧缝隙*/
select, input { vertical-align:middle; }
 select, input, textarea {font-size:12px; margin:0;}
textarea { resize:none; }
table { border-collapse:collapse; }
i,em{font-style:normal;} /*让倾斜的变正常*/
s,del{ text-decoration:none;}
h1,h2,h3,h4,h5,h6{font-size:100%; font-weight:normal;}
body {font-size:12px; color:#f3f3e7;font-family: '微软雅黑','Microsoft YaHei';}
html,body{height:100%;}
.bigallbox{max-width:435px;max-height:546px;margin:0 auto; background-size:100%; overflow:hidden;position:relative; min-width:320px;background:url(../images/body5.png) no-repeat;
background-size: 100%;}

.bigbox{height:71%;max-height:400px;margin:0 auto; background-size:100%; overflow:hidden;position:relative;margin-top:20%;width:80%;border-radius: 8px;}

#main-nav-host{ height:32px; line-height:32px; background:#319bd1; font-weight:bold; padding:0 0 0 5px; color:#fff;}

.all{width:97%;overflow:hidden;padding-left: 3%;}

.allheader{height:6%;width:100%;text-align:center;line-height:35px;font-size:15px;}

.landing{ margin-left:0;clear:both;height:70px;margin-top: 0%;}

.landing .qq{ float:left; cursor:pointer;width:88px; height:70px;color:#fff;}

.hinter{height:8%;margin-top:1%;color:#000;font-size: 15px;}

.hint{height:14px;margin-top:1%; color:#cb391f;font-size: 12px;line-height: 14px;}

.hint.one{margin-top: 3%;}

.hint.bg{color:#fff;}

.hint.bg div{display:inline-block;background:url(../images/dl.png) no-repeat center center;background-size: 100% 100%;width:110px;height:18px;line-height: 18px;padding-left:2%;}

.hinter.one{margin-top:0;color:#999;font-size: 12px;}

.account{line-height: 19px;margin-top:3px;}

.account.one{margin-top: 5px;}

.account span{color:#000;font-size: 15px;display:inline-block;width:45px;}

.account.one{letter-spacing: 6px;}

.account.one .span{width:76px; display:inline-block;text-align: center;}

.account.one .spanone{ word-spacing:8px;letter-spacing:4px;}

.account.one .spantwo{ word-spacing:8px;letter-spacing:8px;}

.account input{height:20px; background-color: #c1b385; vertical-align: top;width:42%;color:#000;font-size: 14px;border:1px solid #c1b385;opacity: 0.8}

.account.yzm{height:30px;margin-top: 5px;}

.account.yzm input{width:50px;}

.account.yzm span{width:51px;vertical-align:top;}

.account.yzm .boxyam{width:138px;height:30px;display:inline-block;}

.bigbtn{height:18px;margin-top: 3px;color:#666;}

.bigbtn a{font-size: 13px;margin-right: 15px;color:#666;}

.bigbtn.one{color:#486b88;}

.bigbtn.one a{font-size: 12px;}

.bigbtn.zy{margin-top:20px;}

.bigbtn.zy .right{float:right;margin-right:0;}

.bigbtn .te{color:#666;}

.bigbtn.big{height:22px; margin-top:0;}

.bigbtn.big input{width:186px;height:30px;color:#fff;cursor:pointer;border-radius: 5px;text-align: center; background:url(../images/landing.gif) no-repeat;background-size:100% 100%;font-size: 15px;}

.bigbtn .login{width:80px;margin:0 20px;}

.return{position:absolute;padding-left:13px;line-height:23px;right:5%;top:69%;color:#fff;background:url(../images/return.gif); text-decoration: none;width:53px;}

.last{width:97%;margin:0 auto;height:20%;font-size: 11px;padding-top:2%;color:#907c6d;position:absolute;bottom:-5px;padding-left: 3%;}
@media screen and (max-width: 420px){
    .bigbox{height:67%;}
    .account.one,.account.yzm{margin-top: 2px;}
    .bigbtn{margin-top: 3px;}
    .landing{
        height:52px;
        width:100%;
        margin-top: 2%;
    }
    .landing .qq,.landing .wx{
        height:52px;
    }
    .landing .qq .bj,.landing .wx .bj{
         width:35px;
         height:35px;
    }
}
@media screen and (max-width: 400px){
    .last{font-size: 11px;}
    .bigbox{height:65%;}
}
@media screen and (max-width: 375px){
    .bigbox{height:63%;}
    .landing{
        height:45px;
        width:100%;
        margin-top: 2%;
    }
    .landing .qq,.landing .wx{
        height:45px;
    }
    .landing .qq .bj,.landing .wx .bj{
         width:28px;
         height:28px;
    }
    .last{font-size: 10px;padding-left:3%;width:94%;}
    .account input{height:16px;}
    .account{line-height: 18px;}
    .account span{font-size: 13px;}
    .bigbtn a{font-size: 12px;}
    .allheader{height:0%;}
    .all{height:79%;}
    .bigbtn.big input{}
    .bigbtn.zy{margin-top:7px;}
    .bigbtn.one{margin-top: 1px;}
    .landing .qq .sm,.landing .wx .sm{
        height:17px;font-size: 12px;
    }
}
@media screen and (max-width: 320px){
    .return{top:67%;right:2%;}
    .landing{margin-top: 0%;}
    .last{font-size: 8px;}
    .bigbtn.zy .right{margin-right:10px;}
}
	</style>
    
	 <script type="text/javascript">
		 function fjcHeight(add,arr){
         	$(add).each(function(i,n){
             	var FjcHeight=$(this).attr(arr);
             	var nowWidth=$(this).width();
             	var thisHeight=nowWidth*parseFloat(FjcHeight);
             	$(this).css("height",thisHeight+"px");
         		}); 
     		}
     		window.onresize=function(){  
         		fjcHeight(".bigallbox","F");
    		 }
     		$(function(){
         		fjcHeight(".bigallbox","F");
     		})
        </script>
    </head>
    <body onload="load()">
    <div class="bigallbox" F="1.379">
    	<div id="main-nav-host">
    	<!-- <img src="http://image.yomo.cn/znpt/upload/loginLogo.png" height="26" width="80" alt="" />  <a style="float:right;font-size:15px; margin-right:10px;text-decoration:none;" href="javascript:void(0);" onclick="javascript:addFavorite2()"><span style="color: #fff;">收藏</span></a> --> 
    	</div>
        <div class="bigbox">
        	<div class="allheader"></div>
            <div class="all">
            <br />
            <%
            	if(request.getAttribute("error")!=null){
            		out.print("<span><font color='red'>"+request.getAttribute("error")+"</font></span>");
            	}
            %><br />
              <form id="loginForm" action="/user/login.action" method="post">
		            <input type="hidden" id="gameId" name="loginType" value="xlqy" />

	                <div class="hint" id="msg">                    
	                </div>
	                <div class="hint bg">
                         <div>游戏账号登陆</div>
                    </div>
	                <div class="account one" style="margin-top:3%">
	                    <span>账号</span>
	                     <input id="username" name="username" type="text" value="" />
	                </div>
	                <div class="account one" style="margin-top:3%">
	                    <span>密码</span>
	                    <input id="password" name="password" type="password" value="" />
	                </div>
	                
	                
	                 <div class="bigbtn big" style="margin-top:3%">
                        <input type="submit" value="点击登录" />
                    </div>
                    <div class="bigbtn zy" style="margin-top:5%">
                    	<a href="#">忘记密码？</a>
                        <a href="#">申请账号</a>
                        <a href="#">返回家园</a>
                    </div>
                    <div><strong><font color='#A757A8'><a href='javascript:alert("建设中");'>满级试玩（送+13麻痹戒指）</a></font></strong></div>
                    <div><strong><font color='#A757A8'><a href='javascript:alert("建设中");'>GM号试玩（送【皇帝令牌】）</a></font></strong></div>
	                <div class="bigbtn">
	                                    登录即代表你同意<a href="#" class="te">《用户协议》</a>
	                            </div>
            <div class="last">
            <strong>大狗家园  </strong>
            <br />你凭什么说我是抄的
            <br />明明是他们抄的
            <br />我这才是才良心游戏
            <br />
            </div>
                </form>
            </div>
        </div>
    </div>
    </body>

</html>