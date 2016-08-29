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
<title>${article.title}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/themes/business_group/css/2013_base.css" />

  <script src="${pageContext.request.contextPath}/assets/themes/business_group/js/main.js"></script> 
<script type="text/javascript">
function doZoom(size){
  document.getElementById("content-fontsize").style.fontSize=size+"px";
 }
</script>
<style>
.re_news h3{ color:#ff9900; padding-top:40px;}
.re_news li{ padding-left:10px; line-height:2em; font-size:14px;}
table{display:inline-block;}
#content-fontsize *{word-break:normal!important; font-family: "Microsoft Yahei"!important; }
.newsContent .text p{line-hieght:1.7em; margin-top:24px;}
.newsContent .text{color:#333;}
</style>
</head>

<body class="web-background" id="discover">
    <div class="witer-backgroud">
     <jsp:include page="include/header.jsp"></jsp:include>
    
                <div class="newsmain" style="margin-top:60px">
                    
                  <div class="newsContent">
                     <div> <h1>${article.title}</h1></div>
                      <div class="info"> <span>发稿时 间：${article.issueTime}</span> <span>【 字体：<a href="javascript:doZoom(16);">大</a> <a href="javascript:doZoom(14);">中</a> <a href="javascript:doZoom(12);">小</a> 】</span> </div>
                      <div class="text" id="content-fontsize">
                        <DIV class='TRS_Editor'>
<div class="Custom_UnionStyle">
			        	<c:if test="${article.coverPicture!=null}">
			        		<img src="${pageContext.request.contextPath}/upload/cover/${article.coverPicture}" alt="" style="float:right;margin:10px;" width="250" />
			         	</c:if>
			         	${article.content}<p align="center" class="Custom_UnionStyle" style="overflow-x: hidden; word-break: break-all"></p></div>
</DIV>

                        <div class="newsContentFooter marginbottom">
                          <ul>
                          <!--  <li class="readed"><a href="#">188</a></li>
                            <li class="like"><a href="#">1568</a></li> -->
                            <li class="share">
                              <!-- JiaThis Button BEGIN -->
                              <div class="jiathis_style"> <span class="jiathis_txt">分享到：</span> <a class="jiathis_button_icons_1"></a> <a class="jiathis_button_icons_2"></a> <a class="jiathis_button_icons_3"></a> <a class="jiathis_button_icons_4"></a> <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a></div>
                              <script src="http://v3.jiathis.com/code_mini/jia.js?uid=1372127324506250"></script>
                              <!-- JiaThis Button END -->
                            </li>
                          </ul>
                        </div>
                    </div>
                  </div>
                </div>
                </div>
 <jsp:include page="include/footer.jsp"></jsp:include>
<script src="http://dma.crc.com.cn/dma.js?4c22fadb84909" type="text/javascript"></script>
<!--end footer-->
</div>

<div class="gotopbox">
	<div style="display: none" id="goTopBtn">
    	<a class="iconbg btn-gotohome" href="../../../">返回到首页</a>
    	<a class="iconbg btn-gototop"  id="btn-gototop">回到顶部</a>
    </div>
</div>
<script type="text/javascript">
function removeEditor(){
var con = document.getElementById('content-fontsize');
if(!con){ 
	return;
	}
var tags=con.getElementsByTagName("div");
if(!tags){ 
	return;
	}
 for( var k in tags){
if(tags[k].className=="TRS_Editor"){
	tags[k].className="";
	}
 }
}
removeEditor();
</script>


<!--
<script src="../../../images/2013_jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../../images/images-plug.js"></script>
-->
<script src="http://dma.crc.com.cn/dma.js?4c22fadb84909" type="text/javascript"></script>

</body>
</html>