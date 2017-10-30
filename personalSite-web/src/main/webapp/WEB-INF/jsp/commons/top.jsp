<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

