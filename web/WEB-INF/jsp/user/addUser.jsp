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
</head>
<body>
    <form method="post" action="user/addUser">
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
            <span class="label"></span>
            <div class="input"><input type="submit" value="添加"/></div>
        </div>
    </form>
</body>
</html>
