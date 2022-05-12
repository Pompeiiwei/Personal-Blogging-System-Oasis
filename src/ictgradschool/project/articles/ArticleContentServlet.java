package ictgradschool.project.articles;

import ictgradschool.project.User.*;
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
import java.util.List;



@WebServlet(name = "article-content", urlPatterns = { "/article-content" })

public class ArticleContentServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")){

            int artId = Integer.parseInt(req.getParameter("artId"));

            Article article = ArticleDAO.getArticleById(artId, conn);

            req.setAttribute("article", article);

            List<Comment> comments = CommentDAO.getCommentsByArtId(artId,conn);

            req.setAttribute("comments", comments);

            User author = UserInforDao.getUserInfoById(article.getUserId(),conn);

            author.setUserId(article.getUserId());

            req.setAttribute("author", author);

            req.getRequestDispatcher("WEB-INF/article-content-page.jsp").forward(req,resp);

        }catch (SQLException e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }

    }
}
