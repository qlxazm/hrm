<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/3
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
    <link rel="stylesheet" href="/static/css/addOrUpdateCommon.css"/>
</head>
<body>
    <form method="post" action="addUser?flag=2">
        <p class="message">${message}</p>
        <div class="field">
            <span class="label">用户名：</span>
            <div class="input"><input type="text" name="username"></div>
        </div>
        <div class="field">
            <span class="label">登录名：</span>
            <div class="input"><input type="text" name="loginname"></div>
        </div>
        <div class="field">
            <span class="label">用户状态：</span>
            <div class="input"><input type="text" name="userstatus"></div>
        </div>
        <div class="field">
            <span class="label">密码：</span>
            <div class="input"><input type="text" name="password"></div>
        </div>
        <div class="field">
            <div class="input"><input type="submit" value="添加"/></div>
            <div class="input"><input type="reset" value="取消"/></div>
        </div>
    </form>
</body>
</html>
