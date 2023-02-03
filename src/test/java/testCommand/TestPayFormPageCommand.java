package testCommand;

import controller.commands.PageCommands.PayFormPageCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestPayFormPageCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);
    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("orderIdForConfirm")).thenReturn(String.valueOf(1));
       doNothing().when(session).setAttribute(any(),any());
        assertEquals("/jsp/payForm.jsp", new PayFormPageCommand().execute(request, response));
    }
}

