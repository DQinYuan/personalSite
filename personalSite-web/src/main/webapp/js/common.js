

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

function showModalMsg(node, msg){
    node.append($(" <div class='alert alert-error'> <a class='close' data-dismiss='alert'>×</a> <strong style='color: red' >"
        + msg +"</strong> </div>"));
}

function showLoginMsg(msg) {
    showModalMsg($("#signin-modal-body"), msg);
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

function removeAllCleared() {
    $(".cleared").remove();
}

pageArr = new Array();
pageFlatArr = new Array();
currentCateId = "-1";
function pageArrInit() {
    pageArr["-1"] = 1;
    pageArr["0"] = 1;
    pageArr["1"] = 1;
    pageArr["2"] = 1;
    pageArr["3"] = 1;
    pageFlatArr["-1"] = false;
    pageFlatArr["0"] = false;
    pageFlatArr["1"] = false;
    pageFlatArr["2"] = false;
    pageFlatArr["3"] = false;
}
pageArrInit();

function getArticles(cateId) {
    $.post(
        "/articles/getArticles.action",
        {
            cateId:cateId,
            pageNum : pageArr[String(cateId)]
        },
        function (articles) {
            $('.loadingStr').remove();
            for (var i = 0; i < articles.length; i++ ){
                $("body").append($(" <div class='panel panel-default thumb-panel cleared'><div class='panel-heading'>"
                    + "<h3 class='panel-title'><a href='/articles/readArticle.action?articleId=" +
                    articles[i].id + "'> " + articles[i].title
                    + "</a></h3> </div> <div class='panel-body'>" + articles[i].artAbstract
                    + "</div> </div>"));
            }
            pageArr[String(cateId)] += 1;
            if ( articles.length < 20 ){
                pageFlatArr[String(cateId)] = true;
            }
            cateAFlag = true;
        }
    )
}

function navActive(node, cateId) {
    $(".delActive").removeClass("active");

    if ( cateId != -1 && (cateId == 0 || cateId == 1 || cateId == 2) ){
        $("#mainNavul li:eq(1)").addClass("active");
        return;
    }

    node.parent().addClass("active");
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

var cateAFlag = true;

$(".cate-a").click(function () {
    if ( !cateAFlag ){
        return;
    }
    cateAFlag = false;
    var cateId = $(this).attr("cateId");
    navActive($(this), cateId);
    removeAllCleared();
    pageArrInit();
    getArticles(cateId);
    currentCateId = cateId + "";
});

function addLeaveMessageFromCustom(nowCustom, canModifyFlag, preFlag) {
    var content = nowCustom.leaveMessage.content;
    var leaveMessageId = nowCustom.leaveMessage.id;
    var canModified = nowCustom.canModified;

    var modifyA;
    if ( canModified || canModifyFlag ){
        modifyA = '<a class="pull-right modifyLeaveMessage">修改</a>';
    } else {
        modifyA = '';
    }

    var userShow;
    if ( nowCustom.user == null ){
        userShow = nowCustom.leaveMessage.ip;
    } else {
        userShow = nowCustom.user.nickname;
    }

    if ( preFlag ){
        $('#leaveMessageList').prepend('<li class="list-group-item" leaveMessageId="'+ leaveMessageId + '">' +
            '<span class="glyphicon glyphicon-user"></span>' +
            userShow + ':' + modifyA + '<div>' +
            content + '</div>' +
            '</li>');
    } else {
        $('#leaveMessageList').append('<li class="list-group-item" leaveMessageId="'+ leaveMessageId + '">' +
            '<span class="glyphicon glyphicon-user"></span>' +
            userShow + ':' + modifyA + '<div>' +
            content + '</div>' +
            '</li>');
    }


    if ( modifyA != '' ){
        $('.modifyLeaveMessage').click(function () {
            var leaveMessageId = $(this).parent().attr("leaveMessageId");
            $('#leaveMessageId').val(leaveMessageId);
            $.post(
                "/leaveMessages/originalContent.action",
                {
                    leaveMessageId : leaveMessageId
                },
                function (originalContentVo) {
                    if ( originalContentVo.isSuccess ){
                        $('#modifiedLeaveMessage').val(originalContentVo.originalContent);
                        $('#modifyLeaveMessageModal').modal('show');
                    }
                }
            )
        });
    }
}

$('#leaveMessage').click(function () {
    navActive($(this), -1);
    removeAllCleared();
    $('body').append('<div class="panel panel-default content-panel cleared">' +
           '<div class="panel-heading">' +
               '<form>' +
                  '<textarea placeholder="欢迎留言" id="leaveMessageContent" style="width: 100%" rows="5"></textarea><br/>' +
                  '<button class="btn" id="submitLeaveMessage" type="button">提交留言</button>' +
               '</form>' +
            '</div>' +
        '<ul class="list-group" id="leaveMessageList"></ul>' +
        '</div>');
    $.post(
        "/leaveMessages/getAllLeaveMessages.action",
        {},
        function (leaveMessageCustoms) {
            for ( var i = 0; i < leaveMessageCustoms.length; i++ ){
                var nowCustom = leaveMessageCustoms[i];
                addLeaveMessageFromCustom(nowCustom, false, false);
            }
        }
    );
    currentCateId = "-2";

    $('#submitLeaveMessage').click(function () {
        var originalContent = $('#leaveMessageContent').val().trim();
        if ( originalContent == '' ){
            showModalsecs('留言不能为空');
            return;
        }

        $.post(
            "/leaveMessages/saveLeaveMessage.action",
            {
                originalContent : originalContent
            },
            function (saveLeaveMessagesInfo) {
                if ( saveLeaveMessagesInfo.isSuccess ){
                    showModalsecs('留言成功');
                    addLeaveMessageFromCustom(saveLeaveMessagesInfo.custom, true, true);
                    $('#leaveMessageContent').val('');
                } else {
                    showModalsecs(saveLeaveMessagesInfo.errorInfo);
                }
            }
        );
    });
});

$('#submitModLeaveMessage').click(function () {
    if ( $('#modifiedLeaveMessage').val() == '' ){
        showModalMsg($('#modifyLeaveMessage-modal-body'), '留言不能为空');
        return;
    }

    var leaveMessageId = $('#leaveMessageId').val();

    $.post(
        "/leaveMessages/modifyLeaveMessage.action",
        {
            leaveMessageId : leaveMessageId,
            originalContent : $('#modifiedLeaveMessage').val().trim()
        },
        function (modifyLeaveMessageInfo) {
            if ( modifyLeaveMessageInfo.isSuccess ){
                $('[leaveMessageId=' + leaveMessageId + '] div').html(modifyLeaveMessageInfo.content);
                $('#modifyLeaveMessageModal').modal('hide');
            } else {
                showModalMsg($('#modifyLeaveMessage-modal-body'), modifyLeaveMessageInfo.errorInfo);
            }
        }
    );
});

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
    );
    $(window).scroll(function() {
        if ( currentCateId == "-2" ){
            return;
        }
        if (Math.ceil($(document).scrollTop()) >= $(document).height() - $(window).height()) {
            if ( pageFlatArr[currentCateId] ){
                return;
            }
            $("body").append($("<div style='text-align:center;' class='loadingStr'>正在加载...</div>"));
            getArticles(currentCateId);
        }
    });
});