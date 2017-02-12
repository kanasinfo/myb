<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <!-- link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap-3.3.5/css/bootstrap.min.css" / -->
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css"/>
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.3.min.js"></script>
    <!-- script type="text/javascript" src="${ctx}/assets/bootstrap-3.3.5/js/bootstrap.min.js"></script -->
    <title>用户注册</title>
    <script>

    </script>
</head>
<body>
<div id="header" style="height:auto;">
    <div class="wrapper">
        <div style="text-align:center;padding:30px;"><img src="${ctx}/assets/images/logo-green-bg.png"/>
            <div style="margin:30px auto 0 auto; width:540px;">
                <div style="float:left;width:80px;margin-right:150px;"><img
                        src="${ctx}/assets/images/register_mobile2.png"/></div>
                <div style="float:left;width:80px;margin-right:150px;"><img
                        src="${ctx}/assets/images/register_info2.png"/></div>
                <div style="float:left;width:80px;"><img src="${ctx}/assets/images/register_final1.png"/></div>
                <div style="clear:both;"></div>
            </div>
        </div>
    </div>
</div>
<div id="body" style="padding:10px 0;">
    <div class="wrapper" align="center">
        <form action="${ctx}/page/account/register-account" method="post">
            <div style="width:400px; padding:30px 0 50px 100px;border-radius:4px;text-align:left;">
                <input type="hidden" value="${mobile}" name="phone"/>
                <h2 style="color:gray;">注册完成</h2>
                <p style="color: #888888;">账号注册成功，5秒钟将返回登录页</p>
                <div style="text-align: center">
                    <img src="${ctx}/assets/images/ok_green_big.png" alt="" style="margin: 0 100px 0 0;">
                </div>
            </div>

        </form>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
<script>
    setTimeout(function () {
        location.href = '${ctx}/page/account/login.html';
    }, 5000);
</script>
</body>

</html>