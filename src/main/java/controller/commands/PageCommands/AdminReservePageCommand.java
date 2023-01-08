package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.RoomDao;
import model.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_ADMINRESERVE;

public class AdminReservePageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        RoomDao orderDao = new RoomDao();
        List<Room> listCategory = orderDao.roomList();
        request.setAttribute("listCategory", listCategory);
        return PAGE_ADMINRESERVE;
    }
}
