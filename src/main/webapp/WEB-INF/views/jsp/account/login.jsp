<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<!-- link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-3.3.5/css/bootstrap.min.css" / -->
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css" />
	<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/uploadfile/ajaxfileupload.js"></script>
<title>用户登录</title>
</head>
<body>
	<jsp:include page="../include/topheader.jsp" />
	<div id="body" style="padding:10px 0;">
		<div class="wrapper bodyWrapper"  align="center" style="background:none;padding:40px 0;">
			<form action="${ctx}/page/account/login.html" method="post">
				<div style="background:white;box-shadow:0px 0px 8px #ccc; width:400px; padding:30px 0 50px 100px;border-radius:4px;text-align:left;">
					<div><img src="${ctx}/assets/images/myb-big-logo.png" /></div>
					<h2 style="color:gray;">登录</h2>
					<h3 style="color: red">${msg}</h3>
					<div style="margin:20px 0 10px 0;">账号：</div>
					<div><input type="text" id="loginName" name="loginName" style="backgroud:white;border-radius:5px;width:250px;height:20px;"/></div>
					<div style="margin:20px 0 10px 0;">密码：</div>
					<div><input type="password" id="passWord" name="passWord" style="backgroud:white;border-radius:5px;width:250px;height:20px;"/></div>
					<div style="margin-top:20px;">验证码：</div>
					<div><input type="text" id="code" name="code" style="backgroud:white;border-radius:5px;width:100px;height:20px;font-weight:bold;"></input>&nbsp&nbsp&nbsp&nbsp<img src="${ctx}/servlet/captchaCode" id="captchaCode"></img></div>
					<div style="margin:30px 0 0 0;"><input type="submit" value="登陆" id="Button1"  style="width:250px;height:30px;background-color:#78973E;"/></div>
					<div style="width:250px;text-align:center;padding:10px;">还没注册，请点击<a href="${ctx}/page/account/register.html">注册</a></div>			
				</div>
			</form>
		</div>
    </div>
	<jsp:include page="../include/footer.jsp" />
</body>

<script>



function refreshCaptcha() {
	$("#_captcha_id").attr("src","servlet/captchaCode?t=" + Math.random());
}
jQuery(function($){
	$("#buttonResg").click(function(){
		window.location.href="../account/register.html"
	})
	$("#captchaCode").click(function(){
		$("#captchaCode").attr("src","${ctx}/servlet/captchaCode?t=" + Math.random());
	})
})
</script>
</html>