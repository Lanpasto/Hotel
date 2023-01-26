package controller.commands;


import controller.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import model.dao.UserDao;
import model.entity.User;

import java.io.IOException;
import java.sql.SQLException;

import static controller.validation.Validation.LoginValidation;
@Log4j
public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("started");
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if(LoginValidation(request,email)){
            log.info("LoginCommand validation failed");
            return Path.PAGE_LOGIN;
        }

        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        UserDao userDao = new UserDao();
        User newUser = userDao.findUser(user);

        if(newUser == null){
            request.setAttribute("message", "Wrong information please \n" +
                    "check it out");
            log.info("the user is not logged in");
            return Path.PAGE_LOGIN;
        }else {
            session.setAttribute("currentUserId",newUser.getId());
            session.setAttribute("role",newUser.getRoleId());
            log.info("user successfully logged in");
            return Path.PAGE_INDEX;
        }

    }
}