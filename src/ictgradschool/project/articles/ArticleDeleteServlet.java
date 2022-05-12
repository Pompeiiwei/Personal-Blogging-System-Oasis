package ictgradschool.project.articles;

import ictgradschool.project.comments.CommentDAO;
import ictgradschool.project.util.DBConnectionUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "article-delete", urlPatterns ={ "/article-delete" } )

public class ArticleDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        try(Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            int artId = Integer.parseInt(req.getParameter("artId"));

            int userId= (int) req.getSession().getAttribute("userId");

            CommentDAO.deleteCommentByArtId(artId, conn);

            ArticleDAO.deleteArticle(artId, conn);

            resp.sendRedirect("./user-article-list?userId=" + userId + "&&page=1");

        } catch (SQLException | IOException  e) {
            System.out.println(e.getMessage());
        }
    }
}
