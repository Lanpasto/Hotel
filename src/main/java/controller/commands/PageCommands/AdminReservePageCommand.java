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
        String action = request.getParameter("action1");
        int page = 1;

        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(
                    request.getParameter("page"));

        String status = "";
        Room room;
        if (request.getParameter("statusForSorting") != null && !request.getParameter("statusForSorting").isEmpty())
        {
            status = request.getParameter("statusForSorting");
        }
        room = Room.builder()
                .status(status)
                .build();

        List<Room> AllListRoom = roomDao.sortingRoomByStatus(room, 0, recordsPerPage);
        request.setAttribute("AllListRoom", AllListRoom);
        int noOfRecords = roomDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                / recordsPerPage);

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        AllListRoom = roomDao.sortingRoomByStatus(room, 0,
                recordsPerPage);
        request.setAttribute("listOrders", AllListRoom);


        if(action == null) {

           AllListRoom = roomDao.sortingRoomByStatus(room,0,
                    recordsPerPage);
            request.setAttribute("AllListRoom", AllListRoom);
            return PAGE_ADMINRESERVE;
        }
        else{
             AllListRoom = roomDao.sortingRoomByStatus(room, (Integer.parseInt(action)-1)*recordsPerPage,
                    recordsPerPage);
            request.setAttribute("AllListRoom", AllListRoom);
            return PAGE_ADMINRESERVE;
        }

        //System.out.println(AllListRoom.get(0).getStatus());


    }

}

