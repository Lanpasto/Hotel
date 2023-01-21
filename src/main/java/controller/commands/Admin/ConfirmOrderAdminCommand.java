package controller.commands.Admin;

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

public class ConfirmOrderAdminCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session= request.getSession();
        OrdersDao ordersDao = new OrdersDao();
        RoomDao roomDao = new RoomDao();
        int requestId= Integer.parseInt(request.getParameter("idRequest"));
        ordersDao.updateOrderRequest(requestId);




        String dateOfSettlement= request.getParameter("dateOfSettlement");
        String dateOfOut= request.getParameter("dateOfOut");
        Timestamp firstDate = date(dateOfSettlement);
        Timestamp  secondDate = date(dateOfOut);
        int roomId= Integer.parseInt(request.getParameter("roomId"));
        System.out.println("ddssssssssss"+roomId);
        int userId= Integer.parseInt((request.getParameter("userId"))) ;
        int daysOfReserve = nDaysBetweenTwoDate(dateOfSettlement, dateOfOut);
        Room room = roomDao.findRoomById(roomId);
        int priceRoomForOneDay = room.getPrice();
        int totalPrice=priceRoomForOneDay*daysOfReserve;
        System.out.println(totalPrice);
        Orders orders = Orders.builder()
                .userId(userId)

                .roomId(roomId)

                .dateOfSettlement(firstDate)

                .dateOfOut(secondDate)

                .dateOfCreateOrder(Timestamp.from(Instant.now()))

                .totalPrice(totalPrice)

                .status("Waiting for confirmation")

                .build();

        ordersDao.addOrder(orders);


        return Path.PAGE_INDEX;
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
    }}




