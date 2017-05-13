<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/thumb-panel.css" rel="stylesheet">
    <title>DQYuan's blog</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/commons/top.jsp" />

    <div class="panel
                panel-default
                thumb-panel">
        <div class="panel-heading">
            <h3 class="panel-title">
               <a href="#">Java</a>
            </h3>
        </div>
        <div class="panel-body">
            Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。
            Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程......
        </div>
    </div>

    <div class="panel
                panel-default
                thumb-panel">
        <div class="panel-heading">
            <h3 class="panel-title">
                <a href="#">Python</a>
            </h3>
        </div>
        <div class="panel-body">
            Python具有丰富和强大的库。它常被昵称为胶水语言，能够把用其他语言制作的各种模块（尤其是C/C++）很轻松地联结在一起。常见的一种应用情形是，
            使用Python快速生成程序的原型（有时甚至是程序的最终界面），然后对其中有特别要求的部分，用更合适的语言改写，比如3D游戏中的图形渲染模块，性能要求特别高，
            就可以用C/C++重写......
        </div>
    </div>

    <div class="panel
                panel-default
                thumb-panel">
        <div class="panel-heading">
            <h3 class="panel-title">
                <a href="#">机器学习</a>
            </h3>
        </div>
        <div class="panel-body">
            机器学习(Machine Learning, ML)是一门多领域交叉学科，涉及概率论、统计学、逼近论、凸分析、算法复杂度理论等多门学科。专门研究计算机怎样模拟或实现人类的学习行为，
            以获取新的知识或技能，重新组织已有的知识结构使之......
        </div>
    </div>

    <div class="panel
                panel-default
                thumb-panel">
        <div class="panel-heading">
            <h3 class="panel-title">
                <a href="#">Hadoop</a>
            </h3>
        </div>
        <div class="panel-body">
            Hadoop实现了一个分布式文件系统（Hadoop Distributed File System），简称HDFS。HDFS有高容错性的特点，并且设计用来部署在低廉的（low-cost）硬件上；
            而且它提供高吞吐量（high throughput）来访问应用程序的数据，适合那些有着超大数据集（large data set）的应用程序。HDFS放宽了（relax）POSIX的要求，可以以流的形式访问（streaming access）文件系统中的数据。
            Hadoop的框架最核心的设计就是：HDFS和MapReduce。HDFS为海量的数据提供了存储，则MapReduce为海量的数据提供了计算......
        </div>
    </div>

    <div class="panel
                panel-default
                thumb-panel">
        <div class="panel-heading">
            <h3 class="panel-title">
                <a href="#">数据仓库</a>
            </h3>
        </div>
        <div class="panel-body">
            数据仓库，英文名称为Data Warehouse，可简写为DW或DWH。数据仓库，是为企业所有级别的决策制定过程，提供所有类型数据支持的战略集合。
            它是单个数据存储，出于分析性报告和决策支持目的而创建。 为需要业务智能的企业，提供指导业务流程改进、监视时间、成本、质量以及控制......
        </div>
    </div>
</body>
</html>