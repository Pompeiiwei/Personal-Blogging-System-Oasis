package ictgradschool.project.articles;

import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "user-article-list", urlPatterns = { "/user-article-list" })

public class UserArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            int userId= (int) req.getSession().getAttribute("userId");

            List<Article> articles = ArticleDAO.getArticlesByUserId(userId, conn);

            int listNum = 20;
            int articleSize = 0;

            try {
                articleSize = articles.size();
            }catch (NullPointerException e){
                System.out.println("no article.");
            }

            int pagesNum = (int) Math.ceil((double) articleSize / listNum);

            int page = Integer.parseInt(req.getParameter("page"));

            int begin = page < 4 ? 1 : page - 3;

            int end = page > pagesNum - 3 ? pagesNum : page + 3;

            List<Article> articlesList = new ArrayList<>();

            for(int i = listNum * (page - 1); i < Math.min(listNum * page , articleSize); i++) {
                articlesList.add(articles.get(i));
            }

            req.setAttribute("begin", begin);

            req.setAttribute("end", end);

            req.setAttribute("page", page);

            req.setAttribute("articles", articlesList);

            req.getRequestDispatcher("WEB-INF/user-articles-page.jsp").forward(req, resp);

        } catch (SQLException e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }
    }

}