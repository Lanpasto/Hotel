package controller.commands.User;

import controller.Path;
import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import model.dao.OrdersDao;
import model.dao.RoomDao;

import java.io.IOException;
import java.sql.SQLException;
@Log4j
public class ConfirmOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int orderId= Integer.parseInt(request.getParameter("orderIdForConfirm"));
        RoomDao roomDao = new RoomDao();
        OrdersDao ordersDao= new OrdersDao();
        ordersDao.updateStatusForPayment(orderId);
        return Path.PAGE_LISTOFREQUEST;
    }
}
