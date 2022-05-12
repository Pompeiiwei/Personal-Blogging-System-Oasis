package ictgradschool.project.LoginServlet;

import ictgradschool.project.User.LoginDAO;
import ictgradschool.project.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class RegisterServlet extends HttpServlet {
    private HttpSession session;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(true);
        response.setContentType("text/html");
        Writer out = response.getWriter();
        LoginDAO loginDAO = new LoginDAO();
        String username = request.getParameter("userId");
        String password = request.getParameter("userPassword");
        String rePassword = request.getParameter("Re-userPassword");
        if (username == null
                || username.contains(" ")
                || password == null
                || password.replaceAll(" ", "").equals("")) {

            out.write("<script language='javascript'>alert('can't have space');" +
                    "window.location.href='index.jsp';</script>");

        } else if (!rePassword.equals(password)){
            out.write("<script language='javascript'>alert('Check your passwords');" +
                    "window.location.href='index.jsp';</script>");
        }else {
            if (loginDAO.checkNameExistence(username)) {

                out.write("<script language='javascript'>alert('this Name already exist');" +
                        "window.location.href='index.jsp';</script>");

            } else {
                byte[] salt = PasswordUtil.getNextSalt();
                int iterations = new Random().nextInt(1000) + 100_000;
                byte[] hashPassword = PasswordUtil.hash(password.toCharArray(), salt, iterations);
                String saltBase64=PasswordUtil.base64Encode(salt);
                String hashBase64=PasswordUtil.base64Encode(hashPassword);
                loginDAO.creatLogin(username, hashBase64, saltBase64, iterations);
                out.write("<script language='javascript'>alert('success!');" +
                        "</script>");
                session.setAttribute("username", username);
                request.getRequestDispatcher("Profile").forward(request, response);
            }
        }
    }
}
