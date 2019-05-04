<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="org.java.hrm.util.common.HrmConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="/static/css/neat.css"/>
    <link rel="stylesheet" href="/static/css/common.css"/>
    <link rel="stylesheet" href="/static/css/main.css"/>
</head>
<body>
    <div class="header">
        <h3>当前用户是：[${sessionScope[HrmConstants.USER_SESSION].username}]</h3>
    </div>
    <div>
        <div class="sideBar">
            <ul>
                <li><a href="/user/selectUser">用户查询</a></li>
                <li><a href="/user/addUser?flag=1">添加用户</a></li>
            </ul>
        </div>
        <div class="content">
            <c:import url="${page}"/>
        </div>
    </div>
</body>
</html>
