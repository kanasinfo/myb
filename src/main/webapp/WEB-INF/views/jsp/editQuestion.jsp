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
<title>我的问卷</title>
</head>

<body>
<jsp:include page="include/topheader.jsp" />
<jsp:include page="include/topsurveylist.jsp" />
<div id="body" style="padding-top:20px;">
    <div class="wrapper">
    <form action="../data/createQuestion.html" method="post" id="submit_form">
    	<input type="hidden" id="sub_industry_name" name="sub_industry_name"></input>
    	<input type="hidden" id="groupId" name="groupId"></input>
    	<input type="hidden" id="questionGroupId" name="questionGroupId"></input>
    	<input type="hidden" id="question_group_name" name="question_group_name"></input>
    	<input type="hidden" id="question_group_sort_num" name="question_group_sort_num"></input>
    	<input type="hidden" id="data" name="data" value="${data}"></input>
    	<input type="hidden" id="delType" name="delType" value="1"></input>
    	<input type="hidden" id="basicType" name="basicType" value="1"></input>
    	<input type="hidden" id="question_id" name="question_id"></input>
    	<input type="hidden" id="group_type" name="group_type"></input>
    	<input type="hidden" id="templId" name="templId" value="${templId}"/>
    	<input type="hidden" id="editQuestionTemplateType" name="editQuestionTemplateType"/>
    	<input type="hidden" id="questionEditFlag" name="questionEditFlag" value="false"/>
    	<input type="hidden" id="questionType" name="questionType" value=""/>
    	<!-- 单选框赋值，分值类型还是是否类型 -->
    	<input type="hidden" id="MyRedioFlag" name="MyRedioFlag" value=""/>
        <div id="create_panel">
                <div id="title">基本设置
                	<input type="button" class="basic_button" id="basic_edit" value="编辑"/> 
                	<input type="button" class="basic_button" style="display: none" id="basic_save" value="保存"/> 
                </div>
                <div class="form_row" id="form_row_id">
                	<div class="column_left">选择行业</div>
                	<div class="column" >
                		<span style="color: rgb(-91,99,109)">
                		${industry.industryName}
                		</span>
                	</div>
                	<c:if test="${industry.subIndustryName !=null}">
                	<div class="column_left">选择子行业</div>
                	<div class="column" >
                		<span style="color: rgb(-91,99,109)">
                		${industry.industryName}
                		</span>
                	</div>
                	</c:if>
                	<c:if test="${industry.templName !=null}">
                	<div class="column_left">选择模板</div>
                	<div class="column" >
                		<span style="color: rgb(-91,99,109)">
                		${industry.templName}
                		</span>
                	</div>
                	</c:if>
                </div>
                
                <div class="form_row">
                	<div class="column_left">调查名称</div>
                	<div class="column_right">
                		<span style="color: rgb(-91,99,109)" class="warpper_center_right_span"><span id="basic_questionName">${industry.qustnrName }</span>
                			<input type="text" name="qustnr_name" id="qustnr_name" value="${industry.qustnrName }" style="width:200px;display: none"/>
                		</span>
					</div>
                </div>
                <div class="form_row">
                	<div class="column_left">设定样本量</div>
                	<div class="column_right">
	                	<span style="color: rgb(-91,99,109)"  class="warpper_center_right_span"><span id="basic_credit_amount">${industry.creditAmount }</span>
	                		<input type="text" name="credit_amount" id="credit_amount" value="${industry.creditAmount }" style="width:50px;display: none"/></span><span style="margin-left: 10px;text-align: center; color: rgb(110,164,5)">账户可用样本量:&nbsp;&nbsp;<span id="amount">${amount}</span><input type="button" style="border: 0;background-color: transparent;color: rgb(110,164,5)" id="refurbish" value="刷新"/></span>
	               		</span>
                	</div>
                </div>
        	</div>
        </form>
         <div class="clear_div" id="clear_div">
            <span>问卷设置
            </span>
        </div>
       <c:if test="${list !=null }">
        <c:forEach var="question" items="${list}">
        <c:if test="${ question.key.filterFlag ==true}">
        <div class="div_center">
        <c:if test="${question.key.type ==1 }">
            <div class="div_center_title">
                <span style="margin-left: 100px;font-size:15px;padding-top: 10px;position: absolute;color: <c:if test="${question.key.type ==0}">rgb(204,204,204)</c:if>
                <c:if test="${question.key.type ==1}">rgb(82,82,82)</c:if>">
                	<c:if test="${question.key.type ==1}">
                		<input type="checkbox" id="check_group_${question.key.questionGroupId}" onclick="selectGroup('${question.key.questionGroupId}')"/><a href="javascript:void(0)" style="color: rgb(82,82,82)"  onclick="editquestionTemplate('${question.key.questionGroupId}')"><strong>${question.key.name}</strong></a>
                	</c:if>
                	<c:if test="${question.key.type ==0}">
                		<strong>${question.key.name}</strong>
                	</c:if>
                </span><span style="margin-left: 280px;padding-top: 13px;position: absolute;color: rgb(204,204,204)" id="dispanVale_${question.key.questionGroupId}">${question.key.displayValue}</span>
            </div>
            </c:if>
          	<c:if test="${question.key.type ==1 }">
            	<div class="question_option">
            </c:if>
            <c:if test="${question.key.type ==0 }">
            <div class="question_option_longitudinal">
            </c:if>
            
           	<div>
                <ul id="add_${question.key.questionGroupId}" class="sortcontainer">
	             	<c:forEach var="option" items="${question.value}">
	             		<c:if test="${option.filterFlag ==true}">
	           			 <li id="${option.questionId}">
		           			 <input type="checkbox" onclick="selectOrunSelect('${question.key.questionGroupId}','${option.questionId}')" id="check_${option.questionId}"<c:if test='${option.activeFlag==true}'>checked="checked"</c:if> />
		           			<a href="javascript:void(0)" onclick="editquestion('${option.questionType}','${question.key.questionGroupId}','${option.questionId}','${question.key.name}',${option.templateFlag})">
		           			 <span id="${question.key.questionGroupId}_${option.questionId}">${option.questionName}</span>
		           			 </a>
		           			 <c:if test="${question.key.type ==0 }">
	           			 		<span style="margin-left: 20%; font-size:12px;color:rgb(204,204,204)" id="editSpan_${option.questionId}">${option.questionValue}</span>
	           			 	</c:if>
		           			 <c:if test="${option.templateFlag==false}">
