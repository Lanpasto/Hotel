package controller.commands;


import controller.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDao;
import model.entity.User;

import java.io.IOException;
import java.sql.SQLException;

import static controller.validation.Validation.LoginValidation;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if(LoginValidation(request,email)){
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
            return Path.PAGE_LOGIN;
        }else {
            session.setAttribute("currentUserId",newUser.getId());
            session.setAttribute("role",newUser.getRoleId());
            return Path.PAGE_LOGIN;
        }

    }
}