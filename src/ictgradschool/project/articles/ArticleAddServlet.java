package ictgradschool.project.articles;

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

@WebServlet(name = "AddServlet", urlPatterns = {"/add-article"})

public class ArticleAddServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/article-add-page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Article article = new Article();

            // Save the article to the DB.
            try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String time = localDateTime.format(dateTimeFormatter);

                article.setUserId((int) req.getSession().getAttribute("userId"));
                article.setUserName((String) req.getSession().getAttribute("username"));
                article.setDate(time);
                article.setTitle(req.getParameter("title"));
                article.setDate(time);
                article.setBody(req.getParameter("content"));

                ArticleDAO.insertArticle(article, conn);

                req.setAttribute("article", article);

            } catch (SQLException | IOException e) {

                resp.setStatus(500);
                e.printStackTrace();
                throw new ServletException("Database access error!", e);
            }


        req.getRequestDispatcher("WEB-INF/article-content-page.jsp").forward(req, resp);
    }
}