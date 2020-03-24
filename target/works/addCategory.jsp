<%--
  Created by IntelliJ IDEA.
  User: susu
  Date: 2020/3/23
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>新建图书分类</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/add.css">
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="">
                图书分类管理
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <h1>Hello, XXX!</h1>
        <p>请小心地新增图书分类，要是建了一个错误的就不好了。。。</p>
    </div>
    <div class="page-header">
        <h3><small>新建</small></h3>
    </div>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/AddCategory_Servlet" method="post">
        <div class="form-group">
            <label  class="col-sm-2 control-label">分类ID ：</label>
            <div class="col-sm-8">
                <input maxlength="6" minlength="6" name="categoryId" class="form-control" id="categoryId">
                <span class="msg-default"></span>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">分类名称 ：</label>
            <div class="col-sm-8">
                <input name="categoryName" class="form-control" id="categoryName">
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
<script type="text/javascript">
    var xmlhttp = null;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    $('#categoryId').blur(function () {
         if (this.validity.tooShort) {
            this.nextElementSibling.innerHTML = "ID不能少余6位";
            this.setCustomValidity('ID不能少余6位');
        } else {
            //获取输入框中的值
            var u = document.getElementById("categoryId");
            var account = u.value;
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
                                u.nextElementSibling.innerHTML = 'Id需要以ca开头，后面包括四位数字';
                }
            }
            xmlhttp.open("get", "${pageContext.request.contextPath}/Check_Id_Servlet?id=" + account);
            xmlhttp.send();
        }
    });
    $('#categoryId').focus(function () {
        this.nextElementSibling.innerHTML = '长度6位';
        this.nextElementSibling.className = 'msg-default';
    });

    $("form").submit(function () {
        var regex = /^ca\d{4}$/;
        var id = document.getElementById("categoryId").value;
        var name = document.getElementById("categoryName").value;
        if(regex.test(id) == false){
            alert("Id需要以ca开头，后面包括四位数字");
            return false;
        }
        if(name == null || name == ""){
            alert("请输入分类名称");
            return false;
        }
            else if(regex.test(id))
                return true;
    });
</script>
</body>
</html>

