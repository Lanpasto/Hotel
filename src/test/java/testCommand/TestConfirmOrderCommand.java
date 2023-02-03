package testCommand;

import controller.Path;
import controller.commands.User.ConfirmOrderCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import model.dao.OrdersDao;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Log4j
public class TestConfirmOrderCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Mock
    private OrdersDao ordersDao;

    ConfirmOrderCommand confirmOrderCommand = new ConfirmOrderCommand();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        confirmOrderCommand = new ConfirmOrderCommand();
    }

    @Test
    public void execute() throws ServletException, IOException, SQLException, ServletException {
        when(request.getParameter("roomId")).thenReturn("1");
        when(request.getParameter("orderIdForConfirm")).thenReturn("2");
        String result = confirmOrderCommand.execute(request, response);
        assertEquals(result, Path.PAGE_LISTOFREQUEST);
    }
}

