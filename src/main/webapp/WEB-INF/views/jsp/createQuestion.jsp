
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
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/question.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/validationEngine.jquery.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/template.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css" />
<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/jquery-validation/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/jquery-validation/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/js/createQuestion.js"></script>
<script src="${ctx}/assets/js/jquery.validator.js"></script>


<title>我的问卷</title>

</head>

<body>
<jsp:include page="include/topheader.jsp" />
<jsp:include page="include/topsurveylist.jsp" />

<div id="body" style="padding-top:20px;">
    <div class="wrapper bodyWrapper" style="background-color: rgb(245,245,245)">
    <form action="../data/createQuestion.html" method="post" id="submit_form">
    	<input type="hidden" id="sub_industry_id" name="sub_industry_id"></input>
        <div id="create_panel">
                <div id="title">基本设置
                </div>
                <div class="form_row" id="form_row_id">
                	<div class="column_left">选择行业</div>
                	<div class="column" >
                		<select id="select_industry" name="sub_industry_name" class="validate[required]">
						    <option value="">请选择</option>
						    <c:if test="${industry !=null}">
						    	<c:forEach var="item" items="${industry}">
						    		<option id="${item.id }" value="${item.name}">${item.name}</option>
						    	</c:forEach>
						    </c:if>
						</select>
                	</div>
                </div>
                <div class="form_row">
                	<div class="column_left">调查名称</div>
                	<div class="column_right">
                		<span  class="warpper_center_right_span"><input  data-validation-placeholder="输入不正确" class="validate[chcharacter] text-input" maxlength="15" type="text" name="qustnr_name" id="qustnr_name" style="width:200px" value="" /></span>
					</div>
                </div>
                <div class="form_row">
                	<div class="column_left">设定样本量</div>
                	<div class="column_right">
                		<span  class="warpper_center_right_span"><input type="text" data-validation-placeholder="输入不正确" class="validate[required,custom[integer]] text-input" name="credit_amount" id="credit_amount" style="width:50px;" value=""/></span><span style="margin-left: 10px;text-align: center; color: rgb(110,164,5)">账户可用样本量:&nbsp;&nbsp;<span id="amount">${amount}</span><input type="button" style="border: 0;background-color: transparent;color: rgb(110,164,5)" id="refurbish" value="刷新"/></span>
                	</div>
                </div>
        	</div>
        </form>
        
        <div class="wrapper_button">
            <div>
                <button class="wrapper_button_select" id="create_question">保存设置</button>
                <button class="wrapper_button_onselect">发布调查</button>
            </div>
            <div class="yl">
                <button class="wrapper_button_onselect">问卷预览</button>
            </div>

        </div>

    </div>

</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>