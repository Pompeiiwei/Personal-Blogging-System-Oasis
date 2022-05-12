<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign up</title>
    <script type="text/javascript" src="./js/userNameCompare.js"></script>
    <script type="text/css">
        button {float:left;}
    </script>
<style>
    body{
        background-image: url("./images/5a1badef69784.png");
        background-size: 100%;
    }
    div.container{
        width: 400px;
        margin-top: 10px;
        margin-bottom: 10px;
        padding-right: 15px;
        padding-left: 15px;
        padding-bottom: 10px;
        margin-right: auto;
        margin-left: auto;
        background-color: white;
        opacity: 0.9;
        border-radius: 10px;
    }
</style>

    <jsp:include page="/WEB-INF/nav.jsp"/>
</head>
<body>

<div class="container">



    <div class="content">

    <form class="form-signin" action="./register" method="post">
        <h1 class="h3 mb-3 font-weight-normal">Creat your account</h1>
        username:<br>
        <input  required type="text" id="newUsername" class="form-control" name="userId" pattern="[A-Za-z0-9-]{3,16}">
        <div id="messageText"></div><br>
        Password: <br>
        <input  required type="password"class="form-control" name="userPassword" pattern="[A-Za-z0-9-]{3,16}"><br>
        re-enter Password:<br>
        <input  required type="password"class="form-control" name="Re-userPassword" pattern="[A-Za-z0-9-]{3,16}"><br>
        <input type="submit" class="btn btn-lg btn-primary btn-block" id="register" value="register">
    </form>
    </div>
</div>

</body>
</html>