<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="org.java.hrm.util.common.HrmConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="opertation" uri="/operation-test" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="/static/css/neat.css"/>
    <link rel="stylesheet" href="/static/css/common.css"/>
    <link rel="stylesheet" href="/static/css/main.css"/>
    <link rel="stylesheet" href="/static/css/menu.css"/>
    <link rel="stylesheet" href="/static/css/pagination.css"/>
    <link rel="stylesheet" href="/static/css/breadcrumb.css"/>
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/menu.js"></script>
    <script src="/static/js/sockjs.min.js"></script>
    <script src="/static/js/stomp.min.js"></script>
    <script src="/static/js/stompMessage.js"></script>
</head>
<body>
    <div class="header">
       <%-- <h3>当前用户是：[${sessionScope[HrmConstants.USER_SESSION].username}]</h3>--%>
        <h3>使用spring security后显示的当前用户是：
            <security:authentication property="principal.username"/>
        </h3>
           <a href="/sendMessage?message=这是一条发往所有用户的消息">开始向所有用户发送消息</a>
           <a href="/sendMessageToManager?message=这是给管理员的一条信息">开始向管理员发送消息</a>
           <a href="/sendMessageToUser?message=这是给普通用户的一条信息">开始向用户发送消息</a>
        <a href="/logout" style="font-size: 1.4em">退出</a>
    </div>
    <div class="main_wrapper">
        <div class="sideBar">
            <ul class="menu">
                <li><a href="javascript:void(0)">用户管理</a></li>
                <li>
                    <ul class="sub_menu">
                        <li><a href="/user/selectUser">用户查询</a></li>
                        <li>
                            <security:authorize url="/user/addUser" >
                                <a href="/user/addUser?flag=1">添加用户</a>
                            </security:authorize>
<%--
                            <c:if test="${opertation:operationTest('/user/addUser?flag=1', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/user/addUser?flag=1">添加用户</a>
                            </c:if>--%>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="menu">
                <li><a href="javascript:void(0)">角色管理</a></li>
                <li>
                    <ul class="sub_menu">
                        <li>
                            <a href="/role/selectRole">角色查询</a>

                          <%--  <c:if test="${opertation:operationTest('/role/selectRole', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/role/selectRole">角色查询</a>
                            </c:if>--%>
                        </li>
                        <li>
                            <security:authorize url="/role/addRole" >
                                <a href="/role/addRole?flag=1">添加角色</a>
                            </security:authorize>


                         <%--   <c:if test="${opertation:operationTest('/role/addRole?flag=1', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/role/addRole?flag=1">添加角色</a>
                            </c:if>--%>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="menu">
                <li><a href="javascript:void(0)">部门管理</a></li>
                <li>
                    <ul class="sub_menu">
                        <li><a href="/dept/selectDept">部门查询</a></li>
                        <li>
                            <security:authorize url="/dept/addDept">
                                <a href="/dept/addDept?flag=1">添加部门</a>
                            </security:authorize>
                          <%--  <c:if test="${opertation:operationTest('/dept/addDept?flag=1', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/dept/addDept?flag=1">添加部门</a>
                            </c:if>--%>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="menu">
                <li><a href="javascript:void(0)">员工管理</a></li>
                <li>
                    <ul class="sub_menu">
                        <li><a href="/employee/selectEmployee">员工查询</a></li>
                        <li>
                            <security:authorize url="/employee/addEmployee">
                                <a href="/employee/addEmployee?flag=1">添加员工</a>
                            </security:authorize>
                          <%--  <c:if test="${opertation:operationTest('/employee/addEmployee?flag=1', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/employee/addEmployee?flag=1">添加员工</a>
                            </c:if>--%>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="content">
            <c:import url="${page}"/>
        </div>
    </div>
</body>
</html>
