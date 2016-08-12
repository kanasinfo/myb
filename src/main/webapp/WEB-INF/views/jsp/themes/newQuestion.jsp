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
	<script src="${ctx}/assets/jquery/jquery-1.8.3.js" ></script>

<title>新建问卷</title>
<script>
	jQuery(function($){
		$("#industry").change(function(){
			var select = $("#industry").val();
			alert(select);
			$.ajax({
				url:'${ctx}/page/data/queryIndustryByParentId.html',
				data:select,
				method:'post',
				dataType:'json',
				success:function(data){
					if(data.success){
						alert(data.data);
					}else{
						alert(data.message);
					}
				},
				error:function(){
					alert("通讯失败！");
				}
				
				
			})			
		})
	})
</script>
</head>

<body>
	选择行业：<select id="industry" >
		<option>请选择</option>
		<c:if test="${industry !=null }">
			<c:forEach var="indu" items="${industry}">
				<option id="${indu.id }" value="${indu.id }">${indu.name}</option>
			</c:forEach>
		</c:if>
	</select>
	<br/>
	选择子行业
	<select id="parentIndustry">
	</select>
	<br/>
	选择模板
	<br/>
	调查名称：<input type="text" id="name" name ="name"></input><br/>
	设置样本数量:<input type="text" id="questionName" name="questionName"></input>
	餐厅名称:<input type="text" id="testName" name="testName"></input>
</body>
</html>