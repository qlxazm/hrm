<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/5
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加部门</title>
    <link rel="stylesheet" href="/static/css/addOrUpdateCommon.css"/>
</head>
<body>
<div class="breadcrumb">当前位置：<span class="nav">部门管理</span><span class="separator">/</span><span class="nav nav_active">添加部门</span></div>

<c:if test="${message.indexOf(\"成功\") >= 0}">
    <p class="success">${message}</p>
</c:if>
<c:if test="${message.indexOf(\"失败\") >= 0}">
    <p class="error">${message}</p>
</c:if>

<form:form modelAttribute="dept" method="post" action="addDept?flag=2">
    <div class="field">
        <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;名称：</span>
        <div class="input"><form:input path="name"/></div>
        <form:errors path="name" cssClass="message"/>
    </div>
    <div class="field">
        <span class="label">描述：</span>
        <div class="input"><form:input path="remark"/></div>
    </div>
    <div class="field">
        <div class="input"><input type="submit" value="添加"/></div>
        <div class="input"><input type="reset" value="取消"/></div>
    </div>
</form:form>
</body>
</html>
