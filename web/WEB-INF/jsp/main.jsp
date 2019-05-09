<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="org.java.hrm.util.common.HrmConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="opertation" uri="/operation-test" %>

<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="/static/css/neat.css"/>
    <link rel="stylesheet" href="/static/css/common.css"/>
    <link rel="stylesheet" href="/static/css/main.css"/>
    <link rel="stylesheet" href="/static/css/menu.css"/>
    <link rel="stylesheet" href="/static/css/pagination.css"/>
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/menu.js"></script>
</head>
<body>
    <div class="header">
        <h3>当前用户是：[${sessionScope[HrmConstants.USER_SESSION].username}]</h3>
        <a href="/logout" style="font-size: 1.4em">注销</a>
    </div>
    <div>
        <div class="sideBar">
            <ul class="menu">
                <li><a href="javascript:void(0)">用户管理</a></li>
                <li>
                    <ul class="sub_menu">
                        <li><a href="/user/selectUser">用户查询</a></li>
                        <li>
                            <c:if test="${opertation:operationTest('/user/addUser?flag=1', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/user/addUser?flag=1">添加用户</a>
                            </c:if>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="menu">
                <li><a href="javascript:void(0)">角色管理</a></li>
                <li>
                    <ul class="sub_menu">
                        <li>
                            <c:if test="${opertation:operationTest('/role/selectRole', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/role/selectRole">角色查询</a>
                            </c:if>
                        </li>
                        <li>
                            <c:if test="${opertation:operationTest('/role/addRole?flag=1', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/role/addRole?flag=1">添加角色</a>
                            </c:if>
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
                            <c:if test="${opertation:operationTest('/dept/addDept?flag=1', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/dept/addDept?flag=1">添加部门</a>
                            </c:if>
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
                            <c:if test="${opertation:operationTest('/employee/addEmployee?flag=1', sessionScope.get(HrmConstants.USER_OPERATION_SESSION))}">
                                <a href="/employee/addEmployee?flag=1">添加员工</a>
                            </c:if>
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
