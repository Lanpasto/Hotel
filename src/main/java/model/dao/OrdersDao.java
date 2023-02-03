package model.dao;

import database.DBUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Orders;
import model.entity.Orders_request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j

public class OrdersDao {
    public void addOrder(Orders orders) {
        String query = "INSERT INTO orders( userId, roomId, dateOfSettlement,dateOfOut,dateOfCreateOrder,totalPrice,status) values(?,?,?,?,?,?,?);";
        try {
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
        } catch (SQLException e) {
            log.error("add order room");
            e.printStackTrace();
        }
    }

    public void checkDateOut() {
        try {
            String query = "update room JOIN orders on room.id=orders.roomId set room.status = 'available'\n" + " where  orders.dateOfOut = CURDATE()";
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("check date out");
            e.printStackTrace();
        }
    }

    public void checkDateBooked() throws SQLException {
        String query = "update room JOIN orders on room.id=orders.roomId set room.status = 'booked'\n" + " where  orders.dateOfSettlement = CURDATE()";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public void checkOrderPaid() throws SQLException {//TODO ЗРОБИТИ ЛИСЕНЕР ЗАБОРГОВАНОСТІ
        String query = "update orders set orders.status = 'reject' where orders.status ='Waiting for paid' and DATE_ADD(DATE(orders.dateOfCreateOrder), INTERVAL 2 DAY) = CURDATE();";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.executeUpdate();
        pst.close();
        con.close();
    }


    public void addOrderRequest(Orders_request ordersRequest)   {
        String query = "INSERT INTO orders_request( userId,guests,dateOfSettlement,dateOfOut,dateOfCreateRequest,type_of_room,status) values(?,?,?,?,?,?,?);";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, ordersRequest.getUserId());
            pst.setInt(2, ordersRequest.getGuests());
            pst.setTimestamp(3, ordersRequest.getDateOfSettlement());
            pst.setTimestamp(4, ordersRequest.getDateOfOut());
            pst.setTimestamp(5, ordersRequest.getDateOfCreateRequest());
            pst.setString(6, ordersRequest.getType_of_room());
            pst.setString(7, ordersRequest.getStatus());
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error(" add order request ");
            e.printStackTrace();
        }
    }

    public void DeleteOrder(int id)   {
        String query = ("DELETE FROM orders WHERE id = ?");
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order Delete Order error");
            e.printStackTrace();
        }
    }


    public List<Orders> ordersListUser(int id) {
        String query = "SELECT * FROM orders where  userId = ?";
        ArrayList<Orders> list = null;
        try {
            list = new ArrayList<>();
            Orders newOrders;
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newOrders = Orders.builder().id(rs.getInt("id")).userId(rs.getInt("userId")).dateOfCreateOrder(rs.getTimestamp("dateOfCreateOrder")).dateOfSettlement(rs.getTimestamp("dateOfSettlement")).dateOfOut(rs.getTimestamp("dateOfOut")).roomId(rs.getInt("roomId")).status(rs.getString("status")).totalPrice(rs.getInt("totalPrice")).build();
                list.add(newOrders);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("order orders List User error");
            e.printStackTrace();
        }
        return list;
    }


    public List<Orders> ordersReserveListUser(int id)   {
        String query = "SELECT b.id,b.totalPrice, b.userId, b.roomId,b.dateOfSettlement, b.dateOfOut, " + "b.dateOfCreateOrder,b.status, c.status as statusRoom, c.image as image " + "FROM room as c, orders as b where b.userId = ? and c.id=b.roomId";
        ArrayList<Orders> list = null;
        try {
            list = new ArrayList<>();
            Orders newOrders;
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newOrders = Orders.builder().id(rs.getInt("id")).userId(rs.getInt("userId")).image(rs.getString("image")).statusRoom(rs.getString("statusRoom")).dateOfCreateOrder(rs.getTimestamp("dateOfCreateOrder")).dateOfSettlement(rs.getTimestamp("dateOfSettlement")).dateOfOut(rs.getTimestamp("dateOfOut")).roomId(rs.getInt("roomId")).status(rs.getString("status")).totalPrice(rs.getInt("totalPrice")).build();
                list.add(newOrders);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("order orders Reserve List User  error");
            e.printStackTrace();
        }
        return list;
    }


    public List<Orders_request> requestList() {
        String query = "SELECT b.id, b.type_of_room , b.dateOfSettlement, b.dateOfOut,b.userId, b.dateOfCreateRequest, b.guests,b.status, a.type_of_room " + "as typeName, u.first_name as name,u.last_name as lastName,u.email as email FROM users as u, orders_request as b, " + "type_of_room as a where a.id=b.type_of_room and u.id=b.userId";
        ArrayList<Orders_request> list = null;
        try {
            list = new ArrayList<>();
            Orders_request newOrdersRequest;
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newOrdersRequest = Orders_request.builder().id(rs.getInt("id")).userId(rs.getInt("userId")).email(rs.getString("email")).name(rs.getString("name")).lastName(rs.getString("lastName")).dateOfCreateRequest(rs.getTimestamp("dateOfCreateRequest")).dateOfSettlement(rs.getTimestamp("dateOfSettlement")).dateOfOut(rs.getTimestamp("dateOfOut")).guests(rs.getInt("guests")).status(rs.getString("status")).type_of_room(rs.getString("type_of_room")).typeName(rs.getString("typeName")).build();
                list.add(newOrdersRequest);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("order request List  error");
            e.printStackTrace();
        }
        return list;
    }

    public Orders findOrdersByRoom(int id, String Date, String Date1) {
        //TODO тута
        String query = "SELECT * FROM hotel.orders where roomId = ?" +
                " AND ( (orders.dateOfSettlement BETWEEN '" + Date + "' AND '" + Date1 + "') " +
                "OR(orders.dateOfOut BETWEEN '" + Date + "' AND '" + Date1 + "') " +
                "OR(orders.dateOfSettlement < '" + Date + "' AND orders.dateOfOut > '" + Date1 + "'))";
        Orders newOrders = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                newOrders = Orders.builder().id(rs.getInt("id")).userId(rs.getInt("userId")).roomId(rs.getInt("roomId")).dateOfSettlement(rs.getTimestamp("dateOfSettlement")).dateOfOut(rs.getTimestamp("dateOfOut")).totalPrice(rs.getInt("totalPrice")).status(rs.getString("status")).dateOfCreateOrder(rs.getTimestamp("dateOfCreateOrder")).build();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("order find Orders By Room error");
            e.printStackTrace();
        }
        return newOrders;
    }

    public void updateStatusForPayment(int orderId) {
        String query = "update orders set status = 'Waiting for paid' where id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order update Status For Payment error");
            e.printStackTrace();
        }
    }

    public void updateOrderRequest(int idOrderRequest)   {
        String query = "UPDATE orders_request SET status = 'Successful' WHERE id = ?";
        try {


            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idOrderRequest);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order update Order Request error");
            e.printStackTrace();
        }
    }

    public void updateOrder(int idOrder)   {
        String query = "UPDATE orders SET status = 'Paid' WHERE id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idOrder);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order select update Order error");
            e.printStackTrace();
        }
    }

}
