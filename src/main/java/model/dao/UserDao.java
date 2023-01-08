package model.dao;


import database.DBUtil;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public void insert(User user) throws SQLException {
        String query = "insert into users(email, password, first_name, last_name,roleId) values (?,?,?,?,?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, user.getEmail());
        pst.setString(2, user.getPassword());
        pst.setString(3, user.getFirst_name());
        pst.setString(4, user.getLast_name());
        pst.setInt(5, 1);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public User findUser(User user) throws SQLException{
        String query = "select * from users where email = ? and password = ?";
        User newUser = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,user.getEmail());
        pst.setString(2,user.getPassword());
        ResultSet rs = pst.executeQuery();

        if(rs.next()){
            newUser = User.builder()
                    .id(rs.getInt("id"))
                    .roleId(rs.getInt("roleId"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .build();
        }
        rs.close();
        con.close();
        return newUser;
    }
    }