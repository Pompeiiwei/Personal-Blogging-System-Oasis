<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Article</title>
    <jsp:include page="/WEB-INF/nav.jsp"/>

</head>
<body>

<div class="container">

    <c:if test="${artId != null}">
        <h3>Edit Article: </h3>
    </c:if>

    <c:if test="${artId == null}">
        <h3>New Article: </h3><br>
    </c:if>

    <form class="form-group" action="./article-edit?id=${article.artId}&userId=${article.userId}" , method="post">
        <div>
            <label for="new-article-title">Title:</label><br>
            <input class="form-control" type="text" name="title" id="new-article-title" value=${article.title} pattern="^[\s\S]*.*[^\s][\s\S]*$"
                    maxlength="50" placeholder="Less than 50 characters" required>
        </div>

        <div>
            <label for="new-article-body">Content:</label><br>
            <textarea class="form-control" rows="15" name="body" id="new-article-body" placeholder="Your content here" rows="10"
                      required>${article.body}</textarea>
        </div>
        <br>
        <div>
            <button class="btn btn-outline-info" type="submit">Submit</button>
        </div>

    </form>
</div>

</body>
</html>