package controller.commands.User;

import controller.Path;
import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.OrdersDao;

import java.io.IOException;
import java.sql.SQLException;

public class RejectOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrdersDao ordersDao = new OrdersDao();
        ordersDao.DeleteOrder(orderId);
        return Path.PAGE_LISTOFREQUEST;
    }
}
