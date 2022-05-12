package ictgradschool.project.LoginServlet;

import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "sign-up", urlPatterns = { "/sign-up" })
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            req.getRequestDispatcher("WEB-INF/sign-up-page.jsp").forward(req,resp);

        } catch (SQLException e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }
    }

}