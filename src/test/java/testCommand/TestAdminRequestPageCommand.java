package testCommand;

import controller.commands.PageCommands.AdminRequestPageCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TestAdminRequestPageCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        assertEquals("/jsp/adminRequestRooms.jsp", new AdminRequestPageCommand().execute(request, response));
    }
}

