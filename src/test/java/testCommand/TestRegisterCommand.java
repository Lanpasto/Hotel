package testCommand;

import controller.commands.RegisterCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class TestRegisterCommand {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);


    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        when(request.getParameter("firstName")).thenReturn("dan");
        when(request.getParameter("lastName")).thenReturn("archer");
        when(request.getParameter("email")).thenReturn("aboba@gmail.com");
        when(request.getParameter("password")).thenReturn("1234");
        when(request.getParameter("repeatPassword")).thenReturn("1234");
        UserDao userDao = mock(UserDao.class);

        when(userDao.findUserByEmail(any())).thenReturn(null);


        doNothing().when(userDao).insert(any());
        assertEquals("/jsp/index.jsp", new RegisterCommand().execute(request, response));
    }
}
