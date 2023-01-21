package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.RoomDao;
import model.entity.Room;
import model.entity.Room_of_type;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_ORDERROOM;

public class OrderRoomPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        RoomDao roomDao = new RoomDao();
        List<Room> listCategory = roomDao.roomList();
        request.setAttribute("listCategory", listCategory);
        List<Room_of_type> listCategoryForSorting = roomDao.roomOfTypesList();
        request.setAttribute("listCategoryForSorting", listCategoryForSorting);
        int guest = 0;
        String classRoom = "";
         int fromPrice= 0;
         int priceTo= 0;
        Room room;

        int recordsPerPage = 5;
        if (request.getParameter("guest") != null && !request.getParameter("guest").isEmpty()) {
            guest = Integer.parseInt(request.getParameter("guest"));
        }
        if (request.getParameter("classOfRoom") != null && !request.getParameter("classOfRoom").isEmpty()) {

            classRoom = request.getParameter("classOfRoom");


        }

       if (request.getParameter("fromPrice") != null && !request.getParameter("fromPrice").isEmpty()) {
           fromPrice = Integer.parseInt(request.getParameter("fromPrice"));


      } if (request.getParameter("priceTo") != null && !request.getParameter("priceTo").isEmpty()) {
           priceTo = Integer.parseInt(request.getParameter("priceTo"));
      }
        room = Room.builder()
                .guests(guest)
                .typeName(classRoom)
                .price(priceTo)
                .build();



        List<Room> listOrders = roomDao.sortingRoomByClassGuestsPrice(room);//0,recordsPerPage
       // int noOfRecords = roomDao.getNoOfRecords();
       // int noOfPages = (int) Math.ceil(noOfRecords * 1.0
      //          / recordsPerPage);
        request.setAttribute("listOrders", listOrders);
        return PAGE_ORDERROOM;
    }

}
