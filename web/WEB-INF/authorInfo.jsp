<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .userInfo {
            width:200px;
            border: 1px solid black;
            text-align: center;
            float: right;
            right: 10%;
            position: fixed;
        }
        .avatar {
            width:150px;
            height: 150px;
        }
        @media screen and (max-width: 1000px){
            .userInfo{
                display: none;
            }
        }

    </style>
</head>
<body>
<div class="userInfo">

    <img class="avatar" src="${author.avatar}">


    <p>Author :${author.userName}</p>
    <c:if test="${author.firstName != null || author.lastName != null}">
        <p>Name:${author.firstName} ${author.lastName}</p>
    </c:if>
    <c:if test="${author.birthDate != null}">
        <p>Birthday:${author.birthDate}</p>
    </c:if>
</div>

</body>
</html>
