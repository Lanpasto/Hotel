package controller.commands;


import controller.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import model.dao.UserDao;
import model.entity.User;

import java.io.IOException;
import java.sql.SQLException;

import static controller.validation.Validation.RegisterValidation;
@Log4j
public class RegisterCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("started");
        String forward;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("repeatPassword");

        User user = User.builder()
                .first_name(firstName)
                .last_name(lastName)
                .email(email)
                .password(password)
                .roleId(1)
                .build();

        UserDao userDao = new UserDao();
        if(RegisterValidation(request,firstName,lastName,email,password,confirmPassword,user)){
            log.info("validation failed");
            return Path.PAGE_REGISTER;
        }


        userDao.insert(user);

        forward = Path.PAGE_INDEX;
        log.info("user successfully registered");
        return forward;
    }
}