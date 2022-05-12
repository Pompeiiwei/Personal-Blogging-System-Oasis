<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<title>Oasis Blog</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>


<style>

    body {
        background-color: lightblue;
    }
</style>


<h1 style="text-align: center">Oasis Blog</h1>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark"  style="justify-content: center">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/index.jsp"/>" >Home |</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/article-list?page=1"/>">Look Around |</a>
        </li>
        <li class="nav-item">
            <c:if test="${userId != null}">
            <a class="nav-link" href="<c:url value="/user-article-list?userId=${userId}&&page=1"/>">My Blog |</a>
            </c:if>
            <c:if test="${userId == null}">

                <a class="nav-link" href="<c:url value="/sign-up"/>">Register |</a>

            </c:if>

        </li>
        <li class="nav-item">
            <c:if test="${userId != null}">
            <a class="nav-link" href="<c:url value="/logout"/>">Sign out |</a>
            </c:if>
        </li>

        <li class="nav-item">
            <c:if test="${userId != null}">
                <a class="nav-link" href="<c:url value="Profile"/>">Your Account and Settings</a>
            </c:if>
            <c:if test="${userId == null}">
            </c:if>
        </li>

        <li class="nav-item">
            <c:if test="${userId != null}">
            </c:if>
            <c:if test="${userId == null}">
                <a class="nav-link" href="<c:url value="/login"/>">Login</a>
            </c:if>
        </li>

    </ul>
</nav>
