package ictgradschool.project.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession sess = req.getSession(true);

        sess.invalidate();

        req.setAttribute("message", "Logout successfully! \nSee you Next time! ");

        req.getRequestDispatcher("WEB-INF/message-page.jsp").forward(req,resp);
    }

}
