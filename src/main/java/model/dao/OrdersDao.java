package model.dao;

import database.DBUtil;
import model.entity.Orders;
import model.entity.Orders_request;
import model.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrdersDao {
    public void addOrder(Orders orders) throws SQLException {
        String query = "INSERT INTO orders( userId, roomId, dateOfSettlement,dateOfOut,dateOfCreateOrder,totalPrice,status) values( ?,?,?,?,?,?,?);";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, orders.getUserId());
        pst.setInt(2, orders.getRoomId());
        pst.setTimestamp(3, orders.getDateOfSettlement());
        pst.setTimestamp(4, orders.getDateOfOut());
        pst.setTimestamp(5, orders.getDateOfCreateOrder());
        pst.setInt(6, orders.getTotalPrice());
        pst.setString(7, orders.getStatus());
        pst.executeUpdate();
        pst.close();
        con.close();

    }

    public void checkDateOut() throws SQLException{
        String query = "update room JOIN orders on room.id=orders.roomId set room.status = 'available'\n" +
                " where  orders.dateOfOut = CURDATE()";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.executeUpdate();
        pst.close();
        con.close();
    }


    public void addOrderRequest(Orders_request ordersRequest) throws SQLException {
        String query = "INSERT INTO orders_request( userId,guests,dateOfSettlement,dateOfOut,dateOfCreateRequest,type_of_room,status) values(?,?,?,?,?,?,?);";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, ordersRequest.getUserId());
        pst.setInt(2, ordersRequest.getGuests());
        pst.setTimestamp(3, ordersRequest.getDateOfSettlement());
        pst.setTimestamp(4, ordersRequest.getDateOfOut());
        pst.setTimestamp(5, ordersRequest.getDateOfCreateRequest());
        pst.setString(6, ordersRequest.getType_of_room());
        pst.setString(7,ordersRequest.getStatus());
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    public void addPay(int id,String status,int userId) throws SQLException {
        String query = "INSERT INTO payment( status, ordersId,userIdpay) values(?,?,?);";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, status);
        pst.setInt(2, id);
        pst.setInt(3, userId);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    public void updateOPay(int idOrderRequest) throws SQLException {
        String query = "UPDATE payment SET status = 'Successful' WHERE id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, idOrderRequest);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    public Payment findPayByID(int id) {
        String query = "select * from payment where userIdpay = ?";
        Payment newPayment = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                newPayment = Payment.builder()
                        .id(rs.getInt("id"))
                        .ordersId(rs.getInt("ordersId"))
                        .status(rs.getString("status"))
                        .userIdpay(rs.getInt("userIdpay"))
                        .build();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPayment;
    }

    public void DeleteOrder(int id) throws SQLException {
        String query = ("DELETE FROM orders WHERE id = ?");
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    public void DeleteOrderRequest(int id) throws SQLException {
        String query = ("DELETE FROM orders_request WHERE id = ?");
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public List<Orders> ordersList() throws SQLException {
        String query = "SELECT * FROM orders ";
        ArrayList<Orders> list = new ArrayList<>();
        Orders newOrdes = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newOrdes = Orders.builder()
                    .id(rs.getInt("id"))
                    .userId(rs.getInt("userId"))
                    .dateOfCreateOrder(rs.getTimestamp("dateOfCreateOrder"))
                    .dateOfSettlement(rs.getTimestamp("dateOfSettlement"))
                    .dateOfOut(rs.getTimestamp("dateOfOut"))
                    .roomId(rs.getInt("roomId"))
                    .status(rs.getString("status"))
                    .totalPrice(rs.getInt("totalPrice"))
                    .build();
            list.add(newOrdes);
        }
        rs.close();
        con.close();
        return list;
    }


    public List<Orders> ordersListUser(int id) throws SQLException {
        String query = "SELECT * FROM orders where  userId = ?";
        ArrayList<Orders> list = new ArrayList<>();
        Orders newOrdes = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newOrdes = Orders.builder()
                    .id(rs.getInt("id"))
                    .userId(rs.getInt("userId"))
                    .dateOfCreateOrder(rs.getTimestamp("dateOfCreateOrder"))
                    .dateOfSettlement(rs.getTimestamp("dateOfSettlement"))
                    .dateOfOut(rs.getTimestamp("dateOfOut"))
                    .roomId(rs.getInt("roomId"))
                    .status(rs.getString("status"))
                    .totalPrice(rs.getInt("totalPrice"))
                    .build();
            list.add(newOrdes);
        }
        rs.close();
        con.close();
        return list;
    }
    public List<Orders_request> ordersRequests(int id) throws SQLException {
        String query = "SELECT * FROM orders_request where  userId = ?";
        ArrayList<Orders_request> list = new ArrayList<>();
        Orders_request newOrdersRequest = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newOrdersRequest = Orders_request.builder()
                    .id(rs.getInt("id"))
                    .userId(rs.getInt("userId"))
                    .dateOfCreateRequest(rs.getTimestamp("dateOfCreateRequest"))
                    .dateOfSettlement(rs.getTimestamp("dateOfSettlement"))
                    .dateOfOut(rs.getTimestamp("dateOfOut"))
                    .status(rs.getString("status"))
                    .guests(rs.getInt("guests"))
                    .build();
            list.add(newOrdersRequest);
        }
        rs.close();
        con.close();
        return list;
    }

    public List<Orders> ordersReserveListUser(int id) throws SQLException {
        String query = "SELECT b.id,b.totalPrice, b.userId, b.roomId,b.dateOfSettlement, b.dateOfOut, " +
                "b.dateOfCreateOrder,b.status, c.status as statusRoom, c.image as image " +
                "FROM room as c, orders as b where b.userId = ? and c.id=b.roomId";
        ArrayList<Orders> list = new ArrayList<>();
        Orders newOrders = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newOrders = Orders.builder()
                    .id(rs.getInt("id"))
                    .userId(rs.getInt("userId"))
                    .image(rs.getString("image"))
                    .statusRoom(rs.getString("statusRoom"))
                    .dateOfCreateOrder(rs.getTimestamp("dateOfCreateOrder"))
                    .dateOfSettlement(rs.getTimestamp("dateOfSettlement"))
                    .dateOfOut(rs.getTimestamp("dateOfOut"))
                    .roomId(rs.getInt("roomId"))
                    .status(rs.getString("status"))
                    .totalPrice(rs.getInt("totalPrice"))
                    .build();
            list.add(newOrders);
        }
        rs.close();
        con.close();
        return list;
    }


    public List<Orders_request> requestList() throws SQLException {
            String query = "SELECT b.id, b.type_of_room , b.dateOfSettlement, b.dateOfOut,b.userId, b.dateOfCreateRequest, b.guests,b.status, a.type_of_room " +
                    "as typeName, u.first_name as name,u.last_name as lastName,u.email as email FROM users as u, orders_request as b, " +
                    "type_of_room as a where a.id=b.type_of_room and u.id=b.userId";
        ArrayList<Orders_request> list = new ArrayList<>();
        Orders_request newOrdesRequest = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newOrdesRequest = Orders_request.builder()
                    .id(rs.getInt("id"))
                    .userId(rs.getInt("userId"))
                    .email(rs.getString("email"))
                    .name(rs.getString("name"))
                    .lastName(rs.getString("lastName"))
                    .dateOfCreateRequest(rs.getTimestamp("dateOfCreateRequest"))
                    .dateOfSettlement(rs.getTimestamp("dateOfSettlement"))
                    .dateOfOut(rs.getTimestamp("dateOfOut"))
                    .guests(rs.getInt("guests"))
                    .status(rs.getString("status"))
                    .type_of_room(rs.getString("type_of_room"))
                    .typeName(rs.getString("typeName"))
                    .build();
            list.add(newOrdesRequest);
        }

        rs.close();
        con.close();
        return list;
    }

    public Orders updateStatus(int orderId) {
        String query = "update orders set status = 'Successful' where id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Orders updateStatusForPayment(int orderId) {
        String query = "update orders set status = 'Waiting for paid' where id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateOrderRequest(int idOrderRequest) throws SQLException {
        String query = "UPDATE orders_request SET status = 'Successful' WHERE id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, idOrderRequest);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

}
