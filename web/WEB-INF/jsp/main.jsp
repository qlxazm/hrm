<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="org.java.hrm.util.common.HrmConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
    <div class="header" style="border: 1px solid red;">
        <h3>当前用户是：[${sessionScope[HrmConstants.USER_SESSION].username}]</h3>
    </div>
    <div>
        <div class="sideBar" style="border: 1px solid rebeccapurple;display: inline-block;float: left;">
            <ul>
                <li><a href="/user/selectUser">用户查询</a></li>
                <li><a href="/user/addUser">添加用户</a></li>
            </ul>
        </div>
        <div class="content" style="border: 1px solid red;display: inline-block">
            <c:import url="${page}"/>
        </div>
    </div>
</body>
</html>
