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
            <ul class="nav navbar-nav" id="mainNavul">
                <li class="active delActive"><a class="cate-a" cateId="-1">首页</a></li>
                <li class="dropdown delActive">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        聊聊技术
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="cate-a" cateId="0">杂谈</a></li>
                        <li><a class="cate-a" cateId="1">笔记</a></li>
                        <li><a class="cate-a" cateId="2">教程</a></li>
                    </ul>
                </li>
                <li class="delActive"><a class="cate-a" cateId="3">聊聊生活</a></li>
                <li class="delActive"><a id="leaveMessage"><span class="glyphicon glyphicon-pencil"/>留言</a></li>
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

<%-- 提示消息模态框 --%>
<div class="modal fade" id="show-msg" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <span class="glyphicon glyphicon-tree-deciduous"></span>提示
                </h4>
            </div>
            <div class="modal-body">
                <strong id="msg" style="color: red"></strong>
            </div>
        </div>
    </div>
</div>

<%-- 修改留言模态框 --%>
<div class="modal fade" id="modifyLeaveMessageModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    修改留言
                </h4>
            </div>
            <form action="" class="form-horizontal" role="form">
                <div class="modal-body" id="modifyLeaveMessage-modal-body">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <textarea id="modifiedLeaveMessage" class="form-control"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="submitModLeaveMessage">
                        提交
                    </button>
                </div>
                <input type="hidden" id="leaveMessageId" value=""/>
            </form>
        </div>
    </div>
</div>
</body>

<%--     搜索框    --%>
<form class="pull-right
                     col-lg-3 col-lg-offset-8
                     col-md-3 col-md-offset-8
                     col-sm-3 col-sm-offset-8
                     col-xs-8 col-xs-offset-3" role="form">
    <div class="input-group">
        <span class="input-group-addon"><a><span class="glyphicon glyphicon-search"></span></a></span>
        <input type="text" class="form-control" placeholder="搜索本站">
    </div>
</form>
<br/>
<br/>
<br/>
<%-- Common Content Enf --%>


