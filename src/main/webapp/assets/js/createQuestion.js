jQuery(function(){
	$("#submit_form").validationEngine();
	$("#select_industry").change(function(){ 
		var parentId = $(this).children('option:selected').attr("id");
		//查询下面模板值
		$.ajax({
			url:'../data/queryIndustryByParentId.json?parentId='+parentId,
			method:'get',
			dataType:'json',
			success:function(data){
				if(data.success){
					if(data.type==0){
						$("#select_parentId_industry").remove();
						$("#span_name").remove();
						$("#select_industry_templ").remove();
						$("#span_tel_name").remove();
						var html="<div class='column' id='span_name'>选择子行业</div>";
						html=html+"<div class='column'>"
						html=html+"<select id='select_parentId_industry' name='sub_industry_name' class='validate[required]'>"
						html=html+"<option value=''>请选择</option>";
						var retuDa= $.parseJSON(data.data)
						$.each(retuDa,function(index,item){
							html=html+"<option id="+item.id+" value="+item.id+">"+item.name+"</option>";
						});
						html=html+"</select>";
						html=html+"</div>";
						$("#form_row_id").append(html);
					}else if(data.type==1){
						$("#select_industry_templ").remove();
						$("#span_tel_name").remove();
						$("#select_parentId_industry").remove();
						$("#span_name").remove();
						var html="<div class='column' id='span_tel_name' >选择模板</div>";
						html=html+"<div class='column'>"
						html=html+"<select id='select_industry_templ' name='sub_industry_name' class='validate[required]'>"
						html=html+"<option value=''>请选择</option>";
						var retuDa= $.parseJSON(data.data)
						$.each(retuDa,function(index,item){
							html=html+"<option id="+item.id+" value="+item.name+">"+item.name+"</option>";
						});
						html=html+"</select>";
						html=html+"</div>";
						$("#form_row_id").append(html);
					}
				}else{
//					alert(data.message);
				}
			},
			error:function(){
				
			}
			
		
		})
	})
	$(document).on('change','#select_parentId_industry',function(){ 
		var parentId = $('#select_parentId_industry option:selected').attr("id");
		$("#sub_industry_id").val($(this).children('option:selected').attr("id"));
		//查询下面模板值
		$.ajax({
			url:'../data/queryIndustryByParentId.json?parentId='+parentId,
			method:'get',
			dataType:'json',
			success:function(data){
				if(data.success){
					if(data.type==1){
						$("#select_industry_templ").remove();
						$("#span_tel_name").remove();
						var html="<div class='column' id='span_tel_name'>选择模板</div>";
						html=html+"<div class='column'>"
						html=html+"<select id='select_industry_templ' name='sub_industry_name' class='validate[required]'>"
						html=html+"<option value=''>请选择</option>";
						var retuDa= $.parseJSON(data.data)
						$.each(retuDa,function(index,item){
							html=html+"<option id="+item.id+" value="+item.name+">"+item.name+"</option>";
						});
						html=html+"</select>";
						html=html+"</div>";
						$("#form_row_id").append(html);
					}
				}else{
					alert(data.message);
				}
			},
			error:function(){
				
			}
			
		
		})
		
	}); 
	$("#create_question").click(function(){
		var credit_amount = $("#credit_amount").val();
		var qustnr_name = $("#qustnr_name").val();
		$("#submit_form").submit();
	});
	$(document).on('change','#select_industry_templ',function(){ 
		var parentId = $('#select_industry_templ option:selected').attr("id");
		$("#sub_industry_id").val($(this).children('option:selected').attr("id"));
	}); 
	
})