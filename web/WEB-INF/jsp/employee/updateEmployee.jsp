<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/5
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新员工信息</title>
    <link rel="stylesheet" href="/static/css/addOrUpdateCommon.css"/>
    <style>
        .field{display: inline-block}
        .wrapper{width: 500px}
    </style>
</head>
<body>
<h2>更新用户信息</h2>
<%--还有这两个没填写Date create_date --%>
<c:if test="${message.indexOf(\"成功\") >= 0}">
    <p class="success">${message}</p>
</c:if>
<c:if test="${message.indexOf(\"失败\") >= 0}">
    <p class="error">${message}</p>
</c:if>

<form:form modelAttribute="employee" method="post" action="updateEmployee?flag=2">
    <form:hidden path="id"/>
    <div class="wrapper">
        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;姓名：</span>
            <div class="input"><form:input path="name"/></div>
            <form:errors path="name" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;部门：</span>
            <div class="input">
                <form:select path="dept_id">
                    <form:options items="${depts}" itemLabel="name" itemValue="id"/>
                </form:select>
            </div>
            <form:errors path="dept_id" cssClass="message"/>
        </div>
        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;岗位：</span>
            <div class="input">
                <form:select path="job_id">
                    <form:options items="${jobs}" itemLabel="name" itemValue="id"/>
                </form:select>
            </div>
            <form:errors path="job_id" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;身份证：</span>
            <div class="input"><form:input path="card_id"/></div>
            <form:errors path="card_id" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label">生日：</span>
            <div class="input"><form:input path="birthday"/></div>
            <form:errors path="birthday" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;地址：</span>
            <div class="input"><form:input path="address"/></div>
            <form:errors path="address" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label">邮编：</span>
            <div class="input"><form:input path="post_code"/></div>
            <form:errors path="post_code" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label">电话：</span>
            <div class="input"><form:input path="tel"/></div>
        </div>

        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;手机：</span>
            <div class="input"><form:input path="phone"/></div>
            <form:errors path="phone" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label">QQ：</span>
            <div class="input"><form:input path="QQ_num"/></div>
            <form:errors path="QQ_num" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;邮箱：</span>
            <div class="input"><form:input path="email"/></div>
            <form:errors path="email" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label"><i style="color: red">*</i>&nbsp;&nbsp;&nbsp;性别：</span>
            <div class="input">
                <form:select path="sex">
                    <form:option value="1">男</form:option>
                    <form:option value="2">女</form:option>
                </form:select>
            </div>
            <form:errors path="sex" cssClass="message"/>
        </div>

        <div class="field">
            <span class="label">党派：</span>
            <div class="input"><form:input path="party"/></div>
        </div>

        <div class="field">
            <span class="label">民族：</span>
            <div class="input"><form:input path="race"/></div>
        </div>

        <div class="field">
            <span class="label">学历：</span>
            <div class="input">
                <form:input path="education"/>
            </div>
        </div>

        <div class="field">
            <span class="label">特长：</span>
            <div class="input"><form:input path="speciality"/></div>
        </div>

        <div class="field">
            <span class="label">爱好：</span>
            <div class="input"><form:input path="hobby"/></div>
        </div>

        <div class="field">
            <span class="label">备注：</span>
            <div class="input"><form:input path="remark"/></div>
        </div>

        <div class="field" style="display: block">
            <div class="input"><input type="submit" value="保存"/></div>
            <div class="input"><input type="reset" value="取消"/></div>
        </div>
    </div>
</form:form>

<%--<form method="post" action="updateEmployee?flag=2">


    <div class="wrapper">
        <input style="display: none" name="id" value="${employee.id}"/>
        <div class="field">
            <span class="label">名称：</span>
            <div class="input"><input type="text" name="name" value="${employee.name}"/></div>
        </div>

        <div class="field">
            <span class="label">部门：</span>
            <div class="input">
                <select name="dept_id" value="${employee.dept_id}">
                    <c:forEach items="${depts}" var="dept">
                        <option value="${dept.id}">${dept.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="field">
            <span class="label">岗位：</span>
            <div class="input">
                <select name="job_id" value="${employee.job_id}">
                    <c:forEach items="${jobs}" var="job">
                        <option value="${job.id}">${job.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="field">
            <span class="label">省份证：</span>
            <div class="input"><input type="text" name="card_id" value="${employee.card_id}"/></div>
        </div>

        <div class="field">
            <span class="label">地址：</span>
            <div class="input"><input type="text" name="address" value="${employee.address}"/></div>
        </div>

        <div class="field">
            <span class="label">邮编：</span>
            <div class="input"><input type="text" name="post_code" value="${employee.post_code}"/></div>
        </div>

        <div class="field">
            <span class="label">电话：</span>
            <div class="input"><input type="text" name="tel" value="${employee.tel}"/></div>
        </div>

        <div class="field">
            <span class="label">手机：</span>
            <div class="input"><input type="text" name="phone" value="${employee.phone}"/></div>
        </div>

        <div class="field">
            <span class="label">QQ：</span>
            <div class="input"><input type="text" name="QQ_num" value="${employee.QQ_num}"/></div>
        </div>

        <div class="field">
            <span class="label">邮箱：</span>
            <div class="input"><input type="text" name="email" value="${employee.email}"/></div>
        </div>

        <div class="field">
            <span class="label">性别：</span>
            <div class="input">
                <select name="sex" value="${employee.sex}">
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
        </div>

        <div class="field">
            <span class="label">党派：</span>
            <div class="input"><input type="text" name="party" value="${employee.party}"></div>
        </div>

        <div class="field">
            <span class="label">民族：</span>
            <div class="input"><input type="text" name="race" value="${employee.race}"></div>
        </div>

        <div class="field">
            <span class="label">学历：</span>
            <div class="input"><input type="text" name="education" value="${employee.education}"></div>
        </div>

        <div class="field">
            <span class="label">特长：</span>
            <div class="input"><input type="text" name="speciality" value="${employee.speciality}"></div>
        </div>

        <div class="field">
            <span class="label">爱好：</span>
            <div class="input"><input type="text" name="hobby" value="${employee.hobby}"></div>
        </div>

        <div class="field">
            <span class="label">备注：</span>
            <div class="input"><input type="text" name="remark" value="${employee.remark}"></div>
        </div>

        <div class="field" style="display: block">
            <div class="input"><input type="submit" value="保存"/></div>
            <div class="input"><input type="reset" value="取消"/></div>
        </div>
    </div>
</form>--%>


</body>
</html>
