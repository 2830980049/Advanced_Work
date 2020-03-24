<%--
  Created by IntelliJ IDEA.
  User: susu
  Date: 2020/3/23
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        <%
            String warn = (String)request.getAttribute("warn");
            if(warn != null){
        %>
        alert("${warn}");
        <%
           }
        %>
    </script>
</head>
<body>
<div class="login">
    <div class="header">
        <h1>
            <a href="${pageContext.request.contextPath}/login.do">登录</a>
        </h1>
        <button></button>
    </div>
    <form action="${pageContext.request.contextPath}/login.do" method="post">
        <div class="name">
            <input type="text" id="name" name="username">
            <p></p>
        </div>
        <div class="pwd">
            <input type="password" id="pwd" name="password">
            <p></p>
        </div>
        <div class="code">
            <input type="text" id="code" name="verifyCode" style="width: 150px">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <img id="checkCode" name="checkCode" src="${pageContext.request.contextPath}/CheckCode_Servlet">
            <p></p>
        </div>
        <p style="color: red;font-size: 20px;font-weight: bold">${msg}</p>
        <div class="btn-red">
            <input type="submit" value="登录" id="login-btn">
        </div>
    </form>
</div>
<script type="text/javascript">
    $("#checkCode").click(function () {
       var url = document.getElementById("checkCode");
       url.src = "${pageContext.request.contextPath}/CheckCode_Servlet?time=" + new Date().getTime();
    });
</script>
</body>
</html>