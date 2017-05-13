<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <title>DQYuan's blog</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/commons/top.jsp" />
    <form role="form" class=" col-lg-8 col-lg-offset-2">
        <div class="form-group">
            <label for="title">标题：</label>
            <div>
                <input type="text" class="form-control" id="title" placeholder="文章Title"/>
            </div>
        </div>
        <div class="form-group">
            <label for="content">内容：</label>
            <div>
                <textarea class="form-control" id="content" placeholder="文章Content" rows="30"></textarea>
            </div>
        </div>
        <div class="form-group pull-right">
            <button type="button" id="submit-button" class="btn btn-primary">
                保存到草稿箱
            </button>
        </div>
    </form>

    <%-- 提示消息模态框 --%>
    <div class="modal fade" id="show-msg" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        <span class="glyphicon glyphicon-tree-deciduous"></span>提示
                    </h4>
                </div>
                <div class="modal-body" id="signin-modal-body">
                    <strong id="msg" style="color: red"></strong>
                </div>
            </div>
        </div>
    </div>

    <%-- 覆盖原文本模态框 --%>
    <div class="modal fade" id="cover-msg" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        <span class="glyphicon glyphicon-tree-deciduous"></span>提示
                    </h4>
                </div>
                <div class="modal-body">
                    <strong style="color: red">您的操作可能会覆盖以前编辑的文章，确认继续吗？</strong>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消
                    </button>
                    <button type="button" id="cover-button" class="btn btn-primary" data-dismiss="modal">
                        继续
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    function checkSubmit() {
        var title = $("#title").val().trim();
        if ( title == "" ){
            return "文章标题不能为空";
        }
        return "";
    }

    function showModalsecs(msg) {
        secs = 1;          //模态框1秒自动关闭
        count = secs;
        clearFlag = 0;
        $("#msg").html(msg);
        $("#show-msg").modal("show");
        clearFlag = self.setInterval("autoClose()", 1000);
    }

    function autoClose() {
        if ( count > 0 ){
            count --;
        }else {
            $("#show-msg").modal("hide");
            count = secs;
            self.clearInterval(clearFlag);
        }
    }

    function submitPost(iscovered) {
        var checkResult = checkSubmit();
        if ( checkResult == "" ){
            $.post(
                "/articles/save-unpublished-blog.action",
                {
                    title : $("#title").val().trim(),
                    originalContent : $("#content").val(),
                    iscovered : iscovered
                },
                function (submitInfo) {
                    if ( submitInfo.isSuccess ){
                        showModalsecs("保存成功");
                    }else {
                        if (submitInfo.showcover){
                            $("#cover-msg").modal("show");
                            return;
                        }
                        showModalsecs(submitInfo.msg);
                    }
                }
            )
        }else {
            showModalsecs(checkResult);
        }
    }

    $("#submit-button").click(function () {
         submitPost(false);
    });

    $("#cover-button").click(function () {
        submitPost(true)
    });
</script>
</html>