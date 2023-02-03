package testDao;

import database.DBUtil;
import model.dao.RoomDao;
import model.entity.Room;
import model.entity.Room_of_type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TestRoomDAO {
    private static final Room room = Mockito.mock(Room.class);
    private static final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);


    @BeforeAll
    public static void beforeAll() {

    }
    public static Room getTestRoom() {
        return Room.builder()
                .id(1)
                .typeId(1)
                .guests(1)
                .price(1)
                .status("id")

                .build();
    }
    @Test
    public void updateTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setInt(eq(1), eq(1));
        when(statement.execute()).thenReturn(true);

        RoomDao roomDao = new RoomDao();

        assertDoesNotThrow(() -> roomDao.updateRoom(1));
        assertDoesNotThrow(() -> roomDao.updateRoomStatusManager(1,"abobus"));
    }
    @Test
    public void findByIdTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setInt(eq(1), eq(1));

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true)
                .thenReturn(false);

        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getTimestamp(any())).thenReturn(Timestamp.valueOf("2021-03-24 16:34:26.666"));
        Mockito.when(resultSet.getDouble(any())).thenReturn(1.0);
        Mockito.when(resultSet.getString(any()))
                .thenReturn("id").thenReturn("description");

        RoomDao roomDao = new RoomDao();

        Room resultOrder = roomDao.findRoomById(1);
        assertNotNull(resultOrder);
        assertEquals(getTestRoom(), resultOrder);

    }

    @Test
    public void roomOfTypesListTest() throws SQLException {
        String query = "SELECT * FROM type_of_room ";
        List<Room_of_type> expectedResult = new ArrayList<>();
        Room_of_type room1 = Room_of_type.builder().id(1).guests("2 guests").type_of_room("Double Room").image("room1.jpg").build();
        Room_of_type room2 = Room_of_type.builder().id(2).guests("4 guests").type_of_room("Family Room").image("room2.jpg").build();
        expectedResult.add(room1);
        expectedResult.add(room2);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(resultSet.getString("guests")).thenReturn("2 guests").thenReturn("4 guests");
        when(resultSet.getString("type_of_room")).thenReturn("Double Room").thenReturn("Family Room");
        when(resultSet.getString("image")).thenReturn("room1.jpg").thenReturn("room2.jpg");
        RoomDao roomDao = new RoomDao();
        List<Room_of_type> result = roomDao.roomOfTypesList();

        assertEquals(expectedResult, result);
    }
}
