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
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/question.css" />
<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/js/question.js"></script>
<title>我的问卷</title>
</head>
<body>
	<jsp:include page="include/topheader.jsp" />
	<input type="hidden" id="type"/>
	<input type="hidden" id="question_id"/>
	<div id="body" style="padding-top:10px;">
		<div class="wrapper bodyWrapper">
			<div class="mainNavIgator_top">
				<button class="question_button" id="question_button"></button>
			</div>
			<div class="equal">
				<div class="qrow">
					<div class="four ddd"></div>
					<div class="one ddd">调查名称</div>
					<div class="two ddd">创建时间</div>
					<div class="two ddd">修改时间</div>
					<div class="two ddd">状态</div>
					<div class="two ddd">进度</div>
					<div class="three ddd"></div>
				</div>
				<c:if test="${questionList!=null }">
					<c:forEach var="item" items="${questionList}">
						<div class="qrow table">
							<div class="four question_height" style="background-color: rgb(${item.qustnnrStatusColor})"></div>
							<div style="font-weight:bold;" class="one question_height tbale_name">${item.qustnrName}</div>
							<div class="two question_height table_td"><fmt:formatDate value="${item.createdTime}" pattern="yyyy/MM/dd" /></div>
							<div class="two question_height table_td"><fmt:formatDate value="${item.updatedTime}" pattern="yyyy/MM/dd" /></div>
							<c:choose>
								<c:when test="${item.qustnnrStatus==0 }">
								<div class="two question_height table_td"><button data-toggle="modal" data-target="#stopModal" onclick="stopQuestion('${item.id}','${item.qustnrName}')" class="questionList_button">${item.qustnnrStatusName}</button></div>
								</c:when>
								<c:otherwise>
									<div class="two question_height table_td">${item.qustnnrStatusName}</div>
								</c:otherwise>
							</c:choose>
<%-- 							<div class="two question_height table_td"><button  class="questionList_button">${item.qustnnr_status_Name}</button></div> --%>
							<div class="two question_height table_td">${item.collectedAmount}/${item.creditAmount}</div>
							<div class="three question_height table_td" style="text-align:center;">
								<input type="button" class="button" style="background-image: url('${ctx}/assets/images/question_edit.png')" title="编辑" onclick="optionQuestion('${item.id}')"/>
								<input type="button" class="button" style="background-image: url('${ctx}/assets/images/question_ok.png')" title="采集"  ${item.qustnnrStatus ==0?"onclick=\"  optionRelease('".concat(item.id).concat("')\""):"disabled=\"disabled\""}/>
								<input type="button" class="button" style="background-image: url('${ctx}/assets/images/question_query.png')" title="报告"  ${item.qustnnrStatus<2?"onclick=\"optionReport('".concat(item.id).concat("')\""):"disabled=\"disabled\""} />
								<input type="button" class="button" style="background-image: url('${ctx}/assets/images/question_crm.png')" title="客户管理"  ${item.qustnnrStatus<2?"onclick=\"\"":"disabled=\"disabled\""} />
								<input type="button" class="button" title="删除"  onclick="delQuestion('1','${item.id}'${item.qustnnrStatus<2?'':',\'item.qustnrName\''})" style="background-image: url('${ctx}/assets/images/question_display_del.png')" data-toggle="modal" data-target="#myModal" disabled="disabled"/>

							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
			<div style="background:#FFF;padding:15px;">
			${page}
			</div>

		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
        <div class="modal_question">
            <div class="model_header_question">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="model_center_question">
                <span id="modelName"></span>
            </div>
            <div class="model_footer_question">
                <button class="model_del_ok" id="but_ok"></button>
                <button class="model_del_no" data-dismiss="modal"></button>
            </div>
        </div>
    </div>
    </div>


    <div class="modal fade" id="stopModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
	        <div class="modal_question">
	            <div class="model_header_question">
	                <button type="button" class="close"
	                        data-dismiss="modal" aria-hidden="true">&times;</button>
	            </div>
	            <div class="model_center_question">
	                <span id="modelStopName"></span>
	            </div>
	            <div class="model_footer_question">
	                <button class="model_del_ok" id="but_Stop_ok"></button>
	                <button class="model_del_no" data-dismiss="modal"></button>
	            </div>
	        </div>
	    </div>
    </div>

	<jsp:include page="include/footer.jsp" />
</body>

</html>