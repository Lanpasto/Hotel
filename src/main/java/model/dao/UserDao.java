package model.dao;


import database.DBUtil;
import lombok.extern.log4j.Log4j;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Log4j
public class UserDao {
    public void insert(User user) {
        try {
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
    } catch (SQLException e) {
        log.error("insert user error");
        e.printStackTrace();
    }
    }

    public User findUser(User user) {
        User newUser = null;
        try {
            String query = "select * from users where email = ? and password = ?";
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                newUser = User.builder()
                        .id(rs.getInt("id"))
                        .roleId(rs.getInt("roleId"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .build();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("find User error");
            e.printStackTrace();
        }
        return newUser;
    }
    public User findUserById(int userId) {
        String query = "select * from user where id = ?";
        User newUser = null;
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                newUser = User.builder()
                        .id(rs.getInt("id"))
                        .first_name(rs.getString("first_name"))
                        .last_name(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .roleId(rs.getInt("roleId"))
                        .build();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    public User findUserByEmail(String email) {
        User newUser = null;
        try {
            String query = "select * from users where email = ?";
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                newUser = User.builder()
                        .id(rs.getInt("id"))
                        .first_name(rs.getString("first_name"))
                        .last_name(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .roleId(rs.getInt("roleId"))
                        .build();
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("find user by email error");
            e.printStackTrace();
        }
        return newUser;
    }
}