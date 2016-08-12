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
		<div class="wrapper bodyWrapper"  align="center">
		<h1 style="margin:0 0 10 0px;line-height:35px;font-size:14px;border-bottom: 2px #696 solid;">用户登录</h1>
			<form action="${ctx}/page/account/login.html" method="post">
				<table cellpadding="0" cellspacing="10" class="l-table-edit" style="margin-top:40px;">
					<tr>
						<td colspan="2" align="center"><h3 style="color: red">${msg}</h3></td>
					</tr>
					<tr>
		                <td >用户名:</td>
		                <td ><input type="text" id="loginName" name="loginName"/></td>
					</tr>
					<tr>
		                <td >密码:</td>
		                <td ><input type="password" id="passWord" name="passWord"/></td>
					</tr>
					<tr>
		                <td >验证码:</td>
		                <td ><input style="width:40px;" type="text" id="code" name="code"></input>&nbsp&nbsp&nbsp&nbsp<img src="${ctx}/servlet/captchaCode" id="captchaCode"></img></td>
					</tr>
					<tr>
					<td align="center" colspan="2"><input type="submit" value="登陆" id="Button1" /><input type="button" id="buttonResg" value="注册"/></td> 
					</tr>
				</table>
			</form>
		</div>
		<div>
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