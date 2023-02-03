package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import model.dao.OrdersDao;
import model.entity.Orders_request;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_ADMINREQUEST;
@Log4j
public class AdminRequestPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("page loaded");
        OrdersDao orderDao = new OrdersDao();
        List<Orders_request> requestList = orderDao.requestList();
        request.setAttribute("requestList", requestList);

        return PAGE_ADMINREQUEST;
    }
}
