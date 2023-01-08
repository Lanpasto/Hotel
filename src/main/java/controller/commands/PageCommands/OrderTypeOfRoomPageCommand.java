package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.RoomDao;
import model.entity.Room_of_type;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_ORDERTYPEOFROOM;

public class OrderTypeOfRoomPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        RoomDao orderDao = new RoomDao();
        List<Room_of_type> listCategory = orderDao.roomOfTypesList();
        request.setAttribute("listCategory", listCategory);
        return PAGE_ORDERTYPEOFROOM;
    }
}
