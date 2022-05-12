
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>User's Article List</title>
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

        .add-btn:hover{
            color: white;
        }

    </style>

</head>
<body>

<div class="container">

    <br>
    <h3 style="line-height: 2"> My Articles</h3>

    <br>

    <table class="table table-hover" id = "user-article-list">
        <thead>
        <tr>
            <th class="title" id = "user-article-list-title" onclick="sortTable(0)">Title</th>
            <th id = "user-article-list-date" onclick="sortTable(1)">Date</th>
            <th>edit</th>
            <th>delete</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="article" items="${articles}">
            <tr>
                <td><a href="./article-content?artId=${article.artId}"> ${article.title}</a></td>

                <td>${article.date}</td>

                <td><button class="btn btn-outline-dark" id= ${article.artId}><a href="./article-edit?id=${article.artId}" methods="doPost">Edit</a></button></td>

                <td><button class="btn btn-outline-dark" id= ${article.artId}><a href="./article-delete?artId=${article.artId}&&userId=${userId}">Delete</a></button></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>



    <br>
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


    <br> <br> <br>
    <button class="btn btn-outline-dark"><a class = "add-btn" href="./add-article?${article.userId}">add article</a></button>




        <script>
            function sortTable(n) {
                var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                table = document.getElementById("user-article-list");
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