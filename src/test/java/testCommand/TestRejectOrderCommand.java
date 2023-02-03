package testCommand;

import controller.Path;
import controller.commands.User.RejectOrderCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestRejectOrderCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    @Test
    public void shouldDeleteOrder() throws ServletException, IOException, SQLException {


        when(request.getParameter("orderId")).thenReturn("1");
        RejectOrderCommand command = new RejectOrderCommand();
        String expectedResult = Path.PAGE_LISTOFREQUEST;
        String actualResult = command.execute(request, response);


        assertEquals(expectedResult, actualResult);
    }
}

