package controller.commands.User;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static controller.validation.Validation.OrderReserveValidator;

@Log4j
public class MakeOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("MakeOrderCommand started");
        String forward = Path.PAGE_INDEX;
        HttpSession session = request.getSession();
        String allDate = request.getParameter("datefilter");
        if (OrderReserveValidator(request, allDate)) {
            log.info("MakeOrderRequestCommand validation failed");
            return forward;
        }
        String[] temp = allDate.split(" - ");
        String firstDate = temp[0];
        String secondDate = temp[1];

        Timestamp DateOfSettlement = date(firstDate);
        Timestamp DateOfOut = date(secondDate);
        int idUser = (int) session.getAttribute("currentUserId");
        int idRoom = Integer.parseInt(request.getParameter("room"));

        RoomDao roomDao = new RoomDao();
        Room room = roomDao.findRoomById(idRoom);
        int daysOfReserve = nDaysBetweenTwoDate(firstDate, secondDate);
        int priceRoomForOneDay = room.getPrice();
        int TotalBill = daysOfReserve * priceRoomForOneDay;

        Orders orders = Orders.builder()
                .userId(idUser)
                .roomId(idRoom)
                .dateOfSettlement(DateOfSettlement)
                .dateOfOut(DateOfOut)
                .dateOfCreateOrder(Timestamp.from(Instant.now()))
                .totalPrice(TotalBill)
                .status("Waiting for paid")
                .build();

        OrdersDao ordersDao = new OrdersDao();
        String all = dateFlippers(firstDate);
        String all1 = dateFlippers(secondDate);
        Orders dateCompare = ordersDao.findOrdersByRoom(idRoom, all, all1);

        if (dateCompare == null) {
            log.info("MakeOrdersCommand successfully added");
            ordersDao.addOrder(orders);
            return forward;
        } else {
            log.info("MakeOrdersCommand failed");
            request.setAttribute("message", "Wrong information please check it out");
        }
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
        return new Timestamp(date.getTime());
    }
    public static String dateFlippers(String startDateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(startDateString, formatter).format(formatter2);
    }
}



