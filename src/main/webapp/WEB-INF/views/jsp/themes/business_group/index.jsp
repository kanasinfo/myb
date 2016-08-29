<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
  <meta content="" name="description" /> 
  <meta content="" name="keywords" /> 
  <link href="/images/favicon.ico" rel="shortcut icon" /> 
  <title>景惠仁厚集团欢迎您</title> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/base.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/index_new.css" /> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/jquery-1.7.1.min.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/jquery.SuperSlide.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/swfobject.js"></script> 
  <!-- <script type="text/javascript" src="${pageContext.request.contextPath}/assets/themes/business_group/js/scrollZt.js"></script>  -->
 </head> 
 <body>
  <jsp:include page="include/header.jsp"></jsp:include>
  <div class="home-foc"> 
   <div class="bd"> 
    <ul> 
     <li class="changephoto change-col03"> 
      <div class="photo">
       <img src="${pageContext.request.contextPath}/assets/themes/business_group/image/foc01.jpg" width="1920" height="534" alt="医养结合" /> 
      </div>
      <div class="main"> 
       <div class="titles"> 
        <dl> 
         <dt>
           医养结合 
         </dt> 
         <dd>
          <p>集团拥有医疗与养老床位2000余张，拥有医疗专业技术人员、科研人员、管理咨询人员近2000人，形成了以医疗和养老实体机构经营、医疗机构和养老机构投资与托管、管理咨询与研究为核心业务的现代医疗管理集团。
