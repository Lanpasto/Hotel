package controller.commands.User;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import model.dao.OrdersDao;

import java.io.IOException;
import java.sql.SQLException;

import static controller.Path.PAGE_LISTOFREQUEST;
import static controller.Path.PAGE_PAYMENT;
import static controller.validation.Validation.PayValidation;

@Log4j
public class PaymentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        int orderId = (int) session.getAttribute("orderId");
       if (PayValidation(request, request.getParameter("name"),
                request.getParameter("card"), request.getParameter("yearAndMonth"),
              request.getParameter("cvv"))) {
             log.info("PaymentCommand validation failed");
           return PAGE_PAYMENT;
        }
        OrdersDao ordersDao = new OrdersDao();
        ordersDao.updateOrder(orderId);
        return PAGE_LISTOFREQUEST;
    }
}
