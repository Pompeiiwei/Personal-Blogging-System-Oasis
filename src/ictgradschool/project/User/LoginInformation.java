package ictgradschool.project.User;

import java.sql.SQLException;

public class LoginInformation {
    private String userName;
    private String password,salt;
    private int iterations;
    private int userId;

    public LoginInformation(String userName,String password,String salt, int iterations) throws SQLException {
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.iterations = iterations;
    }
    public LoginInformation(String userName,String password,String salt, int iterations,int userId) throws SQLException {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.salt = salt;
        this.iterations = iterations;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
