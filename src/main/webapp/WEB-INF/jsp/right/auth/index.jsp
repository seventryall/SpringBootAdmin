<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../home/header.jsp"%>
</head>
<body class="layui-layout-body">
<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md2">
            <div class="layui-card" style="overflow:auto; height:400px;">
                <div class="layui-card-header">用户</div>
                <div class="layui-card-body layui-form">
                    <table border="0">
                        <c:forEach var="item" items="${userList}">
                                <tr>
                                    <td><input type="radio" value="${item.ID}" title="${item.realName}" name="user"></td>
                                </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card" style="overflow:auto; height:400px;">
                <div class="layui-card-header">角色</div>
                <div class="layui-card-body">
                    <table id="auth-role-list" lay-filter="auth-role-list"></table>
                    <script type="text/html" id="auth-role-toolbar">
                        <a class="layui-btn layui-btn-xs" lay-event="auth">分配权限</a>
                    </script>
                </div>
                <div class="" style="text-align:right;">
                    <a class="layui-btn layui-btn-normal layui-btn-sm">保存</a>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card" style="overflow:auto; height:400px;">
                <div class="layui-card-header">权限</div>
                <div class="layui-card-body">
                    <div class="" id="menu-func-eleTree" lay-filter="menu-func-eleTree"></div>
                </div>
                <div class="" style="text-align:right;">
                    <a class="layui-btn layui-btn-normal layui-btn-disabled layui-btn-sm">保存</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="text-align:center;">
    <a class="layui-btn layui-btn-normal">提交</a>

</div>
    <link rel="stylesheet" href="../../../../static/layui/css/eleTree.css">

<script>
    layui.config({
        base: "/static/layui/lay/extModules/"
    }).use(['table', 'layer', 'form', 'eleTree'], function () {
        var table = layui.table;
        layer = layui.layer;
        form = layui.form;
        eleTree = layui.eleTree;
        table.render({
            elem: '#auth-role-list'
            , url: '/role/getRoleList' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                { checkbox: true },
                { field: 'id', title: 'id', width: 120, hide: true, fixed: 'left' }
                , { field: 'name', title: '角色名', width: 100 }
                , { field: 'code', title: '角色编码', width: 100 }
                , { fixed: 'right', title: "操作", align: 'center', toolbar: '#auth-role-toolbar' }
            ]]
        });
        var el = eleTree.render({
            elem: '#menu-func-eleTree',
            showCheckbox: true,
            checkOnClickNode: false,
            expandOnClickNode: true,
            //defaultExpandAll: true,
            url: "/auth/getAllMenuFuncTree",
            done: function (res) {
                el.reload({ data: res.data });
            }
        });
    });
</script>




</body>
</html>