jQuery(function($) {
	var optionQuestion = function(id){
		window.location.href='../main/editQuestion/'+id+'.html'
	}
	var optionRelease = function(id){
		window.location.href='../release/releaseOne/'+id+'.html';
	}
	var optionReport = function(id){
		window.location.href='../main/report/'+id+'.html';
//		window.location.href='../main/proreport.html?id='+id;
	}
	
	$("#but_Stop_ok").click(function(){
		var questionId = $("#question_id").val();
		$.ajax({
			url:'../data/stopQuestionById.json?id='+questionId,
			method:"GET",
			dataType:'json',
			success:function(data){
				if(data.success){
					window.location.reload()
				}else{
					alert("删除数据失败！");
				}
			},
			error:function(){
				alert("请稍后重试！");
			}
		})
		
	})
	
	$("#but_ok").click(function(){
		var questionId = $("#question_id").val();
		var type = $("#type").val();
		if(type==1){
			$.ajax({
				url:'../data/delQuestionById.json?id='+questionId,
				method:"GET",
				dataType:'json',
				success:function(data){
					if(data.success){
						window.location.reload()
					}else{
						alert("删除数据失败！");
					}
				},
				error:function(){
					alert("请稍后重试！");
				}
			})
		}else if(type==0){
			
		}
		
	})
	var stopQuestion = function(id,name){
		$("#question_id").val(id);
		$("#modelStopName").text("是否停止\""+name+"\"项目?");
	}
	var delQuestion = function(type,id,name){
		$("#type").val(type);
		$("#question_id").val(id);
		$("#modelName").text("是否删除\""+name+"\"项目?");
	}
	
	$("#question_button").click(function(){
		window.location.href="../main/createQuestion.html";
	})
	window.optionReport = optionReport;
	window.stopQuestion = stopQuestion;
	window.delQuestion = delQuestion;
	window.optionQuestion = optionQuestion;
	window.optionRelease = optionRelease;
	$(".questionList_button").hover(function(){
		name = $(this).text();
		$(this).text("终止");
		$(this).addClass("questionList_button_color");
	},function () {
		$(this).text("进行中");
	    $(this).removeClass("questionList_button_color");
	  })
})