package testCommand;

import controller.Path;
import controller.commands.User.RoomReserveCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomReserveCommandTest {
    @InjectMocks
    RoomReserveCommand roomReserveCommand;
    private final jakarta.servlet.http.HttpServletRequest request = mock(HttpServletRequest.class);
    private final jakarta.servlet.http.HttpServletResponse response = mock(HttpServletResponse.class);
    @Test
    public void testExecute() throws IOException, ServletException, SQLException, jakarta.servlet.ServletException {
        int roomId = 1;
        when(request.getParameter("room")).thenReturn(String.valueOf(roomId));
        String result = roomReserveCommand.execute(request, response);
        assertEquals(Path.PAGE_INDEX, result);
    }
}
