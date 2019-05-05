<%--
  Created by IntelliJ IDEA.
  User: qlxazm
  Date: 2019/5/3
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="page" uri="/pager-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户列表页面</title>
    <link rel="stylesheet" href="/static/css/user.css"/>
</head>
<body>
    <h2>用户列表</h2>

    <%--工具栏--%>
    <div class="tool_bar">
        <form method="post" action="/user/selectUser">
            <div class="field">
                <span class="label">登录名：</span>
                <div class="input"><input type="text" name="loginname"/></div>
            </div>
            <div class="field">
                <span class="label">用户状态：</span>
                <div class="input"><input type="text" name="userstatus"/></div>
            </div>
            <div class="field">
                <span class="label">用户名：</span>
                <div class="input"><input type="text" name="username"/></div>
            </div>
            <div class="field btn">
                <span class="label"><input type="submit" value="查询"/></span>
                <div class="input"><input type="reset" value="重置"/></div>
            </div>
        </form>
    </div>
    <div class="tool_bar">
        <div class="field">
            <div class="input"><button class="batchDelete">批量删除</button></div>
        </div>
    </div>

    <%--内容栏--%>
    <div class="data_list">
        <table border="1">
            <tr>
                <th><input type="checkbox" class="selectAll"/></th>
                <th>序号</th>
                <th>用户名</th>
                <th>登录名</th>
                <th>用户状态</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            <c:set value="1" var="index"/>
            <c:forEach items="${users}" var="user">
                <tr>
                    <th><input type="checkbox" name="category" data-id="${user.id}"/></th>
                    <th>${index}</th>
                    <th>${user.username}</th>
                    <th>${user.loginname}</th>
                    <th>
                        <c:if test="${user.userstatus == 2}">
                            <span>活跃</span>
                        </c:if>
                        <c:if test="${user.userstatus == 1}">
                            <span>禁用</span>
                        </c:if>
                    </th>
                    <th>
                        <fmt:formatDate type="both" value="${user.createdate}" dateStyle="medium" timeStyle="medium"/>
                    </th>
                    <th>
                        <a class="delete" href="/user/removeUser?ids=${user.id}">删除</a>
                        <a class="update" href="/user/updateUser?id=${user.id}&flag=1">修改</a>
                    </th>
                </tr>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
        </table>
    </div>

    <page:pagination pageSize="${pageModel.pageSize}"
                     recordCount="${pageModel.recordCount}"
                     pageIndex="${pageModel.pageIndex}"
                    submitUrl="/user/selectUser?pageIndex={0}"
    />
    <form method="post" action="/user/removeUser" style="display: none;">
        <input type="text" class="delete_ids" name="ids"/>
        <input type="submit" class="delete_user_btn">
    </form>
</body>
<script>
    $(function(){
        var selectAllCheckbox = $('.selectAll');       //全选
        var allCheckbox = $("input[name='category']"); //每条记录的选择框
        var selectCheckboxItems = Object.create(null); //选中的选择框
        var batchDelete = $('.batchDelete');           //批量删除
        var deleteIdsInput = $('.delete_ids');
        var deleteUserBtn = $('.delete_user_btn');     //隐藏的删除按钮
        var deleteUser = $('.delete');                 //删除单行用户


        selectAllCheckbox.change(function(event){
            var ischecked = this.checked;
            if (!selectCheckboxItems) {
                selectCheckboxItems = Object.create(null);
            }
            for (var i = 0, len = allCheckbox.length; i < len; i++){
                var id = $(allCheckbox[i]).attr('data-id');
                allCheckbox[i].checked = ischecked;
                selectCheckboxItems[id] = ischecked;
            }
        });

        allCheckbox.on('change', function (event) {
            var id = $(this).attr('data-id');
            if (!selectCheckboxItems) {
                selectCheckboxItems = Object.create(null)
            }
            selectCheckboxItems[id] = this.checked;
        });

        batchDelete.click(function (){
            var deleteIds = [];
            for(var key of Object.keys(selectCheckboxItems)) {
                if (selectCheckboxItems[key]) {
                    deleteIds.push(key);
                }
            }
            deleteIds = deleteIds.join(',');
            deleteIdsInput.val(deleteIds);
            /** 提交要删除的id **/
            deleteUserBtn.click();
        });

    });
</script>
</html>