<%-- 			           			 <span style="padding-left:1px;"><a style="color: rgb(205,204,204)" data-toggle="modal" onclick="editquestion('${option.questionType}','${question.key.questionGroupId}','${option.questionId}','${question.key.name}')">编辑</a></span> --%>
			           			 <span style="padding-left:2px;color: rgb(82,82,82);margin-left: 8%;"><a style="color: rgb(82,82,82);font-size:12px;" onclick="delQuestion('${option.questionId}','${option.questionName}')">删除</a></span>
		           			 </c:if>
		           			 <c:if test="${option.templateFlag==true&& question.key.name eq '顾客消费习惯及背景调查'}">
		           			 	<span style="padding-left:1px;"><input type="button" id="top_${option.questionId}" class="<c:if test="${option.topFlag==0}">question_top_onSelect</c:if>
		           			 	<c:if test="${option.topFlag==1}">question_top_select</c:if>"
		           			 	 data-toggle="modal" onclick="questionTop('${option.questionId}','${option.topFlag}')" style="background-color:transparent;border:0" value="置顶"/></span>
		           			 </c:if>
	           			 </li>
	             		</c:if>
	                 </c:forEach>
                </ul>
                </div>
                <div class="clearfix"></div>
                <c:if test="${question.key.businessType !='groupOverview' }">
                <div class="div_button" style="margin-top:20px;">
	            	<button onclick="addquestion('${question.key.customQuestionType}','${question.key.questionGroupId}','${question.key.name}','${question.key.sortNumber}','${question.key.displayValue}')"></button>
	                <span style="color:rgb(204,204,204)">添加'${question.key.name}'自定义指标</span>
           		</div>
           		</c:if>
            </div>
        </div>
        </c:if>
        </c:forEach>
        </c:if>
        <div class="div_center">
            <div class="div_center_title">
                <input type="checkbox" style="margin-left: 100px;margin-top: 10px;position: absolute;" onclick="selectWelcomeMsg(this)" <c:if test='${industry.activeFlag==true}'>checked="checked"</c:if>/>
                <span style="margin-left: 115px;padding-top: 7.5px;position: absolute;color: rgb(82,82,82)"><strong>设置调查欢迎语</strong></span>
            </div>
            <div class="question_option">
            	<textarea rows="3" cols="1" id ="welcome_msg" style="width: 615px;height: 70px;" <c:if test='${industry.activeFlag==false}'>disabled="disabled"</c:if> >${industry.welcomeMsg }</textarea>
            </div>
        </div>
        <div class="div_center">
            <div class="div_center_title">
                <input type="checkbox" style="margin-left: 100px;margin-top: 10px;position: absolute;" onclick="selectEndWelcomeMsg(this)" <c:if test='${industry.endActiveFlag==true}'>checked="checked"</c:if>/>
                <span style="margin-left: 115px;padding-top: 7.5px;position: absolute;color: rgb(82,82,82)"><strong>设置调查结后语</strong></span>
            </div>
            <div class="question_option">
            	<textarea rows="3" cols="1" id ="end_welcome_msg" style="width: 615px;height: 70px;" <c:if test='${industry.endActiveFlag==false}'>disabled="disabled"</c:if> >${industry.endWelcomeMsg }</textarea>
            </div>
        </div>
        <div class="wrapper_button">
            <div>
                <button class="wrapper_button_select" id="create_question">保存设置</button>
                <button class="wrapper_button_select" id="release_question" >发布调查</button>
            </div>
            <div class="yl">
                <button class="wrapper_button_select" id="preview_question">问卷预览</button>
            </div>
        </div>
    </div>
