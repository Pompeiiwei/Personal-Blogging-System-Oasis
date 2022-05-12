package ictgradschool.project.LoginServlet;

import ictgradschool.project.User.UserInforDao;
import ictgradschool.project.articles.ArticleDAO;
import ictgradschool.project.comments.CommentDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteUserProfileServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        resp.setContentType("text/html");
        deleteUserProfile(resp,session);
    }

    private void deleteUserProfile(HttpServletResponse response,HttpSession session) throws IOException {
        String username = (String)session.getAttribute("username");
        try(Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            CommentDAO.deleteCommentByUsername(username, connection);
            ArticleDAO.deleteArticleByAuthorName(username, connection);
            ArticleDAO.deleteArticleByUserName(username,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        UserInforDao userInforDao = new UserInforDao();
        userInforDao.deleteUserInfo(username);
        session.removeAttribute("userProfile");
        session.removeAttribute("username");
        session.removeAttribute("userId");
        response.sendRedirect("index.jsp");
    }
}
