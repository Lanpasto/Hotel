package testDao;

import database.DBUtil;
import model.dao.UserDao;
import model.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TestUserDAO {
    private static final User user = Mockito.mock(User.class);
    private static final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);



    @BeforeAll
    public static void beforeAll(){

    }

    public static User getTestUser() {
        return User.builder()
                .id(1)
                .first_name("firstName")
                .last_name("lastName")
                .email("email")
                .password("password")
                .roleId(1)
                .build();
    }


    @Test
    public void mockUserInsertTest() throws SQLException {
        doNothing().when(statement).setString(eq(1), any());
        doNothing().when(statement).setString(eq(2), any());
        doNothing().when(statement).setString(eq(3), any());
        doNothing().when(statement).setString(eq(4), any());
        doNothing().when(statement).setInt(eq(5), eq(1));
        doNothing().when(statement).setString(eq(6), any());

        when(user.getFirst_name()).thenReturn("firstName");
        when(user.getLast_name()).thenReturn("lastName");
        when(user.getEmail()).thenReturn("vww@gmail.com");
        when(user.getPassword()).thenReturn("1234");

        when(DBUtil.getConnection()).thenReturn(jdbcConnection); // mock static
        when(jdbcConnection.createStatement()).thenReturn(statement);

        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);

        when(statement.execute()).thenReturn(true);
        UserDao userDao = new UserDao();

        assertDoesNotThrow(() -> userDao.insert(user));
    }

    @Test
    public void mockFindUserTest() throws SQLException {
        Mockito.when(resultSet.getString(any()))
                .thenReturn("email").thenReturn("password")
                .thenReturn("firstName").thenReturn("lastName");


        doNothing().when(statement).setString(eq(1), any());
        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(2.0);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setString(eq(1), any());

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true)
                .thenReturn(false);

        UserDao userDao = new UserDao();


        User resultUser = userDao.findUser(user);
        assertNotNull(resultUser);
        assertEquals(getTestUser(), resultUser);
    }

    @Test
    public void mockUserByEmailTest() throws SQLException {
        Mockito.when(resultSet.getString(any()))
                .thenReturn("firstName").thenReturn("lastName")
                .thenReturn("email").thenReturn("password");

        doNothing().when(statement).setString(eq(1), any());
        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(2.0);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);


        doNothing().when(statement).setString(eq(1), any());

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true)
                .thenReturn(false);

        UserDao userDao = new UserDao();


        User resultUser = userDao.findUserByEmail("email");
        assertNotNull(resultUser);
        assertEquals(getTestUser(), resultUser);
    }
}