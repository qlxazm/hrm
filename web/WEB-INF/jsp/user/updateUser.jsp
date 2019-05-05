<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/5
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新用户信息</title>
    <link rel="stylesheet" href="/static/css/addUser.css"/>
</head>
<body>
<form method="post" action="updateUser?flag=2">
    <input type="text" name="id" value="${user.id}" style="display: none"/>
    <p class="message">${message}</p>
    <div class="field">
        <span class="label">用户名：</span>
        <div class="input"><input type="text" name="username" value="${user.username}"/></div>
    </div>
    <div class="field">
        <span class="label">登录名：</span>
        <div class="input"><input type="text" name="loginname" value="${user.loginname}"/></div>
    </div>
    <div class="field">
        <span class="label">用户状态：</span>
        <div class="input"><input type="text" name="userstatus" value="${user.userstatus}" /></div>
    </div>
    <div class="field">
        <span class="label">密码：</span>
        <div class="input"><input type="text" name="password" value="${user.password}" /></div>
    </div>
    <div class="field">
        <span class="label"></span>
        <div class="input"><input type="submit" value="保存"/></div>
    </div>
</form>
</body>
</html>
