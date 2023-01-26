package controller.commands.Admin;

import controller.Path;
import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class ConfirmOrderAdminCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        String dateOfOut = (String) session.getAttribute("dateOfOut");
        String dateOfSettlement= (String) session.getAttribute("dateOfSettlement");
        int requestId= (int) session.getAttribute("idRequest");
        int userId= (int) session.getAttribute("userId");
        OrdersDao ordersDao = new OrdersDao();
        RoomDao roomDao = new RoomDao();
        ordersDao.updateOrderRequest(requestId);
        Timestamp firstDate = date(dateOfSettlement);
        Timestamp  secondDate = date(dateOfOut);
        int roomId= Integer.parseInt(request.getParameter("room"));
        int daysOfReserve = nDaysBetweenTwoDate(dateOfSettlement, dateOfOut);
        Room room = roomDao.findRoomById(roomId);
        int priceRoomForOneDay = room.getPrice();
        int totalPrice=priceRoomForOneDay*daysOfReserve;
        Orders orders = Orders.builder()
                .userId(userId)

                .roomId(roomId)

                .dateOfSettlement(firstDate)

                .dateOfOut(secondDate)

                .dateOfCreateOrder(Timestamp.from(Instant.now()))

                .totalPrice(totalPrice)

                .status("Waiting for confirmation")

                .statusOfPay("process")

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




