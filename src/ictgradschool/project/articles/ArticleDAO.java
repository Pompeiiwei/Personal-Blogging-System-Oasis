package ictgradschool.project.articles;

import ictgradschool.project.comments.CommentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleDAO {

    public static List<Article> getAllArticles(Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        try(Statement stmt = conn.createStatement()) {

           try(ResultSet rs = stmt.executeQuery("SELECT * FROM articles")) {

                while (rs.next()) {

                    Article article = createArticleFromResultSet(rs);
                    articles.add(article);
                }
            }
        }

        Collections.sort(articles);

        return articles;
    }

    public static Article getArticleById(int artId, Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(

                "SELECT * FROM articles WHERE artId = ?")) {

            stmt.setInt(1, artId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Article article = createArticleFromResultSet(rs);
                    articles.add(article);
                }
            }

            if (articles.size() > 0 ) return articles.get(0);
            else {
                System.out.println("Not article of id " + artId + " !");
                return null;
            }
        }
    }

    public static List<Article> getArticlesByUserId(int userId, Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(

                "SELECT * FROM articles WHERE userId = ?")) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Article article = createArticleFromResultSet(rs);
                    articles.add(article);
                }
            }

            if(articles.size() == 0) {
                System.out.println("No article of the user " + userId + " !");
                return null;
            }else {
                return articles;
            }
        }
    }

    public static List<Article> getArticlesByUserName(String userName, Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(

                "SELECT * FROM articles WHERE userName = ?")) {

            stmt.setString(1, userName);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Article article = createArticleFromResultSet(rs);
                    articles.add(article);
                }
            }

            if(articles.size() == 0) {
                System.out.println("No article of the user " + userName + " !");
                return null;
            }else {
                return articles;
            }
        }
    }


    public static List<Article> getArticlesMatching(String title, Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM articles WHERE title = ?")) {

            stmt.setString(1, title);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Article article = createArticleFromResultSet(rs);
                    articles.add(article);
                }
            }
        }
        return articles;
    }

    public static List<Article> getArticlesMatching(int userId, String title, Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(

                "SELECT * FROM articles WHERE userId = ? AND title = ? ")) {

            stmt.setInt(1, userId);
            stmt.setString(2,title);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Article article = createArticleFromResultSet(rs);
                    articles.add(article);
                }
            }
        }
        return articles;
    }


    public static boolean insertArticle(Article article, Connection conn) throws SQLException {

        try(PreparedStatement stmt = conn.prepareStatement(

                "INSERT INTO articles (userId, userName, title, date, body) VALUES (?, ?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, article.getUserId());
            stmt.setString(2, article.getUserName());
            stmt.setString(3, article.getTitle());
            stmt.setString(4, article.getDate());
            stmt.setString(5, article.getBody());

            int rowsAffected = stmt.executeUpdate(); // rowsAffected will be 1 if the insert was successful (because we're trying to insert 1 row), or 0 if it wasn't.

            if(rowsAffected == 0){  // If we didn't actually insert anything, we can't continue.
                return false;
            }
            // This code will allow us to get the primary keys that were generated by the database.
            // These will be returned in a ResultSet with one column.
            // Each row will correspond to one generated key (in case we inserted multiple rows at once).
            // In this case, there will be only one row since we only inserted one lecturer.
            try(ResultSet keys = stmt.getGeneratedKeys()){
                keys.next(); //Move to the first row
                int artId = keys.getInt(1);
                article.setArtId(artId);
                return  true;
            }
        }
    }

    public static boolean editArticle(Article article, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(

                "UPDATE articles SET userId = ?, userName=?, title = ?, date = ?, body = ? WHERE artId = ?")) {

            stmt.setInt(1, article.getUserId());
            stmt.setString(2, article.getUserName());
            stmt.setString(3, article.getTitle());
            stmt.setString(4, article.getDate());
            stmt.setString(5, article.getBody());
            stmt.setInt(6, article.getArtId());

            int rowsAffected = stmt.executeUpdate(); // rowsAffected will be 1 if the insert was successful (because we're trying to insert 1 row), or 0 if it wasn't.

            if (rowsAffected == 0) {  // If we didn't actually insert anything, we can't continue.v
                System.out.println("insert nothing!");
                return false;
            }
            // This code will allow us to get the primary keys that were generated by the database.
            // These will be returned in a ResultSet with one column.
            // Each row will correspond to one generated key (in case we inserted multiple rows at once).
            // In this case, there will be only one row since we only inserted one lecturer.
            else return true;
        }
    }

    public static void deleteArticle(int artId, Connection conn) throws SQLException{

        try (PreparedStatement stmt = conn.prepareStatement(
                "DELETE  FROM articles WHERE artId = ?")) {

            stmt.setInt(1, artId);

            int rowAffected = stmt.executeUpdate();
            if (rowAffected == 1) {
                System.out.println("Deleted successfully!");
            }
        }
    }
    public static void deleteArticleByUserName(String userName, Connection conn) throws SQLException{

        try (PreparedStatement stmt = conn.prepareStatement(
                "DELETE  FROM articles WHERE userName = ?")) {

            stmt.setString(1, userName);

            int rowAffected = stmt.executeUpdate();
            if (rowAffected == 1) {
                System.out.println("Deleted successfully!");
            }
        }
    }

    public static void deleteArticleByAuthorName(String userName, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(

                "SELECT * FROM articles WHERE userName = ?")) {

            stmt.setString(1, userName);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Article article = createArticleFromResultSet(rs);
                    CommentDAO.deleteCommentByArtId(article.getArtId(),conn);
                }
            }
        }
    }




    public static Article createArticleFromResultSet(ResultSet rs) throws SQLException{
        return new Article(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6)
        );
    }

}

