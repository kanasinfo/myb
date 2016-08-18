<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <link href="/images/favicon.ico" rel="shortcut icon" /> 
  <title>景惠仁厚集团欢迎您</title> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/base.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/exchange.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/jquery.ui.datepicker.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/investor.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/about.css" /> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/modernizr-2.5.3.min.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/jquery-1.7.1.min.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/scroll.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/gototop.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/jquery.ui.core.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/jquery.ui.datepicker.js"></script> 
 </head> 
 <body>
  <jsp:include page="include/header.jsp"></jsp:include>
  <div class="other-topimg" id="featured"> 
   <div id="slider"> 
    <ul> 
     <li class="sliderImage"><em class="img-top-bg"></em> <img src="${pageContext.request.contextPath}/assets/themes/business_group/image/P020130923604349585626.jpg" /> <span></span> </li> 
    </ul> 
   </div> 
  </div> 
  <!--end other-topimg--> 
  <div id="overlay"> 
   <div class="otherswrap" id="discover"> 
    <div class="layout"> 
     <div class="othersmenu fl"> 
      <div class="other-titles"> 
       <h2><c:if test="${parentColumn!=null}">${parentColumn.label}</c:if></h2> 
      </div> 
      <div class="menu"> 
      	<c:if test="${parentColumn.hasChildren}">
	       <ul> 
	       		<c:forEach items="${parentColumn.childrenColumns}" var="brotherColumn">
		        <li <c:if test="${brotherColumn.label==column.label}"> class="on" </c:if>> <a href="${pageContext.request.contextPath}/page/column/${brotherColumn.id}.html" class="tit">${brotherColumn.label}</a> 
		         <ul class="submenu" id="bro_6120"> 
		         </ul> </li> 
		        </c:forEach>
	       </ul> 
       	</c:if>
      </div> 
      <!--end menu--> 
     </div> 
     <script language="javascript">
var obj=6120;//当前栏目
$("#OWNER_6120").attr("class","on");

document.getElementById("bro_"+obj).className=null==document.getElementById("input_6120")?"submenu":"submenu block";

function loc(cur_obj,url){
if(document.getElementById("input_"+cur_obj)==null){if(url.indexOf("http://")<0){window.location.href=url;}else{window.open(url);}}
else{document.getElementById("bro_"+cur_obj).className=document.getElementById("bro_"+cur_obj).className=="submenu"?"submenu block":"submenu";}
}

	//打开当前树

$("#OWNER_6214 a").attr("target","_blank"); 

     
     
     
     </script> 
     <!--end othersmenu--> 
     <div class="othersmain business-brandactiv fr"> 
      <div class="other-titles"> 
       <h2><c:if test="${column!=null}">${column.label}</c:if></h2> 
      </div> 
      <div class="responsibility_concept"> 
       <dl class="item"> 
        <p><c:if test="${defaultArticle!=null}">
        	<c:if test="${defaultArticle.coverPicture!=null}">
        		<img src="${pageContext.request.contextPath}/upload/cover/${defaultArticle.coverPicture}" alt="" style="float:right;margin:10px;" width="150" />
        	</c:if>
        	${defaultArticle.content}
        	</c:if></p> 
        <p>&nbsp;</p> 
        <c:if test="${articleList.size()>0}">
        	<c:forEach items="${articleList}" var="article">
        		<c:if test="${article.id != defaultArticle.id}">
			        <dt class="tit"> 
			         <span>${article.title}</span> 
			        </dt> 
			        <dd class="conts">
			        	<c:if test="${article.coverPicture!=null}">
			        		<img src="${pageContext.request.contextPath}/upload/cover/${article.coverPicture}" alt="" style="float:right;margin:10px;" width="150" />
			         	</c:if>
			         <p>${article.content}</p> 
			         <p>&nbsp;</p> 
			        </dd> 
		        </c:if>
	        </c:forEach>
        </c:if>
       </dl> 
      </div> 
     </div> 
     <!--end othersmain--> 
    </div> 
   </div> 
   <!--end otherswrap--> 
 <jsp:include page="include/footer.jsp"></jsp:include>
   <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/dma.js" type="text/javascript"></script> 
  </div> 
  <!--end overlay--> 
  <div class="gotopbox"> 
   <div style="display: none" id="goTopBtn"> 
    <a class="iconbg btn-gotohome" href="${pageContext.request.contextPath}/page/index.html">返回到首页</a> 
    <a class="iconbg btn-gototop" id="btn-gototop">回到顶部</a> 
   </div> 
  </div> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/jianfan.js"></script>  
 </body>
</html>