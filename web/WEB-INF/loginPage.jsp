<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Login Now!</title>
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
            margin-right: auto;
            margin-left: auto;
            background-color: white;
            opacity: 0.9;
            border-radius: 20px;
        }
        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
        .form-signin .checkbox {
            font-weight: 400;
        }
        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="userId"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body class="text-center">
<jsp:include page="/WEB-INF/nav.jsp"/>

<div class="container">
<form  class="form-signin" action="./login" method="post">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    username:<br>
    <input  required type="text" class="form-control"  placeholder="Username" name="userId" pattern="[A-Za-z0-9-]{3,16}"><br>
    Password: <br>
    <input  required type="password" class="form-control" placeholder="Password" name="userPassword" pattern="[A-Za-z0-9-]{3,16}"><br>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="login">
    <p class="mt-5 mb-3 text-muted">&copy; 2020-2099</p>
</form>
</div>
</body>
</html>
