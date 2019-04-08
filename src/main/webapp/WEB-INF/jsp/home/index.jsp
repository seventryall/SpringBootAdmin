<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="header.jsp"%>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin app-container">
    <div class="layui-header">
        <div class="layui-logo">SpringBoot后台管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">
                <a href="#" lay-id="/Home/Index">主页</a>
            </li>
            <c:forEach items="${rootMenus}" var="item">
            <li class="layui-nav-item">
                <a href="">${item.label}</a>
            </li>
            </c:forEach>

        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="https://avatars2.githubusercontent.com/u/16729118?s=40&v=4" class="layui-nav-img" />管理员
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="">基本资料</a>
                    </dd>
                    <dd>
                        <a href="">安全设置</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="/login/logout">退出</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-side-menu layui-bg-black adminui-navMenu">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                ${leftMenuHtml}
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div style="padding: 15px;">
            <div class="layui-tab"  lay-allowClose="true" lay-filter="layadmin-layout-tabs" overflow>
                <ul class="layui-tab-title">
                    <li class="layui-this" lay-id="/Home/Index">主页</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <%@ include file="main.jsp"%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../../static/js/main.js"></script>


</body>
</html>