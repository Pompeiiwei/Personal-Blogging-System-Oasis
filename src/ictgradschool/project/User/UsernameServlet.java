package ictgradschool.project.User;

import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "username-compare", urlPatterns ={ "/username-compare" } )

public class UsernameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            String username = req.getParameter("username");

            boolean isExist = UsernameInfoDao.getUserInfoById(username,conn);

            JSONResponse.send(resp, isExist);

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}