<%--
  Created by IntelliJ IDEA.
  User: boss-bian
  Date: 2018/8/16
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录</title>
    <script type="text/javascript" src="/statics/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<h3>登录页面</h3>



<form action="/user/login" method="get" onsubmit="return submitCheck();">
    <table>
        <tr><td>姓名：</td><td><input id="userName" type="text"  name="userName"></td></tr>
        <tr><td>密码：</td><td><input  id="password" type="password" name="password"></td></tr>
        <tr><td><input type="submit" value="提交"></td></tr>

    </table>

</form>

<script type="text/javascript">
    function  submitCheck() {
        var bool = true;
        var userName = $("#userName").val();
        var password = $("#password").val();
        if(userName==""){
            alert("姓名不能为空");
            bool =false;
        }
        if(password==""){
            alert("密码不能为空");
            bool =false;
        }
        return bool;
    }

    <c:if test="${islogin=='false'}">
        alert("登录失败用户名或密码错误");
    </c:if>
</script>
</body>
</html>
