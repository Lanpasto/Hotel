package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.OrdersDao;
import model.dao.RoomDao;
import model.entity.Orders_request;
import model.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_ADMINREQUEST;

public class AdminRequestPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        OrdersDao orderDao = new OrdersDao();
        List<Orders_request> requestList = orderDao.requestList();
        request.setAttribute("requestList", requestList);
        RoomDao roomDao = new RoomDao();
        List<Room> AllListRoom = roomDao.roomAllAvailableRoomForRequest();
        request.setAttribute("AllListRoom", AllListRoom);
        // System.out.println(AllListRoom.get(0).getId());
        return PAGE_ADMINREQUEST;
    }
}
