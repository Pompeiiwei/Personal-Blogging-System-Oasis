package ictgradschool.project.LoginServlet;

import ictgradschool.project.User.LoginDAO;
import ictgradschool.project.User.LoginInformation;
import ictgradschool.project.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

/**
 * TODO This is a test servlet, and should be deleted from the project.
 */
public class LoginServelt extends HttpServlet {
    private HttpSession session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/loginPage.jsp").forward(request,response);
        doPost(request, response);
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        Writer out = response.getWriter();
        session = request.getSession(true);
        String name = request.getParameter("userId");
        String password = request.getParameter("userPassword");
        if (name==null
                || name.replaceAll(" ", "").equals("") ||password==null
                || password.replaceAll(" ", "").equals("")){
            out.write("<script language='javascript'>alert('Incorrect username or password!');" +
                    "window.location.href='index.jsp';</script>");
        }



        try(LoginDAO ud = new LoginDAO()){
            assert ud!=null;
            LoginInformation loginInformation = ud.getInfo(name);
            if (!ud.checkNameExistence(name)){
                out.write("<script language='javascript'>alert('Incorrect username or password!');" +
                        "window.location.href='index.jsp';</script>");
            }else{
            if (PasswordUtil.isExpectedPassword(password.toCharArray(),
                    PasswordUtil.base64Decode(loginInformation.getSalt()),
                    loginInformation.getIterations(),
                    PasswordUtil.base64Decode(loginInformation.getPassword()))) {

                session.setAttribute("username", name);

                session.setAttribute("userId",loginInformation.getUserId());

                response.sendRedirect("./user-article-list?page=1");
            } else  {
                out.write("<script language='javascript'>alert('Incorrect username or password!');" +
                        "window.location.href='index.jsp';</script>");
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (IllegalAccessException ce) {
                e.printStackTrace();
            } catch (InstantiationException ce) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException ce) {
                e.printStackTrace();
            } catch (ClassNotFoundException ce) {
                e.printStackTrace();
            }
            out.write("<script language='javascript'>alert('Incorrect username or password!');" +
                    "window.location.href='index.jsp';</script>");
        }catch(Exception e){e.getMessage(); e.getStackTrace();}

    }
}
