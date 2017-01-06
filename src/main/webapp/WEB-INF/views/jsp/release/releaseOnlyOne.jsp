
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
<link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/validationEngine.jquery.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/template.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/question.css" />
<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/jquery-validation/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/jquery-validation/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<title>我的问卷</title>

</head>

<body>
<input type="hidden" id="templateId" value="${templateId }"/>
<jsp:include page="../include/topheader.jsp" />
	<div id="subHeader">
		<div class="wrapper">
	        <div id="backToLast"><a href="${ctx }/page/main/question.html">返回</a></div>
	        <ul id="subNavigator">
	            <li><a href="" >问卷设置</a></li>
	            <li><a href="" class="current">数据采集</a></li>
	            <li><a href="">分析报告</a></li>
	            <li><a href="">顾客管理</a></li>
	        </ul>
	    </div>
	</div>
	<div id="body">
	 <div class="wrapper">
        <div class="clearfix" style="height:10px;"></div>
        <div id="release_panel">
            <div id="release_title">
                <ul>
                    <li><a href="${ctx}/page/release/releaseOne/${templId }.html" >所有顾客公用一个链接/二维码</a></li>
                    <li><a  href="${ctx}/page/release/releaseStore/${templId }.html">每个分店的顾客使用该分店的专属链接/二维码</a></li>
                    <li><a href="${ctx}/page/release/releaseOnlyOne/${templId }.html" class="release_title_select">每个顾客使用自己的专属链接</a></li>
                </ul>

            </div>
            <div id="release_center">
                <div class="release_center_top">
                    <a href="#">问卷预览</a>
                </div>
                <div class="release_center_center">
                    <div class="release_center_center_desc">
                        <span>一个顾客对应一个链接,每次输入并下载最大值为1000,多于1000时需要重复操作</span>
                    </div>
                    <div class="release_center_center_text">
                        <span>输入链接数:</span>
                        <input type="text" id="selectDownLoadCount" style="height: 20px;width: 17%"/> <span>${creditAmount}</span>
                    </div>
                    <div class="release_center_center_download"> <button id="dowanOnlyOne">下载链接</button></div>
                </div>
                <div class="clearfix" style="height:10px;background-color: rgb(243,243,243)"></div>
                <div id="release_botton">
                    <div class="release_botton_desc"><span>需要满意吧帮您发送短信/邮件/微信,请联系客服专员</span></div>
                    <table>
                        <tr>
                            <td>客服专员:</td>
                            <td>小张</td>
                        </tr>
                        <tr>
                            <td>客服电话:</td>
                            <td>010-000000</td>
                        </tr>
                        <tr>
                            <td>E-mail:</td>
                            <td>小张</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </div>
<%--      <form action="${ctx}/page/release/downGroupLoadExcel.html" method="post" id="downLoadZipFrom" enctype="application/x-www-form-urlencoded"> --%>
<!--         	<input type="hidden" id="downLoadData" name="data" value=""/> -->
<%--         	<input type="hidden" id="downLoadQuestionId" name="questionId" value="${templId }"/> --%>
<!--         	<input type="hidden" id="downLoadGroupId" name="groupId" value=""/> -->
<!--         	<input type="hidden" id="parentId" name="parentId" value=""/> -->
<!--         </form> -->
        <form action="${ctx}/page/release/downOnlyONeLoadExcel.html" method="post" id="downLoadFrom" enctype="application/x-www-form-urlencoded">
        	<input type="hidden" id="questionId" name="questionId" value="${templId }"/>
        	<input type="hidden" id="downCount" name="downCount" value=""/>
        </form>
    <div class="clearfix" style="height:10px;"></div>
	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript">
    </script>
    <script type="text/javascript">
    	jQuery(function($){
    	$("#dowanOnlyOne").click(function(){
		//判断是否选中门店分组
		var downCount = $("#selectDownLoadCount").val();
		if($.trim(downCount)==null){
			alert("请输入下载连接数");
		}else{
			 $("#downCount").val(downCount);
			 $("#downLoadFrom").submit();
		}
	})
    	})
    </script>
    
</body>
</html>