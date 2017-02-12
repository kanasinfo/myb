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
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div id="header" style="height:auto;">
    <div class="wrapper">
        <div style="text-align:center;padding:30px;"><img src="${ctx}/assets/images/logo-green-bg.png"/>
            <div style="margin:30px auto 0 auto; width:540px;">
                <div style="float:left;width:80px;margin-right:150px;"><img
                        src="${ctx}/assets/images/register_mobile2.png"/></div>
                <div style="float:left;width:80px;margin-right:150px;"><img
                        src="${ctx}/assets/images/register_info1.png"/></div>
                <div style="float:left;width:80px;"><img src="${ctx}/assets/images/register_final2.png"/></div>
                <div style="clear:both;"></div>
            </div>
        </div>
    </div>
</div>
<div id="body" style="padding:10px 0;">
    <div class="wrapper" align="center">
        <form action="${ctx}/page/account/register-account" method="post">
            <div style="width:400px; padding:30px 0 50px 100px;border-radius:4px;text-align:left;">
                <input type="hidden" value="${mobile}" name="phone" id="phone"/>
                <h2 style="color:gray;">注册信息</h2>
                <h3 style="color: red">${error}</h3>
                <div style="margin:20px 0 10px 0;">账号：</div>
                <div><input type="text" id="loginEmail" name="loginEmail"
                            style="backgroud:white;border-radius:5px;width:250px;height:20px;"/>
                    <span class="error"></span>
                </div>
                <div style="margin:20px 0 10px 0;">密码：</div>
                <div><input type="password" id="loginPass" name="loginPass"
                            style="backgroud:white;border-radius:5px;width:250px;height:20px;"/>
                    <span class="error"></span>
                </div>
                <div style="margin:20px 0 10px 0;">确认密码：</div>
                <div><input type="password" id="confirm_loginPass" name="confirm_loginPass"
                            style="backgroud:white;border-radius:5px;width:250px;height:20px;"/>
                    <span class="error"></span>
                </div>
                <div style="margin:20px 0 40px 0;"><label><input type="checkbox" id="agreement"
                                                                 name="agreement"></input>我同意<a
                        href="${ctx}/page/info/secret.html" style="color:#333;" target="_blank">保密协议</a>以及<a
                        href="${ctx}/page/info/service.html" style="color:#333;" target="_blank">服务条款</a></label>
                    <div>
                        <span class="error"></span>
                    </div>
                </div>
                <div><input type="submit" value="下一步"
                            style="width:250px;height:30px;background-color:#78973E;border-radius:5px;color:#FFF;font-weight:bold;"/>
                </div>
                <div style="width:250px;text-align:center;padding:10px;">已经注册，请<a href="${ctx}/page/account/login.html"
                                                                                  style="text-decoration: none;color: #09f; font-weight: bold;">登录</a>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
<script src="//cdn.bootcss.com/jquery-validate/1.15.1/jquery.validate.min.js"></script>
<script src="//cdn.bootcss.com/jquery-validate/1.15.1/additional-methods.min.js"></script>
<script src="//cdn.bootcss.com/jquery-validate/1.15.1/localization/messages_zh.min.js"></script>
<script>
    (function(){
        if(!$('#phone').val() || $('#phone').val() == undefined || $('#phone').val() == '' || $('#phone').val() == 'null'){
            location.href = '${ctx}/page/account/login.html';
        }
        $('form').validate({
            rules: {
                loginEmail: {
                    required: true,
                    remote: {
                        url: '${ctx}/page/account/loginEmail/verify',
                        type: 'POST',
                        data : {
                            loginEmail: function(){
                                return $('#loginEmail').val();
                            }
                        }
                    }
                },
                loginPass: {
                    required: true
                },
                confirm_loginPass: {
                    equalTo: '#loginPass'
                },
                agreement: {
                    required: true
                }
            },
            messages: {
                loginEmail: {
                    required: '请输入账号',
                    remote: '用户名已存在'
                },
                loginPass: {
                    required: '请输入密码'
                },
                confirm_loginPass: {
                    equalTo: '两次密码输入不一致'
                },
                agreement: {
                    required: '请同意《保密协议》以及《服务条款》'
                }
            },
            errorPlacement: function(error, element) {
                error.appendTo( element.closest('div').find('span.error'));
            }
        });
    })();
</script>
</body>

</html>