package controller;


import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        String forward;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String number = request.getParameter("contact");

        User user = User.builder()
                .first_name(firstName)
                .last_name(lastName)
                .email(email)
                .password(password)
                .roleId(1)
                .build();

        UserDao userDao = new UserDao();



        userDao.insert(user);

        forward = Path.PAGE_INDEX;

        return forward;
    }
}