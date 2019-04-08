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
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">左侧菜单</div>
                <div class="layui-card-body layui-form">
                    <div class="eleTree" id="menu-eleTree-test" lay-filter="menu-eleTree-test"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">角色列表</div>
                <div class="layui-card-body">
                    <table id="user-role-list" lay-filter="user-role-list"></table>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">已分配权限</div>
                <div class="layui-card-body" style="height:400px;overflow-x:hidden;">
                    <div class="eleTree" id="menufunc-eleTree-test" lay-filter="menufunc-eleTree-test"></div>
                </div>
            </div>
        </div>
    </div>
</div>
    <link rel="stylesheet" href="../../../../static/layui/css/eleTree.css">
<script>
    layui.config({
        base: "../../../../static/layui/lay/extModules/"
    }).use(["jquery", "eleTree"], function () {
        var $ = layui.$;
        var eleTree = layui.eleTree;
        var el1 = eleTree.render({
            elem: '#menu-eleTree-test',
           // showCheckbox: true,
            //checkOnClickNode: true,
            expandOnClickNode: true,
            url: "/auth/getUserAuthMenuTree",
            done: function (res) {
               el1.reload({ data: res.data });
            }
        });

        var el2 = eleTree.render({
            elem: '#menufunc-eleTree-test',
            showCheckbox: true,
            checkOnClickNode: false,
            expandOnClickNode: true,
            defaultExpandAll: true,
            url: "/auth/getUserAuthMenuFuncTree",
            done: function (res) {
                el2.reload({ data: res.data });
            }
        });
        })

    layui.use(['table', 'form'], function () {
        var table = layui.table;
        form = layui.form;

        table.render({
            elem: '#user-role-list'
            , url: '/auth/getUserRoles' //数据接口
            , page: false //开启分页
            , cols: [[ //表头
                { field: 'name', title: '角色名', width: 130 }
                , { field: 'code', title: '角色编码', width: 150 }
            ]]
        });
    });
</script>




</body>
</html>