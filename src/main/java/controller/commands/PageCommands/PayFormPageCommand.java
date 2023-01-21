package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.OrdersDao;
import model.entity.Payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static controller.Path.PAGE_PAYMENT;

public class PayFormPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        OrdersDao ordersDao = new OrdersDao();
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderIdForConfirm"));
        String status = request.getParameter("statusPay");
        int idUser = (int) session.getAttribute("currentUserId");
        Payment payment = ordersDao.findPayByID(idUser);
        if (!Objects.equals(payment.getOrdersId(), orderId)) {
            ordersDao.addPay(orderId, status, idUser);
        }
        return PAGE_PAYMENT;
    }
}
