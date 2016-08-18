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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/about.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/business.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/investor.css" /> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/modernizr-2.5.3.min.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/jquery-1.7.1.min.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/scroll.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/gototop.js"></script> 
 </head> 
 <body> 
   <jsp:include page="include/header.jsp"></jsp:include>
  <div class="other-topimg" id="featured"> 
   <div id="slider"> 
    <ul> 
     <li class="sliderImage"><em class="img-top-bg"></em> <img src="${pageContext.request.contextPath}/assets/themes/business_group/image/P020131202603982816497.jpg" /> <span></span> </li> 
    </ul> 
   </div> 
  </div> 
  <!--end other-topimg--> 
  <div id="overlay"> 
   <div class="otherswrap" id="discover"> 
    <div class="layout"> 
     <div class="othersmenu fl" id="div_6066" style="display:none;"> 
      <div class="other-titles"> 
       <h2><c:if test="${parentColumn!=null}">${parentColumn.label}</c:if></h2> 
      </div> 
      <div class="menu" id="ul_6066" style="display:none;"> 
      	<c:if test="${parentColumn.hasChildren}">
	       	<ul> 
		         <c:forEach items="${parentColumn.childrenColumns}" var="brotherColumn">
		        <li <c:if test="${brotherColumn.label==column.label}"> class="on" </c:if>> <a href="${pageContext.request.contextPath}/page/column/${brotherColumn.id}.html" class="tit">${brotherColumn.label}</a> 
		         <ul class="submenu" id="bro_6121"> 
		         </ul> 
		         </li> 
		        </c:forEach>
	       	</ul> 
       	</c:if>
      </div> 
      <!--end menu--> 
     </div>
     
     <script language="javascript">
var obj_parent_2;
var obj_parent=6074;
var obj=6079;

   
      if(obj_parent==6074)
          obj_parent_2=6066;
   
      if(obj_parent==6075)
          obj_parent_2=6066;
   
      if(obj_parent==6121)
          obj_parent_2=6066;
   

   
      if(obj_parent==6076)
          obj_parent_2=6067;
   
      if(obj_parent==6077)
          obj_parent_2=6067;
   
      if(obj_parent==6078)
          obj_parent_2=6067;
   

   
      if(obj_parent==6096)
          obj_parent_2=6068;
   
      if(obj_parent==6097)
          obj_parent_2=6068;
   
      if(obj_parent==6098)
          obj_parent_2=6068;
   

   
      if(obj_parent==6102)
          obj_parent_2=6069;
   
      if(obj_parent==6103)
          obj_parent_2=6069;
   
      if(obj_parent==6104)
          obj_parent_2=6069;
   
      if(obj_parent==6105)
          obj_parent_2=6069;
   
      if(obj_parent==6106)
          obj_parent_2=6069;
   

   
      if(obj_parent==6115)
          obj_parent_2=6070;
   
      if(obj_parent==6189)
          obj_parent_2=6070;
   
      if(obj_parent==6118)
          obj_parent_2=6070;
   
      if(obj_parent==6214)
          obj_parent_2=6070;
   

   
      if(obj_parent==6120)
          obj_parent_2=6071;
   
      if(obj_parent==9113)
          obj_parent_2=6071;
   
      if(obj_parent==9114)
          obj_parent_2=6071;
   
      if(obj_parent==6123)
          obj_parent_2=6071;
   
      if(obj_parent==6366)
          obj_parent_2=6071;
   
      if(obj_parent==6122)
          obj_parent_2=6071;
   
      if(obj_parent==6367)
          obj_parent_2=6071;
   
      if(obj_parent==6368)
          obj_parent_2=6071;
   
      if(obj_parent==6124)
          obj_parent_2=6071;
   
      if(obj_parent==6125)
          obj_parent_2=6071;
   
      if(obj_parent==6369)
          obj_parent_2=6071;
   
      if(obj_parent==6476)
          obj_parent_2=6071;
   
      if(obj_parent==6126)
          obj_parent_2=6071;
   

   
      if(obj_parent==6129)
          obj_parent_2=6072;
   
      if(obj_parent==6130)
          obj_parent_2=6072;
   
      if(obj_parent==6131)
          obj_parent_2=6072;
   

   
      if(obj_parent==8548)
          obj_parent_2=8547;
   
      if(obj_parent==8549)
          obj_parent_2=8547;
   
      if(obj_parent==8550)
          obj_parent_2=8547;
   
      if(obj_parent==8552)
          obj_parent_2=8547;
   

   
      if(obj_parent==6159)
          obj_parent_2=6151;
   
      if(obj_parent==6161)
          obj_parent_2=6151;
   
      if(obj_parent==6164)
          obj_parent_2=6151;
   
      if(obj_parent==6165)
          obj_parent_2=6151;
   
      if(obj_parent==6167)
          obj_parent_2=6151;
   
      if(obj_parent==6283)
          obj_parent_2=6151;
   

   
      if(obj_parent==6154)
          obj_parent_2=6166;
   
      if(obj_parent==6153)
          obj_parent_2=6166;
   
      if(obj_parent==6347)
          obj_parent_2=6166;
   

document.getElementById("div_"+obj_parent_2).style.display ="inline";
document.getElementById("ul_"+obj_parent_2).style.display ="inline";
document.getElementById("bro_"+obj_parent).className="submenu block";
document.getElementById("OWNER_6079").className="on";

function loc(cur_obj,url){
   if(document.getElementById("input_"+cur_obj)==null){
       if(url.indexOf("http://")<0){
          window.location.href=url;
        }else{
           window.open(url);
   }
}
else{document.getElementById("bro_"+cur_obj).className=document.getElementById("bro_"+cur_obj).className=="submenu"?"submenu block":"submenu";}
}

	//打开当前树 

     
     
     
     </script> 
     <!--end othersmenu--> 
     <div class="othersmain business-brandactiv fr"> 
      <div class="other-titles"> 
       <h2><c:if test="${column!=null}">${column.label}</c:if></h2> 
      </div> 
      <div class="TRS_Editor"> 
       <div class="about-honormain"> 
        <div class="about_introduction2"> 
        	<c:if test="${defaultArticle!=null}">
        	<c:if test="${defaultArticle.coverPicture!=null}">
        		<img src="${pageContext.request.contextPath}/upload/cover/${defaultArticle.coverPicture}" alt="" style="float:right;margin:10px;" width="150" />
        	</c:if>
        		${defaultArticle.content}
        	</c:if>
        </div> 
       </div> 
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