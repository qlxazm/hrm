<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/9
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新角色信息</title>
    <link rel="stylesheet" href="/static/css/addOrUpdateCommon.css"/>
</head>
<body>
<h2>更新角色信息</h2>
    <c:if test="${message.indexOf(\"成功\") >= 0}">
        <p class="success">${message}</p>
    </c:if>
    <c:if test="${message.indexOf(\"失败\") >= 0}">
        <p class="error">${message}</p>
    </c:if>
    <form:form modelAttribute="role" method="post" action="updateRole?flag=2">
        <form:hidden path="id"/>
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
            <div class="input"><input type="submit" value="保存"/></div>
            <div class="input"><input type="reset" value="取消"/></div>
        </div>
    </form:form>
</body>
</html>
