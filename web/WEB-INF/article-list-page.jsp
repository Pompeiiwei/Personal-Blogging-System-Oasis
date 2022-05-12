<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Article List</title>
    <jsp:include page="/WEB-INF/nav.jsp"/>

    <style>
        a {
            color: #444444;
            text-decoration: none;
            background-color: transparent;
        }

        a:hover{
            color: cornsilk;
        }

        a:hover, .title{
            color: black;
        }

        table{
            border-collapse: collapse;
            border-spacing: 0;
            table-layout: fixed;
        }
    </style>

</head>
<body>


<div class="container">

    <br>
    <h3 style="line-height: 2"> Recommended Articles</h3>

    <div class="search">
    <form action="./search-article-list" method="get" >
        Search Article by Title:
        <input type="text" name="title">
        <input type="hidden" name="page" value="1">
        <input class="btn btn-outline-dark btn-sm" type="submit" value="Search">
    </form>
    </div>

    <br>

    <br>
    <table class="table table-hover" id="article-list">
        <thead>
        <tr>
            <th >Title</th>
            <th onclick="sortTable(1)">Author</th>
            <th onclick="sortTable(2)" style="text-align: right">Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${articles}">
            <tr>
                <td><a href="./article-content?artId=${article.artId}"  class="title"> ${article.title}</a> </td>

                <td>${article.userName}</td>

                <td style="text-align: right">${article.date}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id="pages" style="display: block">
        <a href="?page=1">first page</a>
    <c:forEach begin="${begin}" step="1" end="${end}" var="count">
        <c:if test="${count != page}">
            <a href="?page=${count}" style="margin-left: 9px">${count}</a>
        </c:if>
        <c:if test="${count == page}">
            <a href="?page=${count}" style="margin-left: 9px; color: red">${count}</a>
        </c:if>
    </c:forEach>
    </div>

    <script>
        function sortTable(n) {
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
            table = document.getElementById("article-list");
            switching = true;
            dir = "asc";
            while (switching) {
                switching = false;
                rows = table.rows;
                for (i = 1; i < (rows.length - 1); i++) {
                    shouldSwitch = false;
                    x = rows[i].getElementsByTagName("TD")[n];
                    y = rows[i + 1].getElementsByTagName("TD")[n];
                    if (dir == "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            shouldSwitch= true;
                            break;
                        }
                    } else if (dir == "desc") {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                }
                if (shouldSwitch) {
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                    switchcount ++;
                } else {
                    if (switchcount == 0 && dir == "asc") {
                        dir = "desc";
                        switching = true;
                    }
                }
            }
        }
    </script>

</div>

</body>
</html>