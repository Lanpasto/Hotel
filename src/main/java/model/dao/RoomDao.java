package model.dao;

import database.DBUtil;
import model.entity.Room;
import model.entity.Room_of_type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    private int noOfRecords;

    public void updateRoom(int id) throws SQLException {
        String query = "UPDATE room SET status = 'booked' WHERE id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.executeUpdate();
        pst.close();
        con.close();
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
                newRoom = Room.builder()
                        .id(rs.getInt("id"))
                        .typeId(rs.getInt("typeId"))
                        .guests(rs.getInt("guests"))
                        .price(rs.getInt("price"))
                        .status(rs.getString("status"))
                        .build();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newRoom;
    }

    public void updateRoomStatusManager(int id, String status) throws SQLException {
        String query = "UPDATE room SET status = ? WHERE id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, status);
        pst.setInt(2, id);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public void RequestManager(int id) throws SQLException {
        String query = "UPDATE room SET status = 'Waiting for confirmation' WHERE id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public List<Room> roomList() throws SQLException {
        String query = "SELECT r.id, r.guests, r.typeId,r.price,r.image, a.type_of_room" +
                " FROM room as r JOIN type_of_room as a on a.id = r.typeId " +
                "AND r.status = 'available' group by r.id, r.image, r.guests, r.typeId,r.price";
        ArrayList<Room> list = new ArrayList<>();
        Room newRoom = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newRoom = Room.builder()
                    .id(rs.getInt("id"))
                    .guests(rs.getInt("guests"))
                    .typeName(rs.getString("type_of_room"))
                    .typeId(rs.getInt("typeId"))
                    .price(rs.getInt("price"))
                    .image(rs.getString("image"))
                    .build();
            list.add(newRoom);
        }
        rs.close();
        con.close();
        return list;
    }

    public List<Room> roomAllList() throws SQLException {
        String query = "SELECT b.id,b.guests,b.typeId,b.status,b.price,b.image, a.type_of_room " +
                "as typeName FROM room as b, type_of_room as " +
                "a where a.id=b.typeId";
        ArrayList<Room> list = new ArrayList<>();
        Room newRoom = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newRoom = Room.builder()
                    .id(rs.getInt("id"))
                    .guests(rs.getInt("guests"))
                    .typeId(rs.getInt("typeId"))
                    .typeName(rs.getString("typeName"))
                    .status(rs.getString("status"))
                    .price(rs.getInt("price"))
                    .image(rs.getString("image"))
                    .build();
            list.add(newRoom);
        }
        rs.close();
        con.close();
        return list;
    }

    public List<Room> roomAllAvailableRoomForRequest() throws SQLException {
        String query = "SELECT b.id,b.guests,b.typeId,b.status,b.price,b.image, a.type_of_room " +
                "as typeName FROM room as b, type_of_room as " +
                "a where b.status = 'available' and b.typeId =a.id";
        ArrayList<Room> list = new ArrayList<>();
        Room newRoom = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newRoom = Room.builder()
                    .id(rs.getInt("id"))
                    .guests(rs.getInt("guests"))
                    .typeId(rs.getInt("typeId"))
                    .typeName(rs.getString("typeName"))
                    .status(rs.getString("status"))
                    .price(rs.getInt("price"))
                    .image(rs.getString("image"))
                    .build();
            list.add(newRoom);
        }
        rs.close();
        con.close();
        return list;
    }

    public List<Room_of_type> roomOfTypesList() throws SQLException {
        String query = "SELECT * FROM type_of_room ";
        ArrayList<Room_of_type> list = new ArrayList<>();
        Room_of_type newRoom = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newRoom = Room_of_type.builder()
                    .id(rs.getInt("id"))
                    .guests(rs.getString("guests"))
                    .type_of_room(rs.getString("type_of_room"))
                    .image(rs.getString("image"))
                    .build();
            list.add(newRoom);
        }
        rs.close();
        con.close();
        return list;
    }

    public List<Room> sortingRoomByStatus(Room room) {
        String surQuery = "";
        if (!(room.getStatus() == null) && !room.getStatus().isEmpty()) {
            surQuery += "and r.status = '" + room.getStatus() + "'";
        }
        String query = "SELECT r.id, r.guests, r.typeId,r.price,r.image,r.status, a.type_of_room as typeName" +
                " FROM room as r JOIN type_of_room as a on a.id = r.typeId " +
                 surQuery + "group by r.id, r.image, r.guests, r.typeId,r.price";
        ArrayList<Room> list = new ArrayList<>();
        Room newRoom = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newRoom = Room.builder()
                        .id(rs.getInt("id"))
                        .guests(rs.getInt("guests"))
                        .typeId(rs.getInt("typeId"))
                        .price(rs.getInt("price"))
                        .image(rs.getString("image"))
                        .typeName(rs.getString("typeName"))
                        .status(rs.getString("status"))
                        .build();
                list.add(newRoom);
            }
            rs.close();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Room> sortingRoomByClassGuestsPrice(Room room/*, int offset, int noOfRecords*/) {
        String surQuery = "";
        if (!(room.getTypeName() == null) && !room.getTypeName().isEmpty()) {
            surQuery += " and a.type_of_room = '" + room.getTypeName() + "'";

        }
        if (!(room.getGuests() == null) && room.getGuests() != 0) {
            surQuery += " and r.guests = " + room.getGuests() + "  ";

        }
        if (room.getPrice() != null && room.getPrice()!=0) {
             int fromPrice = room.getPrice();;
             int byPrice =  room.getPrice();
             surQuery += " and (r.price > '" + fromPrice + "' and  '" + byPrice + "' > r.price)";
             System.out.println(surQuery+"dsdsdsds");
         }


        String query = "SELECT r.id, r.guests, r.typeId,r.price,r.image, a.type_of_room as typeName" +
                " FROM room as r JOIN type_of_room as a on a.id = r.typeId " +
                "AND r.status = 'available'" + surQuery + "group by r.id, r.image, r.guests, r.typeId,r.price";
        //"ORDER BY o.id DESC limit " + offset + ", " + noOfRecords + ";";//
        ArrayList<Room> list = new ArrayList<>();
        Room newRoom = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newRoom = Room.builder()
                        .id(rs.getInt("id"))
                        .guests(rs.getInt("guests"))
                        .typeId(rs.getInt("typeId"))
                        .price(rs.getInt("price"))
                        .image(rs.getString("image"))
                        .typeName(rs.getString("typeName"))
                        .build();
                list.add(newRoom);
            }
            rs.close();

          //  rs = pst.executeQuery("SELECT FOUND_ROWS()");

          //  if (rs.next())
            //    this.noOfRecords = rs.getInt(1);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getNoOfRecords() {
        return noOfRecords;
    }


}