“精心守护健康，爱心相伴夕阳”，景惠仁厚愿倾注心血和热情为每一个家庭幸福圆梦！</p> 
         </dd> 
        </dl> 
       </div> 
       <div class="bgcolor"></div> 
      </div> 
      <!--end main--> </li> 
     <!--end changephoto--> 
     <li class="changephoto change-col02"> 
      <div class="photo"> 
       <img src="${pageContext.request.contextPath}/assets/themes/business_group/image/foc02.jpg" width="1920" height="534" alt="健康绿色通道" /> 
      </div> 
      <div class="main"> 
       <div class="titles"> 
        <dl> 
         <dt>
           健康绿色通道 
         </dt> 
         <dd>
          <p>为企业团体或个体提供全面的、及时的、科学的健康状态监测、分析、评估，对潜在的健康危险因素进行干预，全程跟踪客户的健康状态，及时解决客户的健康问题。在医疗资源和患者数量比例严重失调的现状下，“就医绿色通道”特色服务，为客户提供有效、便捷、专业的就诊陪护系列服务，尽可能为客户节约开支，节省就诊时间。</p> 
         </dd> 
        </dl> 
       </div> 
       <div class="bgcolor"></div> 
      </div> 
      <!--end main--> </li> 
     <!--end changephoto--> 
     <li class="changephoto change-col04"> 
      <div class="photo"> 
       <img src="${pageContext.request.contextPath}/assets/themes/business_group/image/foc03.jpg" width="1920" height="534" alt="医院管理咨询" /> 
      </div> 
      <div class="main"> 
       <div class="titles">
        <dl> 
         <dt>
           医院管理咨询 
         </dt> 
         <dd>
          <p>秉承着“以医院满意为导向”的宗旨，以不断推动中国医院职业化管理进程和提高医院职业化管理水平为己任。积累了大量的案例和医院管理咨询与培训经验，充分结合医院实际，以“咨询式培训”和“辅导式咨询”的培训与咨询模式为医疗机构提供适用、可操作的个性化解决方案，提供经得住时间检验的咨询方案，并关注实施，长期跟踪辅导。</p> 
         </dd> 
        </dl> 
       </div>
       <div class="bgcolor"></div> 
      </div> 
     </li>
     <li class="changephoto change-col01"> 
      <div class="photo"> 
       <img src="${pageContext.request.contextPath}/assets/themes/business_group/image/foc04.jpg" width="1920" height="534" alt="养老产业服务" /> 
      </div> 
      <div class="main"> 
       <div class="titles">
        <dl>
         <dt>
           养老产业服务 
         </dt>
         <dd>
          <p>景惠仁厚人，积极整合养老资源，用多年积累的医疗、养老运营经验，以高度社会责任感打造专业服务团队，积极探索中国式医疗养老行业发展之路。养老机构设立的政策解读、市场调研、规划与建造、设施统购，机构运营中的品牌定位、市场营销、机构管理、人员技能提升、风险防控，机构改造建议、养老资源对接，养老方案选择……</p> 
         </dd>
        </dl>
       </div>
       <div class="bgcolor"></div>
      </div>
     </li>
    </ul>
   </div>
   <div class="home-foc-nav hd"> 
    <ul> 
     <li><a class="a03 iconbg" title="医养结合">医养结合</a></li> 
     <li><a class="a02 iconbg" title="健康绿色通道">健康绿色通道</a></li> 
     <li><a class="a04 iconbg" title="医院管理咨询">医院管理咨询</a></li>
     <li><a class="a01 iconbg" title="养老产业服务">养老产业服务</a></li>
    </ul> 
   </div> 
  </div> 
  <!--end home-foc--> 
  <script> jQuery(".home-foc").slide({ mainCell:".bd ul",effect: "fade",autoPlay:"ture"});
  
  
  
  </script> 
  <div class="homemain"> 
   <div class="layout"> 
    <div class="homenews fl"> 
     <div class="titels"> 
      <h2 class="fl">
      <c:if test="${jtdtColumn!=null}">
      <a href="${pageContext.request.contextPath}/page/column/${jtdtColumn.id}.html">
      		${jtdtColumn.label}
      </a></c:if>
      </h2> 
      <div class="fr more"> 
       <a class="a-more iconbg" href="${pageContext.request.contextPath}/page/column/${jtdtColumn.id}.html"> 更多</a> 
      </div> 
     </div> 
     <div class="homemain-conts homenews-conts" style="border:none;"> 
      <div class="homenews-list"> 
      	<c:if test="${jtdtArticles1!=null}">
	       <ul class="two_col_list"> 
	       	<c:forEach items="${jtdtArticles1}" var="jarticle1">
	        	<li><a href="${pageContext.request.contextPath}/page/article/${jarticle1.id}.html" target="_blank">${jarticle1.title}</a></li> 
	       	</c:forEach>
	       </ul>
	   </c:if> 
	   <c:if test="${jtdtArticles2!=null}">
	       <ul class="two_col_list"> 
	       	<c:forEach items="${jtdtArticles2}" var="jarticle2">
	        	<li><a href="${pageContext.request.contextPath}/page/article/${jarticle2.id}.html" target="_blank">${jarticle2.title}</a></li> 
	       	</c:forEach>
	       </ul>
	   </c:if> 
      </div> 
      <!--end homenews-list--> 
     </div>  
    </div> 
    <!--end homenews--> 
    <div class="homevideo fr"> 
     <div class="index-ad" style="position:relative;text-align:center;"> 
      <a href="${pageContext.request.contextPath}/page/article/7e43a1808b50e1455a4086ec2e9f0b9d.html" target="_blank" style="margin-bottom:10px; display:block;"><img src="${pageContext.request.contextPath}/upload/cover/2015/31a023d1cdc144d6a00e1a83a0223955.jpg" border="0" width="200" /> </a> 
      <a href="${pageContext.request.contextPath}/page/article/7e43a1808b50e1455a4086ec2e9f0b9d.html" target="_blank" style="margin-bottom:10px; display:block;">景惠仁厚医疗管理集团董事长寄语</a>  
     </div>
    </div> 
    <div class="homeview fl" style="margin-top:20px;"> 
     <div class="titels"> 
      <h2 class="fl">
       	商机在线</h2>
      <div class="fr more"> 
       <a class="a-more iconbg" href="#" target="_blank"></a> 
      </div> 
     </div> 
     <div class="homemain-conts"> 
      <img src="${pageContext.request.contextPath}/assets/images/sjzx.jpg" alt="" /> 
     </div> 
    </div> 
    <!--end homeview--> 
    <div class="homemedia fl" style="margin-top:20px;"> 
     <div class="titels"> 
      <h2 class="fl"><a href="${pageContext.request.contextPath}/page/column/${fwxmColumn.id}.html"><c:if test="${fwxmColumn!=null }">聚焦养老</c:if></a></h2> 
      <div class="fr more"> 
       <a class="a-more iconbg" href="${pageContext.request.contextPath}/page/column/${fwxmColumn.id}.html">更多</a> 
      </div> 
     </div> 
     <div class="homemain-conts"> 
      <div class="homenews-list"> 
      	<c:if test="${fwxmArticles!=null}">
	       <ul> 
	       	 <c:forEach items="${fwxmArticles}" var="fwArticle">
	        	<li><a href="${pageContext.request.contextPath}/page/article/${fwArticle.id}.html" target="_blank">${fwArticle.title}</a></li> 
	       	 </c:forEach>
	       </ul> 
       	</c:if>
      </div> 
      <!--end homenews-list--> 
     </div> 
    </div> 
    <!--end homemedia--> 
   </div> 
   <!--end layout--> 
  </div> 
  <!--end homemain--> 
 <jsp:include page="include/footer.jsp"></jsp:include>
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/dma.js" type="text/javascript"></script> 
  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/jianfan.js"></script>  
 </body>
</html>