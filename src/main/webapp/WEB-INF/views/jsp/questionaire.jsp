<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-star-rating-master/css/star-rating.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/Font-Awesome-3.2.1/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/questionaire.css"/>
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrap-star-rating-master/js/star-rating.js"></script>
    <title>欢迎</title>
</head>
<body>
<input type="hidden" id="data" name="data" value="${data }">
<div id="header">
    <div class="container">
        <div class="row" style="padding:0 10px;">
            "${projectName }"满意度调查
        </div>
    </div>
</div>

<div id="body">
    <div class="container">
        <c:if test="${msg!=''}">
            <div class="row title">
                <div>
                    尊敬的顾客：
                </div>
            </div>
            <div class="row welcome">
                    ${msg}
            </div>
        </c:if>


        <c:if test="${list !=null}">
            <c:forEach var="group" items="${list}">
                <!-- 是否显示大帽子 和当前问题组下是否有选中问题
                select or activeFlag ??
                selectQuestionCount ???
                -->

                <c:if test="${group.key.activeFlag==1}">
                    <c:if test="${group.key.type==1&&!group.key.filterFlag==false}">
                        <div class="row ask">
                            <div class="block"></div>
                                ${group.key.displayValue}
                        </div>
                        <div class="row option">
                            <div class="col-lg-4 col-md-4 col-sm-12" style="text-align: center">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="btn-group-vertical" data-toggle="buttons" style="padding: 0 10px;">
                                            <c:forEach var="question" items="${group.value}">
                                                <c:if test="${question.activeFlag ==true }">
                                                    <c:choose>
                                                        <c:when test="${question.questionType=='com.myb.questiontype.Degree'||question.questionType=='com.myb.questiontype.Score'}">
                                                            <div class="row" style="margin-top: 10px; margin-bottom: 5px;">
                                                                <div class="col-md-12" style="text-align: left">
                                                                        ${question.questionValue}
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="btn-group" data-toggle="buttons"
                                                                         style="text-align:center;">
                                                                        <c:forEach var="option" begin="1" end="10">
                                                                            <label class="btn btn-default"
                                                                                   onclick="selectRadio('${question.questionnaireId}','${option}')"
                                                                                   style="width:auto;float:left">
                                                                                <input type="radio"
                                                                                       id="${question.questionnaireId}_${option }"
                                                                                       name="${question.questionnaireId}"
                                                                                       onclick="selectRadio('${question.questionnaireId}','${option}')"
                                                                                       value="${option}">${option}</label>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="maxValue"
                                                                         style="float:right; font-size: 12px; color: #b0b0b0;">
                                                                        非常满意
                                                                    </div>
                                                                    <div class="minValue"
                                                                         style="float:left; font-size: 12px; color: #b0b0b0;">
                                                                        非常不满意
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${question.questionType=='com.myb.questiontype.Judge'}">
                                                            <label class="btn btn-default"
                                                                   onclick="selectCheck('${question.questionnaireId}')">
                                                                <input type="checkbox" id="${question.questionnaireId}"
                                                                       value="${option.optionValue}"
                                                                       name="${question.questionnaireId}"
                                                                       onclick="selectCheck('${question.questionnaireId}')"/>
                                                                    ${question.questionName}
                                                            </label>
                                                        </c:when>
                                                        <c:when test="${question.questionType=='com.myb.questiontype.SingleSelect'}">
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <c:forEach var="option" items="${question.options}">
                                                                        <label class="btn btn-default"
                                                                               onclick="selectRadio('${question.questionnaireId}','${option.optionId}')">
                                                                            <input type="radio"
                                                                                   id="${question.questionnaireId}_${option.optionId}"
                                                                                   value="${option.optionValue }"
                                                                                   name="${question.questionnaireId}"
                                                                                   onclick="selectRadio('${question.questionnaireId}','${option.optionId}')"/>
                                                                                ${option.optionName}
                                                                        </label>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${question.questionType=='com.myb.questiontype.MultiSelect'}">
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <c:forEach var="option" items="${question.options}">
                                                                        <label class="btn btn-default"
                                                                               onclick="selectRadio('${question.questionnaireId}','${option.optionId}')">
                                                                            <input type="checkbox"
                                                                                   id="${question.questionnaireId}_${option.optionId}"
                                                                                   value="${option.optionValue }"
                                                                                   name="${question.questionnaireId}"
                                                                                   onclick="selectRadio('${question.questionnaireId}','${option.optionId}')"/>
                                                                                ${option.optionName}
                                                                        </label>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${group.key.type==0&&!group.key.filterFlag==false}">
                        <c:forEach var="question" items="${group.value}">
                            <c:if test="${question.activeFlag ==true }">
                                <div class="row ask">
                                    <div class="block"></div>
                                        ${question.questionValue}
                                </div>
                                <div class="row option">
                                    <div class="col-lg-4 col-md-4 col-sm-12" style="text-align: center">
                                        <c:choose>
                                            <c:when test="${question.questionType=='com.myb.questiontype.Judge'}">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="btn-group-vertical" data-toggle="buttons">
                                                            <label class="btn btn-default"
                                                                   onclick="selectCheck('${question.questionnaireId}')">
                                                                <input type="checkbox" id="${question.questionnaireId}"
                                                                       value="${option.optionValue }"
                                                                       name="${question.questionnaireId}"
                                                                       onclick="selectCheck('${question.questionnaireId}')"/>
                                                                    ${question.questionName}
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${question.questionType=='com.myb.questiontype.Degree'||question.questionType=='com.myb.questiontype.Score'}">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="btn-group" data-toggle="buttons">
                                                            <c:forEach var="option" begin="1" end="10">
                                                                <label class="btn btn-default"
                                                                       onclick="selectRadio('${question.questionnaireId}','${option}')">
                                                                    <input type="radio" id="${question.questionnaireId}_${option }"
                                                                           name="${question.questionnaireId}"
                                                                           onclick="selectRadio('${question.questionnaireId}','${option}')"
                                                                           value="${option}">${option}</label>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row" style="padding:0 10px;margin-bottom: 5px;">
                                                    <div class="col-md-12">
                                                        <div class="maxValue"
                                                             style="float:right; font-size: 12px; color: #b0b0b0;">非常满意
                                                        </div>
                                                        <div class="minValue"
                                                             style="float:left;  font-size: 12px; color: #b0b0b0;">非常不满意
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${question.questionType=='com.myb.questiontype.SingleSelect'}">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="btn-group-vertical" data-toggle="buttons" style="padding: 0 10px;">
                                                            <c:forEach var="option" items="${question.options}">
                                                                <label class="btn btn-default"
                                                                       onclick="selectRadio('${question.questionnaireId}','${option.optionId}')">
                                                                    <input type="radio"
                                                                           id="${question.questionnaireId}_${option.optionId}"
                                                                           value="${option.optionValue }"
                                                                           name="${question.questionnaireId}"
                                                                           onclick="selectRadio('${question.questionnaireId}','${option.optionId}')"/>
                                                                        ${option.optionName}
                                                                </label>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${question.questionType=='com.myb.questiontype.MultiSelect'}">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="btn-group-vertical" data-toggle="buttons">
                                                            <c:forEach var="option" items="${question.options}">
                                                                <label class="btn btn-default"
                                                                       onclick="selectRadio('${question.questionnaireId}','${option.optionId}')">
                                                                    <input type="checkbox"
                                                                           id="${question.questionnaireId}_${option.optionId}"
                                                                           value="${option.optionValue }"
                                                                           name="${question.questionnaireId}"
                                                                           onclick="selectRadio('${question.questionnaireId}','${option.optionId}')"
                                                                    />
                                                                        ${option.optionName}
                                                                </label>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${question.questionType=='com.myb.questiontype.TextAnswer'}">
                                                <div class="row">
                                                    <div class="col-md-12" style="padding: 0 25px;">
                                                        <input type="text" id="textName" class="form-control"
                                                               name="${question.questionnaireId}">
                                                    </div>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:if>
            </c:forEach>
        </c:if>

        <div class="row" style="text-align: center;padding-bottom:50px;">
            <button class="btn btn-default" id="submitBtn" type="submit">提交</button>
        </div>
    </div>
