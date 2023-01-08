package controller.commands.User;

import controller.Path;
import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.RoomDao;

import java.io.IOException;
import java.sql.SQLException;

public class RoomReserveCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String forward;
        int roomId = Integer.parseInt(request.getParameter("room"));
        RoomDao roomDao = new RoomDao();
        roomDao.updateRoom(roomId);
        forward = Path.PAGE_INDEX;
        return forward;
    }
}
