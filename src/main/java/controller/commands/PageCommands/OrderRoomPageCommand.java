package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import model.dao.RoomDao;
import model.entity.Room;
import model.entity.Room_of_type;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_ORDERROOM;
@Log4j
public class OrderRoomPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session= request.getSession();
        log.info("page loaded");
        RoomDao roomDao = new RoomDao();
        List<Room_of_type> listCategoryForSorting = roomDao.roomOfTypesList();
        request.setAttribute("listCategoryForSorting", listCategoryForSorting);
        String action = request.getParameter("action1");

        int page = 1;

        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(
                    request.getParameter("page"));



        int guest = 0;
        String classOfRoom = "";
        String fromPrice = "0";
        String priceTo = "0";
        Room room;

        if (request.getParameter("guest") != null && !request.getParameter("guest").isEmpty()) {
            guest = Integer.parseInt(request.getParameter("guest"));
        }
        if (request.getParameter("classOfRoom") != null && !request.getParameter("classOfRoom").isEmpty()) {
            classOfRoom = request.getParameter("classOfRoom");
        }

        if (request.getParameter("fromPrice") != null && !request.getParameter("fromPrice").isEmpty()) {
            fromPrice = request.getParameter("fromPrice");
        }
        if (request.getParameter("priceTo") != null && !request.getParameter("priceTo").isEmpty()) {
            priceTo = request.getParameter("priceTo");
        }


            room = Room.builder()
                    .guests(guest)
                    .typeName(classOfRoom)
                    .fromPrice(Integer.parseInt(fromPrice))
                    .byPrice(Integer.parseInt(priceTo))
                    .build();



        List<Room> listOrders = roomDao.sortingRoomByClassGuestsPrice(room, 0, recordsPerPage);
        request.setAttribute("listOrders", listOrders);
        int noOfRecords = roomDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                / recordsPerPage);

        request.setAttribute("noOfPages", noOfPages);
        session.setAttribute("guest", guest);
        request.setAttribute("currentPage", page);
        session.setAttribute("classOfRoom", classOfRoom);
        session.setAttribute("fromPrice", (fromPrice));
        session.setAttribute("priceTo", (priceTo));


        listOrders = roomDao.sortingRoomByClassGuestsPrice(room, 0,
                recordsPerPage);
        request.setAttribute("listOrders", listOrders);

        if (action == null) {
            listOrders = roomDao.sortingRoomByClassGuestsPrice(room, 0,
                    recordsPerPage);
            request.setAttribute("listOrders", listOrders);
            return PAGE_ORDERROOM;
        }
        else {
            listOrders = roomDao.sortingRoomByClassGuestsPrice(room, (Integer.parseInt(action) - 1) * recordsPerPage,
                    recordsPerPage);
            request.setAttribute("listOrders", listOrders);
            return PAGE_ORDERROOM;
        }
    }
}