</div>
<script type="application/javascript">
    var ctx = '<%=request.getSession().getAttribute("ctx")%>';
    jQuery(function ($) {
        var data = decodeURIComponent($("#data").val());
        var reg = new RegExp("^[0-9]*$");
        var dataDecode = JSON.parse(data);
        var selectCheck = function (id) {
            for (var i = 0; i < dataDecode.answers.length; i++) {
                if (dataDecode.answers[i].questionnaireId == id) {
                    if ($("#" + id).is(':checked')) {
                        dataDecode.answers[i].questionIdValue = id + "_false";
                        dataDecode.answers[i].optionValue = false;
                        var key = id + "_optionValue";
                        dataDecode.answers[i][key] = false;

                    } else {
                        dataDecode.answers[i].questionIdValue = id + "_true";
                        dataDecode.answers[i].optionValue = true;
                        var key = id + "_optionValue";
                        dataDecode.answers[i][key] = true;
                    }
                }
            }
        }
        var selectRadio = function (questionnaireId, id) {
            for (var i = 0; i < dataDecode.answers.length; i++) {
                if (dataDecode.answers[i].questionnaireId == questionnaireId) {
                    dataDecode.answers[i].questionIdValue = questionnaireId + "_" + $("#" + questionnaireId + "_" + id).val();
                    var reg = new RegExp("^[0-9]*$");
                    var questionVal = $("#" + questionnaireId + "_" + id).val();
                    var key = questionnaireId + "_optionValue";
                    if (reg.test(questionVal)) {
                        questionVal = parseInt(questionVal);
                        dataDecode.answers[i].optionValue = questionVal;
                        dataDecode.answers[i][key] = questionVal;
                    } else {
                        dataDecode.answers[i].optionValue = $("#" + questionnaireId + "_" + id).val();
                        dataDecode.answers[i][key] = $("#" + questionnaireId + "_" + id).val();
                    }


                }
            }
        }

        $("#submitBtn").click(function () {
            var textName = $("#textName").val();
            var id = $("input:text").prop("name");
            if (textName != "" && textName != null) {
                for (var i = 0; i < dataDecode.answers.length; i++) {
                    if (dataDecode.answers[i].questionnaireId == id) {
                        dataDecode.answers[i].questionIdValue = id + "_" + textName;
                        dataDecode.answers[i].optionValue = textName;
                        dataDecode.answers[i]["VoiceOfCustomers"] = "VoiceOfCustomers";
                        var key = id + "_optionValue";
                    }
                }
            }
            $.ajax({
                url: ctx + '/page/answer/addAnswer.json?R' + Math.random(),
                data: {
                    "data": JSON.stringify(dataDecode)
                },
                method: 'POST',
                dataType: 'JSON',
                success: function (data) {
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
            stars: 10,
            showClear: false,
            clearButtonTitle: false,
            starCaptions: {
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
        $('.abcd').on('rating.change', function (event, value, caption, target) {
            $('#' + event.currentTarget.attributes.id.nodeValue).rating('update', value);
            //给json赋值
            for (var i = 0; i < dataDecode.answers.length; i++) {
                if (dataDecode.answers[i].questionnaireId == event.currentTarget.attributes.id.nodeValue) {
                    dataDecode.answers[i].questionIdValue = event.currentTarget.attributes.id.nodeValue + "_" + $("#" + event.currentTarget.attributes.id.nodeValue).val();
                    dataDecode.answers[i].optionValue = $("#" + event.currentTarget.attributes.id.nodeValue).val();
                    var key = event.currentTarget.attributes.id.nodeValue + "_optionValue";
                    dataDecode.answers[i][key] = $("#" + event.currentTarget.attributes.id.nodeValue).val();
                }
            }
        });
        window.selectRadio = selectRadio;
        window.selectCheck = selectCheck;
    })

</script>


</body>
</html>