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
  <title>景惠仁厚集团欢迎您</title> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/2013_base.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/2013_mainbusiness.css" /> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/2013_modernizr-2.5.3.min.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/2013_jquery-1.7.1.min.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/2013_main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/2013_scroll.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/2013_gototop.js"></script> 
  <style>
.pages .box {
    margin: 0 2px;
    width:100%;
}
.pages .box span{

 float: left;

}

.title {
color: #7B7A7A;
    text-decoration: none;
    height: 33px;
    line-height: 33px;
}
.title_text {
color: #F26522;
    text-decoration: none;
    height: 33px;
    line-height: 33px;
}

.pages .box a {
    background-color: #EAE6E2;
    border: 1px solid #FFFFFF;
    color: #7B7A7A;
    height: 33px;
    line-height: 33px;
    width: 83px;
}



  
  
  
  </style> 
 </head> 
 <body> 
  <!--end header--> 
  <jsp:include page="include/header.jsp"></jsp:include>
  <div class="other-topimg" id="featured"> 
   <div id="slider"> 
    <ul id="sliderContent"> 
     <li class="sliderImage"><em class="img-top-bg"></em><img src="${pageContext.request.contextPath}/assets/themes/business_group/image/2013_news_f01.jpg" width="1920" height="320" alt="" /><span></span></li> 
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
			         <ul class="submenu" id="bro_2548"> 
			         </ul> </li> 
	         	</c:forEach>
	       </ul> 
	      </c:if>
      </div> 
      <!--end menu--> 
     </div> 
     <!--end othersmenu--> 
     <div class="othersmain newsmain fr"> 
      <div class="other-titles"> 
       <h2><c:if test="${column!=null}">${column.label}</c:if></h2> 
      </div> 
      <div class="newslist"> 
      	<c:if test="${articleList.size()>0}">
	      <ul> 
	      		<c:forEach items="${articleList}" var="news">
			        <li> 
			         <div class="time"> 
			          <fmt:formatDate value="${news.issueTime}" pattern="dd" var="day" />
			          <span class="day">${day}</span>
			          <fmt:formatDate value="${news.issueTime}" pattern="yyyy-MM" var="yearMonth" /> 
			          <span class="date">${yearMonth}</span>
			         </div> 
			         <div class="conts"> 
			          <h3><a href="${pageContext.request.contextPath}/page/article/${news.id}.html" target="_blank">${news.title}</a></h3> 
			          <p> ${news.summary}<a class="more" href="${pageContext.request.contextPath}/page/article/${news.id}.html" target="_blank"><img src="${pageContext.request.contextPath}/assets/themes/business_group/image/2013_i08.gif" alt="更多" /></a></p> 
			         </div> </li> 
		         </c:forEach>
	       </ul> 
       	</c:if>
      </div> 
      <!--end newslist--> 
      <div class="pages"> 
       <span class="box btnpre"> 
       	<span class="title">总共</span>
       	<span class="title_text">${pageCount}</span>
       	<span class="title">页 </span>
       	<span class="title">当前第</span>
       	<span class="title_text">${currentPage}</span>
       	<span class="title">/</span>
       	<span class="title_text">${pageCount}</span>
       	<span class="title">页  </span>
       	<table cellspacing="0" cellpadding="0" border="0">
       		<tbody>
       			<tr>
       				<td>
       					<table width="55" cellspacing="0" cellpadding="0" border="0">
       						<tbody>
       							<tr> 
       								<td width="24"><a class="page" href="${pageContext.request.contextPath}/page/column/${column.id}.html">首页</a></td>
       							</tr>
       						</tbody>
       					</table>
       				</td>
       				<td>
       					<table width="67" cellspacing="0" cellpadding="0" border="0">
       						<tbody>
       							<tr> 
       								<td width="36"><a class="page" <c:if test="${currentPage > 1}">href="${pageContext.request.contextPath}/page/column/${column.id}.html?page=${currentPage-1}"</c:if>>上一页</a></td>
       							</tr>
       						</tbody>
       					</table>
       				</td>
       				<td><table width="67" cellspacing="0" cellpadding="0" border="0">
       					<tbody><tr> <td width="36"><a target="_self" class="page" <c:if test="${currentPage < pageCount}">href="${pageContext.request.contextPath}/page/column/${column.id}.html?page=${currentPage+1}"</c:if>>下一页</a></td></tr></tbody>
       					</table>
       				</td>
       				<td><table width="55" cellspacing="0" cellpadding="0" border="0">
       					<tbody><tr>  <td width="24"><a target="_self" class="page" href="${pageContext.request.contextPath}/page/column/${column.id}.html?page=${pageCount}">尾页</a></td></tr></tbody>
       					</table>
       				</td>
       				<td style="padding-left:40px;">
       					<select onchange="location.replace(this.value)" style="margin-bottom:-3px;" name="select">
       						<option selected="">跳至</option>
       						<c:forEach var="i" begin="1" end="${pageCount}">
       							<option value="${pageContext.request.contextPath}/page/column/${column.id}.html?page=${i}">${i}页</option>
       						</c:forEach>
       					</select>
       				</td>
       			</tr></tbody></table>
       </span> 
      </div> 
      <!--end pages--> 
     </div> 
     <!--end othersmain--> 
    </div> 
   </div> 
   <!--end otherswrap--> 
   <jsp:include page="include/footer.jsp"></jsp:include>
   <!--end footer--> 
   <!--end footer--> 
  </div> 
  <!--end overlay--> 
  <div class="gotopbox"> 
   <div style="display: none" id="goTopBtn"> 
    <a class="iconbg btn-gotohome" href="${pageContext.request.contextPath}/page/index.html">返回到首页</a> 
    <a class="iconbg btn-gototop" id="btn-gototop">回到顶部</a> 
   </div> 
  </div> 
 </body>
</html>