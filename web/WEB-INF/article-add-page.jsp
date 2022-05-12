<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Article</title>
    <jsp:include page="/WEB-INF/nav.jsp"/>
</head>
<body>

<div class="container">

    <h1>New Article</h1>

    <form  class="form-group" action="./add-article" method="post" >
        <div>
            <label for="new-article-title">Title:</label><br>
            <input class="form-control" type="text" name="title" id="new-article-title" maxlength="50" pattern="^[\s\S]*.*[^\s][\s\S]*$" placeholder="Less than 50 characters" required>
        </div>


        <div>
            <label for="new-article-body">Content:</label><br>
            <textarea class="form-control" rows="15" name="content" id="new-article-body" placeholder="Your content here" rows="10" required></textarea>
        </div>

        <div>
            <button class="btn btn-outline-dark" type="submit">Submit</button>
        </div>

    </form>

</div>

</body>
</html>