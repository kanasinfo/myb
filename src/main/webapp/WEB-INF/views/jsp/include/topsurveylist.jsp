<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="subHeader">
	<div class="wrapper">
        <div id="backToLast"><a href="${ctx }/page/main/question.html">返回</a></div>
        <ul id="subNavigator">
            <li><a href="${ctx}/page/main/editQuestion/${id}.html" ${pageContext.request.servletPath=="/WEB-INF/views/jsp/editQuestion.jsp"?"class=\"current\"":""}>问卷设置</a></li>
            <li><a href="${ctx}/page/release/releaseOne/${id}.html"  ${pageContext.request.servletPath=="/WEB-INF/views/jsp/release/releaseone.jsp"?"class=\"current\"":""}>数据采集</a></li>
            <li><a href="${ctx}/page/main/report/${id}.html" ${pageContext.request.servletPath=="/WEB-INF/views/jsp/report.jsp"?"class=\"current\"":""}>分析报告</a></li>
            <li><a href="${ctx}/page/main/home.html">顾客管理</a></li>
        </ul>
    </div>
</div>