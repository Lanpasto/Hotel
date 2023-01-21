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
        RoomDao roomDao = new RoomDao();
        String status = "";
        Room room;
        String status1 = request.getParameter("statusForSorting");
        System.out.println("121231231231231");
        System.out.println(status1);
        if (request.getParameter("statusForSorting") != null && !request.getParameter("statusForSorting").isEmpty())
        {
            status = request.getParameter("statusForSorting");
        }
        room = Room.builder()
                .status(status)
                .build();


        List<Room> AllListRoom = roomDao.sortingRoomByStatus(room);
        //System.out.println(AllListRoom.get(0).getStatus());
        request.setAttribute("AllListRoom", AllListRoom);
        return PAGE_ADMINRESERVE;
    }

}

