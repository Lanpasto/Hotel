package controller.validation;

import jakarta.servlet.http.HttpServletRequest;
import model.dao.UserDao;
import model.entity.User;

import java.sql.SQLException;

public class Validation {
    public static boolean LoginValidation(HttpServletRequest request, String email) {
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            request.setAttribute("message", "Please write your email in correct way");
            return true;
        }
        return false;
    }

    public static boolean RegisterValidation(HttpServletRequest request, String name, String lastName, String email, String password,
                                             String confirmPassword, User users) throws SQLException {
        UserDao userDao = new UserDao();
        if (name.isEmpty() || !name.matches("^[A-Za-z]+$")) {
            request.setAttribute("message", "Please write your first name in correct way");
            return true;
        }
        if (lastName.isEmpty() || !lastName.matches("^[A-Za-z]+$")) {
            request.setAttribute("message", "Please write your last name in correct way");
            return true;
        }
        if (name.length() > 35 || lastName.length() > 35) {
            request.setAttribute("message", "Your first name or last name is too long");
            return true;
        }
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            request.setAttribute("message", "Please write your email in correct way");
            return true;
        }
        if (password.length() < 4 || password.length() > 16) {
            request.setAttribute("message", "Your password must have at least 4 length and not be larger than 16");
            return true;
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("message", "Passwords don't match");
            return true;
        }
        if (userDao.findUserByEmail(users.getEmail()) != null) {
            request.setAttribute("message", "User with same email already exists");
            return true;
        }
        return false;
    }

    public static boolean PayValidation(HttpServletRequest request, String name,
                                        String card, String yearAndMonth, String cvv) {

        if (name.isEmpty() || !name.matches("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$")) {
            request.setAttribute("message", "Please write your name in correct way");
            return true;
        }
        if (card.isEmpty() || !card.matches("^([0-9]{4}[\s-]?){3}([0-9]{4})$")) {
            request.setAttribute("message", "Please write your card number in correct way");
            return true;
        }
        if (yearAndMonth.isEmpty() || !yearAndMonth.matches("^(0?[1-9]|1[012])[/](19|20)?[0-9]{2}$")) {
            request.setAttribute("message", "Please write your exp year in correct way");
            return true;
        }
        if (cvv.isEmpty() || !cvv.matches("^\\d{3}$")) {
            request.setAttribute("message", "Please write your cvv in correct way");
            return true;
        }
        return false;
    }

    public static boolean OrderRequestValidator(HttpServletRequest request, String guests, String date, String roomClass) {
        boolean hasErrors = false;

        if (roomClass == null || roomClass.isEmpty()) {
            request.setAttribute("classError", "Room class cannot be empty");
            hasErrors = true;
        }

        if (date == null || date.isEmpty()) {
            request.setAttribute("dateError", "Date cannot be empty");
            hasErrors = true;
        }

        if (guests == null || guests.isEmpty()) {
            request.setAttribute("guestsError", "Number of guests cannot be empty");
            hasErrors = true;
        }
        return hasErrors;
    }
    public static boolean OrderReserveValidator(HttpServletRequest request, String date) {
        boolean hasErrors = false;

        if (date == null || date.isEmpty()) {
            request.setAttribute("dateError", "Date cannot be empty");
            hasErrors = true;
        }

        return hasErrors;
    }

}






