<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录页</title>
    <link rel="stylesheet" href="../../../static/layui/css/layui.css">
    <link rel="stylesheet" href="../../../static/layui/css/admin.css">
    <link rel="stylesheet" href="../../../static/css/style.css">
    <script src="../../../static/layui/layui.js"></script>
</head>
<body>

<div class="login-main">
    <header class="layui-elip">SpringBootAdmin</header>
    <form class=" layadmin-user-login-box layadmin-user-login-body layui-form">
        <div class="layui-form-item">
            <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
            <input type="text" name="userName" id="LAY-user-login-username" value="admin" lay-verify="required" placeholder="用户名" class="layui-input">
        </div>
        <div class="layui-form-item">
            <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
            <input type="password" name="userPwd" id="LAY-user-login-password" value="123" lay-verify="required" placeholder="密码" class="layui-input">
        </div>
        <div class="layui-form-item" style="margin-bottom: 20px;">
            <input type="checkbox" name="remember" lay-skin="primary" title="记住我">
        </div>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 录</button>
        </div>
    </form>
</div>



<script type="text/javascript">
    layui.use(['form','layer','jquery'], function () {

        // 操作对象
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        form.render();
        //提交
        form.on('submit(LAY-user-login-submit)',function (data) {
            // console.log(data.field);
            $.ajax({
                url:'/login/userLogin',
                data:data.field,
                dataType:'json',
                type:'post',
                success:function (res) {
                    if (res.code == 0) {
                        layer.msg('登录成功', {
                            offset: '15px'
                            , icon: 1
                            , time: 1000
                        }, function () {
                            location.href = '/home/index'; //后台主页
                        });
                    }
                    else {
                        layer.msg(res.msg);
                    }
                }
            })
            return false;
        })

    });
</script>
</body>
</html>