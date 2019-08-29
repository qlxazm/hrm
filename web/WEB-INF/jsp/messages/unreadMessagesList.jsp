<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" uri="/pager-tags" %>
<%@ page import="org.java.hrm.util.common.HrmConstants" %>
<%@ taglib prefix="opertation" uri="/operation-test" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/8/28
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>未读消息列表</title>
</head>
<body>
    <div class="data_list">
        <table border="1">
            <tr>
                <th><input type="checkbox" class="selectAll"/></th>
                <th>序号</th>
                <th>发送者</th>
                <th>发送时间</th>
                <th>操作</th>
            </tr>
            <c:set value="1" var="index"/>
            <c:forEach items="${unreadMessages}" var="message">
                <tr>
                    <th>
                        <input type="checkbox" name="category" data-id="${message.id}"/>
                    </th>
                    <th>${index}</th>
                    <th>${message.source}</th>
                    <th>${message.releaseTime}</th>
                    <th>
                        <a href="/message/getMessageById?id=${message.id}">读取</a>
                    </th>
                </tr>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
        </table>
    </div>
</body>
</html>
