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
</head>
<body>
    <h2>这是用户列表页面</h2>
    <table>
        <form method="post" action="/user/selectUser">
            <tr>
                <th><label>登录名：</label></th>
                <th><input type="text" name="loginname"/></th>
            </tr>
            <tr>
                <th><label>用户状态：</label></th>
                <th><input type="text" name="userstatus"/></th>
            </tr>
            <tr>
                <th><label>用户名：</label></th>
                <th><input type="text" name="username"/></th>
            </tr>
            <tr>
                <th><input type="submit" value="查询"/></th>
            </tr>
        </form>
    </table>

    <p>当前页码：${pageModel.pageIndex}</p>
    <p>当前记录数：${pageModel.recordCount}</p>
    <p>当前分页大小：${pageModel.pageSize}</p>
    <p>当前总页数： ${pageModel.totalSize}</p>
    <p>从哪里开始： ${pageModel.firstLimitParam}</p>

    <table border="1">
        <tr>
            <th>用户名</th>
            <th>登录名</th>
            <th>用户状态</th>
            <th>创建时间</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <th>${user.username}</th>
                <th>${user.loginname}</th>
                <th>${user.userstatus}</th>
                <th>${user.createdate}</th>
            </tr>
        </c:forEach>
    </table>

    <page:pagination pageSize="${pageModel.pageSize}"
                     recordCount="${pageModel.recordCount}"
                     pageIndex="${pageModel.pageIndex}"
                    submitUrl="/user/selectUser?pageIndex={0}"
    />
</body>
</html>
