package ictgradschool.project.comments;

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


@WebServlet(name = "comment-add", urlPatterns = {"/comment-add"})

public class CommentAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            Comment comment = new Comment();

            comment.setUserId(Integer.parseInt(req.getParameter("userId")));

            String username = req.getParameter("username");

            comment.setUserName(username);

            int artId = Integer.parseInt(req.getParameter("artId"));
            comment.setArtId(artId);

            try {
                String hidden = req.getParameterValues("hidden")[0];
                comment.setHidden(true);
            } catch (NullPointerException ignored){}

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = localDateTime.format(dateTimeFormatter);
            comment.setComDate(time);

            comment.setComContent(req.getParameter("comContent"));

            try{
                comment.setParentId(Integer.parseInt(req.getParameter("parentId")));
            }catch (Exception e){
                System.out.println("No parentId");
            }

            CommentDAO.insertComment(comment, conn);

            req.setAttribute("comment", comment);

            resp.sendRedirect("./article-content?artId=" + artId);

        } catch (SQLException e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }

    }

}
