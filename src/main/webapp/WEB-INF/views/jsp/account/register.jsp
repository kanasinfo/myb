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
        .mobile-code-btn {
            width: 100px;
            height: 30px;
            background-color: #78973E;
            border-radius: 5px;
            color: #FFF;
            font-weight: bold;
        }
        .disable {
            background-color: #ccc;
            color: #0c0c0c;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
<jsp:include page="register_header.jsp"/>
<div id="body" style="padding:10px 0;">
    <div class="wrapper" align="center">
        <form action="${ctx}/page/account/register" method="post">
            <div style="width:400px; padding:30px 0 50px 100px;border-radius:4px;text-align:left;">
                <h2 style="color:gray;">验证手机号</h2>
                <h3 style="color: red">${msg}</h3>
                <div style="margin:20px 0 10px 0;">输入手机号：</div>
                <div><input type="text" id="mobile" name="mobile"
                            style="backgroud:white;border-radius:5px;width:250px;height:20px;"/>
                    <span class="error"></span>
                </div>
                <div style="margin:20px 0 10px 0;">输入手机验证码：</div>
                <div>
                    <input type="text" id="mobileCode" name="mobileCode"
                           style="backgroud:white;border-radius:5px;width:150px;height:20px;"/>
                    <button id="mobileCodeBtn" class="mobile-code-btn">获取验证码</button>
                    <span class="error"></span>
                </div>
                <div style="margin:20px 0 10px 0;">输入验证码：</div>
                <div><input type="text" id="code" name="code"
                            style="backgroud:white;border-radius:5px;width:150px;height:20px;font-weight:bold;"></input>&nbsp&nbsp&nbsp&nbsp<img
                        src="${ctx}/servlet/captchaCode" id="captchaCode"></img>
                    <span class="error"></span>
                </div>

                <div>
                    <button type="submit"
                            style="width:250px;height:30px;margin-top: 20px;background-color:#78973E;border-radius:5px;color:#FFF;font-weight:bold;">
                        下一步
                    </button>
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
    (function () {
        $('form').validate({
            rules: {
                mobile: {
                    required: true,
                    remote: {
                        url: '${ctx}/page/account/phone/verify',
                        type: 'POST',
                        data : {
                            phone: function(){
                                return $('#mobile').val();
                            }
                        }
                    }
                },
                mobileCode: {
                    required: true
                },
                code: {
                    required: true,
                    remote: {
                        url: '${ctx}/page/account/code/verify',
                        type: 'POST',
                        data : {
                            phone: function(){
                                return $('#code').val();
                            }
                        }
                    }
                },
                agreement: {
                    required: true
                }
            },
            messages: {
                mobile: {
                    required: '请输入手机号',
                    remote: '手机号已被占用'
                },
                mobileCode: {
                    required: '请输入手机验证码'
                },
                code: {
                    required: '请输入验证码',
                    remote: '验证码错误'
                },
                agreement: {
                    required: '请勾选同意保密协议及服务条款'
                }
            },
            errorPlacement: function(error, element) {
                error.appendTo( element.closest('div').find('span.error'));
            }
        });
        $("#captchaCode").click(function () {
            $("#captchaCode").attr("src", "${ctx}/servlet/captchaCode?t=" + Math.random());
        });
        $('#mobileCodeBtn').click(function (e) {
            e.preventDefault();
            var $this = $(this);
            $this.text('重新发送(50)')
        });
    })();
</script>
</body>

</html>