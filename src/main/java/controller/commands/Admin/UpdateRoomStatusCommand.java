package controller.commands.Admin;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import model.dao.RoomDao;

import java.io.IOException;
import java.sql.SQLException;

import static controller.Path.PAGE_ADMINRESERVE;
@Log4j
public class UpdateRoomStatusCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int idRoom = Integer.parseInt(request.getParameter("room"));
        String status = request.getParameter("statusOfRoom");
        RoomDao roomDao = new RoomDao();
        roomDao.updateRoomStatusManager(idRoom,status);

        return PAGE_ADMINRESERVE;
    }
}
