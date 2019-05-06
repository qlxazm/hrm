<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/5
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新用户信息</title>
    <link rel="stylesheet" href="/static/css/addOrUpdateCommon.css"/>
</head>
<body>
<h2>用户更新</h2>
<c:if test="${message.indexOf(\"成功\") >= 0}">
    <p class="success">${message}</p>
</c:if>
<c:if test="${message.indexOf(\"失败\") >= 0}">
    <p class="error">${message}</p>
</c:if>

<form:form modelAttribute="user" method="post" action="updateUser?flag=2">
    <form:hidden path="id"/>
    <div class="field">
        <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;用户名：</span>
        <div class="input"><form:input path="username"/></div>
        <form:errors cssClass="message" path="username"/>
    </div>
    <div class="field">
        <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;登录名：</span>
        <div class="input"><form:input path="loginname"/></div>
        <form:errors cssClass="message" path="loginname"/>
    </div>
    <div class="field">
        <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;用户状态：</span>
        <div class="input">
            <form:select path="userstatus">
                <form:option value="2">活跃</form:option>
                <form:option value="1">禁用</form:option>
            </form:select>
        </div>
        <form:errors cssClass="message" path="userstatus"/>
    </div>
    <div class="field">
        <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;密码：</span>
        <div class="input"><form:input path="password" /></div>
        <form:errors cssClass="message" path="password"/>
    </div>
    <div class="field">
        <div class="input"><input type="submit" value="保存"/></div>
        <div class="input"><input type="reset" value="取消"/></div>
    </div>
</form:form>

</body>
</html>
