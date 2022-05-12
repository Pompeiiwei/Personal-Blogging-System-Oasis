package ictgradschool.project.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsernameInfoDao {
    public static boolean getUserInfoById(String userName, Connection conn) throws SQLException {
        try(PreparedStatement p = conn.prepareStatement("SELECT * FROM user WHERE userName =? ")){
            p.setString(1,userName);
            try(ResultSet r = p.executeQuery()){
                if (r.next()){
                    return false;
                }
            }
        }
        return true;
    }
}
