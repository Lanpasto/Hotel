package testCommand;

import controller.commands.LoginCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.entity.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLoginCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);


    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        when(request.getParameter("email")).thenReturn("oo@gmail.com");
        when(request.getParameter("password")).thenReturn("1234");
        UserDao userDao = mock(UserDao.class);
        User user = mock(User.class);
        when(userDao.findUser(any(User.class))).thenReturn(user);

        assertEquals("/jsp/loginView.jsp", new LoginCommand().execute(request, response));
    }
}
