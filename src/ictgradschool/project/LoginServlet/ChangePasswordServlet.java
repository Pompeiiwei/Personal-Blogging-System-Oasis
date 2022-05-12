package ictgradschool.project.LoginServlet;

import ictgradschool.project.User.LoginDAO;
import ictgradschool.project.User.LoginInformation;
import ictgradschool.project.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class ChangePasswordServlet extends HttpServlet {
    private String username;
    private String password;
    private String newPassword;
    private String reNewPassword;
    private LoginInformation loginInfo;
    private HttpSession session;
    LoginDAO loginDAO = new LoginDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession(true);
        resp.setContentType("text/html");
        Writer out = resp.getWriter();
        username = (String) session.getAttribute("username");

        password = req.getParameter("password");
        newPassword = req.getParameter("newPassword");
        reNewPassword = req.getParameter("reNewPassword");
        loginInfo = loginDAO.getInfo(username);
        if (!newPassword.equals(reNewPassword)){
            out.write("<script language='javascript'>alert('Check your password');" +
                    "window.location.href='/Profile';</script>");
        }else{
        if (!rightPassword()) {
            out.write("<script language='javascript'>alert('Incorrect password!');" +
                    "window.location.href='/Profile';</script>");
        } else if (rightPassword()) {
            updatePassword();
            session.removeAttribute("userId");
            out.write("<script language='javascript'>alert('success!');" +
                    "window.location.href='index.jsp';</script>");
        } else {
            out.write("Fail");
        }
        }
    }
    public boolean rightPassword() {
        return PasswordUtil.isExpectedPassword(password.toCharArray(), PasswordUtil.base64Decode(loginInfo.getSalt()),
                loginInfo.getIterations(),
                PasswordUtil.base64Decode(loginInfo.getPassword()));
    }

    public void updatePassword() throws ServletException, IOException {
        byte[] newSalt = PasswordUtil.getNextSalt();
        int newIterations = new Random().nextInt(1000) + 100_000;
        byte[] newHashPassword = PasswordUtil.hash(newPassword.toCharArray(), newSalt, newIterations);
        String saltBase64=PasswordUtil.base64Encode(newSalt);
        String hashBase64=PasswordUtil.base64Encode(newHashPassword);
        loginDAO.changeLogin(username, hashBase64, saltBase64, newIterations);
        session.removeAttribute("username");
    }
}
