jQuery(function($) {
	var data = decodeURIComponent($("#data").val());
	var data1 = JSON.parse(data);
	$("#refurbish").click(function(){
		$.ajax({
			url:'../queryAmount.json?r='+Math.random(),
			method:'get',
			dataType:'json',
			success:function(data){
				if(data.success){
					$("#amount").html(data.data);
				}else{
//					alert(data.message);
				}
			},
			error:function(){
			}
		})
	})
	$("#preview_question").click(function() {
		data1.welcome.welcomeMsg = $("#welcome_msg").val();
		openPostWindow("../../answer/previewAnswer.html", data1, "target");
	})

	$("#release_question").click(
			function() {
				var questionEditFlag = $("#questionEditFlag").val();
				if (data1.welcome.welcomeMsg != $("#welcome_msg").val()) {
					alert("请保存数据!");
				} else if (questionEditFlag == 'true') {
					alert("请保存数据!");
				} else {
					var number = $("input:checkbox:checked").size();
					alert(number);
					if(number==0){
						alert("没有选中任何问题！");
					}else{

						var templateId = $("#templId").val();
						window.location.href = "../../release/releaseQuestion/"+ templateId + ".html";	
					}
					
				}
			})
	var delQuestion = function(id, name) {
		var delName = "是否确认删除”";
		delName += name;
		delName += "“问题选项？";
		$("#delName").text(delName);
		$("#delModelQuestion").dialog("open");
		$("#question_id").val(id);

	}
	$('ul.sortcontainer').sortable();
	$("ul.sortcontainer").sortable({
		update : function(event, ui) {
			$(".question_option .sortcontainer li").each(function(i) {
				for (var j = 0; j < data1.questions.length; j++) {
					if (this.id == data1.questions[j].questionId) {
						data1.questions[j].sortNumber = i;
					}
				}
			})
		}
	});
	$("#myEditOverAll").dialog(
			{
				autoOpen : false,
				height : 300,
				width : 500,
				modal : true,
				buttons : {
					"保存" : function() {
						if ($('#myEditCheckForm').validationEngine(
								'validate') == false)
							return;
						
						var groupId = $("#groupId").val();
						var questionEditScoreVal = $("#questionEditScoreVal").val();
						var question_id = $("#question_id").val();
						for(var i =0 ;i<data1.questions.length;i++){
							if(data1.questions[i].questionId==question_id){
								data1.questions[i].questionValue = questionEditScoreVal;
							}
						}
						$("#editSpan_"+question_id).html(questionEditScoreVal);
						$(this).dialog("close");
					},
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			})
	
	$("#myOverAll").dialog(
					{
						autoOpen : false,
						height : 300,
						width : 500,
						modal : true,
						buttons : {
							"保存" : function() {
								if ($('#MyRediofrom').validationEngine(
										'validate') == false)
									return;
								var groupId = $("#groupId").val();
								var templId = $("#templId").val();
								var questionName = $("#questionScoreName").val();
								var question = $("#questionScoreVal").val();
								var questionJson = {};
								var question_id = $("#question_id").val();
								if (question_id == '') {
									question_id = UUID.prototype.createUUID();
								}
								questionJson.questionId = question_id;
								questionJson.questionGroupId = groupId;
								questionJson.filterFlag = true;
								questionJson.questionGroupName = $("#question_group_name").val();
								questionJson.questionGroupSortNumber = $("#question_group_sort_num").val();
								questionJson.questionName = question;
								questionJson.questionValue = questionName;
								questionJson.sortNumber = 2;
								questionJson.activeFlag = true;
								questionJson.editFlag = 1;
								questionJson.templateFlag = false;
								var mathNumber =  $('input[name="questionScoreRadio"]:checked').val();
								var optionData = [];
								//获取分值类型
								for(var i =1;i<=mathNumber;i++){
									var option = {};
									option.optionId = UUID.prototype.createUUID();
									option.activeFlag = "y";
									option.sortNumber = 0;
									option.optionName = i+"分";
									option.optionValue = i;
									optionData.push(option);
								}
								questionJson.options = optionData;
								data1.questions.push(questionJson);
								var html = "<li id=" + question_id+ "><input type='checkbox' /><span id="+ groupId + "_" + question_id + ">"+ question + "</span>";
								html = html
										+ "<span style='padding-left:1px;'><a style='color: rgb(205,204,204)' onclick='editquestion(\""
										+ $("#group_type").val() + "\",\""
										+ $("#groupId").val() + "\",\""
										+ question_id + "\")' >编辑</a></span>";
								html = html
										+ "<span style='padding-left:2px;color: rgb(205,204,204)'><a style='color: rgb(205,204,204)' onclick='delQuestion(&apos;"
										+ question_id + "&apos;,&apos;"
										+ questionName
										+ "&apos;)'>删除</a></span></li>";
								for (var j = 0; j < data1.questions.length; j++) {
									if (data1.questions[j].questionId == question_id) {
										data1.questions.splice(j, 1);
									}
								}
								for (var i = 0; i < data1.questionGroup.length; i++) {
									if (data1.questionGroup[i].questionGroupId == groupId) {
										questionJson.chartOneDimnsn = data1.questionGroup[i].chartOneDimnsn;
										questionJson.chartMultiDimnsn = data1.questionGroup[i].chartMultiDimnsn;
										questionJson.chartStore = data1.questionGroup[i].chartStore;
										questionJson.chartTime = data1.questionGroup[i].chartTime;
										questionJson.chartTimeDimnsn = data1.questionGroup[i].chartTimeDimnsn;
										questionJson.questionType = data1.questionGroup[i].customQuestionType
									}
								}
								var textVla = $(
										"#" + groupId + "_" + question_id)
										.text();
								if (textVla == '') {
									$("#add_" + groupId).append(html);
								} else {
									$("#" + groupId + "_" + question_id).text(
											questionName)
								}
								data1.questions.push(questionJson);
								$(this).dialog("close");
							},
							"关闭" : function() {
								$(this).dialog("close");
							}
						}
					})

	$("#editTemplateDisplayVal").dialog(
					{
						autoOpen : false,
						height : 180,
						width : 500,
						modal : true,
						buttons : {
							"保存" : function() {
								if (jQuery('#myEditTemplateFrom')
										.validationEngine('validate') == false)
									return;
								var questionId = $("#question_id").val();
								var groupId = $("#groupId").val();
								var editQuestionTemplateType = $(
										"#editQuestionTemplateType").val();
								var questionTemplateVal = $(
										"#questionTemplateVal").val();
								for (var i = 0; i < data1.questions.length; i++) {
									if (data1.questions[i].questionId == questionId) {
										data1.questions[i].questionValue = questionTemplateVal;
										if (editQuestionTemplateType == 1) {
											data1.questions[i].questionName = questionTemplateVal;
											$("#" + groupId + "_" + questionId)
													.text(questionTemplateVal);
										}
									}
								}
								$(this).dialog("close");
							},
							"关闭" : function() {
								$(this).dialog("close");
							}
						}
					})
	$("#MyScore")
			.dialog(
					{
						autoOpen : false,
						height : 230,
						width : 420,
						modal : true,
						buttons : {
							"保存" : function() {
								if (jQuery('#myScoreFrom').validationEngine('validate') == false)
									return;
								var groupId = $("#groupId").val();
								var templId = $("#templId").val();
								var questionName = $("#questionName").val();
								var question = $("#question").val();
								var option = "";
								var questionJson = {};
								var question_id = $("#question_id").val();
								if (question_id == '') {
									question_id = UUID.prototype.createUUID();
								}
								questionJson.questionId = question_id;
								questionJson.filterFlag = true;
								questionJson.questionGroupId = groupId;
								questionJson.questionGroupName = $("#question_group_name").val();
								questionJson.questionGroupSortNumber = $("#question_group_sort_num").val();
								questionJson.questionName = questionName;
								questionJson.questionValue = questionName;
								questionJson.sortNumber = 2;
								questionJson.activeFlag = true;
								questionJson.editFlag = 1;
								questionJson.templateFlag = false;
								var optionData = [];
								var html = "<li id=" + question_id+ "><input type='checkbox' /><span id="
										+ groupId + "_" + question_id + ">"
										+ questionName + "</span>";
								html = html
										+ "<span style='padding-left:1px;'><a style='color: rgb(205,204,204)' onclick='editquestion(\""
										+ $("#group_type").val() + "\",\""
										+ $("#groupId").val() + "\",\""
										+ question_id + "\")' >编辑</a></span>";
								html = html
										+ "<span style='padding-left:2px;color: rgb(205,204,204)'><a style='color: rgb(205,204,204)' onclick='delQuestion(&apos;"
										+ question_id + "&apos;,&apos;"
										+ questionName
										+ "&apos;)'>删除</a></span></li>";
								for (var i = 0; i < 10; i++) {
									var option = {};
									option.optionId = UUID.prototype
											.createUUID();
									option.activeFlag = "y";
									option.sortNumber = i;
									option.optionName = $(this).val();
									option.optionValue = i + 1;
									optionData.push(option);
								}
								questionJson.options = optionData;
								for (var j = 0; j < data1.questions.length; j++) {
									if (data1.questions[j].questionId == question_id) {
										data1.questions.splice(j, 1);
									}
								}
								for (var i = 0; i < data1.questionGroup.length; i++) {
									if (data1.questionGroup[i].questionGroupId == groupId) {
										questionJson.chartOneDimnsn = data1.questionGroup[i].chartOneDimnsn;
										questionJson.chartMultiDimnsn = data1.questionGroup[i].chartMultiDimnsn;
										questionJson.chartStore = data1.questionGroup[i].chartStore;
										questionJson.chartTime = data1.questionGroup[i].chartTime;
										questionJson.chartTimeDimnsn = data1.questionGroup[i].chartTimeDimnsn;
										questionJson.questionType = data1.questionGroup[i].customQuestionType
									}
								}
								var textVla = $("#" + groupId + "_" + question_id).text();
								if (textVla == '') {
									$("#add_" + groupId).append(html);
								} else {
									$("#" + groupId + "_" + question_id).text(
											questionName)
								}
								data1.questions.push(questionJson);
								$(this).dialog("close");
							},
							"关闭" : function() {
								$(this).dialog("close");
							}
						}
					});
	$("#delModelQuestion").dialog({
		autoOpen : false,
		height : 220,
		width : 500,
		modal : true,
		buttons : {
			"保存" : function() {
				var id = $("#question_id").val();
				for (var i = 0; i < data1.questions.length; i++) {
					if (data1.questions[i].questionId == id) {
						data1.questions.splice(i, 1);
						$("#" + id).remove();
					}
				}
				$(this).dialog("close");
			},
			"关闭" : function() {
				$(this).dialog("close");
			}
		}
	})
	
	$("#myEditCheck").dialog(
			{
				autoOpen : false,
				height : 330,
				width : 600,
				modal : true,
				buttons : {
					"保存" : function() {
						if ($('#MyEditRediofrom').validationEngine(
								'validate') == false)
							return;
						var groupId = $("#groupId").val();
						var templId = $("#templId").val();
						var questionName = $("#redioQuestionName").val();
						var question = $("#redioQuestion").val();
						var question_id = $("#question_id").val();
						var questionEditCheckName = $("#questionEditCheckName").val();
						var questionEditCheckVal = $("#questionEditCheckVal").val();
						for(var i = 0;i<data1.questions.length;i++){
							if(data1.questions[i].questionId==question_id){
								data1.questions[i].questionName = redioEditQuestionName;
								data1.questions[i].questionValue = redioEditQuestionValue;
							}
						}
						$("#"+groupId+"_"+question_id).html(questionEditCheckName);
						$("#editSpan_"+question_id).html(questionEditCheckVal);
						$(this).dialog("close");
					},
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			}
			)
	$("#MyEditRedio").dialog(
			{
				autoOpen : false,
				height : 230,
				width : 500,
				modal : true,
				buttons : {
					"保存" : function() {
						if ($('#MyEditRediofrom').validationEngine(
								'validate') == false)
							return;
						var groupId = $("#groupId").val();
						var templId = $("#templId").val();
						var questionName = $("#redioQuestionName").val();
						var question = $("#redioQuestion").val();
						var question_id = $("#question_id").val();
						var redioEditQuestionName = $("#redioEditQuestionName").val();
						var redioEditQuestionValue = $("#redioEditQuestionValue").val();
						for(var i = 0;i<data1.questions.length;i++){
							if(data1.questions[i].questionId==question_id){
								data1.questions[i].questionName = redioEditQuestionName;
								data1.questions[i].questionValue = redioEditQuestionValue;
							}
						}
						var questionType = $("#questionType").val();
						$("#"+groupId+"_"+question_id).html(redioEditQuestionName);
						if(questionType==2){
							$("#editSpan_"+question_id).html(redioEditQuestionValue);
						}
						$(this).dialog("close");
					},
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			}
			)
	$("#MyRedio").dialog(
					{
						autoOpen : false,
						height : 230,
						width : 500,
						modal : true,
						buttons : {
							"保存" : function() {
								if ($('#MyRediofrom').validationEngine(
										'validate') == false)
									return;
								var groupId = $("#groupId").val();
								var templId = $("#templId").val();
								var questionName = $("#redioQuestionName").val();
								var question = $("#redioQuestion").val();
								var questionJson = {};
								var question_id = $("#question_id").val();
								if (question_id == '') {
									question_id = UUID.prototype.createUUID();
								}
								questionJson.questionId = question_id;
								questionJson.questionGroupId = groupId;
								questionJson.filterFlag = true;
								questionJson.questionGroupName = $("#question_group_name").val();
								questionJson.questionGroupSortNumber = $("#question_group_sort_num").val();
								questionJson.questionName = question;
								questionJson.questionValue = questionName;
								questionJson.sortNumber = 2;
								questionJson.activeFlag = true;
								questionJson.editFlag = 1;
								questionJson.templateFlag = false;
								//获取类型 0 1 2
								var MyRedioFlag = $("#MyRedioFlag").val();
								var optionData = [];
								//十分之题
								if(MyRedioFlag==0){
									for(var i = 1;i<=10;i++){
										var option = {};
										option.optionId = UUID.prototype.createUUID();
										option.activeFlag = "y";
										option.sortNumber = i;
										option.optionName = i+"分";
										option.optionValue = i;
										optionData.push(option);
									}
								//是否题
								}else if(MyRedioFlag==1){
									var option = {};
									option.optionId = UUID.prototype.createUUID();
									option.activeFlag = "y";
									option.sortNumber = "1";
									option.optionName = "是";
									option.optionValue = "1";
									optionData.push(option);
									option.optionId = UUID.prototype.createUUID();
									option.activeFlag = "y";
									option.sortNumber = "2";
									option.optionName = "否";
									option.optionValue = "2";
									optionData.push(option);
								//text题
								}
								var questionType = $("#questionType").val();
								questionJson.options = optionData;
								data1.questions.push(questionJson);
								var html = "";
								if(MyRedioFlag==2){
									 html = html +"<li id=" + question_id
										+ ">" 
										+"<input type='checkbox' onclick='selectOrunSelect(&apos;"+groupId+"&apos;,&apos;"+question_id+"&apos;)' id='check_"+question_id+"'/>"
										+"<a href='javascript:void(0)' onclick='editquestion(&apos;"+questionType+"&apos;,&apos;"+groupId+"&apos;,&apos;"+question_id+"&apos;,&apos;"+questionJson.questionGroupName+"&apos;,"+false+")'>"
										+"<span id="
										+ groupId + "_" + question_id + ">"
										+ question + "</span>"
										+"</a>"
										+"<span style='margin-left: 20%; font-size:12px;color:rgb(204,204,204)' id='editSpan_"+question_id+"'>"+questionName+"</span>"
										;
								html = html
										+ "<span style='padding-left:2px;color: rgb(205,204,204)'><a style='color: rgb(205,204,204)' onclick='delQuestion(&apos;"
										+ question_id + "&apos;,&apos;"
										+ questionName
										+ "&apos;)'>删除</a></span></li>";
								}else{
									 html = html +"<li id=" + question_id
									+ ">" 
									+"<input type='checkbox' onclick='selectOrunSelect(&apos;"+groupId+"&apos;,&apos;"+question_id+"&apos;)' id='check_"+question_id+"'/>"
									+"<a href='javascript:void(0)' onclick='editquestion(&apos;"+questionType+"&apos;,&apos;"+groupId+"&apos;,&apos;"+question_id+"&apos;,&apos;"+questionJson.questionGroupName+"&apos;,"+false+")'>"
									+"<span id="
									+ groupId + "_" + question_id + ">"
									+ question + "</span>"
									+"</a>";
							html = html
									+ "<span style='padding-left:2px;color: rgb(205,204,204)'><a style='color: rgb(205,204,204)' onclick='delQuestion(&apos;"
									+ question_id + "&apos;,&apos;"
									+ questionName
									+ "&apos;)'>删除</a></span></li>";
								}
								
								for (var j = 0; j < data1.questions.length; j++) {
									if (data1.questions[j].questionId == question_id) {
										data1.questions.splice(j, 1);
									}
								}
								for (var i = 0; i < data1.questionGroup.length; i++) {
									if (data1.questionGroup[i].questionGroupId == groupId) {
										questionJson.chartOneDimnsn = data1.questionGroup[i].chartOneDimnsn;
										questionJson.chartMultiDimnsn = data1.questionGroup[i].chartMultiDimnsn;
										questionJson.chartStore = data1.questionGroup[i].chartStore;
										questionJson.chartTime = data1.questionGroup[i].chartTime;
										questionJson.chartTimeDimnsn = data1.questionGroup[i].chartTimeDimnsn;
										questionJson.questionType = data1.questionGroup[i].customQuestionType
									}
								}
								var textVla = $(
										"#" + groupId + "_" + question_id)
										.text();
								if (textVla == '') {
									$("#add_" + groupId).append(html);
								} else {
									$("#" + groupId + "_" + question_id).text(
											questionName)
								}
								data1.questions.push(questionJson);
								$(this).dialog("close");
							},
							"关闭" : function() {
								$(this).dialog("close");
							}
						}
					});
	$("#myCheck")
			.dialog(
					{
						autoOpen : false,
						height : 520,
						width : 700,
						modal : true,
						buttons : {
							"保存" : function() {
								if (jQuery('#myCheckForm').validationEngine(
										'validate') == false)
									return;
								var groupId = $("#groupId").val();
								var templId = $("#templId").val();
								var questionName = $("#questionCheckName")
										.val();
								var question = $("#questionCheckVal").val();
								var option = "";
								var questionJson = {};
								var question_id = $("#question_id").val();
								if (question_id == '') {
									question_id = UUID.prototype.createUUID();
								}
								questionJson.questionId = question_id;
								questionJson.filterFlag = true;
								questionJson.questionGroupId = groupId;
								questionJson.questionGroupName = $(
										"#question_group_name").val();
								questionJson.questionGroupSortNumber = $(
										"#question_group_sort_num").val();
								questionJson.questionName = questionName;
								questionJson.questionValue = question;
								questionJson.sortNumber = 2;
								questionJson.activeFlag = true;
								questionJson.templateFlag = false;
								questionJson.editFlag = 1;
								var optionData = [];
								var html = "<li id=" + question_id
										+ "><input type='checkbox' /><span id="
										+ groupId + "_" + question_id + ">"
										+ questionName + "</span>";
								html = html
										+ "<span style='padding-left:1px;'><a style='color: rgb(205,204,204)' onclick='editquestion(\""
										+ $("#group_type").val() + "\",\""
										+ groupId + "\",\"" + question_id
										+ "\")' >编辑</a></span>";
								html = html
										+ "<span style='padding-left:2px;color: rgb(205,204,204)'><a style='color: rgb(205,204,204)' onclick='delQuestion("
										+ question_id + "&apos;,&apos;"
										+ questionName
										+ "&apos;)'>删除</a></span></li>";
								$("input[name='checkOption']").each(
										function(i) {
											var option = {};
											option.optionId = UUID.prototype
													.createUUID();
											option.activeFlag = "y";
											option.sortNumber = i;
											option.optionName = $(this).val();
											option.optionValue = i + 1;
											optionData.push(option);
										})
								questionJson.options = optionData;
								for (var j = 0; j < data1.questions.length; j++) {
									if (data1.questions[j].questionId == question_id) {
										data1.questions.splice(j, 1);
									}
								}
								var textVla = $(
										"#" + groupId + "_" + question_id)
										.text();
								if (textVla == '') {
									$("#add_" + groupId).append(html);
								} else {
									$("#" + groupId + "_" + question_id).text(
											questionName)
								}
								for (var i = 0; i < data1.questionGroup.length; i++) {
									if (data1.questionGroup[i].questionGroupId == groupId) {
										questionJson.chartOneDimnsn = data1.questionGroup[i].chartOneDimnsn;
										questionJson.chartMultiDimnsn = data1.questionGroup[i].chartMultiDimnsn;
										questionJson.chartStore = data1.questionGroup[i].chartStore;
										questionJson.chartTime = data1.questionGroup[i].chartTime;
										questionJson.chartTimeDimnsn = data1.questionGroup[i].chartTimeDimnsn;
										questionJson.questionType = data1.questionGroup[i].customQuestionType
									}
								}
								data1.questions.push(questionJson);
								$(this).dialog("close");
								$("tr[name='delDiv']").each(function() {
									this.remove();
								})
								$(this).dialog("close");
							},
							"关闭" : function() {
								$("tr[name='delDiv']").each(function() {
									this.remove();
								})
								$(this).dialog("close");
							}
						}
					});
	$("#question_edit").click(function() {
		$("#delType").val(0)
	})
	$("#question_save").click(function() {
		var templId = $("#templId").val();
		$("#delType").val(1)
		// 保存当前save值
		$.ajax({
			url : '../data/updateQuestion.json?R' + Math.random(),
			data : {
				"templId" : templId,
				"data" : JSON.stringify(data1)
			},
			method : 'POST',
			dataType : 'JSON',
			success : function(data) {
				if (data.success) {
					$("#question_save").css('display', 'none');
					$("#question_edit").css('display', '');
					$("#welcome_msg").attr('disabled', "true");
					$(".div_button>button").each(function(i) {
						$(this).attr('disabled', "true");
					});
					$("input[type='checkbox']").each(function(i) {
						$(this).attr('disabled', "true");
					})
				} else {
					alert(data.message)
				}
			}
		})
	})
	var selectEndWelcomeMsg = function(checkBox) {
		$("#questionEditFlag").val(true);
		if (checkBox.checked) {
			data1.endWelcome.activeFlag = true;
			$("#end_welcome_msg").attr("disabled", false);
		} else {
			data1.endWelcome.activeFlag = false;
			$("#end_welcome_msg").attr("disabled", true);
		}
	}
	var selectWelcomeMsg = function(checkBox) {
		$("#questionEditFlag").val(true);
		if (checkBox.checked) {
			data1.welcome.activeFlag = true;
			$("#welcome_msg").attr("disabled", false);
		} else {
			data1.welcome.activeFlag = false;
			$("#welcome_msg").attr("disabled", true);
		}
	}
	$("#basic_save").click(function() {
		$("#basicType").val(0);
		var qustnr_name = $("#qustnr_name").val();
		var credit_amount = $("#credit_amount").val();
		var templId = $("#templId").val();
		data1.qustnrName = qustnr_name;
		data1.creditAmount = credit_amount;
		$("#basic_edit").css('display', '');
		$("#basic_save").css('display', 'none');
		$("#basic_credit_amount").css('display', '');
		$("#basic_questionName").css('display', '');
		$("#qustnr_name").css('display', 'none');
		$("#credit_amount").css('display', 'none');
		$("#basic_credit_amount").text(credit_amount);
		$("#basic_questionName").text(qustnr_name);
		$("#qustnr_name").val(qustnr_name);
		$("#credit_amount").val(credit_amount);
		$.ajax({
			url : '../../data/updateQuestion.json?R' + Math.random(),
			data : {
				"templId" : templId,
				"data" : JSON.stringify(data1)
			},
			method : 'POST',
			dataType : 'JSON',
			success : function(data) {
				if (data.success) {
					$("#basic_edit").css('display', '');
					$("#basic_save").css('display', 'none');
				} else {
					alert(data.message);
				}
			}
		})
	})

	$("#basic_edit").click(function() {
		$("#questionEditFlag").val(true);
		$("#basicType").val(0);
		$("#basic_edit").css('display', 'none');
		$("#basic_save").css('display', '');
		$("#basic_credit_amount").css('display', 'none');
		$("#basic_questionName").css('display', 'none');
		$("#qustnr_name").css('display', '');
		$("#credit_amount").css('display', '');
		$("#create_question").removeClass("wrapper_button_onselect");
		$("#create_question").addClass("wrapper_button_select");
		$("#create_question").removeAttr('disabled', true);
	})

	$("#saveRedio").on(
					'shown.bs.modal',
					function() {
						var top = ($(document.body).height() - that.$element
								.height()) / 2;
						var scrollY = document.documentElement.scrollTop
								|| document.body.scrollTop; // 滚动条解决办法
						var top = (window.screen.height / 4) + scrollY - 120; // 滚动条解决办法
					})
	$("#saveRedio")
			.click(
					function() {
						var groupId = $("#groupId").val();
						var templId = $("#templId").val();
						var questionName = $("#redioQuestionName").val();
						var question = $("#redioQuestion").val();
						var questionJson = {};
						var question_id = UUID.prototype.createUUID();
						questionJson.questionId = question_id;
						questionJson.questionGroupId = groupId;
						questionJson.questionGroupName = $(
								"#question_group_name").val();
						questionJson.questionGroupSortNumber = $(
								"#question_group_sort_num").val();
						questionJson.questionName = questionName;
						questionJson.questionValue = question;
						questionJson.sortNumber = 2;
						questionJson.activeFlag = true;
						questionJson.templateFlag = false;
						var optionData = [];
						var option = {};
						option.optionId = UUID.prototype.createUUID();
						option.activeFlag = "y";
						option.sortNumber = "1";
						option.optionName = "是";
						option.optionValue = "1";
						optionData.push(option);
						option.optionId = UUID.prototype.createUUID();
						option.activeFlag = "y";
						option.sortNumber = "2";
						option.optionName = "否";
						option.optionValue = "2";
						optionData.push(option);
						questionJson.options = optionData;
						data1.questions.push(questionJson);
						var html = "<li><input type='checkbox' />"
								+ questionName + "";
						html = html
								+ "<span style='padding-left:1px;'><a style='color: rgb(205,204,204)' onclick='editquestion(\""
								+ $("#group_type").val() + "\",\""
								+ $("#groupId").val() + "\",\"" + question_id
								+ "\")' >编辑</a></span>";
						html = html
								+ "<span style='padding-left:2px;color: rgb(205,204,204)'><a style='color: rgb(205,204,204)' onclick='delQuestion(&apos;"
								+ question_id + "&apos;,&apos;" + questionName
								+ "&apos;)'>删除</a></span></li>";
						$("#add_" + groupId).append(html);
						$('#MyRedio').modal('hide');
					})

	$("#create_question").click(function() {
		if ($("#basicType").val() == 0) {
			var qustnr_name = $("#qustnr_name").val();
			var credit_amount = $("#credit_amount").val();
			data1.qustnrName = qustnr_name;
			data1.creditAmount = credit_amount;
			$("#basic_credit_amount").css('display', '');
			$("#basic_questionName").css('display', '');
			$("#qustnr_name").css('display', 'none');
			$("#credit_amount").css('display', 'none');
			$("#basic_credit_amount").text(credit_amount);
			$("#basic_questionName").text(qustnr_name);
			$("#qustnr_name").val(qustnr_name);
			$("#credit_amount").val(credit_amount);
		}
		$("#questionEditFlag").val(false);
		data1.welcome.welcomeMsg = $("#welcome_msg").val();
		var templId = $("#templId").val();
		var group_value = $("#group_value").val();
		var questionGroupId = $("#questionGroupId").val();
		// 保存当前save值
		$.ajax({
			url : '../../data/updateQuestion.json?R' + Math.random(),
			data : {
				"templId" : templId,
				"data" : JSON.stringify(data1)
			},
			method : 'POST',
			dataType : 'JSON',
			success : function(data) {
				if (data.success) {
					alert(data.message);
					$("#question_edit").css('display', '');
					$("#question_save").css('display', 'none');
					$("#basic_edit").css('display', '');
					$("#basic_save").css('display', 'none');
				} else {
					alert(data.message);
				}
			}
		})
	})
	var selectOrunSelect = function(groupId, id) {
		$("#questionEditFlag").val(true);
		var a = $("#add_" + groupId + " li input:checkbox:checked");
		var groupNumber = 0;
		for (var j = 0; j < data1.questionGroup.length; j++) {
			if (data1.questionGroup[j].questionGroupId == groupId) {
				groupNumber = data1.questionGroup[j].optionalNumber;
			}
		}
		if (a.length <= groupNumber) {
			if ($("#check_" + id).prop("checked")) {
				if(!$("#check_group_"+groupId).prop("checked")){
					$("#check_group_"+groupId).attr('checked',true)
					for(var j = 0;j<data1.questionGroup.length;j++){
						if(data1.questionGroup[j].questionGroupId == groupId){
							data1.questionGroup[j].select = true;
						}
					}
				}
				
				for (var i = 0; i < data1.questions.length; i++) {
					if (data1.questions[i].questionId == id) {
						data1.questions[i].activeFlag = true;
						for (var ji = 0; ji < data1.questionGroup.length; ji++) {
							if (data1.questionGroup[ji].questionGroupId == groupId) {
								data1.questionGroup[ji].selectQuestionCount = ++data1.questionGroup[ji].selectQuestionCount;
							}
						}

					}
				}
			} else {
				if(a.length==0){
					if($("#check_group_"+groupId).prop("checked")){
						$("#check_group_"+groupId).attr('checked',false)
						for(var j = 0;j<data1.questionGroup.length;j++){
							if(data1.questionGroup[j].questionGroupId == groupId){
								data1.questionGroup[j].select = false;
							}
						}
					}
				}
				for (var i = 0; i < data1.questions.length; i++) {
					if (data1.questions[i].questionId == id) {
						data1.questions[i].activeFlag = false;
						for (var ji = 0; ji < data1.questionGroup.length; ji++) {
							if (data1.questionGroup[ji].questionGroupId == groupId) {
								data1.questionGroup[ji].selectQuestionCount = --data1.questionGroup[ji].selectQuestionCount;
							}
						}

					}
				}
			}
		} else {
			$("#check_" + id).attr("checked", false);
			alert("可选问题超过上限!");
		}

	}
	var delCheck = function(id) {
		$("#div_" + id).remove();
	}

	$("#checkBtn")
			.click(
					function() {
						var html = "<tr name='delDiv' id='div_" + id + "'>";
						html += "<td class='table_question'></td>"
						html += "<td class='table_question_text_list'><input type='text' id="
								+ id
								+ " name='checkOption' data-validation-placeholder='输入不正确' class='validate[required] text-input table_question_group_text' /></td>";
						html += "</tr>";
						var id = UUID.prototype.createUUID();
						var html = "<tr name='delDiv' id='div_" + id + "'>";
						html += "<td class='table_question'></td>"
						html += "<td class='table_question_text_list'><input type='text' id="
								+ id
								+ " name='checkOption' data-validation-placeholder='输入不正确' class='validate[required] text-input table_question_group_text' /><a name='delcheck' onclick='delCheck(\""
								+ id + "\")'>删除</a></td>";
						html += "</tr>";
						$("#tableListAll").append(html);
					})

	$("#MyGroupRadio").dialog(
			{
				autoOpen : false,
				height : 230,
				width : 500,
				modal : true,
				buttons : {
					"保存" : function() {
						if ($('#MyGroupRediofrom').validationEngine(
								'validate') == false)
							return;
						var groupId = $("#groupId").val();
						var redioQredioGroupDesc = $("#redioQredioGroupDesc").val();
						//获取value值
						for (var i = 0; i < data1.questionGroup.length; i++) {
							if (data1.questionGroup[i].questionGroupId == groupId) {
								data1.questionGroup[i].displayValue = redioQredioGroupDesc;
							}
						}
						$("#dispanVale_"+groupId).html(redioQredioGroupDesc);
						
						$(this).dialog("close");
					},
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			});
					
	var editquestionTemplate = function(groupId) {
		$("#groupId").val(groupId);
		for (var i = 0; i < data1.questionGroup.length; i++) {
			if (data1.questionGroup[i].questionGroupId == groupId) {
				$('#redioGroupName').attr('disabled',true);
				$('#redioQredioGroupDesc').attr('disabled',true);
				$('#redioQredioGroupDesc').removeAttr("disabled");
				$("#redioGroupName").val(data1.questionGroup[i].name);
				$("#redioQredioGroupDesc").val(data1.questionGroup[i].displayValue);
				$("#MyGroupRadio").dialog('option', 'title', data1.questionGroup[i].name).dialog("open");
			}
		}
	}
	var editquestion = function(type, groupId, questionId, question_group_name,templateFlag) {
		$("#questionEditFlag").val(true);
		$("#question_id").val(questionId);
		$("#groupId").val(groupId);
		$("#tableListAll tr").remove();
		$("#questionType").val(type);
		alert(templateFlag);
		if (type == 0|| type == 1||type == 2 ) {
			for (var i = 0; i < data1.questions.length; i++) {
				if (data1.questions[i].questionId == questionId) {
					if(templateFlag==false){
						$('#redioEditQuestionName').attr('disabled',true);
						$('#redioEditQuestionValue').attr('disabled',true);
						$('#redioEditQuestionName').removeAttr("disabled");
						$('#redioEditQuestionValue').removeAttr("disabled");
						$("#redioEditQuestionName").val(data1.questions[i].questionName);
						$("#redioEditQuestionValue").val(data1.questions[i].questionValue);
						$("#MyEditRedio").dialog('option', 'title', question_group_name).dialog("open");	
					}else{
						$('#redioEditQuestionName').attr('disabled',true);
						$('#redioEditQuestionValue').attr('disabled',true);
						$('#redioEditQuestionValue').removeAttr("disabled");
						$("#redioEditQuestionName").val(data1.questions[i].questionName);
						$("#redioEditQuestionValue").val(data1.questions[i].questionValue);
						$("#MyEditRedio").dialog('option', 'title', question_group_name).dialog("open");
					}
					
				}
			}
		} else if (type == 3) {
			for (var i = 0; i < data1.questions.length; i++) {
				if (data1.questions[i].questionId == questionId) {
					$("#option_List").empty();
					if(templateFlag==true){
						$("#questionEditCheckName").attr("disabled",true);
						$("#questionEditCheckName").val(data1.questions[i].questionName);
						$("#questionEditCheckVal").val(data1.questions[i].questionValue);
						var html = "";
						for (var ii = 0; ii < data1.questions[i].options.length; ii++) {
							html=html+"<input type='checkbox' disabled style='margin-left:20px'>"+ data1.questions[i].options[ii].optionName;
						}
						$("#option_List").append(html);
						$("#myEditCheck").dialog('option', 'title', question_group_name).dialog("open");
					}else{
						$("#questionCheckName").val(data1.questions[i].questionName);
						$("#questionCheckVal").val(data1.questions[i].questionValue);
						for (var ii = 0; ii < data1.questions[i].options.length; ii++) {
							var id = UUID.prototype.createUUID();
							var html = "<tr name='delDiv' id='div_" + id + "'>";
							html += "<td class='table_question'></td>"
							html += "<td class='table_question_text_list'><input type='text' id="
									+ id
									+ " name='checkOption' data-validation-placeholder='输入不正确' class='validate[required] text-input table_question_text' value="
									+ data1.questions[i].options[ii].optionName
									+ " /><a onclick='delCheck(\""
									+ id
									+ "\")'>删除</a></td>";
							html += "</tr>";
							$("#tableListAll").append(html);
						}
						$("#myCheck")
								.dialog('option', 'title', question_group_name)
								.dialog("open");
					}
					
				}
			}
		} else if(type=4){
			for (var i = 0; i < data1.questions.length; i++) {
				if (data1.questions[i].questionId == questionId) {
					$("#questionEditScoreName").val(data1.questions[i].questionName);
					$("#questionEditScoreVal").val(data1.questions[i].questionValue);
					$("#myEditOverAllScore").html(data1.questions[i].options.length+"分");
					$("#myEditOverAll").dialog('option', 'title', question_group_name).dialog("open");
				}
			}
			
		}
	}

	var addquestion = function(group_type, groupId, question_group_name,
			question_group_sort_num, displayValue) {
		$("#questionEditFlag").val(true);
		$("#question_id").val('');
		$("#questionType").val(group_type);
		// questionScoreRadio
		$("#questionScoreVal").val('');
		$("#questionScoreName").val('');
		$("#redioQuestionName").val('');
		$("#redioQuestion").val('');
		$("#questionName").val('');
		$("#question").val('');
		$("#questionCheckName").val('');
		$("#questionCheckVal").val('');
		$("#tableListAll tr").remove()
		var id = UUID.prototype.createUUID();
		var html = "<tr name='delDiv' id='div_" + id + "'>";
		html += "<td class='table_question'></td>"
		html += "<td class='table_question_text_list'><input type='text' id="
				+ id
				+ " name='checkOption' data-validation-placeholder='输入不正确' class='validate[required] text-input table_question_group_text' /></td>";
		html += "</tr>";
		$("#tableListAll").append(html);
		$("#redioQuestionName").attr('disabled', false);
		$("input[name='option']").each(function() {
			$(this).val('');
		})
		alert(group_type);
		if (group_type == 0) {
			$("#MyRedioFlag").val(0);
			$("#redioQuestion").attr('disabled', true)
			$("#redioQuestionName").attr('disabled', true);
			$("#redioQuestion").removeAttr('disabled');
			$("#redioQuestionName").removeAttr('disabled');
			$("#MyRedio").dialog('option', 'title', question_group_name)
			.dialog("open");
		} else if (group_type == 1) {
			$("#MyRedioFlag").val(1)
			$("#redioQuestion").attr('disabled', true)
			$("#redioQuestionName").attr('disabled', true);
			$("#redioQuestion").removeAttr('disabled');
			$("#redioQuestionName").removeAttr('disabled');
			$("#MyRedio").dialog('option', 'title', question_group_name).dialog("open");
		} else if (group_type == 2) {
			$("#MyRedioFlag").val(2);
			$("#MyRedio").dialog('option', 'title', question_group_name).dialog("open");
		} else if (group_type == 3) {
			$("#myCheck").dialog('option', 'title', question_group_name).dialog("open");
		} else if (group_type == 4) {
			$("#myOverAll").dialog('option', 'title', question_group_name)
					.dialog("open");
		}

		$("#group_type").val(group_type);
		$("#groupId").val(groupId);
		$("#question_group_name").val(question_group_name);
		$("#question_group_sort_num").val(question_group_sort_num);
	}

	var questionTop = function(question_id, flag) {
		for (var j = 0; j < data1.questions.length; j++) {
			if (data1.questions[j].questionId == question_id) {
				if (data1.questions[j].topFlag == 0) {
					data1.questions[j].topFlag = 1;
					$("#top_" + question_id).removeClass(
							"question_top_onSelect");
					$("#top_" + question_id).addClass("question_top_select");
				} else {
					data1.questions[j].topFlag = 0;
					$("#top_" + question_id).removeClass("question_top_select");
					$("#top_" + question_id).addClass("question_top_onSelect");
				}
			}
		}
	}
	var editGroupVale = function(groupId) {
		$("#questionEditFlag").val(true);
		$("#questionGroupId").val(groupId);
		$("#group_span_" + groupId).css("display", "none");
		$("#group_text_" + groupId).css("display", "block");
	}
	var selectGroup = function(groupid){
		if($("#check_group_"+groupid).prop("checked")){
			$("#"+$("#add_"+groupid+" li input:checkbox")[0].id).attr('checked',true);
			var array = $("#add_"+groupid+" li input:checkbox")[0].id.split("_");
			//问题组选中
			for(var j = 0;j<data1.questionGroup.length;j++){
				if(data1.questionGroup[j].questionGroupId == groupid){
					data1.questionGroup[j].select = true;
				}
			}
			//选中第一个问题
			for(var i = 0;i<data1.questions.length;i++){
				if(data1.questions[i].questionId=array[1]){
					data1.questions[i].questionId.activeFlag = true;
				}
			}
		}else{
			$("#"+$("#add_"+groupid+" li input:checkbox")[0].id).attr('checked',false);
			var array = $("#add_"+groupid+" li input:checkbox");
			//所有的问题都不被选中
			for(var i = 0;i<array.size();i++){
				$("#"+$("#add_"+groupid+" li input:checkbox")[i].id).attr('checked',false);
				for(var ii = 0;ii<data1.questions.length;ii++){
					if(data1.questions[ii].questionId=array[1]){
						data1.questions[ii].questionId.activeFlag = false;
					}
				}
			}
			
			//问题组不被选中
			for(var j = 0;j<data1.questionGroup.length;j++){
				if(data1.questionGroup[j].questionGroupId == groupid){
					data1.questionGroup[j].select = false;
				}
			}
			
			
		}
		
		
	}
	window.selectEndWelcomeMsg = selectEndWelcomeMsg;
	window.selectGroup = selectGroup;
	window.editquestionTemplate = editquestionTemplate;
	window.editGroupVale = editGroupVale;
	window.questionTop = questionTop;
	window.selectWelcomeMsg = selectWelcomeMsg;
	window.delCheck = delCheck;
	window.editquestion = editquestion;
	window.delQuestion = delQuestion;
	window.addquestion = addquestion;
	window.selectOrunSelect = selectOrunSelect;
})