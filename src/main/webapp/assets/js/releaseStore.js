jQuery(function($){
	var customStore = [];
//	<input type="hidden" id="id" name="id" value="${templateId }"/>
//    	<input type="hidden" id="downCount" name="downCount" value=""/>
	$("#downloadCode").click(function(){
		//判断是否选中门店分组
		var parentId = $('option:selected').attr("id")
		$("#parentId").val(parentId);
		//判断可以下载的门店
		var downLoadData = $("#downLoadData").val();
		var downLoadQuestionId = $("#downLoadQuestionId").val();
		var downLoadGroupId = $("#downLoadGroupId").val();
		if(downLoadData!=''&&downLoadQuestionId!=''){
			$("#downLoadZipFrom").submit();
		}else{
			alert("请选中要下载的数据");
		}
	})
	
	$("#selectGroupId").change(function(){ 
		var parentId = $(this).children('option:selected').attr("id");
		$("#downLoadGroupId").val(parentId);
		$("#mainListTbody").empty();
		var templateId = $("#templateId").val();
		$.ajax({
			url : '../queryStoreByGroupId.json?R' + Math.random(),
			data:{'groupId':parentId,'templateId':templateId},
			method : 'GET',
			dataType : 'JSON',
			success : function(data) {
				if (data.success) {
					var storeHtml = "";
					$.each(data.data,function(index,content){
						storeHtml += "<tr><td class='table_left'><input name='mainCheckBox' value='"+content.storeId+"_"+content.storeName+"' type='checkbox'/>"+content.storeName+"</td>";
						storeHtml +="<td class='table_center'><input type='text' value='"+content.url+"'/></td>"
						storeHtml +="<td class='table_right'><img src='${ctx }/assets/images/towcodeDemo.png' style='width: 25px;height: 25px'/></td></tr>"
					});
					$("#mainListTbody").append(storeHtml);
				} else {
					alert(data.message)
				}
			}
			
		})
	})
	$("#groupStoreDiv").dialog(
			{
				autoOpen : false,
				height : 420,
				width : 400,
				modal : true,
				buttons : {
					"保存" : function() {
						var id = [];
						$(":checkbox:checked","#inputGroupStore").each(function(){
							id.push($(this).attr('value'));
						})
						var groupId = $("#groupId").val();
						if(id.length!=0){
							$.ajax({
								url : '../addStoreToGroup.json?R' + Math.random(),
								 data : {'storeId':JSON.stringify(id),'groupId':groupId},
								async:false,
								method : 'GET',
								dataType : 'JSON',
								success : function(data) {
									if (data.success) {
										alert(data.message);
									} else {
										alert(data.message);
									}
								}
							})
						}
						$("#Groupstore").empty();
						$.ajax({
				            url : '../queryGroupStoreByAccountId.json?R' + Math.random(),
				            async:false,
				            method : 'GET',
				            data:{'groupId':groupId},
				            dataType : 'JSON',
				            success : function(data) {
				                if (data.success) {
				                    var storeHtml = "";
				                    $.each(data.data.store,function(index,content){
				                        storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableCheckBox' value="+content.storeId+"></td><td class='group_table_rigth'>"+content.storeName+"</td></tr>";
				                    });
				                    $("#Groupstore").append(storeHtml);
				                    
				                } else {
				                    alert(data.message)
				                }
				            }
				        })
						$(this).dialog("close");
						
					},
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			});
	$("#inputStore").dialog(
			{
				autoOpen : false,
				height : 420,
				width : 650,
				modal : true,
				buttons : {
					"保存" : function() {
						$.ajax({
							url : '../addStore.json?R' + Math.random(),
							data : {'store':JSON.stringify(customStore)},
							async:false,
							method : 'POST',
							dataType : 'JSON',
							success : function(data) {
								if (data.success) {
									alert(data.message)
								} else {
									alert(data.message)
								}
							}
						})
						$('#storeList').empty();
						$('#store').empty();
						$.ajax({
							url : '../queryAllStoreByAccountId.json?R' + Math.random(),
							method : 'GET',
							async:false,
							dataType : 'JSON',
							success : function(data) {
								if (data.success) {
									var storeHtml = "";
									$.each(data.data.store,function(index,content){
										storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableCheckBox' value="+content.storeId+"></td><td class='table_td_conter'>"+content.storeName+"</td><td class='table_td_right'>"+content.group+"</td></tr>";
									});
									$("#store").append(storeHtml);
									var groupHtml = "";
								} else {
									alert(data.message)
								}
							}
						})
						$(this).dialog("close");
						
					},
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			});
	$("#MybAddSotre").dialog({
		autoOpen : false,
		height : 420,
		width : 500,
		modal : true,
		buttons : {
			"保存" : function() {
				if ($('#MybAddSotreFrom').validationEngine(
				'validate') == false)
			return;
				var json = {};
				var storeName=$("#storeName").val();
				var managerName=$("#managerName").val();
				var managerEmail=$("#managerEmail").val();
				var managerPhone=$("#managerPhone").val();
				var managerNumber=$("#managerNumber").val();
				var id = UUID.prototype.createUUID();
				json.id = id;
				json.storeName = storeName;
				json.managerName = managerName;
				json.managerEmail = managerEmail;
				json.managerPhone = managerPhone;
				json.managerNumber = managerNumber;
				customStore.push(json);
				var html ="<tr id="+id+"><td  class='main_add_list_talbe_td_left'>"+managerName+"</td>";
				html+="<td class='main_add_list_talbe_td_right' ><a onclick='delCustomStore(\"" + id+ "\")'>删除</a></td>";
				html+="</tr>";
				$("#storeList").append(html);
				$(this).dialog("close");
			},
			"关闭" : function() {
				$(this).dialog("close");
			}
		}
	}
			)
	$(".main_group").dialog(
			{
				autoOpen : false,
				height : 520,
				width : 700,
				modal : true,
				buttons : {
					"保存" : function() {
						
					},
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
			});
	$(".branchMange").click(function(){
		$("#groupList").children().filter('li').remove();
		$.ajax({
			url : '../queryAllStoreByAccountId.json?R' + Math.random(),
			method : 'GET',
			dataType : 'JSON',
			success : function(data) {
				if (data.success) {
					var storeHtml = "";
					$.each(data.data.store,function(index,content){
						storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableCheckBox' value="+content.storeId+"></td><td class='table_td_conter'>"+content.storeName+"</td><td class='table_td_right'>"+content.group+"</td></tr>";
					});
					$("#store").append(storeHtml);
					var groupHtml = "";
					$.each(data.data.group,function(index,content){
						groupHtml +="<li id='li_"+content.groupId+"'><div class='abcd' id='div_"+content.groupId+"'> <input type='text' id='text_"+content.groupId+"' readonly='readonly' onclick='groupClick(\""+content.groupId+"\")' ondblclick='groupdbClick(\""+content.groupId+"\")'  value="+content.groupName+" /><button id='delGroup_"+content.groupId+"' onclick='delGroupById(\""+content.groupId+"\")' >x</button></div></li>"
					})
					$("#groupList").append(groupHtml);
				} else {
					alert(data.message)
				}
			}
		})
		
		
		
		$(".main_group").dialog('option','title','修改分店或网店').dialog("open");
	})
	$("#addStoreOfGroup").click(function(){
		 $("#inputGroupStore").empty();
		$.ajax({
            url : '../queryAllStoreByAccountId.json?R' + Math.random(),
            method : 'GET',
            dataType : 'JSON',
            success : function(data) {
                if (data.success) {
                    var storeHtml = "";
                    $.each(data.data.store,function(index,content){
                        storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableGroupCheckBoxList' value="+content.storeId+"></td><td class='group_table_rigth'>"+content.storeName+"</td></tr>";
                    });
                    $("#inputGroupStore").append(storeHtml);
                } else {
                    alert(data.message)
                }
            }
        })
		
		$("#groupStoreDiv").dialog('option','title','添加分店/网店到此组').dialog("open");
	})
	$("#addStore").click(function(){
		$("#inputStore").dialog('option','title','添加分店/网店').dialog("open");
	})
	$("#addCustomStore").click(function(){
		$("#storeName").val('');
		$("#managerName").val('');
		$("#managerEmail").val('');
		$("#managerPhone").val('');
		$("#managerNumber").val('');
		$("#MybAddSotre").dialog('option', 'title', "添加门店").dialog("open");
	})
	$("#allStore").click(function(){
		$('#store').empty();
		$("#allStoreList").css('display','');
		$("#groupStoreList").css('display','none');
		$("#addStore").css('display','');
		$("#addStoreOfGroup").css('display','none');
		$(".add_group_select").removeClass("add_group_select");
		$("#allStore").addClass("add_group_select");
		$.ajax({
            url : '../queryAllStoreByAccountId.json?R' + Math.random(),
            method : 'GET',
            dataType : 'JSON',
            success : function(data) {
                if (data.success) {
                    var storeHtml = "";
                    $.each(data.data.store,function(index,content){
                        storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableCheckBox' value="+content.storeId+"></td><td class='table_td_conter'>"+content.storeName+"</td><td class='table_td_right'>"+content.group+"</td></tr>";
                    });
                    $("#store").append(storeHtml);
                    $("#delType").val(0);
                } else {
                    alert(data.message)
                }
            }
        })
	})
	$("#submit").click(function(){
		var pathName = $("#fileField").val();
		var k = pathName.substr(pathName.indexOf("."))
		if(pathName!=''&&k=='.xlsx'){
		$.ajaxFileUpload({
			url:'../uploadXls.json?R' + Math.random(),
			type:'POST',
			secureuri:false,
			fileElementId:'fileField',
			dataType:'json',
			success:function(data){
				if(data.success){
					alert(data.message);
				}else{
					alert(data.message);
				}
			},error:function(data,status,e){
			 	alert(e);
			}
		})
		}else{
			alert("请输入内容。");
		}
	})
	$("#mainListCheckBox").click(function(){
		if($("#mainListCheckBox").is(':checked')){
			$("input[name='mainCheckBox']").each(function(){
				$(this).prop("checked",true);
			}); 
		}else{
			$("input[name='mainCheckBox']").each(function(){
				$(this).prop("checked",false);
			});	
		}
		var id = [];
		$(":checkbox:checked","#mainListTbody").each(function(){
			id.push($(this).attr('value'));
		})
		//获取选中数据
		$("#downLoadData").val(JSON.stringify(id));
	})
	$("#delCheckbox").click(function(){
		var id = [];
		var delType = $("#delType").val();
		if(delType=0){
			$(":checkbox:checked","#store").each(function(){
				id.push($(this).attr('value'));
			})
			if(id.length!=0){
				$.ajax({
		            url : '../delStoreId.json?R' + Math.random(),
		            method : 'GET',
		            async:false,
		            data : {'store':JSON.stringify(id)},
		            dataType : 'JSON',
		            success : function(data) {
		                if (data.success) {
		                	  alert(data.message)
		                } else {
		                    alert(data.message)
		                }
		            }
		        })
		        $('#store').empty();
		        $.ajax({
	            url : '../queryAllStoreByAccountId.json?R' + Math.random(),
	            async:false,
	            method : 'GET',
	            dataType : 'JSON',
	            success : function(data) {
	                if (data.success) {
	                    var storeHtml = "";
	                    $.each(data.data.store,function(index,content){
	                        storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableCheckBox' value="+content.storeId+"></td><td class='table_td_conter'>"+content.storeName+"</td><td class='table_td_right'>"+content.group+"</td></tr>";
	                    });
	                    $("#store").append(storeHtml);
	                    var groupHtml = "";
	                } else {
	                    alert(data.message)
	                }
	            }
	        })
			}else{
				alert("请选中数据！");
			}
		}else{
			$(":checkbox:checked","#Groupstore").each(function(){
				id.push($(this).attr('value'));
			})
			if(id.length!=0){
				var groupId = $("#groupId").val();
				$.ajax({
		            url : '../delGroupStore.json?R' + Math.random(),
		            method : 'GET',
		            async:false,
		            data : {'storeId':JSON.stringify(id),'groupId':groupId},
		            dataType : 'JSON',
		            success : function(data) {
		                if (data.success) {
		                	  alert(data.message)
		                } else {
		                    alert(data.message)
		                }
		            }
		        })
		        $("#Groupstore").empty();
				$.ajax({
		            url : '../queryGroupStoreByAccountId.json?R' + Math.random(),
		            async:false,
		            method : 'GET',
		            data:{'groupId':groupId},
		            dataType : 'JSON',
		            success : function(data) {
		                if (data.success) {
		                    var storeHtml = "";
		                    $.each(data.data.store,function(index,content){
		                        storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableGroupCheckBox'  value="+content.storeId+"></td><td class='group_table_rigth'>"+content.storeName+"</td></tr>";
		                    });
		                    $("#Groupstore").append(storeHtml);
		                    $("#delType").val(1);
		                } else {
		                    alert(data.message)
		                }
		            }
		        })
			}else{
				alert("请选中数据！");
			}
			
		}
	})
	function groupClick(id){
		$("#groupId").val(id);
		$(".add_group_select").removeClass("add_group_select");
		$("#div_"+id).addClass("add_group_select");
		$("#allStoreList").css('display','none');
		$("#groupStoreList").css('display','');
		$("#addStore").css('display','none');
		$("#addStoreOfGroup").css('display','');
		$("#Groupstore").empty();
		$.ajax({
            url : '../queryGroupStoreByAccountId.json?R' + Math.random(),
            async:false,
            method : 'GET',
            data:{'groupId':id},
            dataType : 'JSON',
            success : function(data) {
                if (data.success) {
                    var storeHtml = "";
                    $.each(data.data.store,function(index,content){
                        storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableGroupCheckBox'  value="+content.storeId+"></td><td class='group_table_rigth'>"+content.storeName+"</td></tr>";
                    });
                    $("#Groupstore").append(storeHtml);
                    $("#delType").val(1);
                } else {
                    alert(data.message)
                }
            }
        })
		//查询数据
	}
	function groupdbClick(id){
		$("#text_"+id).removeAttr("readonly");
		$("#delGroup_"+id).remove();
		var html ="<button id='saveGroup_"+id+"' onclick='saveGroup(\""+id+"\")' >√</button>";
		$("#div_"+id).append(html);
	}
	$("#tableCheckBoxId").click(function(){
		if($("#tableCheckBoxId").is(':checked')){
			$("input[name='tableCheckBox']").each(function(){
				$(this).prop("checked",true);
			}); 
		}else{
			$("input[name='tableCheckBox']").each(function(){
				$(this).prop("checked",false);
			});	
		}
		  
	})
	
	$("#groupStoreCheckBoxId").click(function(){
		if($("#groupStoreCheckBoxId").is(':checked')){
			$("input[name='tableGroupCheckBoxList']").each(function(){
				$(this).prop("checked",true);
			}); 
		}else{
			$("input[name='tableGroupCheckBoxList']").each(function(){
				$(this).prop("checked",false);
			});	
		}
	})
	
	$("#tableStoreCheckBoxId").click(function(){
		if($("#tableStoreCheckBoxId").is(':checked')){
			$("input[name='tableGroupCheckBox']").each(function(){
				$(this).prop("checked",true);
			}); 
		}else{
			$("input[name='tableGroupCheckBox']").each(function(){
				$(this).prop("checked",false);
			});	
		}
	})
	
	
	
	
	var delCustomStore = function(id){
		var array = eval(customStore);
		for(var i =0;i<array.length;i++){
			if(array[i].id==id){
				delete array[i];
			}
		}
		$("#"+id).remove();
	}
	var delGroupById = function(id){
		$.ajax({
            url : '../delGroupById.json?R' + Math.random(),
            method : 'GET',
            async:false,
            data : {'groupId':id},
            dataType : 'JSON',
            success : function(data) {
                if (data.success) {
                	  alert(data.message)
                	  $("#li_"+id).remove();
                } else {
                    alert(data.message)
                }
            }
        })
	}
	$("#addGroup").click(function(){
		var id = UUID.prototype.createUUID();
		var html ="<li id='li_"+id+"'><div class='abcd' id='div_"+id+"'> <input type='text' id ='text_"+id+"' onclick='groupClick(\""+id+"\")' ondblclick='groupdbClick(\""+id+"\")'   value='' /><button id='saveGroup_"+id+"' onclick='saveGroup(\""+id+"\")' >√</button></div></li>"
		$("#groupList").append(html);
	})
	var saveGroup = function(id){
		//获取text文本框值并吧button按钮替换为删除
		var name = $("#text_"+id).val();
		$.ajax({
            url : '../addGroup.json?R' + Math.random(),
            method : 'GET',
            async:false,
            data : {'id':id,'groupName':name},
            dataType : 'JSON',
            success : function(data) {
                if (data.success) {
                	$("#saveGroup_"+id).remove();
            		$("#text_"+id).attr("readonly","readonly");
            		var html = "<button id='delGroup' onclick='delGroupById(\""+id+"\")' >x</button>";
            		$("#div_"+id).append(html);
            		$("#Groupstore").empty();
            		$.ajax({
                        url : '../queryGroupStoreByAccountId.json?R' + Math.random(),
                        async:false,
                        method : 'GET',
                        data:{'groupId':id},
                        dataType : 'JSON',
                        success : function(data) {
                            if (data.success) {
                                var storeHtml = "";
                                $.each(data.data.store,function(index,content){
                                    storeHtml += "<tr><td class='table_td_left'><input type='checkbox' name='tableCheckBox'  value="+content.storeId+"></td><td class='group_table_rigth'>"+content.storeName+"</td></tr>";
                                });
                                $("#Groupstore").append(storeHtml);
                                
                            } else {
                                alert(data.message)
                            }
                        }
                    })
                } else {
                    alert(data.message)
                }
            }
		})
	}
	window.saveGroup = saveGroup;
	window.delGroupById = delGroupById;
	window.groupClick = groupClick;
	window.groupdbClick = groupdbClick;
	window.delCustomStore = delCustomStore;
})