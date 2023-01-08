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
    public void insertRoom(Room room) throws SQLException {
        String query = "INSERT INTO room(id, quests, typeId,price,status) VALUES (?,?,?,?,?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, room.getId());
        pst.setInt(2, room.getGuests());
        pst.setInt(3, room.getTypeId());
        pst.setInt(4,room.getPrice());
        pst.setString(5, room.getStatus());
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    public void updateRoom(int id) throws SQLException {
        String query = "UPDATE room SET status = 'no_available' WHERE id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    public List<Room> roomList() throws SQLException {
        String query = "SELECT r.guests, r.typeId,r.price,r.image, count(*), a.type_of_room" +
                " FROM room as r JOIN type_of_room as a on a.id = r.typeId " +
                "AND r.status = 'available' group by r.image, r.guests, r.typeId,r.price";
        ArrayList<Room> list = new ArrayList<>();
        Room newRoom = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newRoom = Room.builder()
                    .guests(rs.getInt("guests"))
                    .typeName(rs.getString("type_of_room"))
                    .typeId(rs.getInt("typeId"))
                    .price(rs.getInt("price"))
                    .image(rs.getString("image"))
                    .count(rs.getInt("count(*)"))
                    .build();
            list.add(newRoom);
        }
        rs.close();
        con.close();
        return list;
    }
    public List<Room_of_type> roomOfTypesList() throws SQLException {
        String query = "SELECT * FROM type_of_room " ;
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

    }

