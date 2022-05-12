package ictgradschool.project.LoginServlet;

import ictgradschool.project.User.User;
import ictgradschool.project.User.UserInforDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        String action = request.getParameter("profileAction");
        if (action == null) {
            getUserProfile(request, response, session);
        } else {
            switch (action) {
                case "Update":
                    updateUserProfile(request, response, session);
                    break;
            }
        }
    }
    private void getUserProfile(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException {
        UserInforDao userInforDao = new UserInforDao();
        String username = (String) session.getAttribute("username");

        User userProf = userInforDao.getUserInfo(username);
        request.setAttribute("userProfile", userProf);
        List<String> iconList = AvatarEditServlet.iconList();
        request.setAttribute("iconList",iconList);
        request.getRequestDispatcher("WEB-INF/UpdateUserProfile.jsp").forward(request, response);

    }

    private void updateUserProfile(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        UserInforDao userInforDao = new UserInforDao();
        String username = (String) session.getAttribute("username");
        String newUsername = request.getParameter("username");
        String newFirstName = request.getParameter("firstName");
        String newLastName = request.getParameter("lastName");
        String newEmail = request.getParameter("email");
        String newBirthday = request.getParameter("birthday");
        String newGender = request.getParameter("gender");
        String newDes = request.getParameter("description");
        try {
            userInforDao.updateUserInfo( username, newUsername, newFirstName, newLastName, newEmail, newBirthday, newGender,newDes);
            session.setAttribute("username", newUsername);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("Profile");
    }
    private void deleteUserProfile(HttpServletResponse response,HttpSession session) throws IOException {

            UserInforDao userInforDao = new UserInforDao();
            String username = (String)session.getAttribute("username");
            userInforDao.deleteUserInfo(username);
            session.removeAttribute("userProfile");
            session.removeAttribute("username");
            response.sendRedirect("index.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

}
