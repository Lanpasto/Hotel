package controller.commands.User;

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

public class MakeOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();

        OrdersDao ordersDao = new OrdersDao();
        List<Orders> listCategory = ordersDao.listSelect();
        request.setAttribute("listCategory", listCategory);
        String cargoType = request.getParameter("flexRadioDefault");
        String numReceiver = request.getParameter("numReceiver");
        String idenPackage = request.getParameter("idenPackage");
        String citySender = request.getParameter("sender");
        String cityReceiver = request.getParameter("receiver");
        String stringWeight = request.getParameter("weight");
        String stringWidth = request.getParameter("width");
        String stringHeight = request.getParameter("height");
        String stringLength = request.getParameter("length");

        return cargoType;
    }
}