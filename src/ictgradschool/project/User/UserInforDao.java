package ictgradschool.project.User;

import ictgradschool.project.articles.ArticleDAO;
import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInforDao implements AutoCloseable {
    @Override
    public void close() throws Exception {

    }

    private Connection connection;

    public UserInforDao(){
        try {
            this.connection =  DBConnectionUtils.getConnectionFromClasspath("connection.properties");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserInfo(String username){
        User user = null;
        try(PreparedStatement p =connection.prepareStatement("SELECT * FROM user WHERE userName =? ")){
          p.setString(1,username);
          try(ResultSet r = p.executeQuery()){
              while (r.next()){
                  user = userInfoFromResultSet(r);
              }
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void updateUserIcon(String avatar,String userName) throws SQLException {
        try(PreparedStatement p = connection.prepareStatement("UPDATE user SET avatar=? WHERE userName=?")){
            p.setString(1,avatar);
            p.setString(2,userName);
            p.executeUpdate();
        }
    }


    public void updateUserInfo(String username, String newUsername, String newFirstName, String newLastName, String newEmail, String newBirthDate, String newGender,String newDes){
        try (PreparedStatement p = connection.prepareStatement("UPDATE user SET userName=?, firstName = ?, lastName=?, email=?, birthDate=?, gender = ?,description=? WHERE username = ?;")) {
            p.setString(1, newUsername);
            p.setString(2, newFirstName);
            p.setString(3, newLastName);
            p.setString(4, newEmail);
            p.setString(5, newBirthDate);
            p.setString(6, newGender);
            p.setString(7, newDes);
            p.setString(8,username);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteUserInfo(String username){
      try (PreparedStatement p =connection.prepareStatement("DELETE FROM user WHERE username = ?")){
          p.setString(1,username);
          p.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }

    public static User getUserInfoById(int userId, Connection conn) throws SQLException {
        User user = null;
        try(PreparedStatement p = conn.prepareStatement("SELECT * FROM user WHERE userId =? ")){
            p.setInt(1,userId);
            try(ResultSet r = p.executeQuery()){
                while (r.next()){
                    user = userInfoFromResultSet(r);
                }
            }
        }
        return user;
    }
    public static User userInfoFromResultSet(ResultSet r) throws SQLException {
        return new User(
                r.getString("username"),
                r.getString("firstName"),
                r.getString("lastName"),
                r.getString("email"),
                r.getString("birthDate"),
                r.getString("gender"),
                r.getString("avatar"),
                r.getString("description"));
    }

}

