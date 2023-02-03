package testCommand;

import controller.commands.ChangeLanguageCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestChangeLanguageCommand {
    private final jakarta.servlet.http.HttpServletRequest request = mock(HttpServletRequest.class);
    private final jakarta.servlet.http.HttpServletResponse response = mock(HttpServletResponse.class);

    private final jakarta.servlet.http.HttpSession session = mock(HttpSession.class);

    private ChangeLanguageCommand changeLanguageCommand;

    @Before
    public void setUp()   {
        MockitoAnnotations.initMocks(this);
        changeLanguageCommand = new ChangeLanguageCommand();
    }

    @Test
    public void testExecute_withValidLanguage_shouldReturnRedirect() throws IOException, jakarta.servlet.ServletException {
        when(request.getParameter("lang")).thenReturn("en");
        when(request.getSession(false)).thenReturn(session);
        when(request.getHeader("Referer")).thenReturn("http://localhost:8080/hotel/");

        String result = changeLanguageCommand.execute(request, response);

        verify(session).setAttribute("lang", "en");
        assertEquals("redirect:", result);
    }

    @Test
    public void testExecute_withInvalidLanguage_shouldReturnRedirect() throws IOException, jakarta.servlet.ServletException {
        when(request.getParameter("lang")).thenReturn("xyz");
        when(request.getSession(false)).thenReturn(session);
        when(request.getHeader("Referer")).thenReturn("http://localhost:8080/hotel/");

        String result = changeLanguageCommand.execute(request, response);

        verify(session).setAttribute("lang", "en");
        assertEquals("redirect:", result);
    }
}
