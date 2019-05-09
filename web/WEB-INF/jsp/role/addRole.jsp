<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/9
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加角色</title>
    <link rel="stylesheet" href="/static/css/addOrUpdateCommon.css"/>
</head>
<body>
    <h2>添加新的角色</h2>
    <c:if test="${message.indexOf(\"成功\") >= 0}">
        <p class="success">${message}</p>
    </c:if>
    <c:if test="${message.indexOf(\"失败\") >= 0}">
        <p class="error">${message}</p>
    </c:if>
    <form:form modelAttribute="role" method="post" action="addRole?flag=2">
        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;名称：</span>
            <div class="input"><form:input path="name"/></div>
            <form:errors path="name" cssClass="message"/>
        </div>
        <div class="field">
            <span class="label">禁止的权限：</span>
            <div class="input">
                <form:checkboxes items="${permissions}" itemValue="id" itemLabel="remark" path="permissionIds"></form:checkboxes>
            </div>
        </div>
        <div class="field">
            <div class="input"><input type="submit" value="添加"/></div>
            <div class="input"><input type="reset" value="取消"/></div>
        </div>
    </form:form>
</body>
</html>
