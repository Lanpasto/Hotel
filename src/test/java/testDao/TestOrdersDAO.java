package testDao;

import database.DBUtil;
import model.dao.OrdersDao;
import model.entity.Orders;
import model.entity.Orders_request;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TestOrdersDAO {
    private static final Orders order = Mockito.mock(Orders.class);
    private static final Orders_request order_request = Mockito.mock(Orders_request.class);
    private static final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);

    @BeforeAll
    public static void beforeAll() {
        Mockito.mockStatic(DBUtil.class);
    }

    @Test
    public static Orders getOrders() {
        return Orders.builder()
                .userId(1)

                .roomId(1)

                .dateOfSettlement(Timestamp.valueOf("2021-03-24 16:34:26.666"))

                .dateOfOut(Timestamp.valueOf("2021-03-24 16:34:26.666"))

                .dateOfCreateOrder(Timestamp.from(Instant.now()))

                .totalPrice(1)

                .status("Waiting for paid")


                .build();
    }

    @Test
    public static Orders_request getOrdersRequest() {
        return Orders_request.builder()
                .userId(1)

                .guests(1)

                .dateOfSettlement(Timestamp.valueOf("1985‑09‑25 17:45:30.005"))
                .dateOfOut(Timestamp.valueOf("1985‑09‑25 17:45:30.005"))

                .dateOfCreateRequest(Timestamp.from(Instant.now()))

                .type_of_room("Economy")

                .status("Pending")

                .build();
    }

    @Test
    public void addOrderTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        doNothing().when(statement).setInt(eq(1), eq(1));
        doNothing().when(statement).setInt(eq(2), eq(1));
        doNothing().when(statement).setTimestamp(eq(3), any());
        doNothing().when(statement).setTimestamp(eq(4), any());
        doNothing().when(statement).setTimestamp(eq(5), any());
        doNothing().when(statement).setInt(eq(6), eq(1));
        doNothing().when(statement).setString(eq(7), any());

        when(DBUtil.getConnection()).thenReturn(jdbcConnection); // mock static
        when(jdbcConnection.createStatement()).thenReturn(statement);

        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);

        when(statement.execute()).thenReturn(true);
        OrdersDao ordersDao = new OrdersDao();


        assertDoesNotThrow(() -> ordersDao.addOrder(order));
    }

    @Test
    public void addOrderRequestTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        doNothing().when(statement).setInt(eq(1), eq(1));
        doNothing().when(statement).setInt(eq(2), eq(1));
        doNothing().when(statement).setTimestamp(eq(3), any());
        doNothing().when(statement).setTimestamp(eq(4), any());
        doNothing().when(statement).setTimestamp(eq(5), any());
        doNothing().when(statement).setString(eq(6), any());
        doNothing().when(statement).setString(eq(7), any());

        when(DBUtil.getConnection()).thenReturn(jdbcConnection); // mock static
        when(jdbcConnection.createStatement()).thenReturn(statement);

        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);

        when(statement.execute()).thenReturn(true);
        OrdersDao ordersDao = new OrdersDao();


        assertDoesNotThrow(() -> ordersDao.addOrderRequest(order_request));
    }

    @Test
    public void updateTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setInt(eq(1), eq(1));
        when(statement.execute()).thenReturn(true);

        OrdersDao ordersDao = new OrdersDao();

        assertDoesNotThrow(() -> ordersDao.updateOrder(1));
        assertDoesNotThrow(() -> ordersDao.updateOrderRequest(1));
        assertDoesNotThrow(() -> ordersDao.updateStatusForPayment(1));

    }

    @Test
    public void mockListSelectTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        Mockito.when(resultSet.getString(any()))
                .thenReturn("name").thenReturn("name_ua");
        Mockito.when(resultSet.getInt(any())).thenReturn(1);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        ;

        OrdersDao ordersDao = new OrdersDao();

        List<Orders_request> ordersFromDB = ordersDao.requestList();
        assertEquals(1, ordersFromDB.size());
    }
    @Test
    public void testOrdersReserveListUser() throws SQLException {
        int userId = 1;
        OrdersDao ordersReserveListUser = new OrdersDao();
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        // mock the result set to return the expected data when the method is called
        when(resultSet.next()).thenReturn(true, true, true, false);
        when(resultSet.getInt("id")).thenReturn(1, 2, 3);
        when(resultSet.getInt("userId")).thenReturn(userId, userId, userId);
        when(resultSet.getInt("totalPrice")).thenReturn(100, 200, 300);
        // add additional when statements to return the expected values for other properties

        // mock the connection and prepared statement to return the mocked result set when the method is called
        when(jdbcConnection.prepareStatement(Mockito.anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);

        // call the method to be tested
        List<Orders> orders = ordersReserveListUser.ordersReserveListUser(userId);

        // test if the size of the orders list is equal to the expected size
        int expectedSize = 3;
        assertEquals(expectedSize, orders.size());

        // test if the first order in the list has the expected values
        Orders firstOrder = orders.get(0);
        int expectedId = 1;
        assertEquals(expectedId, firstOrder.getId());
        int expectedUserId = userId;
        assertEquals(expectedUserId, firstOrder.getUserId());
        int expectedTotalPrice = 100;
        assertEquals(expectedTotalPrice, firstOrder.getTotalPrice());
    }

    @Test
    public void testRequestList() throws SQLException {
        OrdersDao ordersDao = new OrdersDao();

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true, true, false);
        Mockito.when(resultSet.getInt("id")).thenReturn(1, 2);
        Mockito.when(resultSet.getInt("userId")).thenReturn(1, 2);
        Mockito.when(resultSet.getString("email")).thenReturn("user1@email.com", "user2@email.com");
        Mockito.when(resultSet.getString("name")).thenReturn("User", "User");
        Mockito.when(resultSet.getString("lastName")).thenReturn("One", "Two");
        Mockito.when(resultSet.getTimestamp("dateOfCreateRequest")).thenReturn(new Timestamp(1), new Timestamp(2));
        Mockito.when(resultSet.getTimestamp("dateOfSettlement")).thenReturn(new Timestamp(1), new Timestamp(2));
        Mockito.when(resultSet.getTimestamp("dateOfOut")).thenReturn(new Timestamp(1), new Timestamp(2));
        Mockito.when(resultSet.getInt("guests")).thenReturn(1, 2);
        Mockito.when(resultSet.getString("status")).thenReturn("status1", "status2");
        Mockito.when(resultSet.getString("type_of_room")).thenReturn("type1", "type2");
        Mockito.when(resultSet.getString("typeName")).thenReturn("typeName1", "typeName2");

        when(jdbcConnection.prepareStatement(Mockito.anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);

        List<Orders_request> result = ordersDao.requestList();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(1, result.get(0).getUserId());
        assertEquals("user1@email.com", result.get(0).getEmail());
        assertEquals("User", result.get(0).getName());
        assertEquals("One", result.get(0).getLastName());
        assertEquals(new Timestamp(1), result.get(0).getDateOfCreateRequest());
        assertEquals(new Timestamp(1), result.get(0).getDateOfSettlement());
        assertEquals(new Timestamp(1), result.get(0).getDateOfOut());
        assertEquals(1, result.get(0).getGuests());
        assertEquals("status1", result.get(0).getStatus());

    }
    @Test
    public void testFindOrdersByRoom() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        int roomId = 1;
        String date = "2022-01-01";
        String date1 = "2022-01-02";
        OrdersDao ordersDao = new OrdersDao();
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("userId")).thenReturn(2);
        when(resultSet.getInt("roomId")).thenReturn(roomId);
        when(resultSet.getTimestamp("dateOfSettlement")).thenReturn(null);
        when(resultSet.getTimestamp("dateOfOut")).thenReturn(null);
        when(resultSet.getInt("totalPrice")).thenReturn(100);
        when(resultSet.getString("status")).thenReturn("Pending");
        when(resultSet.getTimestamp("dateOfCreateOrder")).thenReturn(null);

        Orders order = ordersDao.findOrdersByRoom(roomId, date, date1);
        assertNotNull(order);
        assertEquals(1, order.getId());
        assertEquals(2, order.getUserId());
        assertEquals(roomId, order.getRoomId());
    }
}

