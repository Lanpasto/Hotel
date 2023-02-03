package testCommand;

import controller.commands.PageCommands.SelectRoomForRequestPageCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSelectRoomForRequestPageCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    private final HttpSession session = mock(HttpSession.class);
    @Test
    void testExecute() throws ServletException, SQLException, IOException {

        when(request.getParameter("userId")).thenReturn(String.valueOf(1));
        when(request.getParameter("idRequest")).thenReturn(String.valueOf(1));
        when(request.getParameter("dateOfOut")).thenReturn(String.valueOf("03/24/2024"));
        when(request.getParameter("dateOfSettlement")).thenReturn(String.valueOf("03/24/2024"));
        when(request.getSession()).thenReturn(session);
        assertEquals("/jsp/selectRoomForRequest.jsp", new SelectRoomForRequestPageCommand().execute(request, response));
    }

}