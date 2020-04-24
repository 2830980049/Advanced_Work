<%--
  Created by IntelliJ IDEA.
  User: susu
  Date: 2020/3/23
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书后台管理</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#searchContent").keyup(function () {
                var value = $(this).val();
                $("#all > tr").remove();
                $.ajax({
                    "url" : "${pageContext.request.contextPath}/SearchBook_Servlet",
                    "data" : {"value" : value},
                    "dataType" : "json",
                    "type" : "post",
                    "success": function (json) {
                        for (var i = 0; i < json.length; i++){
                            var tres = (
                                "<tr>" +
                                "<td>" + (i+1).toString() +"</td>" +
                                "<td>" + json[i].id +"</td>" +
                                "<td>" + json[i].book_name + "</td>" +
                                "<td>" + json[i].type  + "</td>" +
                                "<td>" + json[i].price + "</td>" +
                                "<td>" + "<img src=" + "img/" + json[i].img + " style='width: 160px;height: 90px'>" +"</td>" +
                                "<td>" +
                                "<a href='${pageContext.request.contextPath}/UpdateBook_Servlet?id=" + json[i].id + "'>"+ "修改" + "</a>" + " " +
                                "<a href='${pageContext.request.contextPath}/DeleteBook_Servlet?id=" + json[i].id + "'>"+ "删除" + "</a>" +
                                "</td>" +
                                "</tr>"
                            );
                            console.log(i);
                            $("#all").append(tres);
                        }
                    }
                });
            });

            $("#btn").click(function () {
                var value = $("#searchContent").val();
                $("#all > tr").remove();
                $.ajax({
                    "url" : "${pageContext.request.contextPath}/SearchBook_Servlet",
                    "data" : {"value" : value},
                    "dataType" : "json",
                    "type" : "post",
                    "success": function (json) {
                        for (var i = 0; i < json.length; i++){
                            var tres = (
                                "<tr>" +
                                "<td>" + (i+1).toString() +"</td>" +
                                "<td>" + json[i].id +"</td>" +
                                "<td>" + json[i].book_name + "</td>" +
                                "<td>" + json[i].type  + "</td>" +
                                "<td>" + json[i].price + "</td>" +
                                "<td>" + "<img src=" + "img/" + json[i].img + " style='width: 160px;height: 90px'>" +"</td>" +
                                "<td>" +
                                "<a href='${pageContext.request.contextPath}/UpdateBook_Servlet?id=${c.id}'>"+ "修改" + "</a>" + " " +
                                "<a href='${pageContext.request.contextPath}/DeleteBook_Servlet?id=${c.id}'>"+ "删除" + "</a>" +
                                "</td>" +
                                "</tr>"
                            );
                            console.log(i);
                            $("#all").append(tres);
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<header>
    <div class="container">
        <nav>
            <a href="${pageContext.request.contextPath}/bookList.jsp" >图书信息管理</a>
        </nav>
        <nav>
            <a href="${pageContext.request.contextPath}/categoryList.jsp" >分类管理</a>
        </nav>

    </div>

</header>

<section class="banner">
    <div class="container">
        <div>
            <h1>图书管理系统</h1>
            <p>图书信息管理</p>
        </div>
    </div>
</section>
<section class="main">


    <div class="container">
            <div class="form-group"  style="float: right;">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="btn">查询</button>&nbsp;&nbsp;&nbsp;
                </div>
            </div>
            <div class="form-group" style="float: right;width: 300px;">
                <div class="col-sm-8">
                    <input name="searchContent" class="form-control" id="searchContent"
                           placeholder="输入要查询的分类" style="width: 250px">
                </div>
            </div>
    </div>
    <div class="container">

        <table class="table table-striped">
            <thead>
            <tr>
                <th>序号</th>
                <th>图书编号</th>
                <th>图书名称</th>
                <th>分类</th>
                <th>价格</th>
                <th>图书封面</th>
                <th>操作</th>

            </tr>
            </thead>
            <tbody id="all">
            <%
                String flag = request.getParameter("flag");
                if(flag == "1"){
            %>
                <c:forEach items="${applicationScope.book}" var="c" varStatus="idx">
                    <tr id="tr1">
                        <td>${idx.index + 1}</td>
                        <td>${c.id}</td>
                        <td>${c.book_name}</td>
                        <td>${c.type}</td>
                        <td>￥${c.price}</td>
                        <td><img src="img/${c.img}" style="width: 160px;height: 90px"></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/UpdateBook_Servlet?id=${c.id}">修改</a>
                            <a href="${pageContext.request.contextPath}/DeleteBook_Servlet?id=${c.id}">删除</a>
                        </td>
                        <!--在循环显示数据时，此处的book0001可以用EL表达式进行替换-->
                    </tr>
                </c:forEach>
            <%
                }
                else{
            %>
                <c:forEach items="${applicationScope.bookList}" var="c" varStatus="idx">
                    <tr id="tr1">
                        <td>${idx.index + 1}</td>
                        <td>${c.id}</td>
                        <td>${c.book_name}</td>
                        <td>${c.type}</td>
                        <td>￥${c.price}</td>
                        <td><img src="img/${c.img}" style="width: 160px;height: 90px"></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/UpdateBook_Servlet?id=${c.id}">修改</a>
                            <a href="${pageContext.request.contextPath}/DeleteBook_Servlet?id=${c.id}">删除</a>
                        </td>
                        <!--在循环显示数据时，此处的book0001可以用EL表达式进行替换-->
                    </tr>
                </c:forEach>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</section>
<section class="page">
    <div class="container">
        <div id="fatie">
            <a href="${pageContext.request.contextPath}/addBook.jsp"><button>新建</button></a>
        </div>
    </div>
</section>
<footer>
    Create By WuQiLi
</footer>
</body>
</html>
