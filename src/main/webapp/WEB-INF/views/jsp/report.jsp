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
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">	</meta>		
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/report.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-3.3.5/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/bootstrap-datepicker.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/themes-cust/myb-green/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/star-rating.min.css" />
<title>摘要报告页面</title>
</head>
<body>
	<jsp:include page="include/topheader.jsp" />
	<jsp:include page="include/topsurveylist.jsp" />
  	<div id="body">
  		<div class="wrapper">
  			<div id="titleBar">
  				<button id="feed"></button>
  				<span id="title">摘要报告</span>
  				<span id="titleTip" style="font-size: 12px;"><a href="${ctx}/page/main/proreport/${id}.html">点击进入详细报告</a></span>
  			</div>
			<div class="clearfix"></div>
			<div style="line-height: 30px;">
				<div style="float: right"><span>环比周期 </span> 
				<span  style="float:right;margin-left:3px">
					<select id="showmenu" style="width:80px;">
						<option selected="selected" value="month">月</option>
						<option value="quarter">季</option>
						<option value="year">年</option>
					</select></span>
				</div>
				<div style="float: left;">
					<span>时间范围</span> <span> <input type="text"
						name="start" class="summary_datepicker"/> 
						<img width="6px" alt="to" id="timerangeicon" src="${ctx}/assets/images/h_right.png"></img>
						<input type="text" name="end" class="summary_datepicker"/></span>
				</div>
			</div>
			<div class="clearfix"></div>
  			<div>
  				<div class="rightColumn storeoverview">
  					<div class="columnTitle">
  						<button class="zoomIn" onclick="zoomCard(this)"></button>
  						<span>分店表现</span>
  					</div>
  					<div class="firstRowContent">
  						<div style="height:35px">
							<span  style="margin-left:3px">
							<select id="storemenu" style="width:95px;">
								<option selected="selected" value="all">全部分店</option>
								<option value="store1">分店1</option>
								<option value="store1">分店2</option>
							</select></span>
							<span  style="margin-left:3px">
							<select id="satifymenu" style="width:95px;">
								<option selected="selected" value="summary">总体满意度</option>
								<option value="nps">净推荐值</option>
								<option value="health">健康指数</option>
							</select></span>
  						</div>
  						<div style="height: 250px; position: relative; background-color: transparent;top:-55px" class="storeCardChart"></div>
  					</div>
  				</div>
  				<div class="leftColumnMain" id="overviewcard">
  					<div class="columnTitle">
  						<span>总体表现</span>
  					</div>
					<div class="firstRowContent">
					<div class="summarynodata" style="text-align:center; font-size: 16px; color: rgb(153, 153, 153); padding-top: 100px;">暂无数据</div>
					<div class="summarydata" style="display:none">
					 <div style="width:33%;float:left;" id="" class="firstColumn chart-main">
					   <div style="height:115px">
						   <div style="float:left;width:50%;height:115px;" class="overviewChart" > </div>
						   <div style="float:left;width:50%;height:115px;" class="overviewLegend"></div>
					   </div>
					   <div style="height:110px">
					   		<div style="position: relative; overflow: hidden; width: 196px; height: 210px; cursor: default;top:-70px" class="overviewTrend" ></div>
					   </div>
					  </div>
					 <div style="width:33%;float:left;" id="" class="secondColumn chart-main">
				     <div style="height:115px">
						   <div style="float:left;width:50%;height:115px;" class="recommendChart" > </div>
						   <div style="float:left;width:50%;height:115px;" class="recommendLegend"></div>
					   </div>
					   
					   <div style="height:110px">
					   		<div style="position: relative; overflow: hidden; width: 196px; height: 210px; cursor: default;top:-70px" class="recommendTrend">
						   </div>
					   </div>
					    </div>
					 <div style="width:33%;float:left;" id="" class="secondColumn chart-main">
	
					   <div style="height:115px">
						   <div style="float:left;width:50%;height:115px;" class="healthChart" > </div>
						   <div style="float:left;width:50%;height:115px;" class="healthLegend"></div>
					   </div>	
						<div style="height:110px">
					   		<div style="position: relative; overflow: hidden; width: 196px; height: 210px; cursor: default;top:-70px" class="healthTrend">
						   </div>
					   </div>								
					  </div>					  
					</div>
  				</div>
  			</div>
			<div class="clearfix" style="height:5px;"></div>
  			<div>
  				<div class="rightColumn">
  					<div class="columnTitle">
  						<button class="zoomIn" onclick="zoomCard(this)"></button>
  						<span>顾客之声</span>
  					</div>
  					<div class="secondRowContent">
						<div><select ><option>最新评论</option></select></div>
						<table style="width:100%;border-bottom:1px solid #eee;">
							<tr>
								<td rowspan="2" width="50px;">头像</td>
								<td colspan="2">未知用户：巴拉巴拉巴拉巴拉拉巴拉</td>
							</tr>
							<tr>
								<td width="90">2016/01/01</td>
								<td>分店1</td>
							</tr>
						</table>
						<table width="100%" style="border-bottom:1px solid #eee;">
							<tr>
								<td rowspan="2" width="50px;">头像</td>
								<td colspan="2">未知用户：巴拉巴拉巴拉巴拉拉巴拉</td>
							</tr>
							<tr>
								<td width="90">2016/01/01</td>
								<td>分店1</td>
							</tr>
						</table>
						<table width="100%" style="border-bottom:1px solid #eee;">
							<tr>
								<td rowspan="2" width="50px;">头像</td>
								<td colspan="2">未知用户：巴拉巴拉巴拉巴拉拉巴拉</td>
							</tr>
							<tr>
								<td width="90">2016/01/01</td>
								<td>分店1</td>
							</tr>
						</table>
					</div>
  				</div>
  				<div class="leftColumn">
  					<div class="columnTitle">
  						<button class="zoomIn" onclick="zoomCard(this)"></button>
  						<span>细项表现</span>
  					</div>
  					<div class="secondRowContent detailedItemDiv">
						<table class="detailedItemTable" style="width:100%">
							<tr>
							<td width="125px;"></td>
							<td><div style="height:1px;width:100px;background-color:#ffffff"></div></td>
							</tr>							
						</table>
					</div>
  				</div>
  				<div class="middleColumn">
  					<div class="columnTitle">
  						<button class="zoomIn" onclick="zoomCard(this)"></button>
  						<span>服务规范</span>
  					</div>
  					<div class="secondRowContent">
						<table class="servicesTable" style="width:100%">
							<tr>
							<td width="125px;"></td>
							<td><div style="height:1px;width:100px;background-color:#ffffff"></div></td>
							</tr>	
						</table>
					</div>
  				</div>
  			</div>
  		</div>
  		<div class="clearfix" style="height:10px;"></div>
  		<div class="cardDialog" style="display:none"></div>
  	</div>
  	<jsp:include page="include/footer.jsp" />
  	<script type="text/javascript" src="${ctx}/assets/jqueryui/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/assets/jqueryui/jquery-ui.js"></script>
	<script type="text/javascript" src="${ctx}/assets/echarts/echarts-3.1.10.min.js"></script>
  	<script type="text/javascript" src="${ctx}/assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
  	<script type="text/javascript" src="${ctx}/assets/js/summaryreport.js"></script>
  	<script>
	$(function($) {
	  	reportGlobal.ctx = '${ctx}';
	  	reportGlobal.id = '${id}';
	  	reportGlobal.initSumReport();
	});
  	</script>
</body>
</html>