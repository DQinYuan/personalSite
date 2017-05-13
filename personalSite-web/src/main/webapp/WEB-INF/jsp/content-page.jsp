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
    <link href="css/content-panel.css" rel="stylesheet">
    <title>DQYuan's blog</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/commons/top.jsp" />

    <div class="panel panel-primary content-panel">
        <div class="panel-heading">
           <div class="panel-title">
               <h3 class="content-panel-title">Python</h3>
           </div>
        </div>
            <div class="panel-body">
                    Python[1]  （英国发音：/ˈpaɪθən/ 美国发音：/ˈpaɪθɑːn/）, 是一种面向对象的解释型计算机程序设计语言，由荷兰人Guido van Rossum于1989年发明，第一个公开发行版发行于1991年。<br/>
                    Python是纯粹的自由软件， 源代码和解释器CPython遵循 GPL(GNU General Public License)协议[2]  。<br/>
                    Python语法简洁清晰，特色之一是强制用空白符(white space)作为语句缩进。<br/>
                <p> Python具有丰富和强大的库。它常被昵称为胶水语言，能够把用其他语言制作的各种模块（尤其是C/C++）很轻松地联结在一起。常见的一种应用情形是，使用Python快速生成程序的原型（有时甚至是程序的最终界面）
                    ，然后对其中[3]  有特别要求的部分，用更合适的语言改写，比如3D游戏中的图形渲染模块，性能要求特别高，就可以用C/C++重写，而后封装为Python可以调用的扩展类库。
                    需要注意的是在您使用扩展类库时可能需要考虑平台问题，某些可能不提供跨平台的实现。
                </p>
                <p>
                    由于Python语言的简洁性、易读性以及可扩展性，在国外用Python做科学计算的研究机构日益增多，一些知名大学已经采用Python来教授程序设计课程。
                    例如卡耐基梅隆大学的编程基础、麻省理工学院的计算机科学及编程导论就使用Python语言讲授。众多开源的科学计算软件包都提供了Python的调用接口，
                    例如著名的计算机视觉库OpenCV、三维可视化库VTK、医学图像处理库ITK。而Python专用的科学计算扩展库就更多了，例如如下3个十分经典的科学计算扩展库：
                    NumPy、SciPy和matplotlib，它们分别为Python提供了快速数组处理、数值运算以及绘图功能。因此Python语言及其众多的扩展库所构成的开发环境十分适合工程技术、科研人员处理实验数据、
                    制作图表，甚至开发科学计算应用程序。
                </p>
            </div>
        </div>
    </div>


    <div class="panel panel-default content-panel">
        <div class="panel-heading">
            <div class="panel-title">
                <h5>评论区：</h5>
                <button class="btn" data-toggle="modal" data-target="#write-comment">写评论</button>
            </div>
        </div>
        <ul class="list-group">
            <li class="list-group-item">
                <span class="glyphicon glyphicon-user"></span>192.168.10.2:<br/>
                666</li>
            <li class="list-group-item">
                <span class="glyphicon glyphicon-user"></span>192.168.10.9:<br/>
                666</li>
            <li class="list-group-item">
                <span class="glyphicon glyphicon-user"></span>192.168.10.10:<br/>
                666</li>
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
                        <span class="glyphicon glyphicon-pencil"/> 写评论（网友：192.168.1.2）
                    </h4>
                </div>
                <form action="" class="form-horizontal" role="form">
                    <div class="modal-body">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <textarea id="comment" class="form-control"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">
                            提交
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>