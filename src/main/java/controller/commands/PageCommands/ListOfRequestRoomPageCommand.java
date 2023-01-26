package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import model.dao.OrdersDao;
import model.entity.Orders;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_LISTOFREQUEST;
@Log4j
public class ListOfRequestRoomPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("page loaded");
        HttpSession session = request.getSession();
        int idUser = (int) session.getAttribute("currentUserId");
        OrdersDao orderDao = new OrdersDao();
        List<Orders> ordersListUser = orderDao.ordersListUser(idUser);
        request.setAttribute("ordersList", ordersListUser);
        return PAGE_LISTOFREQUEST;
    }
}
