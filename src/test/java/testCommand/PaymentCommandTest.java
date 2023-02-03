package testCommand;

import controller.commands.User.PaymentCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentCommandTest {

    private final jakarta.servlet.http.HttpServletRequest request = mock(HttpServletRequest.class);
    private final jakarta.servlet.http.HttpSession session = mock(HttpSession.class);
    private final jakarta.servlet.http.HttpServletResponse response = mock(HttpServletResponse.class);


    @Test
    public void execute() throws Exception {
        PaymentCommand paymentCommand = new PaymentCommand();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("orderId")).thenReturn(1);
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("card")).thenReturn("1234 1234 1234 1234");
        when(request.getParameter("yearAndMonth")).thenReturn("01/2024");
        when(request.getParameter("cvv")).thenReturn("123");

        String result = paymentCommand.execute(request, response);

        assertEquals("/jsp/listOfRequestRoom.jsp", result);
    }
}

