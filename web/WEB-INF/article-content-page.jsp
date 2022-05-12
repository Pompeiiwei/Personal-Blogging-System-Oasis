<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>${article.title}</title>
    <link href="./css/page.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="./js/extra-feature.js"></script>

    <jsp:include page="/WEB-INF/nav.jsp"/>

    <c:if test="${author.avatar != null}">
        <jsp:include page="/WEB-INF/authorInfo.jsp"/>
    </c:if>

    <style>
        a {
            color: #444444;
            text-decoration: none;
            background-color: transparent;
        }

        a:hover {
            color: cornsilk;
        }

        p, h1 {
            word-wrap:break-word;
        }
    </style>
</head>
<body>

<div class="container" class="form-group">

    <div class="sub-container">

        <h1>${article.title}</h1>

        <p>${article.date}</p>

        <p>${article.body}</p>

        <input type="hidden" id="userId" value="${userId}">
        <input type="hidden" id="username" value="${username}">
        <input type="hidden" id="artId" value="${article.artId}">

        <br>


        <c:if test="${ userId != article.userId }">
            <button class="btn btn-outline-dark sm" id= ${article.artId}><a href="./article-list?page=1">Back</a></button>
        </c:if>

        <c:if test="${userId == article.userId}">
            <button class="btn btn-outline-dark sm" id= ${article.artId}><a href="./article-edit?id=${article.artId}" methods="doPost">Edit</a></button>
            <button class="btn btn-outline-dark sm" id= ${article.artId}><a href="./article-delete?artId=${article.artId}">Delete</a></button>
            <button class="btn btn-outline-dark sm" id= ${article.artId}><a href="./user-article-list?page=1">Back</a></button>
        </c:if>


    <hr>

    <div class="commentBox" >
        <div>
            <button id="nested-comment" class="btn btn-outline-dark btn-sm" style="float:right" hidden="true">Show comments</button>
            <button id="hide-comment" class="btn btn-outline-dark btn-sm" style="float:right">hide comments</button>
        </div>
        <h5>Comments: </h5>
        <div id="comments">
        <c:forEach  var="comment" items="${comments}">
            <c:if test="${comment.parentId == 0 && (!comment.hidden || comment.userId == userId || article.userId == userId)}">
                <hr>
                <div id="${comment.comId}">
                    <p class="date">${comment.comDate}</p>
                    <p>${comment.userName}:${comment.comContent}</p>
                    <c:if test="${userId != null}">
                        <button class="comment btn btn-outline-dark btn-sm" value="${comment.userName}">comment</button>
                    </c:if>
                    <c:if test="${userId == comment.userId || userId == author.userId}">
                        <button class="btn btn-outline-dark btn-sm"><a href="./comment-delete?artId=${article.artId}&&userId=${article.userId}&&comId=${comment.comId}">Delete</a></button>
                    </c:if>
                </div>
                    <c:forEach  var="subcomment" items="${comments}">
                        <c:if test="${subcomment.parentId == comment.comId && (!subcomment.hidden || subcomment.userId == userId || article.userId == userId)}">
                            <hr>
                            <div id="${subcomment.comId}" style="margin-left: 10%">
                                <p class="date">${subcomment.comDate}</p>
                                <p>${subcomment.userName}:${subcomment.comContent}</p>
                                <c:if test="${userId != null}">
                                    <button class="btn btn-outline-dark btn-sm comment"
                                            value="${subcomment.userName}">comment
                                    </button>
                                </c:if>
                                <c:if test="${userId == subcomment.userId || userId == author.userId}">
                                    <button class="btn btn-outline-dark btn-sm"><a
                                            href="./comment-delete?artId=${article.artId}&&userId=${article.userId}&&comId=${subcomment.comId}">Delete</a>
                                    </button>
                                </c:if>
                            </div>

                            <c:forEach  var="subsubcomment" items="${comments}">
                                <c:if test="${subsubcomment.parentId == subcomment.comId && (!subsubcomment.hidden || subsubcomment.userId == userId || article.userId == userId)}">
                                    <hr>
                                    <div id="${subsubcomment.comId}" style="margin-left: 20%">
                                        <p class="date">${subsubcomment.comDate}</p>
                                        <p>${subsubcomment.userName}:${subsubcomment.comContent}</p>
                                        <c:if test="${userId == subsubcomment.userId || userId == author.userId}">
                                            <button class="btn btn-outline-dark btn-sm"><a
                                                    href="./comment-delete?artId=${article.artId}&&userId=${article.userId}&&comId=${subsubcomment.comId}">Delete</a>
                                            </button>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>

            </c:if>
        </c:forEach>
        </div>

        <c:if test="${userId != null}">
        <form action="./comment-add?artId=${article.artId}&userId=${userId}&&username=${username}" method="post" >
            <div>
                <label for="new-comment">Your comment:</label><br>
                <textarea class="form-control" name="comContent" id="new-comment" style="resize: none" placeholder="Your comment here" rows="3" cols="30" required></textarea>
            </div>
            <div>
                <input type="checkbox" name="hidden" id="hidden" value="hidden">
                <label for="hidden">Only visible to author</label>
                <button class="btn btn-outline-dark" type="submit">Submit</button>
            </div>
        </form>
        </c:if>

        </div>
    </div>

</div>

</body>
</html>