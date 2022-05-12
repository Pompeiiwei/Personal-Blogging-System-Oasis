package ictgradschool.project.articles;

import ictgradschool.project.comments.Comment;
import ictgradschool.project.comments.CommentDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@WebServlet(name = "article-edit", urlPatterns = { "/article-edit" })

public class ArticleEditServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            String id = req.getParameter("id");

            Article article = ArticleDAO.getArticleById(Integer.parseInt(id), conn);

            req.setAttribute("article", article);

            req.getRequestDispatcher("WEB-INF/article-edit-page.jsp").forward(req, resp);


        } catch (SQLException e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            Article article = new Article();

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = localDateTime.format(dateTimeFormatter);

            int artId = Integer.parseInt(req.getParameter("id"));
            article.setArtId(Integer.parseInt(req.getParameter("id")));
            article.setTitle(req.getParameter("title"));
            article.setUserId((int) req.getSession().getAttribute("userId"));
            article.setUserName((String) req.getSession().getAttribute("username"));
            article.setDate(time);
            article.setBody(req.getParameter("body"));

            ArticleDAO.editArticle(article, conn);
            List<Comment> comments = CommentDAO.getCommentsByArtId(artId,conn);

            req.setAttribute("article", article);
            req.setAttribute("comments",comments);

            req.getRequestDispatcher("WEB-INF/article-content-page.jsp").forward(req, resp);

        } catch (SQLException e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }
    }
}

