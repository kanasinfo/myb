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
	<!-- script type="text/javascript" src="${ctx}/assets/bootstrap-3.3.5/js/bootstrap.min.js"></script -->
<title>用户注册</title>
<script>
	
</script>
</head>
<body>
	<jsp:include page="register_header.jsp" />
	<div id="body" style="padding:10px 0;">
		<div class="wrapper"  align="center">
			<form action="${ctx}/page/account/register" method="post">
				<div style="width:400px; padding:30px 0 50px 100px;border-radius:4px;text-align:left;">
					<h2 style="color:gray;">注册信息</h2>
					<h3 style="color: red">${msg}</h3>
					<div style="margin:20px 0 10px 0;">账号：</div>
					<div><input type="text" id="loginEmail" name="loginEmail" style="backgroud:white;border-radius:5px;width:250px;height:20px;"/></div>
					<div style="margin:20px 0 10px 0;">密码：</div>
					<div><input type="password" id="loginPass" name="loginPass" style="backgroud:white;border-radius:5px;width:250px;height:20px;"/></div>
					<div style="margin:20px 0 10px 0;">确认密码：</div>
					<div><input type="password" id="loginPass" name="loginPass" style="backgroud:white;border-radius:5px;width:250px;height:20px;"/></div>
					<div style="margin:20px 0 40px 0;"><label><input type="checkbox" id="agreement" name="agreement" ></input>我同意<a href="${ctx}/page/info/secret.html" style="color:#333;"  target="_blank">保密协议</a>以及<a href="${ctx}/page/info/service.html" style="color:#333;" target="_blank">服务条款</a></label></div>
					<div><input type="submit" value="下一步" style="width:250px;height:30px;background-color:#78973E;border-radius:5px;color:#FFF;font-weight:bold;"/></div>
					<div style="width:250px;text-align:center;padding:10px;">已经注册，请<a href="${ctx}/page/account/login.html" style="text-decoration: none;color: #09f; font-weight: bold;">登录</a></div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>

</html>