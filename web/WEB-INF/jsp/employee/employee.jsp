<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/5
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="page" uri="/pager-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.java.hrm.util.common.HrmConstants" %>
<%@ taglib prefix="opertation" uri="/operation-test" %>



<html>
<head>
    <title>员工页面</title>
    <link rel="stylesheet" href="/static/css/dataList.css"/>
</head>
<body>
<div class="breadcrumb">当前位置：<span class="nav">员工管理</span><span class="separator">/</span><span class="nav nav_active">员工列表</span></div>

<%-- 工具栏 --%>
    <div class="tool_bar">
        <form method="post" action="/employee/selectEmployee">
            <div class="field">
                <span class="label">职位：</span>
                <div class="input">
                    <select name="job_id">
                        <option value ="">全部</option>
                        <c:forEach items="${jobs}" var="job">
                            <option value ="${job.id}">${job.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="field">
                <span class="label">部门：</span>
                <div class="input">
                    <select name="dept_id">
                        <option value ="">全部</option>
                        <c:forEach items="${depts}" var="dept">
                            <option value ="${dept.id}">${dept.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="field btn">
                <span class="label"><input type="submit" value="查询"/></span>
                <div class="input"><input type="reset" value="重置"/></div>
            </div>
        </form>
    </div>
    <div class="tool_bar">
        <c:if test="${opertation:operationTest('/employee/removeEmployee?ids=', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
            <div class="field">
                <div class="input"><button class="batchDelete">批量删除</button></div>
            </div>
        </c:if>
    </div>
    <%-- 展示的数据 --%>
    <div class="data_list">
        <table border="1">
            <tr>
                <th><input type="checkbox" class="selectAll"/></th>
                <th>序号</th>
                <th>部门</th>
                <th>职位</th>
                <th>姓名</th>
                <th>身份证</th>
                <th>地址</th>
                <th>邮编</th>
                <th>电话</th>
                <th>手机</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>性别</th>
                <th>党派</th>
                <th>生日</th>
                <th>民族</th>
                <th>教育背景</th>
                <th>特长</th>
                <th>爱好</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            <c:set value="1" var="index"/>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <th><input type="checkbox" name="category" data-id="${employee.id}"/></th>
                    <th>${index}</th>
                    <th>
                        <c:forEach items="${depts}" var="dept">
                            <c:if test="${dept.id == employee.dept_id}">
                                ${dept.name}
                            </c:if>
                        </c:forEach>
                    </th>
                    <th>
                        <c:forEach items="${jobs}" var="job">
                            <c:if test="${job.id == employee.job_id}">
                                ${job.name}
                            </c:if>
                        </c:forEach>
                    </th>
                    <th>${employee.name}</th>
                    <th>${employee.card_id}</th>
                    <th>${employee.address}</th>
                    <th>${employee.post_code}</th>
                    <th>${employee.tel}</th>
                    <th>${employee.phone}</th>
                    <th>${employee.QQ_num}</th>
                    <th>${employee.email}</th>
                    <th>${employee.sex}</th>
                    <th>${employee.party}</th>
                    <th>
                            <%--${employee.birthday}--%>
                       <fmt:formatDate type="date" value="${employee.birthday}" />
                    </th>
                    <th>${employee.race}</th>
                    <th>${employee.education}</th>
                    <th>${employee.speciality}</th>
                    <th>${employee.hobby}</th>
                    <th>${employee.remark}</th>

                    <th>
                        <c:if test="${opertation:operationTest('/employee/removeEmployee?ids=', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                            <a class="delete" href="/employee/removeEmployee?ids=${employee.id}">删除</a>
                        </c:if>
                        <c:if test="${opertation:operationTest('/employee/updateEmployee?id=', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                            <a class="update" href="/employee/updateEmployee?id=${employee.id}&flag=1">修改</a>
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
                     submitUrl="/employee/selectEmployee?pageIndex={0}"
    />
    <form method="post" action="/employee/removeEmployee" style="display: none;">
        <input type="text" class="delete_ids" name="ids"/>
        <input type="submit" class="delete_btn">
    </form>
</body>
<script src="/static/js/deleteItems.js"></script>
</html>
