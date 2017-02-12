<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery/jquery-1.8.3.js"></script> --%>
<div id="header">
	<div class="wrapper">
		<div style="float:right">
			<ul id="mainNavigator" class="main-navigator login-reg">
				<shiro:guest>
                    <li><a href="${ctx}/page/account/login.html" id="login" class="font-14">登录</a></li>
                    <li><a href="${ctx}/page/account/register.html" class="font-14">注册</a></li>
                </shiro:guest>
                <shiro:user>
                    <li><a href="#" class="font-14">欢迎回来: ${sessionScope.loginName}</a></li>
                    <li><a href="${ctx}/page/account/loginOut.html" class="font-14">退出</a></li>
                </shiro:user>
			</ul>
		</div>
		<div id="membershortcuts"></div>
		<div id="logo"></div>
		<ul id="mainNavigator" class="main-navigator">
			<li><a href="${ctx}/page/main/home.html" ${pageContext.request.servletPath=="/WEB-INF/views/jsp/home.jsp"?"class=\"current\"":""}>首页</a></li>
			<li><a href="${ctx}/page/main/question.html" ${pageContext.request.servletPath=="/WEB-INF/views/jsp/question.jsp"?"class=\"current\"":""}>我的调查列表</a></li>
			<li><a href="${ctx}/page/main/product.html" ${pageContext.request.servletPath=="/WEB-INF/views/jsp/product.jsp"?"class=\"current\"":""}>产品介绍</a></li>
			<li><a href="${ctx}/page/main/price.html" ${pageContext.request.servletPath=="/WEB-INF/views/jsp/price.jsp"?"class=\"current\"":""}>服务及价格方案</a></li>
		</ul>
	</div>
</div>