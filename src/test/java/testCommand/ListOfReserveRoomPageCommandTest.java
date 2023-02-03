package testCommand;

import controller.commands.PageCommands.LIstOfReserveRoomPageCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.OrdersDao;
import model.entity.Orders;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ListOfReserveRoomPageCommandTest {
    private final jakarta.servlet.http.HttpServletRequest request = mock(HttpServletRequest.class);
    private final jakarta.servlet.http.HttpServletResponse response = mock(HttpServletResponse.class);
    private final jakarta.servlet.http.HttpSession session = mock(HttpSession.class);
    @Mock
    private OrdersDao ordersDao;
    private LIstOfReserveRoomPageCommand listOfReserveRoomPageCommand;
    private List<Orders> ordersReserveListUser;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        listOfReserveRoomPageCommand = new LIstOfReserveRoomPageCommand();
        ordersReserveListUser = new ArrayList<>();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("currentUserId")).thenReturn(1);
    }

    @Test
    public void execute() throws ServletException, IOException, SQLException, jakarta.servlet.ServletException {
        when(ordersDao.ordersReserveListUser(1)).thenReturn(ordersReserveListUser);
        String result = listOfReserveRoomPageCommand.execute(request, response);
        verify(request).setAttribute("ordersRequestListUser", ordersReserveListUser);
        assertEquals(result, controller.Path.PAGE_LISTOFRESERVE);
    }
}

