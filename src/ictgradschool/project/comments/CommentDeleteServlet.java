package ictgradschool.project.comments;

import ictgradschool.project.util.DBConnectionUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "comment-delete", urlPatterns ={ "/comment-delete" } )

public class CommentDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        try(Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            int artId = Integer.parseInt(req.getParameter("artId"));

            int comId = Integer.parseInt(req.getParameter("comId"));

            CommentDAO.deleteComment(comId,conn);

            resp.sendRedirect("./article-content?artId=" + artId);

        } catch (SQLException | IOException  e) {
            System.out.println(e.getMessage());
        }
    }
}
