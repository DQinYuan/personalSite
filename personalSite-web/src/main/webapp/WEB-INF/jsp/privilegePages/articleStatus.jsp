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
    <jsp:include page="/WEB-INF/jsp/commons/top.jsp" />
    <table class="table cleared">
        <thead>
            <tr>
                <th>标题</th>
                <th>浏览次数</th>
                <th>最近被修改时间</th>
                <th>评论及数目</th>
                <th>状态</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${articleInfos}" var="article">
                <tr>
                    <td><a href="/articles/write-blogs.action?artTitle=${article.title}">${article.title}</a></td>
                    <td>${article.browseTimes}</td>
                    <td>${article.latestModifTime}</td>
                    <td><a href="/comments/commentsList.action?title=${article.title}">${article.commentNum}</a></td>
                    <td>
                        <c:if test="${article.isPublished}">
                            <button type="button" articleId="${article.id}" class="btn btn-success publishButton">
                                已发表
                            </button>
                        </c:if>
                        <c:if test="${!article.isPublished}">
                            <button type="button" articleId="${article.id}" class="btn btn-default publishButton">
                                未发表
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="/js/common.js"></script>
<script>
    $('.publishButton').click(function () {
        var nowButton = $(this);
        $.post(
            "/articles/publishToggle.action",
            {
                articleId : $(this).attr("articleId")
            },
            function (info) {
                if ( info ){
                    var classes = nowButton.attr("class");
                    if (  classes.indexOf("btn-success") > 0 ){
                        nowButton.attr("class", "btn btn-default publishButton");
                        nowButton.text("未发表");
                    } else {
                        nowButton.attr("class", "btn btn-success publishButton");
                        nowButton.text("已发表");
                    }
                }
            }
        )
    });
</script>
</html>