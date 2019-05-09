<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/3
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="page" uri="/pager-tags" %>
<%@ taglib prefix="opertation" uri="/operation-test" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.java.hrm.util.common.HrmConstants" %>

<html>
<head>
    <title>用户列表页面</title>
    <link rel="stylesheet" href="/static/css/dataList.css"/>
</head>
<body>
<div class="breadcrumb">当前位置：<span class="nav">用户管理</span><span class="separator">/</span><span class="nav nav_active">用户列表</span></div>
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
    <div class="tool_bar">
        <c:if test="${opertation:operationTest('/user/removeUser?ids=', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
            <div class="field">
                <div class="input"><button class="batchDelete">批量删除</button></div>
            </div>
        </c:if>
    </div>

    <%--内容栏--%>
    <div class="data_list">
        <table border="1">
            <tr>
                <th><input type="checkbox" class="selectAll"/></th>
                <th>序号</th>
                <th>用户名</th>
                <th>登录名</th>
                <th>用户角色</th>
                <th>用户状态</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            <c:set value="1" var="index"/>
            <c:forEach items="${users}" var="user">
                <tr>
                    <th><input type="checkbox" name="category" data-id="${user.id}"/></th>
                    <th>${index}</th>
                    <th>${user.username}</th>
                    <th>${user.loginname}</th>
                    <th>
                        <c:forEach items="${user.roles}" var="role">
                            <span>${role.name}</span>
                        </c:forEach>
                    </th>
                    <th>
                        <c:if test="${user.userstatus == 2}">
                            <span>活跃</span>
                        </c:if>
                        <c:if test="${user.userstatus == 1}">
                            <span>禁用</span>
                        </c:if>
                    </th>
                    <th>
                        <fmt:formatDate type="date" value="${user.createdate}" />
                    </th>
                    <th>
                        <c:if test="${opertation:operationTest('/user/removeUser?ids='.concat(user.id), sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                            <a class="delete" href="/user/removeUser?ids=${user.id}">删除</a>
                        </c:if>
                        <c:if test="${opertation:operationTest('/user/updateUser?id='.concat(user.id), sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                            <a class="update" href="/user/updateUser?id=${user.id}&flag=1">修改</a>
                        </c:if>
                    </th>
                </tr>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
        </table>
    </div>

    <page:pagination pageSize="${pageModel.pageSize}"
                     recordCount="${pageModel.recordCount}"
                     pageIndex="${pageModel.pageIndex}"
                    submitUrl="/user/selectUser?pageIndex={0}"
    />
    <form method="post" action="/user/removeUser" style="display: none;">
        <input type="text" class="delete_ids" name="ids"/>
        <input type="submit" class="delete_btn">
    </form>
</body>
<script src="/static/js/deleteItems.js"></script>
</html>
