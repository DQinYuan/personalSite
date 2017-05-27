<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  Common Content
         导航栏--%>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <img alt="Brand" src="http://i2.buimg.com/589115/99d1e3e2a8c2fb4e.png">
            </a>
            <a href="#" class="navbar-brand dropdown-toggle" data-toggle="dropdown">
                Welecome to DQYuan's blog
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="#">About Me</a></li>
            </ul>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a class="cate-a" cateId="-1">首页</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        聊聊技术
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="cate-a" cateId="-1">全部</a></li>
                        <li><a class="cate-a" cateId="0">杂谈</a></li>
                        <li><a class="cate-a" cateId="1">笔记</a></li>
                        <li><a class="cate-a" cateId="2">教程</a></li>
                    </ul>
                </li>
                <li><a class="cate-a" cateId="3" type="button">聊聊生活</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-pencil"/>留言</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" id="signup-in">
                <li id="signup-nav"><a role="button" data-toggle="modal" data-target="#sign-up"><span
                        class="glyphicon glyphicon-log-in"></span> 注册</a></li>
                <li id="login-nav"><a role="button" data-toggle="modal" data-target="#sign-in"><span
                        class="glyphicon glyphicon-user"></span> 登录</a></li>
            </ul>
        </div>
    </div>
</nav>

<%-- 登录模态框 --%>
<div class="modal fade" id="sign-in" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    <span class="glyphicon glyphicon-user"></span> 登录
                </h4>
            </div>
            <form class="form-horizontal" role="form" method="post">
                <div class="modal-body" id="signin-modal-body">
                    <div class="form-group">
                        <label for="username-signin" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" id="username-signin"  name="nickname" class="form-control" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password-signin" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password-signin"  name="password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="verification-code-signin" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-10">
                            <input type="text" style="width: 30%" class="form-control col-sm-2" id="verification-code-signin" name="vcode"
                                   placeholder="请输入验证码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-10">
                            <img id="loginimg" src="/authImg.jpg"/>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="loginbutton" class="btn btn-primary">
                        提交
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<%-- 注册模态框 --%>
<div class="modal fade" id="sign-up" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <span class="glyphicon glyphicon-log-in"></span> 注册
                </h4>
            </div>
            <%--<form action="" class="form-horizontal" role="form">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="username-signup" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" id="username-signup" class="form-control" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password-signup" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password-signup" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="verification-code" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-10">
                            <input type="text" style="width: 30%" class="form-control col-sm-2" id="verification-code"
                                   placeholder="请输入验证码">
                            <img src="http://images.cnitblog.com/blog/568845/201401/112235265981.jpg"
                                 style="width: 30%;"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        提交
                    </button>
                </div>
            </form>--%>
            <div class="modal-body">
                注册功能暂未开放
            </div>
        </div>
    </div>
</div>

<%--     搜索框    --%>
<form class="pull-right
                     col-lg-3 col-lg-offset-8
                     col-md-3 col-md-offset-8
                     col-sm-3 col-sm-offset-8
                     col-xs-8 col-xs-offset-3" role="form">
    <div class="input-group">
        <span class="input-group-addon"><a><span class="glyphicon glyphicon-search"></span></a></span>
        <input type="text" class="form-control" placeholder="搜索本文">
    </div>
</form>
<br/>
<br/>
<br/>
<script>
    $(function () {
        $.post(
            "/users/login.action",
            {
                iniPage : true
            },
            function (loginInfo) {
                var islogined = loginInfo.islogined;
                if ( islogined ){
                    $("#signup-nav").remove();
                    $("#login-nav").remove();
                    $("#sign-in").remove();
                    $("#sign-up").remove();
                    $("#signup-in").append($("<li><a>欢迎" + loginInfo.nickname +" 光临本站</a></li>"));
                }
            }
        )

        function submitLogin() {
            $.post(
                "/users/login.action",
                {
                    nickname : $("#username-signin") .val().trim(),
                    password : $("#password-signin").val().trim(),
                    vcode: $("#verification-code-signin").val().trim()
                },
                function (loginInfo) {
                    var islogined = loginInfo.islogined;
                    if ( !islogined ){
                        showLoginMsg(loginInfo.errormsg);
                    } else {
                        $("#sign-in").on("hidden.bs.modal", function () {
                            $("#signup-nav").remove();
                            $("#login-nav").remove();
                            $("#sign-in").remove();
                            $("#sign-up").remove();
                            $("#signup-in").append($("<li><a>欢迎" + loginInfo.nickname +" 光临本站</a></li>"));
                        })
                        $("#sign-in").modal('hide');
                    }
                }
            )
        }

        function checkLogin() {
            var nickname = $("#username-signin") .val().trim();
            var password = $("#password-signin").val().trim();
            var vcode = $("#verification-code-signin").val().trim();
            var pattern = new RegExp("[~'!@#$%^&*()-+_=:\<\>]");
            if ( nickname == "" || password == "" || vcode == "" ){
                return "字段不能为空";
            }
            if ( pattern.test(nickname) || pattern.test(password) || pattern.test(vcode) ){
                return "字段中不能含有特殊字符";
            }
            return "";
        }

        function showLoginMsg(msg) {
            $("#signin-modal-body").append($(" <div class='alert alert-error'> <a class='close' data-dismiss='alert'>×</a> <strong style='color: red' >"
                + msg +"</strong> </div>"));
            var timestamp = new Date().getTime();
            $("#loginimg").attr("src", $("#loginimg").attr('src') + '?' +timestamp);
        }

        $("#loginbutton").click(function () {
            var checkResult = checkLogin();
            if ( !(checkResult == "") ){
                showLoginMsg(checkResult)
            } else {
                submitLogin();
            }
        });

        $("#loginimg").click(function () {
            var timestamp = new Date().getTime();
            $(this).attr("src", $(this).attr('src') + '?' +timestamp);
        });
    })

    function getArticles(cateId) {
        $.get(
            "/articles/getArticles.action",
            {
                cateId:cateId
            },
            function (articles) {
                $(".thumb-panel").remove();
                for (var i = 0; i < articles.length; i++ ){
                    $("body").append($(" <div class='panel panel-default thumb-panel'><div class='panel-heading'>"
                        + "<h3 class='panel-title'> <a href='#'>" + articles[i].title
                        + "</a> </h3> </div> <div class='panel-body'>" + articles[i].artAbstract
                        + "</div> </div>"));
                }

            }
        )
    }

    $(".cate-a").click(function () {
        getArticles($(this).attr("cateId"));
    });
</script>
<%-- Common Content Enf --%>


