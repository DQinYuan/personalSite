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
    <ul class="list-group" id="leaveMessageList">
        <c:forEach items="${leaveMessageCustoms}" var="customs">
            <li class="list-group-item">
                <span class="glyphicon glyphicon-user"></span>${customs.user.nickname}：
                <a class="pull-right deleteLeaveMessage" leaveMessageId="${customs.leaveMessage.id}">
                    删除</a>
                <div>
                    ${customs.leaveMessage.content}
                </div>
            </li>
        </c:forEach>

    </ul>
</div>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
    $('.deleteLeaveMessage').click(function () {
        var leaveMessaageId = $(this).attr('leaveMessageId');
        $.post(
            '/leaveMessages/delete.action',
           {
                leaveMessageId: leaveMessaageId
            },
            function () {
                location.reload(true);
            }
        );
    });
</script>
</html>