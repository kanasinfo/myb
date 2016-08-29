<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <div class="header" id="head"> 
   <div class="header_top"> 
    <div class="logo">
     <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/assets/themes/business_group/image/logo.png" alt="景惠仁厚" /></a> 
    </div> 
   </div> 
   <!--end header_top--> 
   <div class="navbody">
    <div class="nav"> 
     <ul class="nav_ul"> 
      <li class="nav_li"><a class="on" href="${pageContext.request.contextPath}/page/index.html" title="首页">首页</a></li> 
      <c:forEach items="${spruceNavigators}" var="sn">
      	 <li class="nav_li">
      	 	<a class="on" <c:choose>
      	 						<c:when test="${sn.type=='LINK'}">
      	 							href="${sn.link}" target="_blank"
      	 						</c:when>
      	 						<c:when test="${sn.type=='ARTICLE'}">
      	 							href="${pageContext.request.contextPath}/page/article/${sn.link}.html"
      	 						</c:when>
      	 						<c:when test="${sn.type=='COLUMN'}">
      	 							href="${pageContext.request.contextPath}/page/column/${sn.link}.html"
      	 						</c:when>
      	 					</c:choose>
      	 		title="${sn.label}">${sn.label}</a>
      	 	<c:if test="${sn.hasChildren}">
      	 		<div class="subnav sub-ul06"> 
       			 <ul>
       			 	<c:forEach items="${sn.childrenNavigators}" var="subSn">
       			 		<li><a <c:choose>
      	 						<c:when test="${subSn.type=='LINK'}">
      	 							href="${subSn.link}" target="_blank"
      	 						</c:when>
      	 						<c:when test="${subSn.type=='ARTICLE'}">
      	 							href="${pageContext.request.contextPath}/page/article/${subSn.link}.html"
      	 						</c:when>
      	 						<c:when test="${subSn.type=='COLUMN'}">
      	 							href="${pageContext.request.contextPath}/page/column/${subSn.link}.html"
      	 						</c:when>
      	 					</c:choose>
       			 		>${subSn.label}</a></li> 
       			 	</c:forEach>
       			 </ul>
       			</div>
      	 	</c:if>
      	 </li>
      </c:forEach>
     </ul> 
    </div> 
    <div class="subnavbg"></div> 
   </div> 
  </div>