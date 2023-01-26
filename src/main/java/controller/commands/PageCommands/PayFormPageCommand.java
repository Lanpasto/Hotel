package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;

import static controller.Path.PAGE_PAYMENT;
@Log4j
public class PayFormPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("page loaded");
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderIdForConfirm"));
        session.setAttribute("orderId", orderId);
        return PAGE_PAYMENT;
    }
}
