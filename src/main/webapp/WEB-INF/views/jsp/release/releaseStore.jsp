
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
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/themes-cust/myb-green/jquery-ui.css"/>
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/validationEngine.jquery.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/template.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/question.css" />
<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/uploadfile/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/assets/jqueryui/jquery-ui.js"></script>
<script src="${ctx}/assets/jquery-validation/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/jquery-validation/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/js/releaseStore.js"></script>
<script src="${ctx}/assets/js/UUID.js"></script>
<title>我的问卷</title>

</head>

<body>
<input type="hidden" id="templateId" value="${templId }"/>
<input type="hidden" id="groupId" value=""/>
<input type="hidden" id="delType" value="0"/>
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
         <div class="wrapper">
        <div class="clearfix" style="height:10px;"></div>
        <div id="release_panel">
            <div id="release_title">
                <ul>
                     <li><a href="${ctx}/page/release/releaseOne/${templId }.html" >所有顾客公用一个链接/二维码</a></li>
                    <li><a  href="${ctx}/page/release/releaseStore/${templId }.html" class="release_title_select">每个分店的顾客使用该分店的专属链接/二维码</a></li>
                    <li><a href="${ctx}/page/release/releaseOnlyOne/${templId }.html" >每个顾客使用自己的专属链接</a></li>
                </ul>

            </div>
            <div id="release_center">
                <div class="release_center_top">
                    <a href="#">问卷预览</a>
                </div>
                <div class="release_center_center">
                    <div>
                        <button class="branchMange">分店管理</button>
                    </div>
                    <div class="release_center_center_desc">
                        <span>选择合适的调用方式来获取问卷链接和二维码.</span>
                    </div>
                    <div class="release_center_center_text">
                        <span>调用方式:</span>
                        <select id="selectGroupId" name="selectGroupId" >
                        	<option value="">请选择</option>
                        	<c:if test="${groupList !=null}">
                        		<c:forEach var="group" items="${groupList }">
                        		<option id="${group.id }" value="${group.name}">${group.name}</option>
                        		</c:forEach>
                        	</c:if>
                            
                        </select>
                        <img />
                        <span>每个分店都是专属链接和二维码</span>
                    </div>
                    <div class="release_table">
						<table class="main_table" id="mainList">
			                	<thead >
			                    <tr class="main_group_table_top">
			                        <td class="tableCheck"><input type="checkbox" id="mainListCheckBox"/> 全选</td>
	                                <td></td>
	                                <td></td>
			                    </tr>
			                    </thead>
			                   <tbody id="mainListTbody">
								<c:if test="${storeList!=null }">
									<c:forEach var="sotre" items="${storeList }">
					                   <tr>
						                    <td class="table_left"><input name="mainCheckBox" value="${sotre.storeId }_${sotre.storeName }" type="checkbox"/> ${sotre.storeName }</td>
											<td class="table_center"><input type="text" value="${sotre.url }"/></td>
											<td class="table_right"><img src="${ctx }/assets/images/towcodeDemo.png" style="width: 25px;height: 25px"/></td>
										</tr>
									</c:forEach>
								</c:if>
			                   </tbody>
			                </table>
                    </div>
                    <div class="release_center_center_download"> <button id="downloadCode">下载所选项的链接和二维码</button></div>
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
        <form action="${ctx}/page/release/downGroupLoadExcel.html" method="post" id="downLoadZipFrom" enctype="application/x-www-form-urlencoded">
        	<input type="hidden" id="downLoadData" name="data" value=""/>
        	<input type="hidden" id="downLoadQuestionId" name="questionnaireId" value="${templId }"/>
        	<input type="hidden" id="downLoadGroupId" name="groupId" value=""/>
        	<input type="hidden" id="parentId" name="parentId" value=""/>
        </form>
        
        <div class="clearfix" style="height:10px;"></div>
    </div>
    </div>
    </div>
        <form action="${ctx}/page/release/downLoad.html" method="post" id="downLoadFrom" enctype="application/x-www-form-urlencoded">
        	<input type="hidden" id="id" name="id" value="${templId }"/>
        </form>
    <div class="clearfix" style="height:10px;"></div>
    <div class="main_group">
        <div class="main_group_left">
            <div class="main_group_left_top">
                <ul>
                    <li class="main_all">全部</li>
                    <li class="main_all_group"><button id="allStore" class="main_all_group_button add_group_select">全部分店/网店</button></li>
                </ul>
            </div>
            <div class="main_group_left_botton">
                <span class="main_all">分组</span>
                <ul id="groupList">
                </ul>
                <button id="addGroup">+</button>
            </div>

        </div>
        <div class="main_group_right">
            <button class="main_group_right_addButton" id="addStore">+添加分店网店</button>
            <button class="main_group_right_addButton" id="addStoreOfGroup" style="display: none">+添加分店/网店到此组</button>
            <div class="main_group_center">
                <table class="table" id="allStoreList">
                <tbody id="store">
                	<thead>
                    <tr class="main_group_table_top">
                        <td class="table_td_left"><input type="checkbox" id="tableCheckBoxId"/></td>
                        <td class="table_td_conter">分店/网店名称</td>
                        <td class="table_td_right">分组</td>
                    </tr>
					</thead>
				</tbody>
                </table>
                 <table class="table" id="groupStoreList" style="display: none">
                <tbody id="Groupstore">
                	<thead>
                    <tr class="main_group_table_top">
                        <td class="table_td_left"><input type="checkbox" id="tableStoreCheckBoxId"/></td>
                        <td class="group_table_rigth">分店/网店名称</td>
                    </tr>
					</thead>
				</tbody>
                </table>
                
            </div>
            <div class="main_group_botton">
                <button class="main_group_botton_button">导入所选项</button>
                <button id="delCheckbox" class="main_group_botton_button button_Select">删除所选项</button>
            </div>
        </div>
    </div>
    
    <div id="MybAddSotre">
		<form action="" id="MybAddSotreFrom">
       	<div id="model_redio" style="height: 100px;">
       		<table>
     				<tr>	
	                    <td  class="table_question">分店名称:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="storeName" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">管理者姓名:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="managerName" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">管理者E-mail:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="managerEmail" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">管理者手机:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required,custom[integer]] text-input table_question_text" maxlength="11" id="managerPhone" /></td>
	                </tr>
	                <tr>
	                    <td class="table_question">管理者微信号:</td>
	                    <td class="table_question_text"><input type="text" data-validation-placeholder="输入不正确" class="validate[required] text-input table_question_text" id="managerNumber" /></td>
	                </tr>
       		</table>
	    </div>
	    </form>
    </div>
    <div class="main_add" id="inputStore">
        <div class="main_add_top">
            <span>批量导入</span>
            <div class="main_add_top_file file-box">
                <input type="text" name="textfield" id="textfield" class="txt" disabled="disabled"/>
                <input type="button" class="btn" value="浏览…" />
                <input type="file" name="fileField" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
                <input type="button" id="submit" class="btn" value="上传" />
        </div>
        <div class="main_add_context">
            <span>上传须知:上传列表标准格式为如下图所示,文件格式为.xlsx</span>
            <span>如果没有模板，请先下载模板：<a>下载</a></span>
            <img src="${ctx }/assets/images/storeDemo.png"/>
        </div>
        <div class="main_add_botton">
            <span>手动添加</span>
            <div class="main_add_list">
               <table id="storeList">
               </table>
            </div>
            <button id="addCustomStore">+</button>
        </div>
    </div>
    </div>
    <div class="main_group_add" id="groupStoreDiv">
            <div class="main_add_top">
                <div class="main_add_top_file file-box">
                    <div><input type="text" value=""/><button>X</button> </div>
                </div>
                <div class="main_group_add_botton">
                    <table class="table" id="inputGroupStoreTable" >
                        <tbody id="inputGroupStore">
                        <thead>
                        <tr class="main_group_table_top">
                            <td class="table_td_left"><input type="checkbox" id="groupStoreCheckBoxId"/></td>
                            <td class="group_table_rigth">分店/网店名称</td>
                        </tr>
                        </thead>
                        </tbody>
                    </table>
                </div>
            </div>
     </div>
    
	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript">
		jQuery(function($){
			$("#downBtn").click(function(){
				$("#downLoadFrom").submit();
			})
		})
    </script>
    
</body>
</html>