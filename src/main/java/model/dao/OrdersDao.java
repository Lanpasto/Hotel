package model.dao;

import database.DBUtil;
import model.entity.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrdersDao {
    public static void addOrder(Orders orders) throws SQLException {
        String query = ("insert into orders( id,userId, roomId, dateOfSettlement,dateOfOut, dateOfCreateOrder) " +
                "values(?,?,?,?,?,?");
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, orders.getId());
        pst.setDate(2, (Date) orders.getDateOfCreateOrder());
        pst.setDate(3, (Date) orders.getDateOfSettlement());
        pst.setDate(4, (Date) orders.getDateOfOut());
        pst.setInt(5, orders.getUserId());
        pst.setInt(5, orders.getRoomId());
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public static void DeleteOrder(int idOrder) throws SQLException {
        String query = ("DELETE FROM orders WHERE id_order = ?");
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
    }

    public List<Orders> listSelect() throws SQLException {
        String query = "SELECT * FROM orders " ;
        ArrayList<Orders> list = new ArrayList<>();
        Orders newOrdes = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newOrdes = newOrdes.builder()
                    .build();
            list.add(newOrdes);
        }
        rs.close();
        con.close();
        return list;
    }

}
