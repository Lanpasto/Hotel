package validation;

import controller.validation.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDao;
import model.entity.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static controller.validation.Validation.PayValidation;
import static controller.validation.Validation.RegisterValidation;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestValidation {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);


    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        User user = mock(User.class);
        UserDao userDao = mock(UserDao.class);

        boolean checkPay = PayValidation(request, "Artem Osa",
                "1234567891234567", "12/2002", "322");
        boolean checkRegister = RegisterValidation(request, "John",
                "Doe", "john.doe@example.com", "password", "password", user);
        boolean loginCheck = Validation.LoginValidation(request, "example@gmail.com");
        assertFalse(checkPay);
        assertFalse(checkRegister);
        assertFalse(loginCheck);
    }

    @Test
    public void testLoginValidation_withCorrectEmail_returnsFalse() {
        String email = "example@gmail.com";
        boolean result = Validation.LoginValidation(request, email);
        assertFalse(result);
    }

    @Test
    public void testLoginValidation_withIncorrectEmail_returnsTrue() {
        String email = "examplegmail.com";
        boolean result = Validation.LoginValidation(request, email);
        assertTrue(result);
    }

    @Test
    public void testLoginValidation_withEmptyEmail_returnsTrue() {
        String email = "";
        boolean result = Validation.LoginValidation(request, email);
        assertTrue(result);
    }
    @Test
    public void testInvalidFirstName() throws SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        User users = mock(User.class);
        String name = "";
        String lastName = "Smith";
        String email = "test@test.com";
        String password = "password";
        String confirmPassword = "password";

        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);

        assertTrue(result);
        verify(request).setAttribute("message", "Please write your first name in correct way");
    }
    @Test
    public void testInvalidLastName() throws SQLException {
        User users = mock(User.class);
        String name = "John";
        String lastName = "";
        String email = "test@test.com";
        String password = "password";
        String confirmPassword = "password";

        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);

        assertTrue(result);
        verify(request).setAttribute("message", "Please write your last name in correct way");
    }

    @Test
    public void testFirstNameOrLastNameTooLong() throws SQLException {
        User users = mock(User.class);
        String name = "John";
        String lastName = "SmithSmithSmithSmithSmithSmithSmithSmithSmithSmithSmithSmith";
        String email = "test@test.com";
        String password = "password";
        String confirmPassword = "password";

        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);

        assertTrue(result);
        verify(request).setAttribute("message", "Your first name or last name is too long");
    }

    @Test
    public void  testInvalidEmail() throws SQLException {
        User users = mock(User.class);
        String name = "John";
        String lastName = "Smith";
        String email = "test";
        String password = "password";
        String confirmPassword = "password";

        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);

        assertTrue(result);
        verify(request).setAttribute("message", "Please write your email in correct way");
    }

    @Test
    public void testFirstNameTooLong() throws SQLException {
        User users = mock(User.class);
        String name = "John";
        String lastName = "SmithSmithSmithSmithSmithSmithSmithSmithSmithSmithSmithSmith";
        String email = "test@test.com";
        String password = "password";
        String confirmPassword = "password";

        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);
        assertTrue(result);
        verify(request).setAttribute("message", "Your first name or last name is too long");
    }
    @Test
    public void testPasswordTooShort()throws SQLException {
        User users = mock(User.class);
        String name = "John";
        String lastName = "Smith";
        String email = "test@test.com";
        String password = "abc";
        String confirmPassword = "abc";

        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);

        assertTrue(result);
        verify(request).setAttribute("message", "Your password must have at least 4 length and not be larger than 16");
    }
    @Test
    public void testPasswordTooLong() throws SQLException {
        User users = mock(User.class);
        String name = "John";
        String lastName = "Smith";
        String email = "test@test.com";
        String password = "PasswordTooLongForThisTestCase";
        String confirmPassword = "PasswordTooLongForThisTestCase";
        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);
        assertTrue(result);
        verify(request).setAttribute("message", "Your password must have at least 4 length and not be larger than 16");
    }
    @Test
    public void testPasswordAndConfirmationNotMatching() throws SQLException {
        User users = mock(User.class);
        String email = "test@example.com";
        String password = "password";
        String confirmPassword = "pass";
        String name = "Test";
        String lastName = "User";

        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);
        assertTrue(result);

        verify(request).setAttribute("message", "Passwords don't match");
    }
    @Test
    public void testUniqueEmail() throws SQLException {
        User users = mock(User.class);
        String email = "test@example.com";
        String password = "password";
        String confirmPassword = "password";
        String name = "Test";
        String lastName = "User";


        UserDao userDao = mock(UserDao.class);
        when(userDao.findUserByEmail(email)).thenReturn(null);

        boolean result = RegisterValidation(request, name, lastName, email, password, confirmPassword, users);
        assertFalse(result);
    }
    @Test
    public void testPayValidationValidName() {
        String name = "John Doe";
        String card = "1234 5678 9012 3456";
        String yearAndMonth = "01/2023";
        String cvv = "123";
        assertFalse(Validation.PayValidation(request, name, card, yearAndMonth, cvv));
    }

    @Test
    public void testPayValidationInvalidName() {
        String name = "johndoe";
        String card = "1234 5678 9012 3456";
        String yearAndMonth = "01/2023";
        String cvv = "123";
        assertTrue(Validation.PayValidation(request, name, card, yearAndMonth, cvv));
    }

    @Test
    public void testPayValidationValidCard() {
        String name = "John Doe";
        String card = "1234 5678 9012 3456";
        String yearAndMonth = "01/2023";
        String cvv = "123";
        assertFalse(Validation.PayValidation(request, name, card, yearAndMonth, cvv));
    }

    @Test
    public void testPayValidationInvalidCard() {
        String name = "John Doe";
        String card = "123456789012345";
        String yearAndMonth = "01/2023";
        String cvv = "123";
        assertTrue(Validation.PayValidation(request, name, card, yearAndMonth, cvv));
    }

    @Test
    public void testPayValidationValidYearAndMonth() {
        String name = "John Doe";
        String card = "1234 5678 9012 3456";
        String yearAndMonth = "01/2023";
        String cvv = "123";
        assertFalse(Validation.PayValidation(request, name, card, yearAndMonth, cvv));
    }

   }
