package controller.commands.User;

import controller.Path;
import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.OrdersDao;
import model.dao.RoomDao;
import model.entity.Orders;
import model.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class MakeOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String forward;
        RoomDao roomDao = new RoomDao();
        HttpSession session = request.getSession();
        String allDate = request.getParameter("datefilter");
        String delimiter = " - ";
        String[] temp = allDate.split(delimiter);
        System.out.println(allDate);
        String firstDate = temp[0];
        String secondDate = temp[1];


        int daysOfReserve = nDaysBetweenTwoDate(firstDate, secondDate);

        Timestamp DateOfSettlement = date(firstDate);
        Timestamp DateOfOut = date(secondDate);

        int idUser = (int) session.getAttribute("currentUserId");
        int idRoom = Integer.parseInt(request.getParameter("room"));
        Room room = roomDao.findRoomById(idRoom);


        int priceRoomForOneDay = room.getPrice();

        int TotalBill = daysOfReserve * priceRoomForOneDay;

        Orders orders = Orders.builder()
                .userId(idUser)

                .roomId(idRoom)

                .dateOfSettlement(DateOfSettlement)

                .dateOfOut(DateOfOut)

                .dateOfCreateOrder(Timestamp.from(Instant.now()))

                .totalPrice(TotalBill)

                .status("Successful")

                .build();
        OrdersDao ordersDao = new OrdersDao();
        int roomId = Integer.parseInt(request.getParameter("room"));
        if(Objects.equals(room.getStatus(), "available")) {
            ordersDao.addOrder(orders);
            roomDao.updateRoom(roomId);
        }
        forward = Path.PAGE_INDEX;

        return forward;
    }

    public static int nDaysBetweenTwoDate(String firstString, String secondString) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date firstDate = null;
        java.util.Date secondDate = null;
        try {
            firstDate = df.parse(firstString);
            secondDate = df.parse(secondString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int days = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
        if (days <= 0)
            return 1;
        return days;
    }


    public static Timestamp date(String firstDate) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = formatter.parse(firstDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Timestamp timeStampDateFirst = new Timestamp(date.getTime());
        return timeStampDateFirst;
    }
}



