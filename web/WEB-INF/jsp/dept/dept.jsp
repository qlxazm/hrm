<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" uri="/pager-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/5
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
    <link rel="stylesheet" href="/static/css/dataList.css"/>
</head>
<body>
    <h2>部门列表</h2>
    <%--工具栏--%>
    <div class="tool_bar">
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
    <div class="tool_bar">
        <div class="field">
            <div class="input"><button class="batchDelete">批量删除</button></div>
        </div>
    </div>
    <%--内容栏--%>
    <div class="data_list">
        <table border="1">
            <tr>
                <th><input type="checkbox" class="selectAll"/></th>
                <th>序号</th>
                <th>名称</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            <c:set value="1" var="index"/>
            <c:forEach items="${depts}" var="dept">
                <tr>
                    <th>
                        <c:if test="${dept.employeeNum > 0}">
                            <input type="checkbox" disabled name="category" data-id="${dept.id}"/>
                        </c:if>
                        <c:if test="${dept.employeeNum == 0}">
                            <input type="checkbox" name="category" data-id="${dept.id}"/>
                        </c:if>
                    </th>
                    <th>${index}</th>
                    <th>${dept.name}</th>
                    <th>${dept.remark}</th>
                    <th>
                        <c:if test="${dept.employeeNum > 0}">
                            <a class="undelete" href="#">不可删除</a>
                        </c:if>
                        <c:if test="${dept.employeeNum == 0}">
                            <a class="delete" href="/dept/removeDept?ids=${dept.id}">删除</a>
                        </c:if>
                        <a class="update" href="?id=${dept.id}&flag=1">修改</a>
                    </th>
                </tr>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
        </table>
    </div>
    <page:pagination
            submitUrl="/dept/selectDept?pageIndex={0}"
            pageIndex="${pageModel.pageIndex}"
            pageSize="${pageModel.pageSize}"
            recordCount="${pageModel.recordCount}"/>
    <form method="post" action="/dept/removeDept" style="display: none;">
        <input type="text" class="delete_ids" name="ids"/>
        <input type="submit" class="delete_btn">
    </form>
</body>
<script src="/static/js/deleteItems.js"></script>
</html>
