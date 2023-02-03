package testCommand;

import controller.commands.PageCommands.ListOfRequestRoomPageCommand;
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

public class TestListOfRequestRoomPageCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);

    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("currentUserId")).thenReturn(1);
        assertEquals("/jsp/listOfRequestRoom.jsp", new ListOfRequestRoomPageCommand().execute(request, response));
        session.setAttribute("currentUserId", 1);
    }
}
