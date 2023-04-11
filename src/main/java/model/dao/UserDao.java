package model.dao;


import database.DBUtil;
import lombok.extern.log4j.Log4j;
import model.entity.User;
import org.mindrot.jbcrypt.BCrypt;

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
            pst.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
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
            String query = "SELECT * FROM users WHERE email = ?";
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getEmail());
            ResultSet rs = pst.executeQuery();

            if (rs.next() && BCrypt.checkpw(user.getPassword(), rs.getString("password"))) {
                newUser = User.builder()
                        .id(rs.getInt("id"))
                        .roleId(rs.getInt("roleId"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .first_name(rs.getString("first_name"))
                        .last_name(rs.getString("last_name"))
                        .build();
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("find User error");
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

    public String findEmailById(int id) {
        String email = null;
        try {
            String query = "select email from users where id = ?";
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("find email by id error");
            e.printStackTrace();
        }
        return email;
    }

    public String findFullNameById(int id) {
        String fullName = null;
        try {
            String query = "select first_name, last_name from users where id = ?";
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                fullName = firstName + " " + lastName;
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("find full name by id error");
            e.printStackTrace();
        }
        return fullName;
    }


}