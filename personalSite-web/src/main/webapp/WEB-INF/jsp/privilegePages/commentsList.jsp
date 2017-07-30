<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <title>DQYuan's blog</title>
</head>
<body>
<div class="panel panel-default content-panel cleared">
    <div class="panel-heading">
        <div class="panel-title">
            <h5>${title} 的评论：</h5>
        </div>
    </div>
    <ul class="list-group">
        <c:forEach items="${commentCustoms}" var="commentCustom">
            <li class="list-group-item" id="${commentCustom.comment.id}">
                <span class="glyphicon glyphicon-user"></span>
                <c:choose>
                    <c:when test="${commentCustom.user != null}">
                        <c:choose>
                            <c:when test="${commentCustom.user.id == article.owner.id}">
                                作者
                            </c:when>
                            <c:otherwise>
                                ${commentCustom.user.nickname}
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:when test="${commentCustom.user == null}">
                        ${commentCustom.comment.ip}
                    </c:when>
                </c:choose>
                回复
                <c:choose>
                    <c:when test="${commentCustom.comment.responseCommentId != null}">
                        <c:choose>
                            <c:when test="${commentCustom.parentCustom.user != null}">
                                ${commentCustom.parentCustom.user.nickname}
                            </c:when>
                            <c:when test="${commentCustom.parentCustom.user == null}">
                                ${commentCustom.parentCustom.comment.ip}
                            </c:when>
                        </c:choose>
                    </c:when>
                    <c:when test="${commentCustom.comment.responseCommentId == null}">
                        作者
                    </c:when>
                </c:choose>:
                <div class="pull-right">
                    <a class="responseComment" comment-id="${commentCustom.comment.id}">回复</a>
                    <a class="deleteComment">删除</a>
                </div>
                <br/>
                    ${commentCustom.comment.content}
            </li>
        </c:forEach>
    </ul>
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
            <div class="modal-body" id="signin-modal-body">
                <strong id="msg" style="color: red"></strong>
            </div>
        </div>
    </div>
</div>

<%-- 写回复模态框 --%>
<div class="modal fade" id="write-response" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="responseTitle">

                </h4>
            </div>
            <form action="" class="form-horizontal" role="form">
                <div class="modal-body" id="response-modal-body">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <textarea id="responseContent" class="form-control"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="submitRes">
                        提交
                    </button>
                </div>
                <input type="hidden" id="parentCommentId" value=""/>
                <input type="hidden" id="articleId" value="${article.id}"/>
            </form>
        </div>
    </div>
</div>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
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

    $('.deleteComment').click(function () {
        var commentId = $(this).parent().parent().attr('id');
        $.post(
            "/comments/deleteComment.action",
            {
                commentId : commentId
            },
            function (deleteCommentInfo) {
                if ( deleteCommentInfo.isSuccess ){
                    showModalsecs("删除成功");
                    $('#' + commentId).remove();
                } else {
                    showModalsecs("删除失败");
                }
            }
        );
    });

    function isNull(contentNode, parentNode) {
        if ( contentNode.val().trim() == '' ){
            showNotNullInfo(parentNode);
            return true;
        }
        return false;
    }

    function showErrorInfo(parentNode, errorInfo) {
        parentNode.append($(" <div class='alert alert-error'> <a class='close' data-dismiss='alert'>×</a> <strong style='color: red' >"
            + errorInfo +"</strong> </div>"));
    }

    function showNotNullInfo(parentNode) {
        showErrorInfo(parentNode, "评论不能为空");
    }


    $('.responseComment').click(function () {
        var commentId = $(this).attr("comment-id");
        var content = $('#' + commentId).text();
        var userResponsed = content.substring(0, content.search('回复'));
        $('#responseTitle').html('<span class="glyphicon glyphicon-pencil"/> 回复 ' + userResponsed);
        $('#parentCommentId').val(commentId);
        $('#write-response').modal('show');
    });
    
    $('#submitRes').click(function () {
        if ( isNull($('#responseContent'), $("#response-modal-body")) ){
            return;
        }

        $.post(
            "/comments/saveComments.action",
            {
                articleId : $("#articleId").val().trim(),
                responseCommentId : $("#parentCommentId").val().trim(),
                originalContent : $("#responseContent").val()
            },
            function (submitCommentInfo) {
                if ( submitCommentInfo.isSuccess ){
                    location.reload(true);
                } else {
                    showNotNullInfo($("#response-modal-body"));
                }

            }
        )
    });
</script>
</html>