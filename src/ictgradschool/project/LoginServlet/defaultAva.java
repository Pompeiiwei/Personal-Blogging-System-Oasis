package ictgradschool.project.LoginServlet;

import ictgradschool.project.User.UserInforDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = "/def")
public class defaultAva extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        UserInforDao userInforDao = new UserInforDao();
        String userName = (String) session.getAttribute("username");
        String avatarFilePath = request.getParameter("def");

        try {
            userInforDao.updateUserIcon(avatarFilePath,userName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("Profile");

    }
}
