package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        String classRoom = "";
        int fromPrice = 0;
        int priceTo = 0;
        Room room;

        if (request.getParameter("guest") != null && !request.getParameter("guest").isEmpty()) {
            guest = Integer.parseInt(request.getParameter("guest"));
        }
        if (request.getParameter("classOfRoom") != null && !request.getParameter("classOfRoom").isEmpty()) {
            classRoom = request.getParameter("classOfRoom");
        }
        System.out.println(request.getParameter("fromPrice")+"REQUEST PED" );

        if (request.getParameter("fromPrice") != null && !request.getParameter("fromPrice").isEmpty()) {
            fromPrice = Integer.parseInt(request.getParameter("fromPrice"));
        }
        if (request.getParameter("priceTo") != null && !request.getParameter("priceTo").isEmpty()) {
            priceTo = Integer.parseInt(request.getParameter("priceTo"));
        }


            room = Room.builder()
                    .guests(guest)
                    .typeName(classRoom)
                    .fromPrice((fromPrice))
                    .byPrice(priceTo)
                    .build();



        List<Room> listOrders = roomDao.sortingRoomByClassGuestsPrice(room, 0, recordsPerPage);
        request.setAttribute("listOrders", listOrders);
        int noOfRecords = roomDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                / recordsPerPage);

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("guest", guest);
        request.setAttribute("currentPage", page);
        System.out.println(fromPrice);
        request.setAttribute("classRoom", classRoom);
        request.setAttribute("fromPrice", (fromPrice));
        request.setAttribute("priceTo", (priceTo));

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