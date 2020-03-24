<%--
  Created by IntelliJ IDEA.
  User: susu
  Date: 2020/3/23
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>新建图书信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/add.css">
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/dept/list.do">
                图书信息管理
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <h1>Hello, XXX!</h1>
        <p>请小心地新增图书信息，要是建了一个错误的就不好了。。。</p>
    </div>
    <div class="page-header">
        <h3><small>新建</small></h3>
    </div>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/add.do" method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label  class="col-sm-2 control-label">图书编号 ：</label>
            <div class="col-sm-8">
                <input name="bookId" minlength="8" maxlength="8" class="form-control" value="${id}" id="bookId">
                <span class="msg-default"></span>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">图书名称 ：</label>
            <div class="col-sm-8">
                <input name="bookName" class="form-control" id="bookName">
            </div>
        </div>
        <div class="form-group">
            <label for="categoryId" class="col-sm-2 control-label">分类 ：</label>
            <select id="categoryId" name="categoryId" class="col-sm-2 form-control" style="width: auto;margin-left: 15px">
                <c:forEach var="c" items="${applicationScope.bookTypes}">
                    <option value="${c.id}" selected="">${c.name}</option>
                </c:forEach>
                <!-- 下拉列表的内容要从分类中进行读取，value值是分类id -->
            </select>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">价格 ：</label>
            <div class="col-sm-8">
                <input name="bookPrice" class="form-control" id="bookPrice">
            </div>
        </div>

        <div class="form-group" >
            <label  class="col-sm-2 control-label">图书封面 ：</label>
            <input type="file" id="bookPic" name="bookPic" style="padding-left: 15px">
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">备注 ：</label>
            <div class="col-sm-8">
                <input name="remarks" class="form-control" id="remarks">
                <input name="flag" id="flag" value="${id}" hidden>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </form>
</div>
<footer class="text-center" >
    copy@imooc
</footer>
<%
    String id = request.getParameter("id");
    if(id != "" && id != null){
%>
<script type="text/javascript">
    $("#bookId").prop('readonly',true);
</script>
<%}%>

<script type="text/javascript">
    var xmlhttp = null;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    $('#bookId').blur(function () {
        if (this.validity.tooShort) {
            this.nextElementSibling.innerHTML = "ID不能少余8位";
        } else {
            //获取输入框中的值
            var u = document.getElementById("bookId");
            var p = document.getElementById("bookPrice");
            var flag = document.getElementById("flag");
            var account = u.value;
            var price = p.value;
            //处理回调函数
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.status == 200 && xmlhttp.readyState == 4) {
                    var msg = xmlhttp.responseText;
                    if (msg == "1") {
                        u.nextElementSibling.innerHTML = 'ID已被注册';
                    } else if(msg == "0"){
                        u.nextElementSibling.innerHTML = 'ID可用';
                    }
                    else if(msg == "-1")
                        u.nextElementSibling.innerHTML = 'ID不能为空';
                    else
                        u.nextElementSibling.innerHTML = 'Id需要以book开头，后面包括四位数字';
                }
            }
            xmlhttp.open("get", "${pageContext.request.contextPath}/CheckBook_ID_Servlet?id=" + account + "&flag=" + "${id}");
            xmlhttp.send();
        }
    });
    $('#bookId').focus(function () {
        this.nextElementSibling.innerHTML = '长度8位';
        this.nextElementSibling.className = 'msg-default';
    });

    $(function () {
        $("form").submit(function () {
            var price = document.getElementById("bookPrice") .value;
            var id = document.getElementById("bookId").value;
            var name = document.getElementById("bookName") .value;
            var imgs = document.getElementById("bookPic").value;
            var regex = /(\d*).(\d*)/;
            var regex1 = /^book\d{4}$/;
            if(regex1.test(id) == false){
                alert("需以book开头,加上4为数字");
                return false;
            }
            if(regex.test(price) == false){
                alert("非数字");
                return false
            }
            else if(id == "" || id == null){
                alert("请输入编号");
                return false;
            }
            else if(name == "" || name == null){
                alert("请输入名称");
                return false;
            }
            else if(imgs == "" || imgs == null){
                alert("请上传图片");
                return false;
            }
                else
                    return  true;
        });
    });
</script>
</body>
</html>