</div>
   <div id="MyGroupRadio">
		<form action="" id="MyGroupRediofrom">
       	<div id="model_redio" style="height: 100px;">
       		<table>
     				<tr>	
	                    <td  class="table_question">名称:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="redioGroupName" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">描述:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="redioQredioGroupDesc" /></td>
	                </tr>
       		</table>
	    </div>
	    </form>
    </div>
	
	
	<div id="MyRedio">
		<form action="" id="MyRediofrom">
       	<div id="model_redio" style="height: 100px;">
       		<table>
     				<tr>	
	                    <td  class="table_question">名称:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="redioQuestion" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">描述:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="redioQuestionName" /></td>
	                </tr>
       		</table>
	    </div>
	    </form>
    </div>
    <div id="MyEditRedio">
		<form action="" id="MyEditRediofrom">
       	<div id="model_redio" style="height: 100px;">
       		<table>
     				<tr>	
	                    <td  class="table_question">名称:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="redioEditQuestionName" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">描述:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="redioEditQuestionValue" /></td>
	                </tr>
       		</table>
	    </div>
	    </form>
    </div>
    
    
    <div id="myCheck">
       	<div id="model_Check">
       	<form action="" id="myCheckForm">
			<table>
				<tr>
       				<td  class="table_question">名称:</td>
       				<td class="table_question_name"><input data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_name" id="questionCheckName" /></td>
       			</tr>
       			<tr>
       				<td class="table_question">描述:</td>
       				<td class="table_question_background_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_background_text" id="questionCheckVal" /></td>
       			</tr>
       			<tr>
       				<td class="table_question">
       					<span>答案选项</span>
       				</td>
       			</tr>
       			</table>
    			<table style="margin-top: -10px;" id="tableListAll">
       			</table>
			</form>
			<div>
				<button style="margin-left: 108px;margin-top: 10px;widht:10px;height:30px" class="checkBtn" id="checkBtn" value="添加">添加</button>
			</div>
		</div>
    </div>
    
    <div id="myEditCheck">
       	<div id="myOverAll_model_Check">
       	<form action="" id="myEditCheckForm">
			<table>
				<tr>
       				<td  class="table_question">名称:</td>
       				<td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="questionEditCheckName" /></td>
       			</tr>
       			<tr>
       				<td class="table_question">描述:</td>
       				<td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="questionEditCheckVal" /></td>
       			</tr>
       			<tr>
       			<td class="table_question">选项:</td>
       				<td class="table_question_text" id="option_List">
       					
       				</td>
       			</tr>
       			</table>
			</form>
		</div>
    </div>
    
     <div id="myOverAll">
	    <div id="myOverAll_model_Check">
	        <form action="" id="myCheckForm">
	            <table>
	                <tr>
	                    <td  class="table_question">名称:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="questionScoreName" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">描述:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="questionScoreVal" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">选项:</td>
	                    <td class="table_question_text"><input type="radio" checked="checked" id="radio5" name="questionScoreRadio" value="5" style="margin-left: 30px"/>5分值<input type="radio" id="radio7"  value="7" name="questionScoreRadio"/>7分值<input id="radio10" type="radio" value="10"  name="questionScoreRadio"/>10分值</td>
	                </tr>
	            </table>
	        </form>
	    </div>
	</div>
	<div id="myEditOverAll">
	    <div id="myOverAll_edit_model_Check">
	        <form action="" id="myEditCheckForm">
	            <table>
	                <tr>
	                    <td  class="table_question">名称:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="questionEditScoreName" disabled="disabled" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">描述:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="questionEditScoreVal" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">选项:</td>
	                    <td class="table_question_text"><span class="table_question_text" id="myEditOverAllScore"></span></td>
	                </tr>
	            </table>
	        </form>
	    </div>
	</div>
	
	
	 <div id="delModelQuestion">
       	<div id="model_del">
			<div style="margin: 20px 0 0 20px" >
				<span id="delName"></span>
			</div>
		</div>
    </div>
		
	<jsp:include page="include/footer.jsp" />
	<script src="${ctx}/assets/js/editDataQuestion.js"></script>
	<script src="${ctx}/assets/js/editQuestion.js"></script>
</body>

</html>