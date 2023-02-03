package testCommand;

import controller.Path;
import controller.commands.Admin.UpdateRoomStatusCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import model.dao.RoomDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@Log4j
public class UpdateRoomStatusCommandTest {
    private final jakarta.servlet.http.HttpServletRequest request = mock(HttpServletRequest.class);
    private final jakarta.servlet.http.HttpServletResponse response = mock(HttpServletResponse.class);
    @Mock
    private RoomDao roomDao;

    private UpdateRoomStatusCommand updateRoomStatusCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        updateRoomStatusCommand = new UpdateRoomStatusCommand();
    }

    @Test
    public void execute() throws ServletException, IOException, SQLException, jakarta.servlet.ServletException {
        when(request.getParameter("room")).thenReturn("1");
        when(request.getParameter("statusOfRoom")).thenReturn("busy");
        String result = updateRoomStatusCommand.execute(request, response);
        assertEquals(result, Path.PAGE_INDEX);
    }
}

