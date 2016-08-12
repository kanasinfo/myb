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
<link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-3.3.5/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-star-rating-master/css/star-rating.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/assets/Font-Awesome-3.2.1/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css"  href="${ctx}/assets/css/answer.css"/>
<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-star-rating-master/js/star-rating.js"></script>
<title>欢迎</title>
</head>
<body>

<div id="header">
	<div class="container">
		<div class="row">
		XXX满意度调查
		</div>
	</div>
</div>

<div id="body">
	<div class="container">
		<div class="row title">
			尊敬的顾客：
		</div>
		<div class="row welcome">
			感谢您利用宝贵的时间参与我们的问卷调查，接下来您将看到以下问卷中出现的20道题目，请您根据自己的想法进行答题，答案没有对错之分，关于您的个人信息我们将只作为研究目的进行分析，绝对不会将您的个人信息透露给第三方知晓，再次感谢您的参与。
		</div>
		<div class="row">
			<div class="block"></div>
			请问您对本次用餐是否满意？
		</div>
		<div class="row">
			<ul>
				<li></li>
			</ul>
		</div>
	</div>
</div>

	<input type="hidden" id="data" name="data" value="${data}"/>
        <div class="panel">
            <div class="main_top">
                <span>顾客满意度调查</span>
            </div>
            <c:if test="${msg!=''}">
	            <div class="main_msg">
	                <div>
	                    尊敬的顾客:
	                </div>
	                <div>
	                   ${msg }
	                </div>
	            </div>
            </c:if>
            <div class="main_botton">
            
             <c:if test="${list !=null}">
            	 <c:forEach var="group" items="${list}">
				<c:if test="${group.key.customQuestionType=='1' || group.key.customQuestionType=='0'}">
					<div class="main_score_des">
                    	<span>${group.key.name }</span>
               		</div>		
				</c:if>
				<c:if test="${group.key.customQuestionType=='0'}">
					 <c:forEach var="questionScore" items="${group.value}">
					 	<c:if test="${questionScore.activeFlag ==true }">
						 	<div>
		                    	${questionScore.questionName}
		                    </div>
	                     	<input id="${questionScore.questionId }" value="1" type="number" class="abcd"/>
                     	</c:if>
                     </c:forEach>
				</c:if>
				<c:if test="${group.key.customQuestionType=='1'}">
					  <ul>
                       <c:forEach var="questionCheck" items="${group.value}">
                       <c:if test="${questionCheck.activeFlag ==true }">
                          <li><input type="checkbox" id="${ questionCheck.questionId}" name="${ questionCheck.questionId}radioName" onclick="selectCheck('${ questionCheck.questionId}')" />${questionCheck.questionName}</li>
                          </c:if>
                         </c:forEach>
                      </ul>
				</c:if>
				<c:if test="${group.key.customQuestionType=='3'}">
					 <c:forEach var="questionMulti" items="${group.value}">
					 	<c:if test="${questionMulti.questionType=='0' }">
					 	<c:if test="${questionMulti.activeFlag ==true }">
						 	<div>
		                    	${questionMulti.questionName}
		                    </div>
                     		<input id="${questionMulti.questionId }" value="1" type="number" class="abcd"/>
                     		</c:if>
					 	</c:if>
					 	<c:if test="${questionMulti.questionType=='1' }">
					 	<c:if test="${questionMulti.activeFlag ==true }">
					 		<div >
	                    		${questionMulti.questionName}
	                    	</div>
                     		<input type="checkbox" id="${ questionMulti.questionId}" name="${ questionMulti.questionId}radioName" onclick="selectCheck('${ questionMulti.questionId}')" />${questionMulti.questionName}
                     		</c:if>
					 	</c:if>
					 	<c:if test="${questionMulti.questionType=='3' }">
					 	<c:if test="${questionMulti.activeFlag ==true }">
					 		<div >
	                    		${questionMulti.questionName}
	                    	</div>
	                    	<ul>
		                      	<c:forEach var="questionmultiOption" items="${questionMulti.options}">
		                          <li><input type="radio" id="${ questionmultiOption.optionId}" name="${ questionMulti.questionId}radioName" value="${questionmultiOption.optionValue }" onclick="selectRadio('${ questionMulti.questionId}','${ questionmultiOption.optionId}')" />${questionmultiOption.optionName}</li>
	                         	</c:forEach>
	                      	</ul>
	                      	</c:if>
					 	</c:if>
                     </c:forEach>
				</c:if>
            	 </c:forEach>
           	 </c:if>
        </div>
        <div class="main_hander">
        	<button id="submitBtn">提交</button>
        </div>
        </div>
        <script type="application/javascript">
           jQuery(function($){
        		var data = decodeURIComponent($("#data").val());
        		var dataDecode = JSON.parse(data);
        		var selectCheck = function(id){
        			 for (var i = 0; i <dataDecode.answers.length; i++) {
 						if(dataDecode.answers[i].questionId == id){
 							dataDecode.answers[i].questionIdValue=id+"_"+$("#" + id).prop("checked");
 							dataDecode.answers[i].optionValue = $("#" + id).prop("checked");
 							var key = id+"_optionValue";
							dataDecode.answers[i][key] = $("#" + id).prop("checked");
 						}
 					}
        		}
        		var selectRadio = function(questionId,id){
        			alert(questionId);
        			alert(id);
       			 for (var i = 0; i <dataDecode.answers.length; i++) {
       				 alert(dataDecode.answers[i].questionId == questionId);
						if(dataDecode.answers[i].questionId == questionId){
							dataDecode.answers[i].questionIdValue=questionId+"_"+$("#" + id).val();
							dataDecode.answers[i].optionValue = $("#" + id).val();
							var key = questionId+"_optionValue";
							dataDecode.answers[i][key] = $("#" + id).val();
						}
					}
       		}
        		$("#submitBtn").click(function(){
        			$.ajax({
        				url : '../answer/addAnswer.json?R' + Math.random(),
        				data : {
        					"data" : JSON.stringify(dataDecode)
        				},
        				method : 'POST',
        				dataType : 'JSON',
        				success : function(data) {
        					if (data.success) {
        						alert(data.message);
        					} else {
        						alert(data.message);
        					}
        				}
        			})
        			
        		})
        		
                 $('.abcd').rating({

            min: 0,
            max: 10,
            step: 1,
            size: 'xs',
            stars:10,
            showClear: false,
            clearButtonTitle:false,
            starCaptions:{
                1: '1分',
                2: '2分',
                3: '3分',
                4: '4分',
                5: '5分',
                6: '6分',
                7: '7分',
                8: '8分',
                9: '9分',
                10: '10分'
            }
        });
               $('.abcd').on('rating.change', function(event, value, caption,target) {
                   $('#'+event.currentTarget.attributes.id.nodeValue).rating('update', value);
                   //给json赋值
                   for (var i = 0; i <dataDecode.answers.length; i++) {
						if(dataDecode.answers[i].questionId == event.currentTarget.attributes.id.nodeValue){
							dataDecode.answers[i].questionIdValue=event.currentTarget.attributes.id.nodeValue+"_"+$("#"+event.currentTarget.attributes.id.nodeValue).val();
							dataDecode.answers[i].optionValue = $("#"+event.currentTarget.attributes.id.nodeValue).val();
							var key = event.currentTarget.attributes.id.nodeValue+"_optionValue";
							dataDecode.answers[i][key] = $("#"+event.currentTarget.attributes.id.nodeValue).val();
						}
					}
               });
               window.selectRadio = selectRadio;
				window.selectCheck = selectCheck;
           })

        </script>

    
</body>
</html>