<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/question.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/themes-cust/myb-green/jquery-ui.css"/>
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/validationEngine.jquery.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jqueryui/jquery-ui.js"></script>
<script src="${ctx}/assets/jquery-validation/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/jquery-validation/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/js/UUID.js"></script>
<title>问卷预览</title>
</head>

<body>
	<div id="showQuestion_panel">
		<div class="showMain">
			<ul>
				<li>PC</li>
				<li>ipad</li>
				<li>mobile</li>
			</ul>
		</div>
	</div>
</body>
</html>