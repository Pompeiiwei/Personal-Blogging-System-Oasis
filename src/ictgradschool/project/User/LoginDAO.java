package ictgradschool.project.User;

import ictgradschool.project.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.*;

public class LoginDAO implements AutoCloseable{

    private  Connection connection;

    public LoginDAO() {
        try {
            this.connection =  DBConnectionUtils.getConnectionFromClasspath("connection.properties");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  Boolean checkNameExistence(String userName){
        try(PreparedStatement p = connection.prepareStatement("SELECT * FROM user WHERE userName=?")) {
            p.setString(1,userName);
            try (ResultSet rs = p .executeQuery()){
                if(rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void creatLogin(String userName,String password,String salt,int iterations){
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(userName,userPassword,salt,iterations,avatar) VALUE (?,?,?,?,?)")){
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,salt);
            preparedStatement.setInt(4,iterations);
            preparedStatement.setString(5,"DefaultAvatar/Default.png");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LoginInformation getInfo(String userName){
        LoginInformation info = null;
        try(PreparedStatement p = connection.prepareStatement("SELECT * FROM user WHERE userName = ?")){
            p.setString(1, userName);
            try (ResultSet rs = p.executeQuery()){
                while (rs.next()){
                    info = loginInformationRS(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }


    public void changeLogin(String userName,String password,String salt,int iterations){
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET userPassword = ?, salt = ? , iterations = ? WHERE userName=?;")){
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,salt);
            preparedStatement.setInt(3,iterations);
            preparedStatement.setString(4,userName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LoginInformation loginInformationRS(ResultSet resultSet) throws SQLException {
        return new LoginInformation(resultSet.getString("userName"),
                resultSet.getString("userPassword"),
                resultSet.getString("salt"),
                resultSet.getInt("iterations"),
                resultSet.getInt("userId"));
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
