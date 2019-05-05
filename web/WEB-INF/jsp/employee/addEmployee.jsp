<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/5
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增员工</title>
    <link rel="stylesheet" href="/static/css/addOrUpdateCommon.css"/>
    <style>
        .field{display: inline-block}
        .wrapper{width: 500px}
    </style>
</head>
<body>
<form method="post" action="addEmployee?flag=2">
    <p class="message">${message}</p>

   <%-- <div class="field">
        <span class="label">用户名：</span>
        <div class="input"><input type="text" name="username"></div>
    </div>
--%>

<%--还有这两个没填写 Date birthday  Date create_date --%>

    <div class="wrapper">
        <div class="field">
            <span class="label">名称：</span>
            <div class="input"><input type="text" name="name"></div>
        </div>

        <div class="field">
            <span class="label">部门：</span>
            <div class="input">
                <select name="dept_id">
                    <c:forEach items="${depts}" var="dept">
                        <option value="${dept.id}">${dept.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="field">
            <span class="label">岗位：</span>
            <div class="input">
                <select name="job_id">
                    <c:forEach items="${jobs}" var="job">
                        <option value="${job.id}">${job.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="field">
            <span class="label">省份证：</span>
            <div class="input"><input type="text" name="card_id"></div>
        </div>

        <div class="field">
            <span class="label">地址：</span>
            <div class="input"><input type="text" name="address"></div>
        </div>

        <div class="field">
            <span class="label">邮编：</span>
            <div class="input"><input type="text" name="post_code"></div>
        </div>

        <div class="field">
            <span class="label">电话：</span>
            <div class="input"><input type="text" name="tel"></div>
        </div>

        <div class="field">
            <span class="label">手机：</span>
            <div class="input"><input type="text" name="phone"></div>
        </div>

        <div class="field">
            <span class="label">QQ：</span>
            <div class="input"><input type="text" name="QQ_num"></div>
        </div>

        <div class="field">
            <span class="label">邮箱：</span>
            <div class="input"><input type="text" name="email"></div>
        </div>

        <div class="field">
            <span class="label">性别：</span>
            <div class="input">
                <select name="sex">
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
        </div>

        <div class="field">
            <span class="label">党派：</span>
            <div class="input"><input type="text" name="party"></div>
        </div>

        <div class="field">
            <span class="label">民族：</span>
            <div class="input"><input type="text" name="race"></div>
        </div>

        <div class="field">
            <span class="label">学历：</span>
            <div class="input"><input type="text" name="education"></div>
        </div>

        <div class="field">
            <span class="label">特长：</span>
            <div class="input"><input type="text" name="speciality"></div>
        </div>

        <div class="field">
            <span class="label">爱好：</span>
            <div class="input"><input type="text" name="hobby"></div>
        </div>

        <div class="field">
            <span class="label">备注：</span>
            <div class="input"><input type="text" name="remark"></div>
        </div>

        <div class="field" style="display: block">
            <div class="input"><input type="submit" value="添加"/></div>
            <div class="input"><input type="reset" value="取消"/></div>
        </div>
    </div>
</form>
</body>
</html>
