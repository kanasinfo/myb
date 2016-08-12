
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
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/validationEngine.jquery.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/assets/jquery-validation/css/template.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/question.css" />
<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/jquery-validation/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/jquery-validation/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/js/ZeroClipboard.js"></script>

<title>我的问卷</title>
<script type="text/javascript">
	var clip = null;
	function $(id) { return document.getElementById(id); }
	function init() {
		clip = new ZeroClipboard.Client();
		clip.setHandCursor( true );
		clip.addEventListener('load', function (client) {
			debugstr("Flash movie loaded and ready.");
		});
		clip.addEventListener('mouseOver', function (client) {
			// update the text on mouse over
			clip.setText( $('fe_text').value );
		});
		clip.addEventListener('complete', function (client, text) {
			debugstr("Copied text to clipboard: " + text );
		});
		clip.glue( 'd_clip_button','d_clip_button1');
	}
	
	function debugstr(msg) {
		var p = document.createElement('p');
		p.innerHTML = msg;
		$('d_debug').appendChild(p);
	}
	
	
		jQuery(function($){
			$("#downBtn").click(function(){
				$("#downLoadFrom").submit();
			});
		})
		
    </script>
</head>

<body onLoad="init()">
<input type="hidden" id="templateId" value="${id }"/>
<jsp:include page="../include/topheader.jsp" />
<jsp:include page="../include/topsurveylist.jsp" />
	<div id="body">
	<div class="wrapper">
        <div class="clearfix" style="height:10px;"></div>
        <div id="release_panel">
            <div id="release_title">
                <ul>
                    <li><a href="${ctx}/page/release/releaseOne/${templId }.html" class="release_title_select">所有顾客公用一个链接/二维码</a></li>
                    <li><a  href="${ctx}/page/release/releaseStore/${templId }.html">每个分店的顾客使用该分店的专属链接/二维码</a></li>
                    <li><a href="${ctx}/page/release/releaseOnlyOne/${templId }.html">每个顾客使用自己的专属链接</a></li>
                </ul>

            </div>
            <div id="release_center">
                <div class="release_center_top">
                    <a href="#">问卷预览</a>
                </div>
                <div class="release_center_center">
                    <div class="release_center_center_desc">
                        <span>您可以将下面的链接分享给顾客,或者通过微信/短信/邮件的方式发送出去</span>
                    </div>
                    <div class="release_center_center_text">
                        <span>问卷链接:</span>
                        <input type="text" value="${url}" id="urlName" class="copy_btn"/>
                        <button id="d_clip_button">复制连接</button>
                    </div>
                    <div class="release_center_center_qrcode">
                        <div>
                            <span>问卷二维码</span>
                        </div>
                        <div>
                            <img src="${ctx}/page/release/getImg.html?id=${templId}"/>
                        </div>
                        <div>
                            <button id="downBtn">下载二维码</button>
                        </div>
                    </div>
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
        </div>
        </div>
        <form action="${ctx}/page/release/downLoad.html" method="post" id="downLoadFrom" enctype="application/x-www-form-urlencoded">
        	<input type="hidden" id="id" name="id" value="${id }"/>
        </form>
    <div class="clearfix" style="height:10px;"></div>
	<jsp:include page="../include/footer.jsp" />
    
</body>
</html>