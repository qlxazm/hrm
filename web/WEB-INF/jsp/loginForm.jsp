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
</head>
<body>
<table>
    <form action="login" method="post">
        <tr>
            <th><label>用户名</label></th>
            <th><input type="text" name="loginname"/></th>
        </tr>
        <tr>
            <th><label>密码</label></th>
            <th><input type="password" name="password"></th>
        </tr>
        <tr>
            <th><input type="submit" value="登录"/></th>
        </tr>
    </form>
</table>
</body>
</html>
