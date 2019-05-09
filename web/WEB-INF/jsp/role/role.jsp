<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="opertation" uri="/operation-test" %>
<%@ taglib prefix="page" uri="/pager-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/8
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
    <link rel="stylesheet" href="/static/css/dataList.css"/>
</head>
<body>
<div class="breadcrumb">当前位置：<span class="nav">角色管理</span><span class="separator">/</span><span class="nav nav_active">角色列表</span></div>

<%--<div class="tool_bar">
        <form method="post" action="/dept/selectDept">
            <div class="field">
                <span class="label">名称：</span>
                <div class="input"><input type="text" name="name"/></div>
            </div>
            <div class="field btn">
                <span class="label"><input type="submit" value="查询"/></span>
                <div class="input"><input type="reset" value="重置"/></div>
            </div>
        </form>
    </div>
    --%>
    <div class="tool_bar">
        <c:if test="${opertation:operationTest('/role/removeRole', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
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
                <th>名称</th>
                <th>操作</th>
            </tr>
            <c:set value="1" var="index"/>
            <c:forEach items="${roles}" var="role">
                <tr>
                    <th>
                        <input type="checkbox" name="category" data-id="${role.id}"/>
                    </th>
                    <th>${index}</th>
                    <th>${role.name}</th>
                    <th>
                        <c:if test="${opertation:operationTest('/role/removeRole?ids=', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                            <a class="delete" href="/role/removeRole?ids=${role.id}">删除</a>
                        </c:if>

                        <c:if test="${opertation:operationTest('/role/updateRole?id=', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                            <a class="update" href="/role/updateRole?id=${role.id}&flag=1">修改</a>
                        </c:if>
                    </th>
                </tr>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
        </table>
    </div>
    <page:pagination
            submitUrl="/role/selectRole?pageIndex={0}"
            pageIndex="${pageModel.pageIndex}"
            pageSize="${pageModel.pageSize}"
            recordCount="${pageModel.recordCount}"/>
    <form method="post" action="/dept/removeDept" style="display: none;">
        <input type="text" class="delete_ids" name="ids"/>
        <input type="submit" class="delete_btn">
    </form>
</body>
</html>
