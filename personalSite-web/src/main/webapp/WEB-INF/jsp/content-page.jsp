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
    <link rel="shortcut icon" href="/images/favicon.ico" />
    <title>DQYuan's blog</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/commons/top.jsp" />
    <div class="panel panel-primary content-panel cleared">
        <div class="panel-heading">
           <div class="panel-title">
               <h1>${article.title}</h1>
           </div>
        </div>
            <div class="panel-body">
                   浏览次数：${article.browseTimes}<br/>
                   最近修改时间：${article.latestModifTime}<br/><br/>
                   ${article.content}
            </div>
        </div>
    </div>


    <div id="comment_panel" class="panel panel-default content-panel cleared">
        <div class="panel-heading">
            <div class="panel-title">
                <h5>评论区：</h5>
                <button class="btn" data-toggle="modal" data-target="#write-comment">写评论</button>
            </div>
        </div>
        <ul class="list-group">
            <c:forEach items="${article.commentCustoms}" var="commentCustom">
                <li class="list-group-item" id="${commentCustom.comment.id}">
                    <a class="responseHim" comment-id="${commentCustom.comment.id}">
                        <span class="glyphicon glyphicon-user"></span>
                    </a>
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
                    <c:if test="${commentCustom.canModify == true}">
                        <a class="modifyComment pull-right" type="button">修改</a>
                    </c:if>
                    <br/>
                    ${commentCustom.comment.content}
                </li>
            </c:forEach>
        </ul>
    </div>

    <!-- 写评论模态框 -->
    <div class="modal fade" id="write-comment" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title">
                        <span class="glyphicon glyphicon-pencil"/> 写评论
                        <c:choose>
                            <c:when test="${islogined == true}">
                                （用户：${user.nickname}）
                            </c:when>
                            <c:when test="${islogined == null or islogined == false}">
                                （网友：${pageContext.request.remoteAddr}）
                            </c:when>
                        </c:choose>
                    </h4>
                </div>
                <form action="" class="form-horizontal" role="form">
                    <div id="commentToArt-modal-body" class="modal-body">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <textarea id="commentToArt" class="form-control"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" id="submitResToA" class="btn btn-primary">
                            提交
                        </button>
                    </div>
                    <input type="hidden" id="articleId" value="${article.id}"/>
                </form>
            </div>
        </div>
    </div>

    <!-- 写回复模态框 -->
    <div class="modal fade" id="write-subcomment" role="dialog" aria-hidden="true">
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
                    <div class="modal-body" id="subcomment-modal-body">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <textarea id="subcomment" class="form-control"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="submitResToC">
                            提交
                        </button>
                    </div>
                    <input type="hidden" id="parentCommentId" value=""/>
                </form>
            </div>
        </div>
    </div>

    <!-- 修改评论模态框 -->
    <div class="modal fade" id="modifyCommentModal" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title">
                       修改评论
                    </h4>
                </div>
                <form action="" class="form-horizontal" role="form">
                    <div class="modal-body" id="modifyComment-modal-body">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <textarea id="modifiedComment" class="form-control"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="submitMod">
                            提交
                        </button>
                    </div>
                    <input type="hidden" id="commentId" value=""/>
                </form>
            </div>
        </div>
    </div>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="/js/common.js"></script>
<script>
    currentCateId = '-2';
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

    $('#submitResToA').click(function () {
        if ( isNull($('#commentToArt'), $("#commentToArt-modal-body")) ){
            return;
        }

        $.post(
            "/comments/saveComments.action",
            {
                articleId : $("#articleId").val().trim(),
                originalContent : $("#commentToArt").val()
            },
            function (submitCommentInfo) {
                if ( submitCommentInfo.isSuccess ){
                    location.reload(true);
                } else {
                    showNotNullInfo($('#commentToArt-modal-body'));
                }

            }
        )
    });

    $('.responseHim').click(function () {
        var commentId = $(this).attr("comment-id");
        var content = $('#' + commentId).text();
        var userResponsed = content.substring(0, content.search('回复'));
        $('#responseTitle').html('<span class="glyphicon glyphicon-pencil"/> 回复 ' + userResponsed);
        $('#parentCommentId').val(commentId);
        $('#write-subcomment').modal('show');
    });

    $('#submitResToC').click(function () {
        if ( isNull($('#subcomment'), $("#subcomment-modal-body")) ){
            return;
        }

        $.post(
            "/comments/saveComments.action",
            {
                articleId : $("#articleId").val().trim(),
                responseCommentId : $("#parentCommentId").val().trim(),
                originalContent : $("#subcomment").val()
            },
            function (submitCommentInfo) {
                if ( submitCommentInfo.isSuccess ){
                    location.href(URL + "#comment_panel");
                } else {
                    showNotNullInfo($("#subcomment-modal-body"));
                }

            }
        )
    });

    $('.modifyComment').click(function () {
        var commentId = $(this).parent().attr("id");
        $('#commentId').val(commentId);

        $.post(
            "/comments/originalContent.action",
            {
                commentId : commentId
            },
            function (originalContentInfo) {
                if ( originalContentInfo.isSuccess ){
                    $('#modifiedComment').val(originalContentInfo.originalContent);
                    $('#modifyCommentModal').modal('show');
                }
            }
        );
    });

    $('#submitMod').click(function () {
        if ( isNull($('#modifiedComment'), $('#modifyComment-modal-body')) ){
            return;
        }

        $.post(
            "/comments/modifyComments.action",
            {
                commentId : $("#commentId").val().trim(),
                newOriginalContent : $("#modifiedComment").val().trim()
            },
            function (modifyCommentInfo) {
                if ( modifyCommentInfo.isSuccess ){
                    location.reload(true);
                } else {
                    showErrorInfo($("#modifyComment-modal-body"), modifyCommentInfo.errorInfo);
                }
            }
        )
    });
</script>
</html>