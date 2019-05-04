<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/3
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="page" uri="/pager-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表页面</title>
    <link rel="stylesheet" href="/static/css/user.css"/>
    <link rel="stylesheet" href="/static/css/pagination.css"/>
</head>
<body>
    <h2>用户列表</h2>

    <%--工具栏--%>
    <div class="tool_bar">
        <form method="post" action="/user/selectUser">
            <div class="field">
                <span class="label">登录名：</span>
                <div class="input"><input type="text" name="loginname"/></div>
            </div>
            <div class="field">
                <span class="label">用户状态：</span>
                <div class="input"><input type="text" name="userstatus"/></div>
            </div>
            <div class="field">
                <span class="label">用户名：</span>
                <div class="input"><input type="text" name="username"/></div>
            </div>
            <div class="field btn">
                <span class="label"><input type="submit" value="查询"/></span>
                <div class="input"><input type="reset" value="重置"/></div>
            </div>
        </form>
    </div>

    <%--内容栏--%>
    <div class="data_list">
        <table border="1">
            <tr>
                <th>用户名</th>
                <th>登录名</th>
                <th>用户状态</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <th>${user.username}</th>
                    <th>${user.loginname}</th>
                    <th>${user.userstatus}</th>
                    <th>${user.createdate}</th>
                    <th>
                        <a class="delete" href="/user/deleteUser?id=${user.id}">删除</a>
                        <a class="update" href="/user/updateUser?id=${user.id}">修改</a>
                    </th>
                </tr>
            </c:forEach>
        </table>
    </div>

    <page:pagination pageSize="${pageModel.pageSize}"
                     recordCount="${pageModel.recordCount}"
                     pageIndex="${pageModel.pageIndex}"
                    submitUrl="/user/selectUser?pageIndex={0}"
    />
</body>
</html>
