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
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">管理员</div>
                <div class="layui-card-body layui-form">
                    <a class="layui-btn layui-btn-normal" id="createUser">新增用户</a>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">普通用户</div>
                <div class="layui-card-body">
                    <a class="layui-btn layui-btn-normal" href="/user/insert">新增用户</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['jquery', 'layer'], function () {
        var layer = layui.layer;
        var $ = layui.$;
        $("#createUser").on("click", function () {
            layer.open(
                {
                    type: 2,
                    title: '新增',
                    //skin: 'layui-layer-molv',
                    shadeClose: false,
                    //shade: 0.8,
                    // btn: ['保存', '取消'],
                    area: ['880px', '350px'],
                    content: ['/user/insert2', 'no'],//跳转的页面

                });
        });

    });
</script>


</body>
</html>