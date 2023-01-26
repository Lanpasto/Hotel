package model.dao;

import database.DBUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Room;
import model.entity.Room_of_type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class RoomDao {
    private int noOfRecords;

    public void updateRoom(int id) {
        String query = "UPDATE room SET status = 'booked' WHERE id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("update Room error");
            e.printStackTrace();
        }
    }

    public Room findRoomById(int idRoom) {
        String query = "select * from room where id = ?";
        Room newRoom = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idRoom);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                newRoom = Room.builder().id(rs.getInt("id")).typeId(rs.getInt("typeId")).guests(rs.getInt("guests")).price(rs.getInt("price")).status(rs.getString("status")).build();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("find Room By Id error");
            e.printStackTrace();
        }
        return newRoom;
    }

    public void updateRoomStatusManager(int id, String status) {
        String query = "UPDATE room SET status = ? WHERE id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, status);
            pst.setInt(2, id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("update Room Status Manager error");
            e.printStackTrace();
        }
    }

    public void RequestManager(int id) {
        String query = "UPDATE room SET status = 'Waiting for confirmation' WHERE id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("cargo add order error");
            e.printStackTrace();
        }
    }


    public List<Room> roomAllAvailableRoomForRequest(String DateOfSet, String DateOfOut){
        String query = "SELECT * FROM room, orders where" +
                " (room.id=orders.roomId AND ( (orders.dateOfSettlement BETWEEN '" + DateOfSet + "' AND '" + DateOfOut + "') " +
                "OR(orders.dateOfOut BETWEEN '" + DateOfSet + "' AND '" + DateOfOut + "') " +
                "OR(orders.dateOfSettlement < '" + DateOfSet + "' AND orders.dateOfOut > '" + DateOfOut + "'))";
        ArrayList<Room> list = null;
        try {
            list = new ArrayList<>();
            Room newRoom;
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newRoom = Room.builder().
                        id(rs.getInt("id")).
                        guests(rs.getInt("guests")).
                        typeId(rs.getInt("typeId")).
                        status(rs.getString("status")).
                        price(rs.getInt("price")).
                        image(rs.getString("image")).build();
                list.add(newRoom);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
           // log.error("room All Available Room For Request error");
            e.printStackTrace();
        }
        return list;
    }

    public List<Room_of_type> roomOfTypesList() {
        String query = "SELECT * FROM type_of_room ";
        ArrayList<Room_of_type> list = null;
        try {
            list = new ArrayList<>();
            Room_of_type newRoom;
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newRoom = Room_of_type.builder().id(rs.getInt("id")).guests(rs.getString("guests")).type_of_room(rs.getString("type_of_room")).image(rs.getString("image")).build();
                list.add(newRoom);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("room Of Types List error");
            e.printStackTrace();
        }
        return list;
    }

    public List<Room> sortingRoomByStatus(Room room, int offset, int noOfRecords) {
        String surQuery = "";
        if (!(room.getStatus() == null) && !room.getStatus().isEmpty()) {
            surQuery += "and r.status = '" + room.getStatus() + "'";
        }
        String query = "SELECT SQL_CALC_FOUND_ROWS r.id, r.guests, r.typeId,r.price,r.image,r.status, a.type_of_room as typeName" + " FROM room as r JOIN type_of_room as a on a.id = r.typeId " + surQuery + "group by r.id, r.image, r.guests, r.typeId,r.price" + "" + " ORDER BY  r.id DESC limit " + offset + ", " + noOfRecords + ";";
        ArrayList<Room> list = null;
        try {
            list = new ArrayList<>();
            Room newRoom;
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newRoom = Room.builder().id(rs.getInt("id")).guests(rs.getInt("guests")).typeId(rs.getInt("typeId")).price(rs.getInt("price")).image(rs.getString("image")).typeName(rs.getString("typeName")).status(rs.getString("status")).build();
                list.add(newRoom);
            }
            rs.close();

            rs = pst.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next()) this.noOfRecords = rs.getInt(1);
            con.close();
        } catch (SQLException e) {
            log.error("sorting Room By Status error");
            e.printStackTrace();
        }
        return list;
    }


    public List<Room> sortingRoomByClassGuestsPrice(Room room, int offset, int noOfRecords) {
        String surQuery = "";
        if (!(room.getTypeName() == null) && !room.getTypeName().isEmpty()) {
            surQuery += " and a.type_of_room = '" + room.getTypeName() + "'";

        }
        if (!(room.getGuests() == null) && room.getGuests() != 0) {
            surQuery += " and r.guests = " + room.getGuests() + "  ";

        }
        if (room.getFromPrice() != 0) {
            int fromPrice = room.getFromPrice();

            surQuery += "  and " + fromPrice + " <= r.price ";
        }
        if (room.getByPrice() != 0) {
            int byPrice = room.getByPrice();
            surQuery += " and  " + byPrice + " >= r.price ";
        }

        String query = "SELECT SQL_CALC_FOUND_ROWS r.id, r.guests, r.typeId,r.price,r.image, a.type_of_room as typeName" + " FROM room as r JOIN type_of_room as a on a.id = r.typeId " + "AND r.status = 'available'" + surQuery + " group by r.id, r.image, r.guests, r.typeId,r.price" + " ORDER BY  r.id DESC limit " + offset + ", " + noOfRecords + ";";
        ArrayList<Room> list = null;
        try {
            list = new ArrayList<>();
            Room newRoom;
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newRoom = Room.builder().id(rs.getInt("id")).guests(rs.getInt("guests")).typeId(rs.getInt("typeId")).price(rs.getInt("price")).image(rs.getString("image")).typeName(rs.getString("typeName")).build();
                list.add(newRoom);
            }
            rs.close();

            rs = pst.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next()) this.noOfRecords = rs.getInt(1);
            con.close();
        } catch (SQLException e) {
            log.error("sorting Room By Class Guests Price error");
            e.printStackTrace();
        }
        return list;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }


}

