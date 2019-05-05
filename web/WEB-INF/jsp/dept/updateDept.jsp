<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/5
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新部门</title>
    <link rel="stylesheet" href="/static/css/addOrUpdateCommon.css"/>
</head>
<body>
<form method="post" action="updateDept?flag=2">
    <p class="message">${message}</p>
    <input type="text" style="display: none" name="id" value="${dept.id}"/>
    <div class="field">
        <span class="label">名称：</span>
        <div class="input"><input type="text" name="name" value="${dept.name}"/></div>
    </div>
    <div class="field">
        <span class="label">描述：</span>
        <div class="input"><input type="text" name="remark" value="${dept.remark}"/></div>
    </div>
    <div class="field">
        <div class="input"><input type="submit" value="保存"/></div>
        <div class="input"><input type="reset" value="取消"/></div>
    </div>
</form>
</body>
</html>
