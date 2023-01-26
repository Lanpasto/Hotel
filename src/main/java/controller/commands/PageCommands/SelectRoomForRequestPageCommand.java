package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import model.dao.RoomDao;
import model.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static controller.Path.PAGE_SELECTREQUESTFORADMIN;
@Log4j
public class SelectRoomForRequestPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("page loaded");
        RoomDao roomDao = new RoomDao();
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(request.getParameter("userId"));
        int idRequest = Integer.parseInt(request.getParameter("idRequest"));
        String dateOfSettlement = request.getParameter("dateOfSettlement");
        String dateOfOut = request.getParameter("dateOfOut");
        session.setAttribute("dateOfOut", dateOfOut);
        session.setAttribute("dateOfSettlement", dateOfSettlement);
        session.setAttribute("userId", userId);
        session.setAttribute("idRequest", idRequest);
        System.out.println(dateOfOut);
        System.out.println(dateOfSettlement);
        System.out.println(userId);
        System.out.println(idRequest);
        String dateOfOutCor = dateFlippers(dateOfOut);
        String dateOfSetCor = dateFlippers(dateOfSettlement);
        List<Room> AllListRoomForSelect = roomDao.roomAllAvailableRoomForRequest(dateOfSetCor,dateOfOutCor);
        request.setAttribute("AllListRoomForSelect", AllListRoomForSelect);
        return PAGE_SELECTREQUESTFORADMIN;
    }
    public static String dateFlippers(String startDateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDateBack = LocalDate.parse(startDateString, formatter).format(formatter2);
        return startDateBack;
    }
}
