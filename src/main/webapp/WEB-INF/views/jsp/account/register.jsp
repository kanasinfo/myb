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
</head>
<body>
	<jsp:include page="../include/topheader.jsp" />
	<div id="body" style="padding:10px 0;">
		<div class="wrapper bodyWrapper"  align="center">
		<h1 style="margin:0 0 10 0px;line-height:35px;font-size:14px;border-bottom: 2px #696 solid;">用户注册</h1>
		<form action="${ctx}/page/account/register" method="post">
			<table cellspacing="5"  style="margin-top:40px;">
			<tr><td>
				<label style="">用户名：</label></td><td>
				<input type="text" id="loginEmail" name="loginEmail"></td>
			</tr>
			<tr><td>
				<label>密码：</label></td><td>
				<input type="password" id="loginPass" name="loginPass"></td>
			</tr>
			<tr><td>
				<label>确认密码：</label></td><td>
				<input type="password" id="newLoginPass" name="newLoginPass"></td>
			</tr>
			<tr><td colspan=2>
			我同意保密协议及服务条款
				<input type="submit" value="下一步"></td>
				</tr>
			</table>
		</form>
		</div>
		</div>
	<jsp:include page="../include/footer.jsp" />
</body>

</html>