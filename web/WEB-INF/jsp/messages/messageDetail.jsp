<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" uri="/pager-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/8/29
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>消息详情</title>
</head>
<body>
    <span>发送人：${message.source}</span><br/>
    <span>发送时间：${message.releaseTime}</span><br/>
    <span>消息内容：${message.content}</span>
</body>
</html>
