<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="org.java.hrm.util.common.HrmConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h3>当前用户是：[${sessionScope[HrmConstants.USER_SESSION].username}]</h3>

<a href="#">用户查询</a>
<a href="#">添加用户</a>
</body>
</html>
