<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/1
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <link rel="stylesheet" href="/static/css/neat.css"/>
    <link rel="stylesheet" href="/static/css/common.css"/>
    <link rel="stylesheet" href="/static/css/loginForm.css"/>
</head>
<body>
    <div class="input_form center_XY">
        <form action="login" method="post">
            <div class="field">
                <span class="label">用户名：</span>
                <div class="input"><input type="text" name="loginname"/></div>
            </div>
            <div class="field">
                <span class="label">密&nbsp;&nbsp;&nbsp;码：</span>
                <div class="input">
                    <input type="password" name="password">
                </div>
                <p class="message">${message}</p>
            </div>
            <div class="field">
                <span class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <div class="input login"><input type="submit" value="登录"/></div>
            </div>
        </form>
    </div>
</body>
</html>
