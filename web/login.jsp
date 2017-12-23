<%@ page pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
    <TITLE>简单小论坛--登录</TITLE>
    <META http-equiv=Content-Type content="text/html; charset=gbk">
    <Link rel="stylesheet" type="text/css" href="style/style.css"/>
</HEAD>

<BODY>
<DIV>
    <IMG src="image/logo.gif">
</DIV>
<!--      用户信息、登录、注册        -->

<DIV class="h">
    您尚未　<a href="login.jsp">登录</a>
    &nbsp;| &nbsp; <A href="reg.jsp">注册</A> |
</DIV>


<BR/>
<!--      导航        -->
<DIV>
    &gt;&gt;<B><a href="index.jsp">论坛首页</a></B>
</DIV>
<!--      用户登录表单        -->
<DIV class="t" style="MARGIN-TOP: 15px" align="center">
    <FORM name="loginForm">
        <br/>用户名 &nbsp;<INPUT class="input" tabIndex="1" type="text" maxLength="20" size="35" id="uName">
        <br/>密　码 &nbsp;<INPUT class="input" tabIndex="2" type="password" maxLength="20" size="40" id="uPass">
        <br/><INPUT class="btn" tabIndex="6" type="button" value="登 录">
    </FORM>
</DIV>
<!--      声明        -->
<BR/>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
    Information Technology Co.,Ltd 版权所有
</CENTER>
</BODY>
</HTML>
<script type="text/javascript" src="plugins/jquery-1.7.2.js"></script>
<script type="text/javascript">

    var f1 = function check() {
        if(document.loginForm.uName.value==""){
            alert("用户名不能为空");
            return false;
        }
        if(document.loginForm.uPass.value==""){
            alert("密码不能为空");
            return false;
        }
    }

    $(".btn").click(function () {
        $.ajax({
            url: "doLogin.do",
            type: "post",
            data: {
                uName: $("#uName").val(),
                uPass: $("#uPass").val()
            },
            beforeSend: f1,
            dataType: "text",
            success: function (data) {
                if ("true" == data) {
                    window.location.href = "<%=basePath%>index.jsp"
                }else{
                    alert("用户名或密码错误！");
                }
            }

        });
    });

</script>