package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.OrdersDao;
import model.entity.Orders;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_LISTOFRESERVE;

public class LIstOfReserveRoomPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        int idUser = (int) session.getAttribute("currentUserId");
        OrdersDao orderDao = new OrdersDao();
        List<Orders> ordersReserveListUser = orderDao.ordersReserveListUser(idUser);
        request.setAttribute("ordersRequestListUser", ordersReserveListUser);
        return PAGE_LISTOFRESERVE;
    }
}